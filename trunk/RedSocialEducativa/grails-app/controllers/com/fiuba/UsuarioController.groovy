package com.fiuba

import static org.springframework.http.HttpStatus.*

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class UsuarioController {
	
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Usuario.findAllByEnabled(false), model:[usuarioInstanceCount: Usuario.findAllByEnabled(false).size()]
	}
	
	// TODO
	// metodo usado en el menu del administrador y mediador
	def show(Usuario usuarioInstance) {
		respond usuarioInstance
	}

}
