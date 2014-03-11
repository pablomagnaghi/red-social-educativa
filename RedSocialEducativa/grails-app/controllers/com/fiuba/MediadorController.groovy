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
	@Secured("hasRole('ROL_ADMIN')")
	def index(Integer max) {
		params.max = Utilidades.MAX_PARAMS
		[mediadorInstanceList: mediadorService.obtenerMediadoresOrdenados()]
	}

	@Secured("hasRole('ROL_ADMIN')")
	def create() {
		respond new Mediador(params)
	}

	@Secured("hasRole('ROL_ADMIN')")
	def save(Mediador mediadorInstance) {
		if (mediadorInstance == null) {
			notFound()
			return
		}
		if (mediadorService.existe(mediadorInstance)) {
			flash.message = "${mediadorInstance.usuario} ya es mediador en el curso ${mediadorInstance.curso}"
			redirect action: "create"
			return
		}
		if (aprendizService.obtenerPorCurso(mediadorInstance.usuario.id, mediadorInstance.curso.id)) {
			flash.message = "El miembro ${mediadorInstance.usuario} ya es aprendiz en el curso ${mediadorInstance.curso}. No puede ser mediador"
			redirect action: "create"
			return
		}
		if (!mediadorService.guardar(mediadorInstance)) {
			respond mediadorInstance, view:'create'
			return
		}
		flash.message = "Mediador ${mediadorInstance.usuario}-${mediadorInstance.usuario.dni} creado"
		redirect action:"index"
	}
	
	@Secured("hasRole('ROL_ADMIN')")
	def editarJerarquia(Mediador mediadorInstance) {
		respond mediadorInstance
	}
	
	@Secured("hasRole('ROL_ADMIN')")
	def actualizarJerarquia(Mediador mediadorInstance) {
		if (mediadorInstance == null) {
			notFound()
			return
		}
		
		if (!mediadorService.guardar(mediadorInstance)) {
			flash.message = "Problemas al actualizar la jerarquia"
			redirect action: "editarJerarquia"
			return
		}
		flash.message = "Mediador ${mediadorInstance.usuario}-${mediadorInstance.usuario.dni} actualizado"
		redirect action:"index"
	}
	
	@Secured("hasRole('ROL_ADMIN')")
	def cambiarEstado(Mediador mediadorInstance) {
		if (mediadorInstance == null) {
			notFound()
			return
		}
		mediadorInstance.activo = mediadorInstance.activo ? false : true
		if (!mediadorService.guardar(mediadorInstance)) {
			flash.message = "Problemas al cambiar el estado"
			redirect action:"index", method:"GET"
			return
		}
		mediadorService.notificar(mediadorInstance)
		// TODO veeerrrrrrrrrrr
		if (!params.cursoId) {
			flash.message = "Mediador ${mediadorInstance.usuario}-${mediadorInstance.usuario.dni} actualizado"
			redirect action:"index", method:"GET"
			return
		} 
		flash.message = "Ha dejado de participar en el curso ${mediadorInstance.curso}"
		redirect controller:"red", action:"revisarRolEnCurso", method:"GET"
		
	}

	protected void notFound() {
		flash.message = "No se encuentra ese mediador"
		redirect action: "index", method: "GET"
	}
}
