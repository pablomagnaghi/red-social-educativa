package com.fiuba

import com.foro.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class TemaController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def temaService
	def usuarioService

	@Secured('isFullyAuthenticated()')
	def index() {
		params.max = Utilidades.MAX_PARAMS
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		[temaInstanceList: Tema.findAllByCurso(Curso.get(params.cursoId),[max: params.max, offset: params.offset]), mediador: mediador, params: ['cursoId': params.cursoId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		respond new Tema(params), model: [mediador: mediador], params: ['cursoId': params.cursoId]
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
			def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
			render view: "create", model: [temaInstance: temaInstance, mediador: mediador], params:['cursoId': params.cursoId]
			return
		}
		flash.message = "Tema ${temaInstance} creado"
		redirect action: "index", params:['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(Tema temaInstance) {
		if (temaInstance == null) {
			notFound()
			return
		}
		temaService.eliminar(temaInstance)
		flash.message = "Tema ${temaInstance} eliminado"
		redirect action:"index", params:['cursoId': params.cursoId], method:"GET"
	}

	protected void notFound() {
		flash.message = "No se encuentra ese tema"
		redirect action: "index", params:['cursoId': params.cursoId], method: "GET"
	}
}
