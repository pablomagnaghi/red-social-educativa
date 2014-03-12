package com.material

import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class MaterialTemaController {

	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def usuarioService
	def materialTemaService

	@Secured("hasRole('ROL_MEDIADOR')")
	def show(MaterialTema materialTemaInstance) {
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		respond materialTemaInstance, model: [mediador: mediador], params: ['cursoId': params.cursoId, 'temaId': params.temaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		respond new MaterialTema(params), model: [mediador: mediador], params: ['cursoId': params.cursoId, 'temaId': params.temaId]
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
		flash.message = "Material ${materialTemaInstance.titulo} del tema ${materialTemaInstance.tema} creado"
		redirect controller:"tema", action:"index", params:['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def edit(MaterialTema materialTemaInstance) {
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		respond materialTemaInstance, model: [mediador: mediador], params: ['cursoId': params.cursoId, 'temaId': params.temaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def update(MaterialTema materialTemaInstance) {
		if (materialTemaInstance == null) {
			notFound()
			return
		}
		if (!materialTemaService.guardar(materialTemaInstance)) {
			def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
			render view:'edit', model: [materialTemaInstance: materialTemaInstance, mediador: mediador], params: ['cursoId': params.cursoId, 'temaId': params.temaId]
			return
		}
		flash.message = "Material ${materialTemaInstance.titulo} del tema ${materialTemaInstance.tema} actualizado"
		redirect controller:"tema", action:"index", params:['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(MaterialTema materialTemaInstance) {
		if (materialTemaInstance == null) {
			notFound()
			return
		}
		materialTemaService.eliminar(materialTemaInstance)
		flash.message = "Material ${materialTemaInstance.titulo} del tema ${materialTemaInstance.tema} eliminado"
		redirect controller:"tema", action:"index", params:['cursoId': params.cursoId], method:"GET"
	}

	protected void notFound() {
		flash.message = "No se encuentra ese material"
		redirect controller:"tema", action:"index", params:['cursoId': params.cursoId], method:"GET"
	}
}
