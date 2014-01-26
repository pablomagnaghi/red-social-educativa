package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MaterialTemaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MaterialTema.list(params), model:[materialTemaInstanceCount: MaterialTema.count()]
    }

    def show(MaterialTema materialTemaInstance) {
        respond materialTemaInstance
    }

    def create() {
        respond new MaterialTema(params)
    }

    @Transactional
    def save(MaterialTema materialTemaInstance) {
        if (materialTemaInstance == null) {
            notFound()
            return
        }

        if (materialTemaInstance.hasErrors()) {
            respond materialTemaInstance.errors, view:'create'
            return
        }

        materialTemaInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'materialTemaInstance.label', default: 'MaterialTema'), materialTemaInstance.id])
                redirect materialTemaInstance
            }
            '*' { respond materialTemaInstance, [status: CREATED] }
        }
    }

    def edit(MaterialTema materialTemaInstance) {
        respond materialTemaInstance
    }

    @Transactional
    def update(MaterialTema materialTemaInstance) {
        if (materialTemaInstance == null) {
            notFound()
            return
        }

        if (materialTemaInstance.hasErrors()) {
            respond materialTemaInstance.errors, view:'edit'
            return
        }

        materialTemaInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MaterialTema.label', default: 'MaterialTema'), materialTemaInstance.id])
                redirect materialTemaInstance
            }
            '*'{ respond materialTemaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(MaterialTema materialTemaInstance) {

        if (materialTemaInstance == null) {
            notFound()
            return
        }

        materialTemaInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MaterialTema.label', default: 'MaterialTema'), materialTemaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialTemaInstance.label', default: 'MaterialTema'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
