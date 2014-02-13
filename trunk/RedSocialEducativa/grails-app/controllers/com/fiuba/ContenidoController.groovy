package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class ContenidoController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def seguridadService
	def contenidoService

	// TODO ver solo este metodo cuando se haga el menu para visitantes/miembros/aprendices
	@Secured('permitAll')
	def general() {

		params.max = Utilidades.MAX_PARAMS

		def tema = Tema.get(params.temaId)

		[contenidos: Contenido.findAllByTema(tema, [max: params.max, offset: params.offset]),
			contenidosCant: Contenido.findAllByTema(tema).size(),
			tema: tema, params: ['cursoId': params.cursoId, 'temaId': params.temaId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def show(Contenido contenidoInstance) {
		respond contenidoInstance, params: ['cursoId': params.cursoId, 'temaId': params.temaId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {
		respond new Contenido(params), params: ['cursoId': params.cursoId, 'temaId': params.temaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def save(Contenido contenidoInstance) {
		if (contenidoInstance == null) {
			notFound()
			return
		}

		if (!contenidoService.guardar(contenidoInstance)) {
			render view:'create', model: [contenidoInstance: contenidoInstance], params: ['cursoId': params.cursoId, 'temaId': params.temaId]
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'contenidoInstance.label', default: 'Contenido'), contenidoInstance.id])
		redirect controller:"tema", action:"edit", params:['id': params.temaId, 'cursoId': params.cursoId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def edit(Contenido contenidoInstance) {
		respond contenidoInstance, params: ['cursoId': params.cursoId, 'temaId': params.temaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def update(Contenido contenidoInstance) {

		if (contenidoInstance == null) {
			notFound()
			return
		}

		if (!contenidoService.guardar(contenidoInstance)) {
			render view:'edit', model: [contenidoInstance: contenidoInstance], params: ['cursoId': params.cursoId, 'temaId': params.temaId]
			return
		}
		
		flash.message = message(code: 'default.updated.message', args: [message(code: 'Contenido.label', default: 'Contenido'), contenidoInstance.id])
		redirect action:"show", params:['id':contenidoInstance.id, 'cursoId': params.cursoId, 'temaId': params.temaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(Contenido contenidoInstance) {

		if (contenidoInstance == null) {
			notFound()
			return
		}

		contenidoService.eliminar(contenidoInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'Contenido.label', default: 'Contenido'), contenidoInstance.id])
		redirect controller:"tema", action:"edit", params:['id': params.temaId, 'cursoId': params.cursoId], method:"GET"
	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'contenidoInstance.label', default: 'Contenido'), params.id])
		redirect controller:"tema", action:"edit", params:['id': params.temaId, 'cursoId': params.cursoId], method: "GET"
	}
}
