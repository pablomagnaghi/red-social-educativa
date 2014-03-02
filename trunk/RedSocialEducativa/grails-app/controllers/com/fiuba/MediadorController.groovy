package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class MediadorController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def mediadorService
	def aprendizService

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

	@Secured("hasRole('ROL_ADMIN')")
	def index(Integer max) {
		params.max = 100 //Utilidades.MAX_PARAMS
		respond Mediador.list(params)
	}

	@Secured("hasRole('ROL_ADMIN')")
	def create() {
		println mediadorService.obtenerCandidatos()
		respond new Mediador(params), model: [usuarios: mediadorService.obtenerCandidatos()]
	}

	@Secured("hasRole('ROL_ADMIN')")
	def save(Mediador mediadorInstance) {

		if (mediadorInstance == null) {
			notFound()
			return
		}

		if (mediadorService.existe(mediadorInstance)) {
			flash.message = "${mediadorInstance} ya es mediador en el curso ${mediadorInstance.curso}"
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

		flash.message = message(code: 'default.created.message', args: [message(code: 'mediadorInstance.label', default: 'Mediador'), mediadorInstance.id])
		redirect action:"index"
	}

	@Secured("hasRole('ROL_ADMIN')")
	def delete(Mediador mediadorInstance) {

		if (mediadorInstance == null) {
			notFound()
			return
		}

		mediadorService.eliminar(mediadorInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'Mediador.label', default: 'Mediador'), mediadorInstance.id])
		redirect action:"index", method:"GET"
	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'mediadorInstance.label', default: 'Mediador'), params.id])
		redirect action: "index", method: "GET"
	}
}
