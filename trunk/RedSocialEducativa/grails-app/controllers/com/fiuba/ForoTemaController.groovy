package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured


@Secured('isFullyAuthenticated()')
class ForoTemaController {

	def usuarioService
	def foroTemaService
	def aprendizService
	
	def general() {
		
		params.max = Utilidades.MAX_PARAMS
				
		def tema = Tema.get(params.temaId)
		
		[publicaciones: PublicacionTema.findAllByForoAndPublicacionInicial(ForoTema.findByTema(tema),null, [max: params.max, offset: params.offset]), 
			publicacionesCant: PublicacionTema.findAllByForoAndPublicacionInicial(ForoTema.findByTema(tema), null).size(),tema: tema, 
			foro: ForoTema.findByTema(tema), params: ['cursoId': params.cursoId, 'temaId': params.temaId]]
	}
	
	def publicaciones() {
		params.max = Utilidades.MAX_PARAMS
		Integer offset = params.offset?.toInteger() ?: 0
		
		def tema = Tema.get(params.temaId)
		def respuestas = foroTemaService.obtenerRespuestas(tema, params.id.toLong(), params.max, offset)
		def respuestasCant = PublicacionTema.findAllByPublicacionInicialAndForo(PublicacionTema.get(params.id),
			ForoTema.findByTema(tema)).size()+1
		
		[publicacion: PublicacionTema.get(params.id), respuestas: respuestas, respuestasCant: respuestasCant, 
			mediador: Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId)), 
			aprendiz: aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, params.cursoId.toLong()),
			params:['pubInicialId': params.id, 'cursoId': params.cursoId, 'temaId': params.temaId]]
	}
}


