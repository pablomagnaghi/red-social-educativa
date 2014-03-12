package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class ContenidoController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def contenidoService
	def usuarioService

	@Secured('isFullyAuthenticated()')
	def index() {
		params.max = Utilidades.MAX_PARAMS
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		[contenidoInstanceList: Contenido.findAllByTema(Tema.get(params.temaId)), mediador: mediador, params: ['cursoId': params.cursoId, 'temaId': params.temaId]]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		respond new Contenido(params), model: [mediador: mediador, params: ['cursoId': params.cursoId, 'temaId': params.temaId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def save(Contenido contenidoInstance) {
		if (contenidoInstance == null) {
			notFound()
			return
		}
		if (contenidoService.existe(contenidoInstance, params.temaId.toLong())) {
			flash.message = "Ya existe el contenido ${contenidoInstance.titulo} en el tema ${Tema.get(params.temaId)}"
			redirect action: "create", params: ['cursoId': params.cursoId, 'temaId': params.temaId]
			return
		}
		if (!contenidoService.guardar(contenidoInstance)) {
			def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
			render view:'create', model: [contenidoInstance: contenidoInstance, mediador: mediador], params: ['cursoId': params.cursoId, 'temaId': params.temaId]
			return
		}
		flash.message = "Contenido ${contenidoInstance} creado"
		redirect action:"index", params:['cursoId': params.cursoId, 'temaId': params.temaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(Contenido contenidoInstance) {
		if (contenidoInstance == null) {
			notFound()
			return
		}
		contenidoService.eliminar(contenidoInstance)
		flash.message = "Contenido ${contenidoInstance} eliminado"
		redirect action:"index", params:['cursoId': params.cursoId, 'temaId': params.temaId], method:"GET"
	}

	protected void notFound() {
		flash.message = "No se encuentra ese contenido"
		redirect action:"index", params:['cursoId': params.cursoId, 'temaId': params.temaId], method: "GET"
	}
}
