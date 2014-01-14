package com.fiuba

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class NoticiaCursoController {

	static scaffold = true
}


