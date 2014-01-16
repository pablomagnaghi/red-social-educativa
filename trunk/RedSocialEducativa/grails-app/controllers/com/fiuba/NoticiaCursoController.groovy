package com.fiuba

import static org.springframework.http.HttpStatus.*
//import grails.transaction.Transactional

//@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class NoticiaCursoController {

	// TODO
	// metodos para el ABM cartelera del curso en menu del mediador
    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond NoticiaCurso.list(params), model:[noticiaCursoInstanceCount: NoticiaCurso.count()]
    }

    def show(NoticiaCurso noticiaCursoInstance) {
        respond noticiaCursoInstance
    }

    def create() {
        respond new NoticiaCurso(params)
    }

    //@Transactional
    def save(NoticiaCurso noticiaCursoInstance) {
        if (noticiaCursoInstance == null) {
            notFound()
            return
        }

        if (noticiaCursoInstance.hasErrors()) {
            respond noticiaCursoInstance.errors, view:'create'
            return
        }

        noticiaCursoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'noticiaCursoInstance.label', default: 'NoticiaCurso'), noticiaCursoInstance.id])
                redirect noticiaCursoInstance
            }
            '*' { respond noticiaCursoInstance, [status: CREATED] }
        }
    }

    def edit(NoticiaCurso noticiaCursoInstance) {
        respond noticiaCursoInstance
    }

    //@Transactional
    def update(NoticiaCurso noticiaCursoInstance) {
        if (noticiaCursoInstance == null) {
            notFound()
            return
        }

        if (noticiaCursoInstance.hasErrors()) {
            respond noticiaCursoInstance.errors, view:'edit'
            return
        }

        noticiaCursoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'NoticiaCurso.label', default: 'NoticiaCurso'), noticiaCursoInstance.id])
                redirect noticiaCursoInstance
            }
            '*'{ respond noticiaCursoInstance, [status: OK] }
        }
    }

    //@Transactional
    def delete(NoticiaCurso noticiaCursoInstance) {

        if (noticiaCursoInstance == null) {
            notFound()
            return
        }

        noticiaCursoInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'NoticiaCurso.label', default: 'NoticiaCurso'), noticiaCursoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'noticiaCursoInstance.label', default: 'NoticiaCurso'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
