package com.fiuba

import grails.plugin.mail.*
import grails.transaction.Transactional

@Transactional
class AdministradorService {

	def guardar(Administrador administrador) {
		if (administrador.save(flush: true)) {
			return administrador
		}
		return null
	}

}
