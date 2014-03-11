package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class MediadorController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def mediadorService
	def aprendizService
	def usuarioService

	@Secured("hasRole('ROL_MEDIADOR')")
	def activarAprendiz() {
		
		if (!mediadorService.activarAprendiz(params.id.toLong())) {
			flash.message = "Problemas con el aprendiz"
			redirect controller: "curso", action: "menuMediador", params: params
			return
		}
	
		def aprendiz = Aprendiz.get(params.id)
		
		flash.message = "Autorización enviada para el aprendiz ${aprendiz.usuario.username} del curso ${aprendiz.cuatrimestre.curso}"
		redirect controller: "aprendiz", action: "index", params: params
	}

	// TODO: listo de aca para abajo
	@Secured("hasAnyRole('ROL_ADMIN', 'ROL_MEDIADOR')")
	def index(Integer max) {
		params.max = Utilidades.MAX_PARAMS
		println "params: ${params}"
		def mediadorInstanceList
		if (params.cursoId) {
			mediadorInstanceList = mediadorService.obtenerMediadoresDeCursoOrdenados(params.cursoId.toLong())
		} else {
			mediadorInstanceList = mediadorService.obtenerMediadoresOrdenados()
		}
		[mediadorInstanceList: mediadorInstanceList, params: ['cursoId': params.cursoId]]
	}

	@Secured("hasAnyRole('ROL_ADMIN', 'ROL_MEDIADOR')")
	def create() {
		respond new Mediador(params), params: ['cursoId': params.cursoId]
	}

	@Secured("hasAnyRole('ROL_ADMIN', 'ROL_MEDIADOR')")
	def save(Mediador mediadorInstance) {
		if (mediadorInstance == null) {
			notFound()
			return
		}
		if (mediadorService.existe(mediadorInstance)) {
			flash.message = "${mediadorInstance.usuario} ya es mediador en el curso ${mediadorInstance.curso}"
			redirect action: "create", params: ['cursoId': params.cursoId]
			return
		}
		if (aprendizService.obtenerPorCurso(mediadorInstance.usuario.id, mediadorInstance.curso.id)) {
			flash.message = "El miembro ${mediadorInstance.usuario} ya es aprendiz en el curso ${mediadorInstance.curso}. No puede ser mediador"
			redirect action: "create", params: ['cursoId': params.cursoId]
			return
		}
		if (!mediadorService.guardar(mediadorInstance)) {
			respond mediadorInstance, view:'create', params: ['cursoId': params.cursoId]
			return
		}
		flash.message = "Mediador ${mediadorInstance.usuario}-${mediadorInstance.usuario.dni} creado"
		redirect action:"index", params: ['cursoId': params.cursoId]
	}
	
	@Secured("hasAnyRole('ROL_ADMIN', 'ROL_MEDIADOR')")
	def editarJerarquia(Mediador mediadorInstance) {
		respond mediadorInstance, params: ['cursoId': params.cursoId]
	}
	
	@Secured("hasAnyRole('ROL_ADMIN', 'ROL_MEDIADOR')")
	def actualizarJerarquia(Mediador mediadorInstance) {
		if (mediadorInstance == null) {
			notFound()
			return
		}
		
		if (!mediadorService.guardar(mediadorInstance)) {
			flash.message = "Problemas al actualizar la jerarquia"
			redirect action: "editarJerarquia", params: ['cursoId': params.cursoId]
			return
		}
		flash.message = "Mediador ${mediadorInstance.usuario}-${mediadorInstance.usuario.dni} actualizado"
		redirect action:"index", params: ['cursoId': params.cursoId]
	}
	
	@Secured("hasAnyRole('ROL_ADMIN', 'ROL_MEDIADOR')")
	def cambiarEstado(Mediador mediadorInstance) {
		if (mediadorInstance == null) {
			notFound()
			return
		}
		mediadorInstance.activo = mediadorInstance.activo ? false : true
		if (!mediadorService.guardar(mediadorInstance)) {
			flash.message = "Problemas al cambiar el estado"
			redirect action:"index", params: ['cursoId': params.cursoId], method:"GET"
			return
		}
		mediadorService.notificar(mediadorInstance)

		if ((!mediadorInstance.activo) && (mediadorInstance.usuario == usuarioService.usuarioActual())) {
			redirect controller:"red", action:"revisarRol",  method:"GET"
			return
		}
		flash.message = "Mediador ${mediadorInstance.usuario}-${mediadorInstance.usuario.dni} actualizado"
		redirect action:"index", params: ['cursoId': params.cursoId], method:"GET"
		return
	}

	protected void notFound() {
		flash.message = "No se encuentra ese mediador"
		redirect action: "index", params: ['cursoId': params.cursoId], method: "GET"
	}
}
