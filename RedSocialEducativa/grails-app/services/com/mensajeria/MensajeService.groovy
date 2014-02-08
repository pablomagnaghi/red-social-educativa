package com.mensajeria

import com.fiuba.Usuario
import grails.transaction.Transactional

@Transactional
class MensajeService {

    def nuevoMensaje(Mensaje mensaje) {
		def hilo = new Hilo()
    	hilo.save()
		mensaje.hilo = hilo;
		mensaje.para = mensaje.receptor.nombres +" "+ 
		mensaje.receptor.apellido + "<"+mensaje.receptor.email+">,"
		def carpeta = Carpeta.findByNombreAndUsuario("Escritorio", mensaje.receptor)
		def nuevaConversacion = new Conversacion(padre: carpeta, hilo: hilo)
		nuevaConversacion.addToMensajes(mensaje)
		if (!mensaje.save()) {
			mensaje.errors.each {
				println it
			}
		}
		nuevaConversacion.save()
    }
	
	def sendMessage(String para, String asunto, String texto, Usuario emisor, Usuario receptor, Hilo hilo){
		def mensaje = new Mensaje(para: para, emisor: emisor, receptor: receptor, asunto: asunto,
			cuerpo: texto, fecha : new Date())
		mensaje.hilo = hilo;
		mensaje.para = para
		def carpeta = Carpeta.findByNombreAndUsuario("Escritorio", receptor)
		def nuevaConversacion = new Conversacion(padre: carpeta, hilo: hilo)
		nuevaConversacion.addToMensajes(mensaje)
		if (!mensaje.save()) {
			mensaje.errors.each {
				println it
			}
		}
		nuevaConversacion.save()
	}
	
	def reply(String para, String asunto, String texto, Usuario emisor, Usuario receptor, Hilo hilo){
		def mensaje = new Mensaje(para: para, emisor: emisor, receptor: receptor, asunto: asunto,
			cuerpo: texto, fecha : new Date())
		mensaje.hilo = hilo;
		mensaje.para = para
		
		def carpetasUsuario = Carpeta.findAllByUsuario(receptor)
		def conversacion = Conversacion.find("from Conversacion as c \
					where c.hilo = :hilo and c.padre in :carpetas", [hilo: hilo, carpetas:carpetasUsuario ])
		def nueva = false
		if (conversacion == null){
			nueva = true
			def carpeta = Carpeta.findByNombreAndUsuario("Escritorio", receptor)
			conversacion = new Conversacion(padre: carpeta, hilo: hilo)
		}
		if (!mensaje.save(flush : true)) {
			mensaje.errors.each {
				println it
			}
		}
		conversacion.addToMensajes(mensaje)
		if (nueva == true){
			conversacion.save()
		}
	}
}