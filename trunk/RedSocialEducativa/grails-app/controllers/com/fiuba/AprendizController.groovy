package com.fiuba

import static org.springframework.http.HttpStatus.*
//import grails.transaction.Transactional

//@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class AprendizController {

	// TODO
	// metodos para ABM aprendices del menu de mediador
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Aprendiz.list(params), model:[aprendizInstanceCount: Aprendiz.count()]
    }

    def show(Aprendiz aprendizInstance) {
        respond aprendizInstance
    }

    def create() {
        respond new Aprendiz(params)
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

        aprendizInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'aprendizInstance.label', default: 'Aprendiz'), aprendizInstance.id])
                redirect aprendizInstance
            }
            '*' { respond aprendizInstance, [status: CREATED] }
        }
    }

    def edit(Aprendiz aprendizInstance) {
        respond aprendizInstance
    }

    //@Transactional
    def update(Aprendiz aprendizInstance) {
        if (aprendizInstance == null) {
            notFound()
            return
        }

        if (aprendizInstance.hasErrors()) {
            respond aprendizInstance.errors, view:'edit'
            return
        }

        aprendizInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Aprendiz.label', default: 'Aprendiz'), aprendizInstance.id])
                redirect aprendizInstance
            }
            '*'{ respond aprendizInstance, [status: OK] }
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
