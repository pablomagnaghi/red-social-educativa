package com.foro

import com.fiuba.*
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
		def foro = ForoTema.findByTema(tema)
		
		[publicaciones: PublicacionTema.findAllByForoAndPublicacionInicial(foro,null), tema: tema, foro: foro, 
			mediador: Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId)), 
			params: ['cursoId': params.cursoId, 'temaId': params.temaId]]
	}
	
	def publicaciones() {
		def tema = Tema.get(params.temaId)
		def foro = ForoTema.findByTema(tema)
		def respuestas = PublicacionTema.findAllByPublicacionInicial(PublicacionTema.get(params.id))
		
		[tema: PublicacionTema.get(params.id), respuestas: respuestas, foro: foro,
			mediador: Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId)), 
			params:['pubInicialId': params.id, 'cursoId': params.cursoId, 'temaId': params.temaId]]
	}
}
