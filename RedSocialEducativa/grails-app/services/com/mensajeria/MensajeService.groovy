package com.mensajeria

import org.springframework.aop.aspectj.RuntimeTestWalker.ThisInstanceOfResidueTestVisitor;

import com.fiuba.Aprendiz
import com.fiuba.Usuario

import grails.transaction.Transactional

@Transactional
class MensajeService {
	
	def aprendizService
	
	/**
	 * Nuevo mensaje para usar solo en el bootstrap
	 * @param mensaje
	 * @return
	 */
    def nuevoMensaje(Mensaje mensaje) {
		def hilo = new Hilo()
    	hilo.save()
		mensaje.hilo = hilo;
		
		this.agregarDestinatarioAMensaje(mensaje, mensaje.receptor.nombres +" "+mensaje.receptor.apellido + "<"+mensaje.receptor.email+">", mensaje.receptor.id.toString())
		
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
		
		this.marcarMensajeEnviadoEnAprendiz(mensaje.emisor)
    }
	
	def agregarMensajeABorradores(Mensaje mensaje){
		def hilo = new Hilo()
		hilo.save()
		mensaje.hilo = hilo;
		mensaje.fecha = new Date()
		
		def carpeta = Carpeta.findByNombreAndUsuario("Borradores", mensaje.emisor)
		this.guardarMensajeEnConversacion(carpeta, mensaje)
	}
	
	
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
	def sendMessage(Conversacion conversacionEmisor, HashMap<String, String> para, String asunto, 
		String texto, Usuario emisor, Usuario receptor, String carpetaDestino){
		def mensaje = new Mensaje(emisor: emisor, receptor: receptor, asunto: asunto,
			cuerpo: texto, fecha : new Date())
		mensaje.hilo = conversacionEmisor.hilo;
		this.agregarDestinatarios(mensaje, para)
		
		String nombreCarpeta = "Escritorio"
		if (carpetaDestino != null){
			nombreCarpeta = carpetaDestino
		}
		
		def carpeta = Carpeta.findByNombreAndUsuario(nombreCarpeta, receptor)
		this.guardarMensajeEnConversacion(carpeta, mensaje)
		
		conversacionEmisor.addToMensajes(mensaje)
		if(!conversacionEmisor.save()){
			conversacionEmisor.errors.each {
					println it
			}
		}
		this.marcarMensajeEnviadoEnAprendiz(emisor)
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
	def reply(Mensaje mensajeOriginal, HashMap<String, String> para, String asunto, String texto, Usuario emisor, 
		Usuario receptor, String carpetaDestino){
		def mensaje = new Mensaje(emisor: emisor, receptor: receptor, asunto: asunto,
			cuerpo: texto, fecha : new Date())
		mensaje.hilo = mensajeOriginal.hilo;
		
		this.agregarDestinatarios(mensaje, para)
		
		//Ubico el mensaje en la carpeta del receptor
		def conversaciones = Conversacion.findAllByHilo(mensajeOriginal.hilo)
		def conversacion = conversaciones.find {
			it.padre.usuario == receptor
		}
		if (conversacion == null){
			String nombreCarpeta = "Escritorio"
			if (carpetaDestino != null){
				nombreCarpeta = carpetaDestino
			}
			def carpeta = Carpeta.findByNombreAndUsuario(nombreCarpeta, receptor)
			def nuevaConversacion = new Conversacion(padre: carpeta, hilo: mensajeOriginal.hilo)
		}
		conversacion.addToMensajes(mensaje)
		if(!conversacion.save()){
			conversacion.errors.each {
					println it
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
		
		this.marcarMensajeEnviadoEnAprendiz(emisor)
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
	
	def guardarConversacion(Conversacion conversacion){
		if(!conversacion.save()){
			conversacion.errors.each {
				println it
			}
		}
	}
	
	def guardarHilo(Hilo hilo){
		if(!hilo.save(flush:true)){
			hilo.errors.each {
				println it
			}
		}
	}
	
	def marcarMensajeLeido(Mensaje m, Usuario usuario){
		m.setLeido(Boolean.TRUE)
		m.save()
		def aprendiz = Aprendiz.findAllByUsuarioAndCursando(usuario, true)
		if (!aprendiz.empty){
			aprendizService.sumarLeido(aprendiz.get(0))
		}
	}
	
	def borrarBorrador(String idMensaje){
		Mensaje m = Mensaje.findById(idMensaje)
		Conversacion c = m.conversaciones.toList().get(0)
		if (!c.delete()){
			c.errors.each {
				println it
			}
		}
	}
	
	private def marcarMensajeEnviadoEnAprendiz(Usuario usuario){
		def aprendiz = Aprendiz.findAllByUsuarioAndCursando(usuario, true)
		if (!aprendiz.empty){
			aprendizService.sumarEnviado(aprendiz.get(0))
		}
	}
	
	private def agregarDestinatarios(Mensaje mensaje, HashMap<String, String> para){
		for (String key : para.keySet()){
			String value = para.get(key);
			this.agregarDestinatarioAMensaje(mensaje, key, value)
		}
	}

	
	private def agregarDestinatarioAMensaje(Mensaje mensaje, String key, String value){
		DestinatariosMensaje mPara = new DestinatariosMensaje()
		mPara.key = key
		mPara.value = value
		mensaje.addToPara(mPara)
	}
	
}
