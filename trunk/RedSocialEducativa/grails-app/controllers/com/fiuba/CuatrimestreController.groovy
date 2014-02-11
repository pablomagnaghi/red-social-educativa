package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

//@Secured("hasAnyRole(['ROL_ADMIN', 'ROL_MEDIADOR'])")
@Secured('permitAll')
class CuatrimestreController {
	// metodos nuevos

	// TODO agregar curso id en todos los metodos

	def cuatrimestreService
	def cursoId

	def historial(Cuatrimestre cuatrimestreInstance) {
		respond cuatrimestreInstance, model: [cursoId: cursoId]
	}

	def muestraMenuAdm(Cuatrimestre cuatrimestreInstance) {
		respond cuatrimestreInstance, model: [cursoId: cursoId]
	}

	// metodos viejos

	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Utilidades.MAX_PARAMS

		cursoId = params.cursoId

		[cuatrimestreInstanceList: Cuatrimestre.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			cuatrimestreInstanceCount: Cuatrimestre.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId]
	}

	def show(Cuatrimestre cuatrimestreInstance) {
		respond cuatrimestreInstance, model: [cursoId: cursoId]
	}

	def create() {
		cursoId = params.cursoId
		respond new Cuatrimestre(params), params:['cursoId': cursoId], model:[cursoId: cursoId]
	}

	def save(Cuatrimestre cuatrimestreInstance) {
		if (cuatrimestreInstance == null) {
			notFound()
			return
		}

		cursoId = params.cursoId

		if (cuatrimestreService.existe(cuatrimestreInstance, cursoId)) {
			flash.message = "Ya existe el cuatrimestre ${cuatrimestreExistente} del curso ${Curso.get(cursoId)}"
			redirect action: "create", params:['cursoId': cursoId]
			return
		}

		def foro = new ForoCurso(nombre: "Foro general del curso ${cuatrimestreInstance.curso} durante el cuatrimestre {cuatrimestreInstance}")
		cuatrimestreInstance.foro = foro

		if (!cuatrimestreService.guardar(cuatrimestreInstance)) {
			respond cuatrimestrInstance, view:'create',  params:['cursoId': cursoId], model: [cursoId: cursoId]
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'cuatrimestreInstance.label', default: 'Cuatrimestre'), cuatrimestreInstance.id])
		redirect action: "index", params:['cursoId': cursoId]
	}

	def edit(Cuatrimestre cuatrimestreInstance) {
		cursoId = params.cursoId
		respond cuatrimestreInstance, params:['cursoId': cursoId], model:[cursoId: cursoId]
	}

	def update(Cuatrimestre cuatrimestreInstance) {

		cursoId = params.cursoId

		if (cuatrimestreInstance == null) {
			notFound()
			return
		}

		if (!cuatrimestreService.guardar(cuatrimestreInstance)) {
			respond cuatrimestreInstance, view:'edit', params:['cursoId': cursoId], model: [cursoId: cursoId]
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'Cuatrimestre.label', default: 'Cuatrimestre'), cuatrimestreInstance.id])
		redirect cuatrimestreInstance
	}

	def delete(Cuatrimestre cuatrimestreInstance) {

		if (cuatrimestreInstance == null) {
			notFound()
			return
		}

		cursoId = params.cursoId

		cuatrimestreService.eliminar(cuatrimestreInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'Cuatrimestre.label', default: 'Cuatrimestre'), cuatrimestreInstance.id])
		redirect action:"index", params:['cursoId': cursoId], method:"GET"

	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuatrimestreInstance.label', default: 'Cuatrimestre'), params.id])
		redirect action: "index", params:['cursoId': cursoId], method: "GET"

	}
}

