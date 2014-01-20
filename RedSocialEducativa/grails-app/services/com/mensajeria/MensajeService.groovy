package com.mensajeria

import com.fiuba.Usuario
import grails.transaction.Transactional

@Transactional
class MensajeService {

    def nuevoMensaje(Mensaje mensaje) {
		def hilo = new Hilo()
    	hilo.save()
		mensaje.hilo = hilo;
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
		def mensaje = new Mensaje(emisor: emisor, receptor: receptor, asunto: asunto,
			cuerpo: texto, fecha : new Date())
		mensaje.hilo = hilo;
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
}
