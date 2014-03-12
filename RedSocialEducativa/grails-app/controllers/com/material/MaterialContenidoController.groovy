package com.material

import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured


class MaterialContenidoController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def usuarioService
	def materialContenidoService

	@Secured("hasRole('ROL_MEDIADOR')")
	def show(MaterialContenido materialContenidoInstance) {
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		respond materialContenidoInstance, model: [mediador: mediador], params: ['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		respond new MaterialContenido(params), model:[mediador: mediador], params: ['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def save(MaterialContenido materialContenidoInstance) {
		if (materialContenidoInstance == null) {
			notFound()
			return
		}
		if (materialContenidoService.existe(materialContenidoInstance, params.contenidoId.toLong())) {
			flash.message = "Ya existe el material ${materialContenidoInstance.titulo} en el contenido ${Contenido.get(params.contenidoId)}"
			redirect action: "create", params: ['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]
			return
		}
		if (!materialContenidoService.guardar(materialContenidoInstance)) {
			def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
			render view:'create', model: [materialContenidoInstance: materialContenidoInstance, mediador: mediador],
			params: ['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]
			return
		}
		flash.message = "Material ${materialContenidoInstance.titulo} del tema ${materialContenidoInstance.contenido} creado"
		redirect controller:"contenido", action:"index", params:['cursoId': params.cursoId, 'temaId': params.temaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def edit(MaterialContenido materialContenidoInstance) {
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		respond materialContenidoInstance, model: [mediador: mediador], params: ['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def update(MaterialContenido materialContenidoInstance) {
		if (materialContenidoInstance == null) {
			notFound()
			return
		}
		if (!materialContenidoService.guardar(materialContenidoInstance)) {
			def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
			render view:'edit', model: [materialContenidoInstance: materialContenidoInstance, mediador: mediador], params: ['cursoId': params.cursoId, 
				'temaId': params.temaId, 'contenidoId': params.contenidoId]
			return
		}
		flash.message = "Material ${materialContenidoInstance.titulo} del tema ${materialContenidoInstance.contenido} actualizado"
		redirect controller:"contenido", action:"index", params:['cursoId': params.cursoId, 'temaId': params.temaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(MaterialContenido materialContenidoInstance) {
		if (materialContenidoInstance == null) {
			notFound()
			return
		}
		materialContenidoService.eliminar(materialContenidoInstance)
		flash.message = "Material ${materialContenidoInstance.titulo} del tema ${materialContenidoInstance.contenido} eliminado"
		redirect controller:"contenido", action:"index", params:['cursoId': params.cursoId, 'temaId': params.temaId], method:"GET"
	}

	protected void notFound() {
		flash.message = "No se encuentra ese material"
		redirect controller:"contenido", action:"index", params:['cursoId': params.cursoId, 'temaId': params.temaId], method: "GET"
	}
}
