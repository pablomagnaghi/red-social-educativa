package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured('isFullyAuthenticated()')
class ForoGeneralController {

	def usuarioService
	def foroGeneralService

	def general() {

		params.max = 100// Utilidades.MAX_PARAMS
		/*
		[publicaciones: PublicacionGeneral.findAllByForoAndPublicacionInicial(ForoGeneral.first(), null, [max: params.max, offset: params.offset]),
			publicacionesCant: PublicacionGeneral.findAllByForoAndPublicacionInicial(ForoGeneral.first(), null).size()]*/
		[publicaciones: foroGeneralService.obtenerPublicacionesOrdenadas()]
	}

	def publicaciones() {
		/*
		params.max = Utilidades.MAX_PARAMS
		
		Integer offset = params.offset?.toInteger() ?: 0
		
		def respuestas = foroGeneralService.obtenerRespuestas(params.id.toLong(), params.max, offset)
		def respuestasCant = PublicacionGeneral.findAllByPublicacionInicial(PublicacionGeneral.get(params.id)).size()+1

		[publicacion: PublicacionGeneral.get(params.id), respuestas: respuestas, respuestasCant: respuestasCant,
			administrador: Administrador.findByUsuario(usuarioService.usuarioActual()), params: ['pubInicialId': params.id]]
		*/
		
		def respuestas = PublicacionGeneral.findAllByPublicacionInicial(PublicacionGeneral.get(params.id))

		[tema: PublicacionGeneral.get(params.id), respuestas: respuestas, respuestasCant: respuestas.size(),
			params: ['pubInicialId': params.id]]
	}
}
