package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class CuatrimestreController {

	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def cuatrimestreService

	@Secured("hasRole('ROL_ADMIN')")
	def muestraMenuAdm(Cuatrimestre cuatrimestreInstance) {
		respond cuatrimestreInstance, params: [cursoId: params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def indexHistoriales(Integer max) {
		params.max = Utilidades.MAX_PARAMS

		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(params.cursoId.toLong())
		
		[cuatrimestreInstanceList: Cuatrimestre.findAllByCursoAndIdNotEqual(Curso.get(params.cursoId), cuatrimestre.id,[max: params.max, offset: params.offset]),
			cuatrimestreInstanceCount: Cuatrimestre.findAllByCursoAndIdNotEqual(Curso.get(params.cursoId), cuatrimestre.id).size(), 
			params: [cursoId: params.cursoId]]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def historial(Cuatrimestre cuatrimestreInstance) {
		respond cuatrimestreInstance, params: [cursoId: params.cursoId]
	}

	// TODO CONSOLIDAR CUATRIMESTRE
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def show(Cuatrimestre cuatrimestreInstance) {
		respond cuatrimestreInstance, params:['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {
		respond new Cuatrimestre(params), params:['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def save(Cuatrimestre cuatrimestreInstance) {
		if (cuatrimestreInstance == null) {
			notFound()
			return
		}

		if (cuatrimestreService.existe(cuatrimestreInstance, params.cursoId.toLong())) {
			flash.message = "Ya existe el cuatrimestre ${cuatrimestreExistente} del curso ${Curso.get(params.cursoId)}"
			redirect action: "create", params:['cursoId': params.cursoId]
			return
		}

		def foro = new ForoCurso(nombre: "Foro general del curso ${cuatrimestreInstance.curso} durante el cuatrimestre {cuatrimestreInstance}")
		cuatrimestreInstance.foro = foro

		if (!cuatrimestreService.guardar(cuatrimestreInstance)) {
			render view:'create', model: [cuatrimestrInstance: cuatrimestrInstance], params:['cursoId': params.cursoId]
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'cuatrimestreInstance.label', default: 'Cuatrimestre'), cuatrimestreInstance.id])
		redirect action: "indexHistoriales", params:['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def edit(Cuatrimestre cuatrimestreInstance) {
		respond cuatrimestreInstance, params:['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def update(Cuatrimestre cuatrimestreInstance) {

		if (cuatrimestreInstance == null) {
			notFound()
			return
		}

		if (!cuatrimestreService.guardar(cuatrimestreInstance)) {
			render view:'edit', model: [cuatrimestrInstance: cuatrimestreInstance], params:['cursoId': params.cursoId]
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'Cuatrimestre.label', default: 'Cuatrimestre'), cuatrimestreInstance.id])
		redirect action: "show", params:['id': cuatrimestreInstance.id, 'cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(Cuatrimestre cuatrimestreInstance) {

		if (cuatrimestreInstance == null) {
			notFound()
			return
		}

		cuatrimestreService.eliminar(cuatrimestreInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'Cuatrimestre.label', default: 'Cuatrimestre'), cuatrimestreInstance.id])
		redirect action:"indexHistoriales", params:['cursoId': params.cursoId], method:"GET"
	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuatrimestreInstance.label', default: 'Cuatrimestre'), params.id])
		redirect action: "indexHistoriales", params:['cursoId': params.cursoId], method: "GET"
	}
}

