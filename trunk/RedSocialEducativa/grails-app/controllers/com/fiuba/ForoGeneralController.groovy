package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class ForoGeneralController {

	def seguridadService
	def foroGeneralService

	def general() {

		params.max = Utilidades.MAX_PARAMS

		[publicaciones: PublicacionGeneral.findAllByForoAndPublicacionInicial(ForoGeneral.first(), null, [max: params.max, offset: params.offset]),
			publicacionesCant: PublicacionGeneral.findAllByForoAndPublicacionInicial(ForoGeneral.first(), null).size()]
	}

	def publicaciones() {
		
		params.max = Utilidades.MAX_PARAMS
		
		Integer offset = params.offset?.toInteger() ?: 0
		
		def respuestas = foroGeneralService.obtenerRespuestas(params.id.toLong(), params.max, offset)
		
		def respuestasCant = PublicacionGeneral.findAllByPublicacionInicial(PublicacionGeneral.get(params.id)).size()+1

		[publicacion: PublicacionGeneral.get(params.id), respuestas: respuestas, respuestasCant: respuestasCant,
			usuario: Usuario.findByUsername(seguridadService.usuarioActual()?.username),
			administrador: Administrador.findByUsuario(seguridadService.usuarioActual()), params: ['pubInicialId': params.id]]
	}
}
