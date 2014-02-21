package com.fiuba

import org.springframework.security.access.annotation.Secured

@Secured("hasRole('ROL_ADMIN')")
class AdministradorController {

	def general() {
	}
}
