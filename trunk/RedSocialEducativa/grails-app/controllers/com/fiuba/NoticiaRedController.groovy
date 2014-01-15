package com.fiuba

import static org.springframework.http.HttpStatus.*

//import grails.transaction.Transactional

//@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class NoticiaRedController {

    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond NoticiaRed.list(params), model:[noticiaRedInstanceCount: NoticiaRed.count()]
    }

    def show(NoticiaRed noticiaRedInstance) {
        respond noticiaRedInstance
    }

    def create() {
        respond new NoticiaRed(params)
    }

    // @Transactional
    def save(NoticiaRed noticiaRedInstance) {
        if (noticiaRedInstance == null) {
            notFound()
            return
        }

        if (noticiaRedInstance.hasErrors()) {
            respond noticiaRedInstance.errors, view:'create'
            return
        }

        noticiaRedInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'noticiaRedInstance.label', default: 'NoticiaRed'), noticiaRedInstance.id])
                redirect noticiaRedInstance
            }
            '*' { respond noticiaRedInstance, [status: CREATED] }
        }
    }

    def edit(NoticiaRed noticiaRedInstance) {
        respond noticiaRedInstance
    }

    // @Transactional
    def update(NoticiaRed noticiaRedInstance) {
        if (noticiaRedInstance == null) {
            notFound()
            return
        }

        if (noticiaRedInstance.hasErrors()) {
            respond noticiaRedInstance.errors, view:'edit'
            return
        }

        noticiaRedInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'NoticiaRed.label', default: 'NoticiaRed'), noticiaRedInstance.id])
                redirect noticiaRedInstance
            }
            '*'{ respond noticiaRedInstance, [status: OK] }
        }
    }

    // @Transactional
    def delete(NoticiaRed noticiaRedInstance) {

        if (noticiaRedInstance == null) {
            notFound()
            return
        }

        noticiaRedInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'NoticiaRed.label', default: 'NoticiaRed'), noticiaRedInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'noticiaRedInstance.label', default: 'NoticiaRed'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
