package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class MaterialGrupoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MaterialGrupo.list(params), model:[materialGrupoInstanceCount: MaterialGrupo.count()]
    }

    def show(MaterialGrupo materialGrupoInstance) {
        respond materialGrupoInstance
    }

    def create() {
        respond new MaterialGrupo(params)
    }

    @Transactional
    def save(MaterialGrupo materialGrupoInstance) {
        if (materialGrupoInstance == null) {
            notFound()
            return
        }

        if (materialGrupoInstance.hasErrors()) {
            respond materialGrupoInstance.errors, view:'create'
            return
        }

        materialGrupoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'materialGrupoInstance.label', default: 'MaterialGrupo'), materialGrupoInstance.id])
                redirect materialGrupoInstance
            }
            '*' { respond materialGrupoInstance, [status: CREATED] }
        }
    }

    def edit(MaterialGrupo materialGrupoInstance) {
        respond materialGrupoInstance
    }

    @Transactional
    def update(MaterialGrupo materialGrupoInstance) {
        if (materialGrupoInstance == null) {
            notFound()
            return
        }

        if (materialGrupoInstance.hasErrors()) {
            respond materialGrupoInstance.errors, view:'edit'
            return
        }

        materialGrupoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MaterialGrupo.label', default: 'MaterialGrupo'), materialGrupoInstance.id])
                redirect materialGrupoInstance
            }
            '*'{ respond materialGrupoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(MaterialGrupo materialGrupoInstance) {

        if (materialGrupoInstance == null) {
            notFound()
            return
        }

        materialGrupoInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MaterialGrupo.label', default: 'MaterialGrupo'), materialGrupoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialGrupoInstance.label', default: 'MaterialGrupo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
