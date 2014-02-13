package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class PublicacionCursoController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def seguridadService
	def publicacionCursoService

	def nueva() {
		
		respond new PublicacionCurso(params), model: [usuario: seguridadService.usuarioActual(), 
			params:['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]
	}
	
	def guardar(PublicacionCurso publicacionCursoInstance) {

		if (publicacionCursoInstance == null) {
			notFound()
			return
		}
	
		if (params.pubInicialId) {
			if (!publicacionCursoService.guardarRespuesta(publicacionCursoInstance, params.pubInicialId.toLong(), seguridadService.usuarioActual())) {
				render view:'nueva', model: [publicacionCursoInstance: publicacionCursoInstance, usuario: seguridadService.usuarioActual()],
					params: ['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
				return
			}
			flash.message = message(code: 'default.created.message', args: [message(code: 'publicacionCursoInstance.label',
				default: 'PublicacionCurso'), publicacionCursoInstance.id])
			redirect controller: "foroCurso", action: "publicaciones", params: ['id': params.pubInicialId,
				'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}

		if	(!publicacionCursoService.guardar(publicacionCursoInstance, seguridadService.usuarioActual())) {
			render view:'nueva', model: [publicacionCursoInstance: publicacionCursoInstance, usuario: seguridadService.usuarioActual()],
					params: ['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'publicacionCursoInstance.label', default: 'PublicacionCurso'), publicacionCursoInstance.id])
		redirect controller: "foroCurso", action: "general", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}
	
	def eliminar(PublicacionCurso publicacion) {
	
		if (publicacion == null) {
			notFound()
			return
		}
		
		def esTema = false
		
		if (!publicacion.publicacionInicial) {
			esTema = true
		} 
		
		publicacionCursoService.eliminar(publicacion)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'PublicacionCurso.label',
			default: 'PublicacionCurso'), publicacion.id])

		if (esTema) {
			redirect controller: "foroCurso", action:"general", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}
		
		redirect controller: "foroCurso", action:"publicaciones", method:"GET", 
			params:['id': params.pubInicialId, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}
	
	def editar(PublicacionCurso publicacionCursoInstance) {
		
		respond publicacionCursoInstance, model: [usuario: seguridadService.usuarioActual()],
			params: [ 'publicacionId': params.id, 'pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 
				'cuatrimestreId': params.cuatrimestreId]
	}
	
	def actualizar(PublicacionCurso publicacionCursoInstance) {

		if (publicacionCursoInstance == null) {
			notFound()
			return
		}

		if (!publicacionCursoService.guardar(publicacionCursoInstance, seguridadService.usuarioActual())) {
			render view:'editar', model: [publicacionCursoInstance: publicacionCursoInstance, usuario: seguridadService.usuarioActual()]
				params: ['publicacionId': params.id, 'pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 
					'cuatrimestreId': params.cuatrimestreId]
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'PublicacionCurso.label', default: 'PublicacionCurso'), publicacionCursoInstance.id])
		redirect controller: "foroCurso", action: "publicaciones", 
			model: [usuario: seguridadService.usuarioActual(), pubInicialId: params.pubInicialId], 
			params: ['id': params.pubInicialId, 'cursoId': params.cursoId, 'cuatrimestreId':  params.cuatrimestreId]
	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'publicacionCursoInstance.label', default: 'PublicacionCurso'), params.id])
		redirect controller: "foroCurso", action: "general", method: "GET"
	}
}
