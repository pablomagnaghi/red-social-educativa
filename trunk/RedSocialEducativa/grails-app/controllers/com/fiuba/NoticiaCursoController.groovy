package com.fiuba

import static org.springframework.http.HttpStatus.*
//import grails.transaction.Transactional

//@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class NoticiaCursoController {

	def springSecurityService
	
	private usuarioActual() {
		return Usuario.get(springSecurityService.principal.id)
	}
	
	def cursoId
	
	// TODO
	// metodos para el ABM cartelera del curso en menu del mediador
    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = 2/* Math.min(max ?: 10, 100)*/

		println "index noticiaCurso"
		println params
		
		if (params.id)
			cursoId = params.id
			
		[noticiaCursoInstanceList: NoticiaCurso.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			noticiaCursoInstanceCount: NoticiaCurso.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId]
    }

    def show(NoticiaCurso noticiaCursoInstance) {
        respond noticiaCursoInstance
    }

    def create() {
        respond new NoticiaCurso(params), model:[cursoId: cursoId, usuario: usuarioActual()]
    }

    //@Transactional
    def save(NoticiaCurso noticiaCursoInstance) {
        if (noticiaCursoInstance == null) {
            notFound()
            return
        }

        if (noticiaCursoInstance.hasErrors()) {
            respond noticiaCursoInstance.errors, view:'create'
            return
        }
		
		def noticiaCursoExistente = NoticiaCurso.findByCursoAndTitulo(Curso.get(noticiaCursoInstance.curso.id), 
			noticiaCursoInstance.titulo)
		println noticiaCursoExistente
		
		if (noticiaCursoExistente) {
			flash.message = "Ya existe la noticia ${noticiaCursoInstance.titulo} del curso ${Curso.get(noticiaCursoInstance.curso.id)}"
			redirect action: "create"
			return
		}

        noticiaCursoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'noticiaCursoInstance.label', default: 'NoticiaCurso'), noticiaCursoInstance.id])
                redirect noticiaCursoInstance
            }
            '*' { respond noticiaCursoInstance, [status: CREATED] }
        }
    }

    def edit(NoticiaCurso noticiaCursoInstance) {
        respond noticiaCursoInstance, model:[cursoId: cursoId, usuario: usuarioActual()]
    }

    //@Transactional
    def update(NoticiaCurso noticiaCursoInstance) {
        if (noticiaCursoInstance == null) {
            notFound()
            return
        }

        if (noticiaCursoInstance.hasErrors()) {
            respond noticiaCursoInstance.errors, view:'edit'
            return
        }

        noticiaCursoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'NoticiaCurso.label', default: 'NoticiaCurso'), noticiaCursoInstance.id])
                redirect noticiaCursoInstance
            }
            '*'{ respond noticiaCursoInstance, [status: OK] }
        }
    }

    //@Transactional
    def delete(NoticiaCurso noticiaCursoInstance) {

        if (noticiaCursoInstance == null) {
            notFound()
            return
        }

        noticiaCursoInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'NoticiaCurso.label', default: 'NoticiaCurso'), noticiaCursoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'noticiaCursoInstance.label', default: 'NoticiaCurso'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
