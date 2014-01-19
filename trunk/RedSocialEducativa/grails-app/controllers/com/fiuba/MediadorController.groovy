package com.fiuba

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class MediadorController {

	// Metodos nuevos
	
	def activarAprendiz() {
		def aprendiz = Aprendiz.get(params.aprendizId)
		println "activarAprendiz params: ${params}"
		//println "${aprendiz}, ${aprendiz.id}, ${aprendiz.participa}"
	
		aprendiz.participa = true
		def mail = aprendiz.usuario.email
		def username = aprendiz.usuario.username
		if (aprendiz.hasErrors()){
			println aprendiz.errors
			flash.message = "Problemas con el aprendiz"
			redirect(controller: "curso", action: "menuMediador")
			return
		} else {
			aprendiz.save();
			sendMail {
				to mail
				subject "Red Social Educativa"
				body "Bienvenido aprendiz ${username} al curso ${aprendiz.curso} de la Red Social Educativa FIUBA 2014"
			}
			flash.message = "Autorización enviada para el aprendiz ${username} del curso ${aprendiz.curso}"
		}
		
		redirect(controller: "curso", action: "menuMediador", params: params)
	}
	
	// TODO
	// Metodos para el ABM de administrador
	
    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Mediador.list(params), model:[mediadorInstanceCount: Mediador.count()]
    }

    def show(Mediador mediadorInstance) {
		println "show"
		
		println params
		
        respond mediadorInstance
    }

	// Crea un mediador vacio que se carga con los parametros en el _form
    def create() {
        respond new Mediador(params)
    }

    @Transactional
    def save(Mediador mediadorInstance) {

        if (mediadorInstance == null) {
            notFound()
            return
        }

        if (mediadorInstance.hasErrors()) {
            respond mediadorInstance.errors, view:'create'
            return
        }
		
		println "save"
		
		def curso = Curso.get(mediadorInstance.curso.id)
		
		def usuario = Usuario.get(mediadorInstance.usuario.id)
		
		println "curso: ${curso}"
		println "usuario: ${usuario}"
		
		def mediador = Mediador.findByUsuarioAndCurso(usuario, curso)
		def aprendiz = Aprendiz.findByUsuarioAndCurso(usuario, curso)
		
		if (mediador) {
			flash.message = "${mediador} ya es mediador en el curso ${curso}"
			redirect action: "create"
			return
		}
		
		if (aprendiz) {
			flash.message = "El miembro ${usuario} ya es aprendiz en el curso ${curso}. No puede ser mediador en el mismo"
			redirect action: "create"
			return
		}

        mediadorInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'mediadorInstance.label', default: 'Mediador'), mediadorInstance.id])
                redirect mediadorInstance
            }
            '*' { respond mediadorInstance, [status: CREATED] }
        }
    }

    @Transactional
    def delete(Mediador mediadorInstance) {
		
		println "delete"

		
		def curso = Curso.get(mediadorInstance.curso.id)
		
		def usuario = Usuario.get(mediadorInstance.usuario.id)
		
		println "curso: ${curso}"
		println "usuario: ${usuario}"
		
		
        if (mediadorInstance == null) {
            notFound()
            return
        }

        mediadorInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Mediador.label', default: 'Mediador'), mediadorInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'mediadorInstance.label', default: 'Mediador'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
