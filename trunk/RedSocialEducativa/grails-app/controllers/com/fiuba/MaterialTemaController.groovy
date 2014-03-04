package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class MaterialTemaController {

	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def usuarioService
	def materialTemaService

	@Secured("hasAnyRole('ROL_APRENDIZ', 'ROL_MEDIADOR')")
	def curso() {
		params.max = Utilidades.MAX_PARAMS
		def tema = Tema.get(params.temaId)

		[materiales: MaterialTema.findAllByTema(tema, [max: params.max, offset: params.offset]),
			materialesCant: MaterialTema.findAllByTema(tema).size(), 
			contenidos: Contenido.findAllByTema(tema, [max: params.max, offset: params.offset]),
			contenidosCant: Contenido.findAllByTema(tema).size(),
			params: ['cursoId': params.cursoId, 'temaId': params.temaId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def show(MaterialTema materialTemaInstance) {
		respond materialTemaInstance, params: ['cursoId': params.cursoId, 'temaId': params.temaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {

		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		respond new MaterialTema(params), model:[mediador: mediador], params: ['cursoId': params.cursoId, 'temaId': params.temaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def save(MaterialTema materialTemaInstance) {
		if (materialTemaInstance == null) {
			notFound()
			return
		}
		
		if (materialTemaService.existe(materialTemaInstance, params.temaId.toLong())) {
			flash.message = "Ya existe el material ${materialTemaInstance.titulo} en el tema ${Tema.get(params.temaId)}"
			redirect action: "create", params: ['cursoId': params.cursoId, 'temaId': params.temaId]
			return
		}

		if (!materialTemaService.guardar(materialTemaInstance)) {
			def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
			render view:'create', model: [materialTemaInstance: materialTemaInstance, mediador: mediador], params: ['cursoId': params.cursoId, 
				'temaId': params.temaId]
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'materialTemaInstance.label', default: 'MaterialTema'), materialTemaInstance.id])
		redirect controller:"tema", action:"index", params:['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def edit(MaterialTema materialTemaInstance) {
		respond materialTemaInstance, params: ['cursoId': params.cursoId, 'temaId': params.temaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def update(MaterialTema materialTemaInstance) {
		if (materialTemaInstance == null) {
			notFound()
			return
		}
		
		if (!materialTemaService.guardar(materialTemaInstance)) {
			render view:'edit', model: [materialTemaInstance: materialTemaInstance],
			params: ['cursoId': params.cursoId, 'temaId': params.temaId]
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'MaterialTema.label', default: 'MaterialTema'), materialTemaInstance.id])
		redirect action:"show", params:['id': params.id, 'cursoId': params.cursoId, 'temaId': params.temaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(MaterialTema materialTemaInstance) {

		if (materialTemaInstance == null) {
			notFound()
			return
		}

		materialTemaService.eliminar(materialTemaInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'MaterialTema.label', default: 'MaterialTema'), materialTemaInstance.id])
		redirect controller:"tema", action:"index", params:['cursoId': params.cursoId], method:"GET"
	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialTemaInstance.label', default: 'MaterialTema'), params.id])
		redirect controller:"tema", action:"edit", params:['id': params.temaId, 'cursoId': params.cursoId], method:"GET"
	}
}
