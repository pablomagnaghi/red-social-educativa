package com.fiuba

import static org.springframework.http.HttpStatus.*

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class UsuarioController {

	def show(Usuario usuarioInstance) {
		respond usuarioInstance
	}
	
}
