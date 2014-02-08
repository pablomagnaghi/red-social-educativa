package com.fiuba

import static org.springframework.http.HttpStatus.*

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class UsuarioController {
	
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		
		println "index usuario"
		println params
		
		[usuarioInstanceList: Usuario.findAllByEnabled(false, [max: params.max, offset: params.offset, sort: params.sort, order: params.order]), 
			usuarioInstanceCount: Usuario.findAllByEnabled(false).size()]
	}
	
	def show(Usuario usuarioInstance) {
		respond usuarioInstance
	}

}
