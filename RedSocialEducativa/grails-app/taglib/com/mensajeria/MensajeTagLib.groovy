package com.mensajeria
import grails.plugin.springsecurity.SpringSecurityService

class MensajeTagLib {
	static namespace = "msg"
	
	def htmlMessage = { attrs, body ->
		out << g.message(message: attrs.args).decodeHTML()
	}

}
