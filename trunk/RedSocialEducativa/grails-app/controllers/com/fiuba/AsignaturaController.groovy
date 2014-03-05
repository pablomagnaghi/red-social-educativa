package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured("hasRole('ROL_ADMIN')")
class AsignaturaController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def asignaturaService

	def index() {
		params.max = 100//Utilidades.MAX_PARAMS
		respond Asignatura.list(params)
	}
	
	def show(Asignatura asignaturaInstance) {
		respond asignaturaInstance
	}

	def create() {
		respond new Asignatura(params)
	}

	def save(Asignatura asignaturaInstance) {
		if (asignaturaInstance == null) {
			notFound()
			return
		}

		if (asignaturaService.existe(asignaturaInstance)) {
			flash.message = "Ya existe la asignatura ${asignaturaInstance.codigo}"
			redirect action: "create"
			return
		}
		
		if (!asignaturaService.guardar(asignaturaInstance)) {
			respond asignaturaInstance, view:'create'
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'asignaturaInstance.label', default: 'Asignatura'), asignaturaInstance.id])
		redirect action:"index"
	}

	def edit(Asignatura asignaturaInstance) {
		respond asignaturaInstance
	}

	def update(Asignatura asignaturaInstance) {
		if (asignaturaInstance == null) {
			notFound()
			return
		}

		if (!asignaturaService.guardar(asignaturaInstance)) {
			respond asignaturaInstance, view:'edit'
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'Asignatura.label', default: 'Asignatura'), asignaturaInstance.id])
		redirect action:"index"
	}

	def delete(Asignatura asignaturaInstance) {
		if (asignaturaInstance == null) {
			notFound()
			return
		}

		asignaturaService.eliminar(asignaturaInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'Asignatura.label', default: 'Asignatura'), asignaturaInstance.id])
		redirect action:"index", method:"GET"
	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'AsignaturaInstance.label', default: 'Asignatura'), params.id])
		redirect action: "index", method: "GET"
	}
}
