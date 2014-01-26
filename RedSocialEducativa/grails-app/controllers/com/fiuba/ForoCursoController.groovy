package com.fiuba

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')

class ForoCursoController {

	def springSecurityService
	
	private usuarioActual() {
		if (springSecurityService.principal.enabled)
			return Usuario.get(springSecurityService.principal.id)
		else
			return null
	}
	
	def cursoId
	
	def general() {
		
		params.max = 5 // Math.min(max ?: 10, 100)

		//println PublicacionCurso.findAllByForoAndPublicacionInicial(ForoCurso.first(), null)
		
		println "foro curso general CURSOID: ${params.cursoId}"
		
		cursoId = params.cursoId
				
		def curso = Curso.get(cursoId)

		[publicaciones: PublicacionCurso.findAllByForoAndPublicacionInicial(ForoCurso.findByCurso(curso), 
			null, [max: params.max, offset: params.offset]),
		publicacionesCant: PublicacionCurso.findAllByForoAndPublicacionInicial(ForoCurso.findByCurso(curso), null).size(),
		cursoId: cursoId]
	}
	
	def publicaciones() {
		//println "foro, publicacion, params ${params}"
		
		params.max = 3
		def offset = params.offset ?: 0
		
		def publicacionId = params.id

		println "foro curso publicaciones CURSOID: ${params.cursoId}"
		
		cursoId = params.cursoId
				
		def curso = Curso.get(cursoId)
		println "curso: ${curso}"
		def foroId = curso.foro.id
		
		
		println PublicacionGeneral.first().foro.id
		
		def c = PublicacionCurso.createCriteria()
		def respuestas = c.list([max: params.max, offset: offset]){
			and {
				or {
					eq("id", publicacionId as long)
					eq("publicacionInicial.id", publicacionId as long)
				}
				eq("foro.id", foroId as long)
			}
		}
		
		def respuestasCant = PublicacionCurso.findAllByPublicacionInicialAndForo(PublicacionCurso.get(publicacionId),
			ForoCurso.findByCurso(curso)).size()+1
		
		println "respuestas"
		println respuestas

	
		
		[publicacion: PublicacionCurso.get(publicacionId), pubInicialId: publicacionId,
			respuestas: respuestas,
			respuestasCant: respuestasCant,
			usuario: Usuario.findByUsername(usuarioActual()?.username),
			mediador: Mediador.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId)),
			cursoId: cursoId]
		
	}
}