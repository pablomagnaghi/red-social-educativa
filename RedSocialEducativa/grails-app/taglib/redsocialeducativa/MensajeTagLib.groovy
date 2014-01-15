package redsocialeducativa

import grails.plugin.springsecurity.SpringSecurityService

class MensajeTagLib {
	static namespace = "msg"
	SpringSecurityService springSecurityService
	
	def showNotifications = { attrs, body ->
		if (springSecurityService.currentUser != null){
			out << "<a href='#'>"
			out << " <img style='border:0;' src='${resource(dir: 'images', file: 'sobre.gif')}'/>"
			out << "</a>"
			out << "(" + attrs.cantMensajes + ")"
		}
	}
    //static encodeAsForTags = [tagName: 'raw']
}
