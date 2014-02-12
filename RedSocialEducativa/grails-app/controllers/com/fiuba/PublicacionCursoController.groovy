package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class PublicacionCursoController {

	def seguridadService
	def publicacionCursoService

	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def nueva() {
		
		def pubInicialId = params.pubInicialId
		respond new PublicacionCurso(params), model: [usuario: seguridadService.usuarioActual(), pubInicialId: params.pubInicialId, 
			params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]
	}
	
	def guardar(PublicacionCurso publicacionCursoInstance) {

		if (publicacionCursoInstance == null) {
			notFound()
			return
		}
		
		publicacionCursoService.asignarResponsable(publicacionCursoInstance, seguridadService.usuarioActual())
		
		if (pubInicialId) {
			if (!publicacionGeneralService.guardarRespuesta(publicacionGeneralInstance, pubInicialId.toLong())) {
				respond publicacionGeneralInstance, view:'nueva',
				model: [pubInicialId: pubInicialId, usuario: seguridadService.usuarioActual()]
				return
			}
			flash.message = message(code: 'default.created.message', args: [message(code: 'publicacionGeneralInstance.label',
				default: 'PublicacionGeneral'), publicacionGeneralInstance.id])
			redirect controller: "foroGeneral", action: "publicaciones", params:['id': pubInicialId]
			return
		}

		if	(!publicacionGeneralService.guardar(publicacionGeneralInstance)) {
			respond publicacionGeneralInstance, view:'nueva',
			model: [pubInicialId: pubInicialId, usuario: seguridadService.usuarioActual()]
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'publicacionGeneralInstance.label', default: 'PublicacionGeneral'), publicacionGeneralInstance.id])
		redirect controller: "foroGeneral", action: "general"




		
		
		
		
		if (publicacionCursoInstance.hasErrors()) {
			respond publicacionCursoInstance, view:'nueva',  model: [pubInicialId: params.pubInicialId, usuario: seguridadService.usuarioActual()], 
				params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
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
		
		flash.message = message(code: 'default.created.message', args: [message(code: 'publicacionCursoInstance.label', default: 'PublicacionCurso'), publicacionCursoInstance.id])
		redirect controller: "foroCurso", action: "general", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}
	
	def eliminar(PublicacionCurso publicacion) {
	
		if (publicacion == null) {
			flash.message = "No existe esa publicacion"
			redirect controller: "foroCurso", action: "publicaciones", method: "GET", 
				params:['id': params.id, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}
		
		def esTema = false
		
		if (!publicacion.publicacionInicial) {
			esTema = true
		} 
		
		publicacion
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
	
	def actualizar(PublicacionCurso publicacionCursoInstance) {

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

		flash.message = message(code: 'default.updated.message', args: [message(code: 'PublicacionCurso.label', default: 'PublicacionCurso'), publicacionCursoInstance.id])
		redirect controller: "foroCurso", action: "publicaciones", 
			model: [usuario: seguridadService.usuarioActual(), pubInicialId: params.pubInicialId], 
			params: ['id': params.pubInicialId, 'cursoId': params.cursoId, 'cuatrimestreId':  params.cuatrimestreId]
	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'publicacionCursoInstance.label', default: 'PublicacionCurso'), params.id])
		redirect controller: "foroCurso", action: "publicaciones", method: "GET",  
			params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}
}
