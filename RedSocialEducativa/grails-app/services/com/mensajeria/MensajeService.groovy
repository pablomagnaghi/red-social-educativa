package com.mensajeria

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
}
