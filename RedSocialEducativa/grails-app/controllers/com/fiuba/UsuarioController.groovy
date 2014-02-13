package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class UsuarioController {
	
	@Secured("hasRole('ROL_ADMIN')")
	def index() {
		params.max = Utilidades.MAX_PARAMS

		[usuarioInstanceList: Usuario.findAllByEnabled(false, [max: params.max, offset: params.offset, sort: params.sort, order: params.order]), 
			usuarioInstanceCount: Usuario.findAllByEnabled(false).size()]
	}
	
	@Secured("hasRole('ROL_ADMIN')")
	def muestraMenuAdm(Usuario usuarioInstance) {
		respond usuarioInstance
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def muestraMenuMed(Usuario usuarioInstance) {
		respond usuarioInstance
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def muestraMenuAprendiz(Usuario usuarioInstance) {
		respond usuarioInstance
	}
}
