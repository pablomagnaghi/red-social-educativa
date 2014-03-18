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
	
	def htmlMessage = { attrs, body ->
		out << g.message(message: attrs.args).decodeHTML()
	}

	def showConversation = { attrs, body ->
		if (attrs.data != null){
			def conversacion = attrs.data
			def mensaje = conversacion.mensajes.last()
			if (mensaje.leido == false){
				out << "<tr conversationId='"+conversacion.id+"' class='unread draggable'>"
			} else {
				out << "<tr conversationId='"+conversacion.id+"' class='draggable'>"
			}
			out<< "<td class='inbox-table-icon \'>\
					<div class='checkbox'>\
						<label> <input type='checkbox' id='"+conversacion.id+"' class='checkbox style-2 checkBoxConv'> \
						<span></span> \
						</label> \
					</div>\
					</td>"
			out << "<td class='inbox-data-from hidden-xs hidden-sm showConv'> \
					<div>"+mensaje.emisor.nombres + " " +mensaje.emisor.apellido+ "</div> \
				</td>"
			out << "<td class='inbox-data-message showConv'> \
					<div> \
						<span>"+mensaje.asunto+"</span> "+mensaje.getCuerpoResumido()+"  ...\
					</div> \
				</td>"
			out << "<td class='inbox-data-date hidden-xs showConv'> \
					<div>22.30</div> \
				</td></tr>"
		}
	}
}
