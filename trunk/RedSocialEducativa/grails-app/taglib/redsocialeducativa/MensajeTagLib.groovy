package redsocialeducativa

import grails.plugin.springsecurity.SpringSecurityService

class MensajeTagLib {
	static namespace = "msg"
	SpringSecurityService springSecurityService
	
	def showNotifications = { attrs, body ->
		if (springSecurityService.currentUser != null){
			out << "<a href='mensajeria/index'>"
			out << " <img style='border:0;' src='${resource(dir: 'images', file: 'sobre.gif')}'/>"
			out << "</a>"
			out << "(" + attrs.cantMensajes + ")"
		}
	}
	
	def showConversation = { attrs, body ->
		def conversacion = attrs.data
		def mensajes = conversacion.mensajes
		mensajes.each {
			if (it.leido == false){
				out << "<div class='msgNoLeido'>"
			} else {
				out << "<div>"
			}
			out << g.link("De:  " + it.emisor.username + " | asunto: " + it.asunto + " | fecha: " + it.fecha,
				action: 'conversacion', id: conversacion.id, class:'draggable', params : [id:conversacion.id])
			out << "</div>"
		}
	}
}
