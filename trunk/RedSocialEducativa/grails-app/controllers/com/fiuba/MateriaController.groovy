package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured("hasRole('ROL_ADMIN')")
class MateriaController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def materiaService

	def index() {
		params.max = Utilidades.MAX_PARAMS
		respond Materia.list(params), model:[materiaInstanceCount: Materia.count()]
	}

	def show(Materia materiaInstance) {
		respond materiaInstance
	}

	def create() {
		respond new Materia(params)
	}

	def save(Materia materiaInstance) {
		if (materiaInstance == null) {
			notFound()
			return
		}

		if (!materiaService.guardar(materiaInstance)) {
			respond materiaInstance, view:'create'
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'materiaInstance.label', default: 'Materia'), materiaInstance.id])
		redirect materiaInstance
	}

	def edit(Materia materiaInstance) {
		respond materiaInstance
	}

	def update(Materia materiaInstance) {
		if (materiaInstance == null) {
			notFound()
			return
		}

		if (!materiaService.guardar(materiaInstance)) {
			respond materiaInstance, view:'edit'
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'Materia.label', default: 'Materia'), materiaInstance.id])
		redirect materiaInstance
	}

	def delete(Materia materiaInstance) {
		if (materiaInstance == null) {
			notFound()
			return
		}

		materiaService.eliminar(materiaInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'Materia.label', default: 'Materia'), materiaInstance.id])
		redirect action:"index", method:"GET"
	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'materiaInstance.label', default: 'Materia'), params.id])
		redirect action: "index", method: "GET"
	}
}
