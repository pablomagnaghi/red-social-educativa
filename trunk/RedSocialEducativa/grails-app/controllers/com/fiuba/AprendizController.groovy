package com.fiuba

import static org.springframework.http.HttpStatus.*
//import grails.transaction.Transactional

//@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class AprendizController {

	// TODO
	// metodos para ABM aprendices del menu de mediador
    // static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def cursoId 
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		
		println "index aprendiz"
		println params
		
		if (params.id)
			cursoId = params.id
			
		[aprendizInstanceList: Aprendiz.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]), 
			aprendizInstanceCount: Aprendiz.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId]
    }

    def show(Aprendiz aprendizInstance) {
        respond aprendizInstance
    }

    def create() {
        respond new Aprendiz(params), model:[cursoId: cursoId]
    }

    //@Transactional
    def save(Aprendiz aprendizInstance) {
        if (aprendizInstance == null) {
            notFound()
            return
        }

        if (aprendizInstance.hasErrors()) {
            respond aprendizInstance.errors, view:'create'
            return
        }

		println "save"
		
		def curso = Curso.get(aprendizInstance.curso.id)
		
		def usuario = Usuario.get(aprendizInstance.usuario.id)
		
		println "curso: ${curso}"
		println "usuario: ${usuario}"
		
		def mediador = Mediador.findByUsuarioAndCurso(usuario, curso)
		def aprendiz = Aprendiz.findByUsuarioAndCurso(usuario, curso)
		
		if (mediador) {
			flash.message = "El miembro ${usuario} ya es mediador en el curso ${curso}. No puede ser aprendiz en el mismo"
			redirect action: "create"
			return
		}
		
		if (aprendiz) {
			flash.message = "${aprendiz} ya es aprendiz en el curso ${curso}"
			redirect action: "create"
			return
		}

        aprendizInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'aprendizInstance.label', default: 'Aprendiz'), aprendizInstance.id])
                redirect aprendizInstance
            }
            '*' { respond aprendizInstance, [status: CREATED] }
        }
    }

    //@Transactional
    def delete(Aprendiz aprendizInstance) {

        if (aprendizInstance == null) {
            notFound()
            return
        }

        aprendizInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Aprendiz.label', default: 'Aprendiz'), aprendizInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'aprendizInstance.label', default: 'Aprendiz'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
