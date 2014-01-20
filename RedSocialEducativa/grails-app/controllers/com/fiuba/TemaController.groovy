package com.fiuba

import static org.springframework.http.HttpStatus.*

//import grails.transaction.Transactional

//@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class TemaController {

	// TODO metodos para el ABM temas del menu mediador
	
    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def cursoId
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		
		println "index tema"
		println params
		
		if (params.id)
		cursoId = params.id
		
		[temaInstanceList: Tema.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			temaInstanceCount: Tema.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId]
    }

    def show(Tema temaInstance) {
        respond temaInstance
    }

    def create() {
        respond new Tema(params), model:[cursoId: cursoId]
    }

    //@Transactional
    def save(Tema temaInstance) {
        if (temaInstance == null) {
            notFound()
            return
        }

		temaInstance.foro = new ForoTema(nombre: "Foro del tema ${temaInstance} del curso ${Curso.get(temaInstance.curso.id)}")

        if (temaInstance.hasErrors()) {
            respond temaInstance.errors, view:'create'
            return
        }

        temaInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'temaInstance.label', default: 'Tema'), temaInstance.id])
                redirect temaInstance
            }
            '*' { respond temaInstance, [status: CREATED] }
        }
    }

    def edit(Tema temaInstance) {
        respond temaInstance, model:[cursoId: cursoId]
    }

    //@Transactional
    def update(Tema temaInstance) {
        if (temaInstance == null) {
            notFound()
            return
        }

        if (temaInstance.hasErrors()) {
            respond temaInstance.errors, view:'edit'
            return
        }

        temaInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Tema.label', default: 'Tema'), temaInstance.id])
                redirect temaInstance
            }
            '*'{ respond temaInstance, [status: OK] }
        }
    }

    //@Transactional
    def delete(Tema temaInstance) {

        if (temaInstance == null) {
            notFound()
            return
        }

        temaInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Tema.label', default: 'Tema'), temaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'temaInstance.label', default: 'Tema'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
