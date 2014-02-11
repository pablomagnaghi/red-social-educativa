package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class MediadorController {

	def mediadorService
	def aprendizService

	// Metodos nuevos

	@Secured('permitAll')
	def activarAprendiz() {
		def aprendiz = Aprendiz.get(params.id)
		println "activarAprendiz params: ${params}"
		//println "${aprendiz}, ${aprendiz.id}, ${aprendiz.participa}"

		aprendiz.participa = true
		def mail = aprendiz.usuario.email
		def username = aprendiz.usuario.username
		if (aprendiz.hasErrors()){
			println aprendiz.errors
			flash.message = "Problemas con el aprendiz"
			redirect(controller: "curso", action: "menuMediador", params: params)
			return
		} else {
			aprendiz.save();
			sendMail {
				to mail
				subject Utilidades.TITULO_RED
				body "Bienvenido aprendiz ${username} al curso ${aprendiz.cuatrimestre.curso} de la Red Social Educativa FIUBA 2014"
			}
			flash.message = "Autorización enviada para el aprendiz ${username} del curso ${aprendiz.cuatrimestre.curso}"
		}

		redirect(controller: "aprendiz", action: "index", params: params)
	}

	// TODO
	// Metodos para el ABM de administrador

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	@Secured("hasRole('ROL_ADMIN')")
	def index(Integer max) {
		params.max = Utilidades.MAX_PARAMS
		respond Mediador.list(params), model:[mediadorInstanceCount: Mediador.count()]
	}

	@Secured("hasRole('ROL_ADMIN')")
	def show(Mediador mediadorInstance) {
		respond mediadorInstance
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
		redirect mediadorInstance
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
