package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CuatrimestreController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Cuatrimestre.list(params), model:[cuatrimestreInstanceCount: Cuatrimestre.count()]
    }

    def show(Cuatrimestre cuatrimestreInstance) {
        respond cuatrimestreInstance
    }

    def create() {
        respond new Cuatrimestre(params)
    }

    @Transactional
    def save(Cuatrimestre cuatrimestreInstance) {
        if (cuatrimestreInstance == null) {
            notFound()
            return
        }

        if (cuatrimestreInstance.hasErrors()) {
            respond cuatrimestreInstance.errors, view:'create'
            return
        }

        cuatrimestreInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cuatrimestreInstance.label', default: 'Cuatrimestre'), cuatrimestreInstance.id])
                redirect cuatrimestreInstance
            }
            '*' { respond cuatrimestreInstance, [status: CREATED] }
        }
    }

    def edit(Cuatrimestre cuatrimestreInstance) {
        respond cuatrimestreInstance
    }

    @Transactional
    def update(Cuatrimestre cuatrimestreInstance) {
        if (cuatrimestreInstance == null) {
            notFound()
            return
        }

        if (cuatrimestreInstance.hasErrors()) {
            respond cuatrimestreInstance.errors, view:'edit'
            return
        }

        cuatrimestreInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Cuatrimestre.label', default: 'Cuatrimestre'), cuatrimestreInstance.id])
                redirect cuatrimestreInstance
            }
            '*'{ respond cuatrimestreInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Cuatrimestre cuatrimestreInstance) {

        if (cuatrimestreInstance == null) {
            notFound()
            return
        }

        cuatrimestreInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Cuatrimestre.label', default: 'Cuatrimestre'), cuatrimestreInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuatrimestreInstance.label', default: 'Cuatrimestre'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
