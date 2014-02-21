package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class TemaController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def temaService

	// TODO ver solo este metodo cuando se haga el menu para visitantes/miembros/aprendices
	@Secured('permitAll')
	def general(Tema tema) {

		[contenidos: Contenido.findAllByTema(tema), materiales: MaterialTema.findAllByTema(tema), tema: tema,
			paramas: ['cursoId': params.cursoId, 'temaId': params.temaId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def index() {
		params.max = Utilidades.MAX_PARAMS

		[temaInstanceList: Tema.findAllByCurso(Curso.get(params.cursoId),[max: params.max, offset: params.offset]),
			temaInstanceCount: Tema.findAllByCurso(Curso.get(params.cursoId)).size(), params: ['cursoId': params.cursoId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def show(Tema temaInstance) {
		respond temaInstance, params: ['cursoId': params.cursoId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {
		respond new Tema(params), params: ['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def save(Tema temaInstance) {
		if (temaInstance == null) {
			notFound()
			return
		}

		if (temaService.existe(temaInstance, params.cursoId.toLong())) {
			flash.message = "Ya existe el tema ${temaInstance.titulo} en el curso ${Curso.get(params.cursoId)}"
			redirect action: "create", params:['cursoId': params.cursoId]
			return
		}

		temaInstance.foro = new ForoTema(nombre: "Foro del tema ${temaInstance} del curso ${Curso.get(params.cursoId)}")
		
		if (!temaService.guardar(temaInstance)) {
			render view: "create", model: [temaInstance: temaInstance], params:['cursoId': params.cursoId]
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'temaInstance.label', default: 'Tema'), temaInstance.id])
		redirect action: "index", params:['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def edit(Tema temaInstance) {
		respond temaInstance, params:['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(Tema temaInstance) {

		if (temaInstance == null) {
			notFound()
			return
		}

		temaService.eliminar(temaInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'Tema.label', default: 'Tema'), temaInstance.id])
		redirect action:"index", params:['cursoId': params.cursoId], method:"GET"
	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'temaInstance.label', default: 'Tema'), params.id])
		redirect action: "index", params:['cursoId': params.cursoId], method: "GET"
	}
}
