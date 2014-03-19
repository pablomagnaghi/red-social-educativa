package com.encuesta



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PreguntaPuntajeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PreguntaPuntaje.list(params), model:[preguntaPuntajeInstanceCount: PreguntaPuntaje.count()]
    }

    def show(PreguntaPuntaje preguntaPuntajeInstance) {
        respond preguntaPuntajeInstance
    }

    def create() {
        respond new PreguntaPuntaje(params)
    }

    @Transactional
    def save(PreguntaPuntaje preguntaPuntajeInstance) {
        if (preguntaPuntajeInstance == null) {
            notFound()
            return
        }

        if (preguntaPuntajeInstance.hasErrors()) {
            respond preguntaPuntajeInstance.errors, view:'create'
            return
        }

        preguntaPuntajeInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'preguntaPuntajeInstance.label', default: 'PreguntaPuntaje'), preguntaPuntajeInstance.id])
                redirect preguntaPuntajeInstance
            }
            '*' { respond preguntaPuntajeInstance, [status: CREATED] }
        }
    }

    def edit(PreguntaPuntaje preguntaPuntajeInstance) {
        respond preguntaPuntajeInstance
    }

    @Transactional
    def update(PreguntaPuntaje preguntaPuntajeInstance) {
        if (preguntaPuntajeInstance == null) {
            notFound()
            return
        }

        if (preguntaPuntajeInstance.hasErrors()) {
            respond preguntaPuntajeInstance.errors, view:'edit'
            return
        }

        preguntaPuntajeInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'PreguntaPuntaje.label', default: 'PreguntaPuntaje'), preguntaPuntajeInstance.id])
                redirect preguntaPuntajeInstance
            }
            '*'{ respond preguntaPuntajeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(PreguntaPuntaje preguntaPuntajeInstance) {

        if (preguntaPuntajeInstance == null) {
            notFound()
            return
        }

        preguntaPuntajeInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'PreguntaPuntaje.label', default: 'PreguntaPuntaje'), preguntaPuntajeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'preguntaPuntajeInstance.label', default: 'PreguntaPuntaje'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
