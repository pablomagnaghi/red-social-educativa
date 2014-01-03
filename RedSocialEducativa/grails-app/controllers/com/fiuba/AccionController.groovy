package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AccionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Accion.list(params), model:[accionInstanceCount: Accion.count()]
    }

    def show(Accion accionInstance) {
        respond accionInstance
    }

    def create() {
        respond new Accion(params)
    }

    @Transactional
    def save(Accion accionInstance) {
        if (accionInstance == null) {
            notFound()
            return
        }

        if (accionInstance.hasErrors()) {
            respond accionInstance.errors, view:'create'
            return
        }

        accionInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'accionInstance.label', default: 'Accion'), accionInstance.id])
                redirect accionInstance
            }
            '*' { respond accionInstance, [status: CREATED] }
        }
    }

    def edit(Accion accionInstance) {
        respond accionInstance
    }

    @Transactional
    def update(Accion accionInstance) {
        if (accionInstance == null) {
            notFound()
            return
        }

        if (accionInstance.hasErrors()) {
            respond accionInstance.errors, view:'edit'
            return
        }

        accionInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Accion.label', default: 'Accion'), accionInstance.id])
                redirect accionInstance
            }
            '*'{ respond accionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Accion accionInstance) {

        if (accionInstance == null) {
            notFound()
            return
        }

        accionInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Accion.label', default: 'Accion'), accionInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'accionInstance.label', default: 'Accion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
