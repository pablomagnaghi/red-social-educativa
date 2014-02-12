package com.fiuba

import grails.plugin.mail.*
import grails.transaction.Transactional

@Transactional
class AdministradorService {

	def activarUsuario(Long usuarioId) {

		def usuario = Usuario.get(usuarioId)
		usuario.enabled = true
		usuario.fechaMemb = new Date().format(Utilidades.FORMATO_FECHA)

		def mail = usuario.email
		def username = usuario.username

		if (!usuario.save(flush: true)) {
			return false
		}

		if (!crearMiembro(usuario)) {
			return false
		}

		sendMail {
			to mail
			subject Utilidades.TITULO_RED
			body Utilidades.MENSAJE_BIENVENIDA
		}

		return true
	}

	private crearMiembro(Usuario usuario) {

		def miembro = new Miembro(usuario: usuario, rol: Rol.findByAuthority(Utilidades.ROL_MIEMBRO))

		if (miembro.save(flush: true)) {
			return miembro
		}

		return null
	}
}
