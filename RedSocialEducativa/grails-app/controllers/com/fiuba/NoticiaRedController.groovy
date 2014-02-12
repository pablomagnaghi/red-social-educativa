package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured("hasRole('ROL_ADMIN')")
class NoticiaRedController {

	def seguridadService
	def noticiaRedService

	// TODO
	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index() {
		params.max = Utilidades.MAX_PARAMS
		respond NoticiaRed.list(params), model:[noticiaRedInstanceCount: NoticiaRed.count()]
	}

	def show(NoticiaRed noticiaRedInstance) {
		respond noticiaRedInstance
	}

	def create() {
		[noticiaRedInstance: new NoticiaRed(params), usuario: seguridadService.usuarioActual()]
	}

	def save(NoticiaRed noticiaRedInstance) {

		if (noticiaRedInstance == null) {
			notFound()
			return
		}

		if (!noticiaRedService.guardar(noticiaRedInstance)) {
			respond noticiaRedInstance, view:'create'
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'noticiaRedInstance.label', default: 'NoticiaRed'), noticiaRedInstance.id])
		redirect noticiaRedInstance
	}

	def edit(NoticiaRed noticiaRedInstance) {
		respond noticiaRedInstance, model: [usuario: seguridadService.usuarioActual()]
	}

	def update(NoticiaRed noticiaRedInstance) {

		if (noticiaRedInstance == null) {
			notFound()
			return
		}

		if (!noticiaRedService.guardar(noticiaRedInstance)) {
			respond noticiaRedInstance, view:'edit'
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'NoticiaRed.label', default: 'NoticiaRed'), noticiaRedInstance.id])
		redirect noticiaRedInstance
	}

	def delete(NoticiaRed noticiaRedInstance) {

		if (noticiaRedInstance == null) {
			notFound()
			return
		}

		noticiaRedService.eliminar(noticiaRedInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'NoticiaRed.label', default: 'NoticiaRed'), noticiaRedInstance.id])
		redirect action:"index", method:"GET"
	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'noticiaRedInstance.label', default: 'NoticiaRed'), params.id])
		redirect action: "index", method: "GET"
	}
}