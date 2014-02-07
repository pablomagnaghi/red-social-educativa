package com.fiuba

import org.springframework.security.access.annotation.Secured

import grails.transaction.Transactional

@Transactional
class SeguridadService {

	def springSecurityService
	
	def usuarioActual() {
		if (springSecurityService.principal.enabled)
			return Usuario.get(springSecurityService.principal.id)
		else
			return null
	}

}
