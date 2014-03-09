package com.fiuba

import grails.plugin.mail.*
import grails.transaction.Transactional

@Transactional
class RedService {
	private crearMiembro(Usuario usuario) {
		def miembro = new Miembro(usuario: usuario, rol: Rol.findByAuthority(Utilidades.ROL_MIEMBRO))

		if (miembro.save(flush: true)) {
			return miembro
		}
		return null
	}
	private activacionCorrecta(Usuario usuario) {
		usuario.fechaMembresia = new Date().format(Utilidades.FORMATO_FECHA)
		if (!usuario.save(flush: true)) {
			return false
		}
		if (!crearMiembro(usuario)) {
			return false
		}
		return true
	}
	private enviarEmail(String email, String msj) {
		sendMail {
			to email
			subject Utilidades.TITULO_BIENVENIDA
			body msj
		}
	}
	def activarUsuario(Usuario usuario) {
		usuario.enabled = true
		if (!activacionCorrecta(usuario)) {
			return false
		}
		String msj = Utilidades.MSJ_MAIL_BIENVENIDA + " miembro ${usuario.username}."
		enviarEmail(usuario.email, msj)
		return true
	}
}
