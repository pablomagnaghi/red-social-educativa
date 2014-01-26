package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MaterialContenidoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MaterialContenido.list(params), model:[materialContenidoInstanceCount: MaterialContenido.count()]
    }

    def show(MaterialContenido materialContenidoInstance) {
        respond materialContenidoInstance
    }

    def create() {
        respond new MaterialContenido(params)
    }

    @Transactional
    def save(MaterialContenido materialContenidoInstance) {
        if (materialContenidoInstance == null) {
            notFound()
            return
        }

        if (materialContenidoInstance.hasErrors()) {
            respond materialContenidoInstance.errors, view:'create'
            return
        }

        materialContenidoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'materialContenidoInstance.label', default: 'MaterialContenido'), materialContenidoInstance.id])
                redirect materialContenidoInstance
            }
            '*' { respond materialContenidoInstance, [status: CREATED] }
        }
    }

    def edit(MaterialContenido materialContenidoInstance) {
        respond materialContenidoInstance
    }

    @Transactional
    def update(MaterialContenido materialContenidoInstance) {
        if (materialContenidoInstance == null) {
            notFound()
            return
        }

        if (materialContenidoInstance.hasErrors()) {
            respond materialContenidoInstance.errors, view:'edit'
            return
        }

        materialContenidoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MaterialContenido.label', default: 'MaterialContenido'), materialContenidoInstance.id])
                redirect materialContenidoInstance
            }
            '*'{ respond materialContenidoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(MaterialContenido materialContenidoInstance) {

        if (materialContenidoInstance == null) {
            notFound()
            return
        }

        materialContenidoInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MaterialContenido.label', default: 'MaterialContenido'), materialContenidoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialContenidoInstance.label', default: 'MaterialContenido'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
