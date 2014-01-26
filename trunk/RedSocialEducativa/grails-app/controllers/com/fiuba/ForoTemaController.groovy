package com.fiuba

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')

class ForoTemaController {

	def springSecurityService
	
	private usuarioActual() {
		if (springSecurityService.principal.enabled)
			return Usuario.get(springSecurityService.principal.id)
		else
			return null
	}
	
	def cursoId
	def temaId
	
	def general() {
		
		params.max = 5 // Math.min(max ?: 10, 100)

		//println PublicacionTema.findAllByForoAndPublicacionInicial(ForoTema.first(), null)
		
		println "foro Tema general CURSOID: ${params.cursoId}"
		println "foro Tema general TEMAID: ${params.temaId}"
		
		cursoId = params.cursoId
		temaId = params.temaId
				
		def tema = Tema.get(temaId)
		
		[publicaciones: PublicacionTema.findAllByForoAndPublicacionInicial(ForoTema.findByTema(tema),
			null, [max: params.max, offset: params.offset]),
		publicacionesCant: PublicacionTema.findAllByForoAndPublicacionInicial(ForoTema.findByTema(tema), null).size(),
		tema: Tema.get(temaId), foro: ForoTema.findByTema(Tema.get(temaId)),
		cursoId: cursoId, temaId: temaId]
	}
	
	def publicaciones() {
		//println "foro, publicacion, params ${params}"
		
		params.max = 3
		def offset = params.offset ?: 0
		
		def publicacionId = params.id

		println "foro curso publicaciones CURSOID: ${params.cursoId}"
		
		cursoId = params.cursoId
		temaId = params.temaId		
		
		def tema = Tema.get(temaId)
		println "tema: ${tema}"
		def foroId = tema.foro.id
		
		def c = PublicacionTema.createCriteria()
		def respuestas = c.list([max: params.max, offset: offset]){
			and {
				or {
					eq("id", publicacionId as long)
					eq("publicacionInicial.id", publicacionId as long)
				}
				eq("foro.id", foroId as long)
			}
		}
		
		def respuestasCant = PublicacionTema.findAllByPublicacionInicialAndForo(PublicacionTema.get(publicacionId),
			ForoTema.findByTema(tema)).size()+1
		
		println "respuestas"
		println respuestas

	
		
		[publicacion: PublicacionTema.get(publicacionId), pubInicialId: publicacionId,
			respuestas: respuestas,
			respuestasCant: respuestasCant,
			usuario: Usuario.findByUsername(usuarioActual()?.username),
			mediador: Mediador.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId)),
			cursoId: cursoId, temaId: temaId]
		
	}
}