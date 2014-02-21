package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class UsuarioController {
	
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
