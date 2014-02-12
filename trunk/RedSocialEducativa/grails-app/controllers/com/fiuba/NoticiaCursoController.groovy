package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured("hasRole('ROL_MEDIADOR')")
class NoticiaCursoController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def seguridadService
	def noticiaCursoService

	def index() {
		params.max = Utilidades.MAX_PARAMS

		def cuatrimestre = Cuatrimestre.get(params.cuatrimestreId)

		[noticiaCursoInstanceList: NoticiaCurso.findAllByCuatrimestre(cuatrimestre,[max: params.max, offset: params.offset]),
			noticiaCursoInstanceCount: NoticiaCurso.findAllByCuatrimestre(cuatrimestre).size(),
			params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]
	}

	def show(NoticiaCurso noticiaCursoInstance) {
		respond noticiaCursoInstance, params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}

	def create() {
		def mediadorId = Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(params.cursoId)).id
		
		respond new NoticiaCurso(params), model: [mediadorId: mediadorId], 
			params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}

	def save(NoticiaCurso noticiaCursoInstance) {
		if (noticiaCursoInstance == null) {
			notFound()
			return
		}
		
		if (!noticiaCursoService.guardar(noticiaCursoInstance)) {
			def mediadorId = Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(params.cursoId)).id
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
		respond noticiaCursoInstance, view:"show", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
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
