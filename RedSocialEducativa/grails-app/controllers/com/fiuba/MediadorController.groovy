package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MediadorController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Mediador.list(params), model:[mediadorInstanceCount: Mediador.count()]
    }

    def show(Mediador mediadorInstance) {
        respond mediadorInstance
    }

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

        mediadorInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'mediadorInstance.label', default: 'Mediador'), mediadorInstance.id])
                redirect mediadorInstance
            }
            '*' { respond mediadorInstance, [status: CREATED] }
        }
    }

    def edit(Mediador mediadorInstance) {
        respond mediadorInstance
    }

    @Transactional
    def update(Mediador mediadorInstance) {
        if (mediadorInstance == null) {
            notFound()
            return
        }

        if (mediadorInstance.hasErrors()) {
            respond mediadorInstance.errors, view:'edit'
            return
        }

        mediadorInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Mediador.label', default: 'Mediador'), mediadorInstance.id])
                redirect mediadorInstance
            }
            '*'{ respond mediadorInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Mediador mediadorInstance) {

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
