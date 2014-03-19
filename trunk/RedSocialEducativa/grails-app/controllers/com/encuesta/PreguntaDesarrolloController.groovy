package com.encuesta

import com.cursado.*
import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class PreguntaDesarrolloController {
	
	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def preguntaDesarrolloService

	@Secured("hasRole('ROL_MEDIADOR')")
	def index(Integer max) {
		params.max = Utilidades.MAX_PARAMS
		[preguntaDesarrolloInstanceList: PreguntaDesarrollo.findAllByEncuesta(Encuesta.get(params.encuestaId)), params: ['cursoId': params.cursoId,
			'encuestaId': params.encuestaId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def show(PreguntaDesarrollo preguntaDesarrolloInstance) {
		respond preguntaDesarrolloInstance, params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {
		respond new PreguntaDesarrollo(params), params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def save(PreguntaDesarrollo preguntaDesarrolloInstance) {
		if (preguntaDesarrolloInstance == null) {
			notFound()
			return
		}
		if (preguntaDesarrolloService.existe(preguntaDesarrolloInstance, params.encuestaId.toLong())) {
			flash.message = "Ya existe la pregunta a desarrollar ${preguntaDesarrolloInstance}"
			redirect action: "create", params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
			return
		}
		if (!preguntaDesarrolloService.guardar(preguntaDesarrolloInstance)) {
			render view:'create', model: [preguntaDesarrolloInstance: preguntaDesarrolloInstance], params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
			return
		}
		flash.message = "Pregunta a desarrollar ${preguntaDesarrolloInstance} creada"
		redirect action: "index", params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def edit(PreguntaDesarrollo preguntaDesarrolloInstance) {
		def pregunta = preguntaDesarrolloInstance.pregunta
		respond preguntaDesarrolloInstance, model: [pregunta: pregunta], params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def update(PreguntaDesarrollo preguntaDesarrolloInstance) {
		if (preguntaDesarrolloInstance == null) {
			notFound()
			return
		}
		if (preguntaDesarrolloService.existe(preguntaDesarrolloInstance, params.encuestaId.toLong())) {
			flash.message = "Ya existe la pregunta a desarrollar ${preguntaDesarrolloInstance}"
			preguntaDesarrolloInstance.pregunta = params.preguntaAntigua
			redirect action: "edit", params: params
			return
		}
		if (!preguntaDesarrolloService.guardar(preguntaDesarrolloInstance)) {
			render view:'edit', model: [preguntaDesarrolloInstance: preguntaDesarrolloInstance], params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
			return
		}
		flash.message = "Pregunta a desarrollar ${preguntaDesarrolloInstance} actualizada"
		redirect action: "index", params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(PreguntaDesarrollo preguntaDesarrolloInstance) {
		if (preguntaDesarrolloInstance == null) {
			notFound()
			return
		}
		preguntaDesarrolloService.eliminar(preguntaDesarrolloInstance)
		flash.message = "Pregunta a desarrollar ${preguntaDesarrolloInstance} eliminada"
		redirect action:"index", params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId], method:"GET"
	}
	
	protected void notFound() {
		flash.message = "No se encuentra esa pregunta a desarrollar"
		redirect action: "index", params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId], method: "GET"
	}

}
