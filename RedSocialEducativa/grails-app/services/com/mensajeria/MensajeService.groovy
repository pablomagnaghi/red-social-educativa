package com.mensajeria

import com.fiuba.Usuario
import grails.transaction.Transactional

@Transactional
class MensajeService {
	
	/**
	 * Nuevo mensaje para usar solo en el bootstrap
	 * @param mensaje
	 * @return
	 */
    def nuevoMensaje(Mensaje mensaje) {
		def hilo = new Hilo()
    	hilo.save()
		mensaje.hilo = hilo;
		mensaje.para.put(mensaje.receptor.nombres +" "+mensaje.receptor.apellido + "<"+mensaje.receptor.email+">", mensaje.emisor.id)

		if(!mensaje.save(flush: true)){
			mensaje.errors.each {
				println it
			}
		}
		
		//Carpeta del receptor guardada
		def carpeta = Carpeta.findByNombreAndUsuario("Escritorio", mensaje.receptor)
		this.guardarMensajeEnConversacion(carpeta, mensaje)
		
		//Carpeta del emisor guardada
		def carpetaEmisor = Carpeta.findByNombreAndUsuario("Escritorio", mensaje.emisor)
		this.guardarMensajeEnConversacion(carpetaEmisor, mensaje)
    }
	
	//TODO PROBAR esto
	/*def agregarMensajeABorradores(Mensaje mensaje){
		def carpeta = Carpeta.findByNombreAndUsuario("Borradores", mensaje.emisor)
		def conversacion = Conversacion.findByPadre(carpeta)
		if (conversacion == null){
			def hilo = new Hilo()
			hilo.save()
			mensaje.hilo = hilo
			mensaje.fecha = new Date()
			conversacion = new Conversacion(padre: carpeta, hilo: hilo)
		} else {
			mensaje.hilo = conversacion.hilo
		}
		if (!mensaje.save()){
			mensaje.errors.each {
				println it
			}
		}
		conversacion.addToMensajes(mensaje)
		conversacion.save()
	}*/
	
	
	/**
	 * Metodo que se llama en el submit de redactar
	 * @param para
	 * @param asunto
	 * @param texto
	 * @param emisor
	 * @param receptor
	 * @param hilo
	 * @return
	 */
	def sendMessage(HashMap<String, String> para, String asunto, String texto, Usuario emisor, Usuario receptor){
		def mensaje = new Mensaje(para: para, emisor: emisor, receptor: receptor, asunto: asunto,
			cuerpo: texto, fecha : new Date())
		def hilo = new Hilo()
		hilo.save()
		mensaje.hilo = hilo;
		mensaje.para = para
		
		if(!mensaje.save(flush: true)){
			mensaje.errors.each {
				println it
			}
		}
		
		def carpeta = Carpeta.findByNombreAndUsuario("Escritorio", receptor)
		this.guardarMensajeEnConversacion(carpeta, mensaje)
		
		def carpetaEmisor = Carpeta.findByNombreAndUsuario("Escritorio", emisor)
		this.guardarMensajeEnConversacion(carpetaEmisor, mensaje)
	}
	
	/**
	 * Responder mensaje (se llama en el responder, reenviar o responderATodos)
	 * @param mensajeOriginal
	 * @param para
	 * @param asunto
	 * @param texto
	 * @param emisor
	 * @param receptor
	 * @return
	 */
	def reply(Mensaje mensajeOriginal, HashMap<String, String> para, String asunto, String texto, Usuario emisor, Usuario receptor){
		def mensaje = new Mensaje(para: para, emisor: emisor, receptor: receptor, asunto: asunto,
			cuerpo: texto, fecha : new Date())
		mensaje.hilo = mensajeOriginal.hilo;
		mensaje.para = para
		
		//Ubico el mensaje en la carpeta del receptor
		def carpetasUsuario = Carpeta.findAllByUsuario(receptor)
		def conversacion = Conversacion.find("from Conversacion as c \
					where c.hilo = :hilo and c.padre in :carpetas", [hilo: hilo, carpetas:carpetasUsuario ])
		if (conversacion == null){
			def carpeta = Carpeta.findByNombreAndUsuario("Escritorio", receptor)
			this.guardarMensajeEnConversacion(carpeta, mensaje)
		} else {
			conversacion.addToMensajes(mensaje)
			if(!conversacion.save()){
				conversacion.errors.each {
					println it
				}
			}
		}
		
		//Ubico el mensaje en la conversacion correspondiente
		def conversacionEmisor = mensajeOriginal.conversaciones.find {
			it.padre.usuario == emisor
		}
		conversacionEmisor.addToMensajes(mensaje)
		if(!conversacionEmisor.save()){
			conversacionEmisor.errors.each {
				println it
			}
		}
	}
	
	private def guardarMensajeEnConversacion(Carpeta carpeta, Mensaje mensaje){
		def nuevaConversacion = new Conversacion(padre: carpeta, hilo: mensaje.hilo)
		nuevaConversacion.addToMensajes(mensaje)
		if(!nuevaConversacion.save()){
			nuevaConversacion.errors.each {
				println it
			}
		}
	}
	
	def marcarMensajeLeido(Mensaje m){
		m.setLeido(Boolean.TRUE)
		m.save()
	}
}
