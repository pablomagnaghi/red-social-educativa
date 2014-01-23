package com.mensajeria

import com.fiuba.Usuario
import grails.transaction.Transactional

@Transactional
class ConversacionService {

	def findConversacionByMessage(Mensaje mensaje, Usuario usuario){
		def carpetasUsuario = Carpeta.findAllByUsuario(usuario)
		def conversacion = Conversacion.find("from Conversacion as c \
					where c.hilo = :hilo and c.padre in :carpetas", [hilo: mensaje.hilo, carpetas:carpetasUsuario ])
		return conversacion
	}
		   
}
