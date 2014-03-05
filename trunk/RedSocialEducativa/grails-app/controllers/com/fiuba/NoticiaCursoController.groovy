package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured("hasRole('ROL_MEDIADOR')")
class NoticiaCursoController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def usuarioService
	def noticiaCursoService

	def index() {
		params.max = 100//Utilidades.MAX_PARAMS
		println "params: ${params}"
		[noticiasCurso: noticiaCursoService.obtenerNoticiasOrdenadas(params.cuatrimestreId.toLong()),
			params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]
	}

	def show(NoticiaCurso noticiaCursoInstance) {
		respond noticiaCursoInstance, params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}

	def create() {
		def mediadorId = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId)).id
		
		respond new NoticiaCurso(params), model: [mediadorId: mediadorId], 
			params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}

	def save(NoticiaCurso noticiaCursoInstance) {
		if (noticiaCursoInstance == null) {
			notFound()
			return
		}
		
		if (!noticiaCursoService.guardar(noticiaCursoInstance)) {
			def mediadorId = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId)).id
			render view:'create', model: [noticiaCursoInstance: noticiaCursoInstance, mediadorId: mediadorId], 
				params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'noticiaCursoInstance.label', default: 'NoticiaCurso'), noticiaCursoInstance.id])
		redirect action: "index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}
	
	def edit(NoticiaCurso noticiaCursoInstance) {
		respond noticiaCursoInstance, params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}

	def update(NoticiaCurso noticiaCursoInstance) {

		if (noticiaCursoInstance == null) {
			notFound()
			return
		}

		if (!noticiaCursoService.guardar(noticiaCursoInstance)) {
			respond noticiaCursoInstance, view:'edit', params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'NoticiaCurso.label', default: 'NoticiaCurso'), noticiaCursoInstance.id])
		redirect action:"index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}
	
	def cambiarVisibilidad(NoticiaCurso noticiaCursoInstance) {
		
		if (noticiaCursoInstance == null) {
			notFound()
			return
		}
		
		noticiaCursoInstance.visibilidad = noticiaCursoInstance.visibilidad ? false : true

		if (!noticiaCursoService.guardar(noticiaCursoInstance)) {
			flash.message = "Problemas al cambiar la visibilidad"
			redirect action:"index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}
		
		redirect action:"index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}

	def delete(NoticiaCurso noticiaCursoInstance) {

		if (noticiaCursoInstance == null) {
			notFound()
			return
		}

		noticiaCursoService.eliminar(noticiaCursoInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'NoticiaCurso.label', default: 'NoticiaCurso'), noticiaCursoInstance.id])
		redirect action:"index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId], method:"GET"
	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'noticiaCursoInstance.label', default: 'NoticiaCurso'), params.id])
		redirect action: "index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId], method: "GET"
	}
}
