package com.fiuba

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class AprendizController {
	static scaffold = true
}
