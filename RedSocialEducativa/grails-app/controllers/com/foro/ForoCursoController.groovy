package com.foro

import com.cursado.*
import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured("hasAnyRole('ROL_MEDIADOR', 'ROL_APRENDIZ')")
class ForoCursoController {

	def usuarioService
	def foroCursoService
	def aprendizService
	
	def general() {
		params.max = Utilidades.MAX_PARAMS
		def cuatrimestre = Cuatrimestre.get(params.cuatrimestreId)
		def foro = ForoCurso.findByCuatrimestre(cuatrimestre)
	
		[publicaciones: PublicacionCurso.findAllByForoAndPublicacionInicial(foro, null), 
			mediador: Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), cuatrimestre.curso),
			params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]
	}
	
	def publicaciones() {
		def cuatrimestre = Cuatrimestre.get(params.cuatrimestreId)
		def foro = ForoCurso.findByCuatrimestre(cuatrimestre)
		def respuestas = PublicacionCurso.findAllByPublicacionInicial(PublicacionCurso.get(params.id))
		
		[tema: PublicacionCurso.get(params.id), respuestas: respuestas, foro: foro,
			mediador: Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), cuatrimestre.curso),
			params:['pubInicialId': params.id, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]
	}
}