package com.fiuba

import grails.plugin.mail.*
import grails.transaction.Transactional

@Transactional
class AdministradorService {
	
	private enviarEmail(String email, String msj) {
		sendMail {
			to email
			subject Utilidades.TITULO_RED
			body msj
		}
	}

	def guardar(Administrador administrador) {
		if (administrador.save(flush: true)) {
			return administrador
		}
		return null
	}

	def notificar(Administrador administrador) {
		def email = administrador.usuario.email
		if (!administrador.activo) {
			def mensaje = "${administrador.usuario.nombres} ${administrador.usuario.apellido} ha dejado de ser administrador"
			enviarEmail(email, mensaje)
			return
		}
		def mensaje = "Miembro ${administrador.usuario.nombres} ${administrador.usuario.apellido} se ha converitdo en administrador"
		enviarEmail(email, mensaje)
	}
}
