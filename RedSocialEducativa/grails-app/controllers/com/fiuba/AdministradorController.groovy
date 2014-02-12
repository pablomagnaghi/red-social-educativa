package com.fiuba

import org.springframework.security.access.annotation.Secured

@Secured("hasRole('ROL_ADMIN')")
class AdministradorController {

	def administradorService

	def general() {
	}

	def activarUsuario() {

		if (!administradorService.activarUsuario(params.id.toLong())) {
			flash.message = "Problemas con activar al usuario"
			redirect(action: "general")
			return
		}

		flash.message = "Autorización enviada"
		redirect(action: "index", controller: "usuario")
	}
}
