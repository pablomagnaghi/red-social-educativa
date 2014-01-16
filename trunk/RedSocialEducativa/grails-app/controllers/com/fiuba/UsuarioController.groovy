package com.fiuba

import static org.springframework.http.HttpStatus.*

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class UsuarioController {

	// TODO
	// metodo usado en el menu del administrador y mediador
	def show(Usuario usuarioInstance) {
		respond usuarioInstance
	}
	
}
