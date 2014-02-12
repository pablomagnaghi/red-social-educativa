package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class ForoCursoController {

	def seguridadService
	def foroCursoService
	
	def general() {
		
		params.max = Utilidades.MAX_PARAMS
				
		def cuatrimestre = Cuatrimestre.get(params.cuatrimestreId)

		[publicaciones: PublicacionCurso.findAllByForoAndPublicacionInicial(ForoCurso.findByCuatrimestre(cuatrimestre), 
			null, [max: params.max, offset: params.offset]),
		publicacionesCant: PublicacionCurso.findAllByForoAndPublicacionInicial(ForoCurso.findByCuatrimestre(cuatrimestre), null).size(),
		params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]
	}
	
	def publicaciones() {

		params.max = Utilidades.MAX_PARAMS
		def offset = params.offset ?: 0
		
		def publicacionId = params.id
		def cuatrimestre = Cuatrimestre.get(params.cuatrimestreId)
		def respuestas = foroCursoService.obtenerRespuestas(cuatrimestre, publicacionId.toLong(), params.max, offset)
		def respuestasCant = PublicacionCurso.findAllByPublicacionInicialAndForo(PublicacionCurso.get(publicacionId),
			ForoCurso.findByCuatrimestre(cuatrimestre)).size()+1
		def usuario = seguridadService.usuarioActual()
		
		[publicacion: PublicacionCurso.get(publicacionId), pubInicialId: publicacionId, respuestas: respuestas, respuestasCant: respuestasCant,
			usuario: Usuario.findByUsername(usuario?.username), mediador: Mediador.findByUsuarioAndCurso(usuario, cuatrimestre.curso),
			params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]
	}
}