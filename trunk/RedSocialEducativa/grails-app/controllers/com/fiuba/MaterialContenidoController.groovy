package com.fiuba

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured


class MaterialContenidoController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def seguridadService
	def materialContenidoService

	@Secured("hasRole('ROL_MEDIADOR')")
	def show(MaterialContenido materialContenidoInstance) {
		respond materialContenidoInstance, params: ['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {
		def mediador = Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(params.cursoId))
		respond new MaterialContenido(params), model:[mediador: mediador],
		params: ['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def save(MaterialContenido materialContenidoInstance) {
		if (materialContenidoInstance == null) {
			notFound()
			return
		}

		if (materialContenidoService.existe(materialContenidoInstance, params.contenidoId.toLong())) {
			flash.message = "Ya existe el material ${materialContenidoInstance.titulo} del contenido ${Contenido.get(params.contenidoId)}"
			redirect action: "create", params: ['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]
			return
		}
		
		if (!materialContenidoService.guardar(materialContenidoInstance)) {
			render view:'create', model: [materialContenidoInstance: materialContenidoInstance],
			params: ['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'materialContenidoInstance.label', default: 'MaterialContenido'), materialContenidoInstance.id])
		redirect controller:"contenido", action:"show", params:['id': params.contenidoId, 'cursoId': params.cursoId, 'temaId': params.temaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def edit(MaterialContenido materialContenidoInstance) {
		respond materialContenidoInstance, params: ['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def update(MaterialContenido materialContenidoInstance) {
		if (materialContenidoInstance == null) {
			notFound()
			return
		}

		if (!materialContenidoService.guardar(materialContenidoInstance)) {
			render view:'edit', model: [materialContenidoInstance: materialContenidoInstance], params: ['cursoId': params.cursoId, 
				'temaId': params.temaId, 'contenidoId': params.contenidoId]
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'MaterialContenido.label', default: 'MaterialContenido'), materialContenidoInstance.id])
		redirect action:"show", params:['id': materialContenidoInstance.id, 'cursoId': params.cursoId, 'temaId': params.temaId, 
			'contenidoId': params.contenidoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(MaterialContenido materialContenidoInstance) {

		if (materialContenidoInstance == null) {
			notFound()
			return
		}

		materialContenidoService.eliminar(materialContenidoInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'MaterialContenido.label', default: 'MaterialContenido'), materialContenidoInstance.id])
		redirect controller:"contenido", action:"edit", params:['id': params.contenidoId, 'cursoId': params.cursoId, 'temaId': params.temaId], method:"GET"
	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialContenidoInstance.label', default: 'MaterialContenido'), params.id])
		redirect controller: "contenido", action:"edit", params:['id': params.contenidoId, 'cursoId': params.cursoId, 'temaId': params.temaId], method: "GET"
	}
}

