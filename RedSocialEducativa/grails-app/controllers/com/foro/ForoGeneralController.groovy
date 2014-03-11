package com.foro

import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured('isFullyAuthenticated()')
class ForoGeneralController {

	def usuarioService
	def foroGeneralService

	def general() {
		params.max = Utilidades.MAX_PARAMS
		[publicaciones: PublicacionGeneral.findAllByForoAndPublicacionInicial(ForoGeneral.first(), null)]
	}

	def publicaciones() {
		def respuestas = PublicacionGeneral.findAllByPublicacionInicial(PublicacionGeneral.get(params.id))
		[tema: PublicacionGeneral.get(params.id), respuestas: respuestas, params: ['pubInicialId': params.id]]
	}
}
