package com.fiuba

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')

class ForoGeneralController {

	def springSecurityService
	
	private usuarioActual() {
		if (springSecurityService.principal.enabled)
			return Usuario.get(springSecurityService.principal.id)
		else
			return null
	}
	
	def general() {
		
		params.max = 10 // Math.min(max ?: 10, 100)

		//println PublicacionGeneral.findAllByForoAndPublicacionInicial(ForoGeneral.first(), null)
		
		[publicaciones: PublicacionGeneral.findAllByForoAndPublicacionInicial(ForoGeneral.first(), 
			null, [max: params.max, offset: params.offset]),
		publicacionesCant: PublicacionGeneral.findAllByForoAndPublicacionInicial(ForoGeneral.first(), null).size()]
	}
	
	def publicaciones() {
		//println "foro, publicacion, params ${params}"
		
		params.max = 3
		def offset = params.offset ?: 0
		
		def publicacionId = params.id

		def c = PublicacionGeneral.createCriteria()
		def respuestas = c.list([max: params.max, offset: offset]){
			or {
				eq("id", publicacionId as long)
				eq("publicacionInicial.id", publicacionId as long)
			}
		}
		
		def respuestasCant = PublicacionGeneral.findAllByPublicacionInicial(PublicacionGeneral.get(publicacionId)).size()+1
		//println respuestas
		//println respuestasCant

		[publicacion: PublicacionGeneral.get(publicacionId), pubInicialId: publicacionId,
			respuestas: respuestas,
			respuestasCant: respuestasCant,
			usuario: Usuario.findByUsername(usuarioActual()?.username),
			administrador: Administrador.findByUsuario(usuarioActual())]
		
	}
}
