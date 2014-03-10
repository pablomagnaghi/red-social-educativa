package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured("hasRole('ROL_ADMIN')")
class AsignaturaController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def asignaturaService

	def index() {
		params.max = Utilidades.MAX_PARAMS
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
		flash.message = "Asignatura ${asignaturaInstance.codigo} creada"
		redirect action:"index"
	}

	def edit(Asignatura asignaturaInstance) {
		def codigo = Asignatura.get(asignaturaInstance.id).codigo
		respond asignaturaInstance, model: [codigo: codigo]
	}

	def update(Asignatura asignaturaInstance) {
		if (asignaturaInstance == null) {
			notFound()
			return
		}
		if (asignaturaService.existe(asignaturaInstance)) {
			flash.message = "Ya existe la asignatura ${asignaturaInstance.codigo}"
			asignaturaInstance.codigo = params.codigoAnterior
			redirect action:'edit', params: params
			return
		}
		if (!asignaturaService.guardar(asignaturaInstance)) {
			respond asignaturaInstance, view:'edit'
			return
		}
		flash.message = "Asignatura ${asignaturaInstance.codigo} actualizada"
		redirect action:"index"
	}

	def delete(Asignatura asignaturaInstance) {
		if (asignaturaInstance == null) {
			notFound()
			return
		}
		asignaturaService.eliminar(asignaturaInstance)
		flash.message = "Asignatura ${asignaturaInstance.codigo} eliminada"
		redirect action:"index", method:"GET"
	}

	protected void notFound() {
		flash.message = "No se encuentra esa asignatura"
		redirect action: "index", method: "GET"
	}
}
