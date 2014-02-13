package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class MaterialCursoController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def seguridadService
	def materialCursoService

	// TODO ver solo este metodo cuando se haga el menu para visitantes/miembros/aprendices
	@Secured('permitAll')
	def general(MaterialCurso material) {

		[material: material, params: ['cursoId': params.cursoId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def index() {
		params.max = Utilidades.MAX_PARAMS

		[materialCursoInstanceList: MaterialCurso.findAllByCurso(Curso.get(params.cursoId),[max: params.max, offset: params.offset]),
			materialCursoInstanceCount: MaterialCurso.findAllByCurso(Curso.get(params.cursoId)).size(), params: ['cursoId': params.cursoId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def show(MaterialCurso materialCursoInstance) {
		respond materialCursoInstance, params: ['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {

		def mediador = Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(params.cursoId))

		respond new MaterialCurso(params), model: [mediador: mediador], params: ['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def save(MaterialCurso materialCursoInstance) {
		if (materialCursoInstance == null) {
			notFound()
			return
		}

		if (materialCursoService.existe(materialCursoInstance, params.cursoId.toLong())) {
			flash.message = "Ya existe el material ${materialCursoInstance.titulo} del curso ${Curso.get(cursoId)}"
			redirect action: "create", params:['cursoId': params.cursoId]
			return
		}

		if (!materialCursoService.guardar(materialCursoInstance)) {
			def mediador = Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(params.cursoId))
			render view:'create', model: [materialCursoInstance: materialCursoInstance, mediador: mediador], params:['cursoId': params.cursoId]
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'materialCursoInstance.label', default: 'MaterialCurso'), materialCursoInstance.id])
		redirect action: "index", params:['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def edit(MaterialCurso materialCursoInstance) {
		respond materialCursoInstance, model:[usuario: seguridadService.usuarioActual()],  params:['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def update(MaterialCurso materialCursoInstance) {

		if (materialCursoInstance == null) {
			notFound()
			return
		}

		if (!materialCursoService.guardar(materialCursoInstance)) {
			render view:'edit', model: [materialCursoInstance: materialCursoInstance], params:['cursoId': params.cursoId]
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'MaterialCurso.label', default: 'MaterialCurso'), materialCursoInstance.id])
		redirect action: "show", params:['id': materialCursoInstance.id, 'cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(MaterialCurso materialCursoInstance) {

		if (materialCursoInstance == null) {
			notFound()
			return
		}

		materialCursoService.eliminar(materialCursoInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'MaterialCurso.label', default: 'MaterialCurso'), materialCursoInstance.id])
		redirect action:"index", params:['cursoId': params.cursoId], method:"GET"
	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialCursoInstance.label', default: 'MaterialCurso'), params.id])
		redirect action: "index", params:['cursoId': params.cursoId], method: "GET"
	}
}
