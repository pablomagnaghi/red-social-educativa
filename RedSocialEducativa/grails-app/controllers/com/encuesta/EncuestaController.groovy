package com.encuesta



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EncuestaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Encuesta.list(params), model:[encuestaInstanceCount: Encuesta.count()]
    }

    def show(Encuesta encuestaInstance) {
        respond encuestaInstance
    }

    def create() {
        respond new Encuesta(params)
    }

    @Transactional
    def save(Encuesta encuestaInstance) {
        if (encuestaInstance == null) {
            notFound()
            return
        }

        if (encuestaInstance.hasErrors()) {
            respond encuestaInstance.errors, view:'create'
            return
        }

        encuestaInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'encuestaInstance.label', default: 'Encuesta'), encuestaInstance.id])
                redirect encuestaInstance
            }
            '*' { respond encuestaInstance, [status: CREATED] }
        }
    }

    def edit(Encuesta encuestaInstance) {
        respond encuestaInstance
    }

    @Transactional
    def update(Encuesta encuestaInstance) {
        if (encuestaInstance == null) {
            notFound()
            return
        }

        if (encuestaInstance.hasErrors()) {
            respond encuestaInstance.errors, view:'edit'
            return
        }

        encuestaInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Encuesta.label', default: 'Encuesta'), encuestaInstance.id])
                redirect encuestaInstance
            }
            '*'{ respond encuestaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Encuesta encuestaInstance) {

        if (encuestaInstance == null) {
            notFound()
            return
        }

        encuestaInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Encuesta.label', default: 'Encuesta'), encuestaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'encuestaInstance.label', default: 'Encuesta'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
