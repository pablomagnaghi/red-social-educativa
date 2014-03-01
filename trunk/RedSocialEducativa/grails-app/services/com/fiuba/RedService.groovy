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
	
	def activarUsuario(Usuario usuario) {
		usuario.enabled = true
		usuario.fechaMembresia = new Date().format(Utilidades.FORMATO_FECHA)
		if (!usuario.save(flush: true)) {
			return false
		}
		if (!crearMiembro(usuario)) {
			return false
		}
		def email = usuario.email
		String msj = Utilidades.MSJ_MAIL_BIENVENIDA + " miembro ${usuario.username}."
		sendMail {
			to email
			subject Utilidades.TITULO_RED
			body msj
		}
		return true
	}
}
