package com.fiuba

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class PublicacionCursoController {

	def springSecurityService
	
	private usuarioActual() {
		if (springSecurityService.principal.enabled)
			return Usuario.get(springSecurityService.principal.id)
		else
			return null
	}

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def nueva() {
		println "publicacion controller nueva params: ${params}"
		println usuarioActual()
		def pubInicialId = params.pubInicialId
		def cursoId = params.cursoId
		def cuatrimestreId = params.cuatrimestreId
		respond new PublicacionCurso(params), model: [usuario: usuarioActual(), 
			pubInicialId: pubInicialId, cursoId: cursoId, cuatrimestreId: cuatrimestreId]
	}
	
	@Transactional
	def guardar(PublicacionCurso publicacionCursoInstance) {
		
		println "publicacion controller guardar pubInicialId: ${params.pubInicialId}, cursoId: ${params.cursoId}"
		
		def pubInicialId = params.pubInicialId
		def cursoId = params.cursoId
		def cuatrimestreId = params.cuatrimestreId
		
		if (publicacionCursoInstance == null) {
			notFound()
			return
		}
		
		if (publicacionCursoInstance.hasErrors()) {
			respond publicacionCursoInstance.errors, view:'nueva',  model: [pubInicialId: pubInicialId, 
				usuario: usuarioActual(), cursoId: cursoId, cuatrimestreId: cuatrimestreId]
			return
		}
		
		if (!usuarioActual()) {
			publicacionCursoInstance.responsable = publicacionCursoInstance.responsable + " (Visitante)"
		} else {
			if (Administrador.findByUsuario(usuarioActual())) {
				publicacionCursoInstance.responsable = publicacionCursoInstance.responsable + " (Administrador)"
			} else {
				publicacionCursoInstance.responsable = publicacionCursoInstance.responsable + " (Miembro)"
			}
		}
	
		if (pubInicialId) {
			println "es una respuesta"
			def publicacion = PublicacionCurso.get(pubInicialId)
			publicacionCursoInstance.titulo = "Respuesta a: " + publicacionCursoInstance.titulo
			publicacion.addToRespuestas(publicacionCursoInstance)
			publicacion.save flush:true
			flash.message = message(code: 'default.created.message', args: [message(code: 'publicacionCursoInstance.label', default: 'PublicacionCurso'), publicacionCursoInstance.id])
			redirect controller: "foroCurso", action: "publicaciones", params:['id': pubInicialId, 'cursoId': params.cursoId, 'cuatrimestreId': cuatrimestreId]
			return
		} else {
			publicacionCursoInstance.save flush:true
		}
		
		request.withFormat {
			form {
				flash.message = message(code: 'default.created.message', args: [message(code: 'publicacionCursoInstance.label', default: 'PublicacionCurso'), publicacionCursoInstance.id])
				redirect controller: "foroCurso", action: "general", params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]
			}
			'*' { respond publicacionCursoInstance, [status: CREATED] }
		}
	}
	
	@Transactional
	def eliminar() {
		
		println "publicacion controller eliminarPublicacion params: ${params}"
		
		def publicacionId = params.id
		def cursoId = params.cursoId
		def cuatrimestreId = params.cuatrimestreId
		
		println "publicaion id: ${publicacionId}"
		
		def publicacion = PublicacionCurso.get(publicacionId)

		println "publicacion: ${publicacion}"
				
		if (publicacion == null) {
			flash.message = "No existe esa publicacion"
			redirect controller: "foroCurso", action: "publicaciones", method: "GET", 
				params:['id': params.pubInicialId, 'cursoId': params.cursoId, 'cuatrimestreId': cuatrimestreId]
			return
		}
		
		def esTema = false
		
		if (!publicacion.publicacionInicial) {
			//println "es inicialllllllllll"
			esTema = true
		} else {
			//println "NO ES INICIAL"
			//println publicacion.publicacionInicial
		}
		
		publicacion.delete flush:true


		flash.message = message(code: 'default.deleted.message', args: [message(code: 'PublicacionCurso.label',
			default: 'PublicacionCurso'), publicacion.id])

		if (esTema) {
			redirect controller: "foroCurso", action:"general", params:['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestreId]
		} else {
			redirect controller: "foroCurso", action:"publicaciones", method:"GET", 
				params:['id': params.pubInicialId, 'cursoId': params.cursoId, 'cuatrimestreId':  cuatrimestreId]
		}
	}
	
	def editar(PublicacionCurso publicacionCursoInstance) {
		
		println "publicacion controller editar params: ${params}"
		println usuarioActual()
		def pubInicialId = params.pubInicialId
		def publicacionId = params.id
		def cursoId = params.cursoId
		def cuatrimestreId = params.cuatrimestreId
		
		respond publicacionCursoInstance, model: [usuario: usuarioActual(),
			pubInicialId: pubInicialId, publicacionId: publicacionId, cursoId: params.cursoId,  cuatrimestreId: cuatrimestreId]
		
	}
	
	@Transactional
	def actualizar(PublicacionCurso publicacionCursoInstance) {

		println "publicacion id: ${params.id}, ${publicacionCursoInstance.id}, cursoId: ${params.cursoIs}"
		
		println "padreId: ${params.pubInicialId}"
		
		println publicacionCursoInstance.properties
		
		if (publicacionCursoInstance == null) {
			notFound()
			return
		}

		if (publicacionCursoInstance.hasErrors()) {
			respond publicacionCursoInstance.errors, view:'editar', model: [usuario: usuarioActual(),
				pubInicialId: params.pubInicialId, publicacionId: params.id, cursoId: params.cursoId, cuatrimestreId: params.cuatrimestreId]
			return
		}

		publicacionCursoInstance.save flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'PublicacionCurso.label', default: 'PublicacionCurso'), publicacionCursoInstance.id])
				redirect controller: "foroCurso", action: "publicaciones", params: ['id': params.pubInicialId, 'cursoId': params.cursoId, 'cuatrimestreId':  params.cuatrimestreId],
				model: [usuario: usuarioActual(), pubInicialId: params.pubInicialId, cursoId: params.cursoId, cuatrimestreId: params.cuatrimestreId]
			}
			'*'{ respond publicacionCursoInstance, [status: OK] }
		}
	}

	protected void notFound() {
		request.withFormat {
			form {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'publicacionCursoInstance.label', default: 'PublicacionCurso'), params.id])
				redirect controller: "foroCurso", action: "publicaciones", method: "GET",  params:['cursoId': cursoId, 'cuatrimestreId':  cuatrimestreId]
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
