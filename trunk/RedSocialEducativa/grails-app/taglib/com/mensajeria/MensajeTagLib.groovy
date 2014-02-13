package com.mensajeria
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
		if (attrs.data != null){
			def conversacion = attrs.data
			def mensaje = conversacion.mensajes.last()
			if (mensaje.leido == false){
				out << "<tr onclick='mostrarConversacion("+conversacion.id+")' class='unread'>"
			} else {
				out << "<tr onclick='mostrarConversacion("+conversacion.id+")'>"
			}
			out<< "<td class='inbox-table-icon'>\
					<div class='checkbox'>\
						<label> <input type='checkbox' class='checkbox style-2'> \
						<span></span> \
						</label> \
					</div>\
					</td>"
			out << "<td class='inbox-data-from hidden-xs hidden-sm'> \
					<div>"+mensaje.emisor.nombres + " " +mensaje.emisor.apellido+ "</div> \
				</td>"
			out << "<td class='inbox-data-message'> \
					<div> \
						<span>"+mensaje.asunto+"</span> "+mensaje.getCuerpoResumido()+"  ...\
					</div> \
				</td>"
			out << "<td class='inbox-data-date hidden-xs'> \
					<div>22.30</div> \
				</td></tr>"
		}
	}
}
