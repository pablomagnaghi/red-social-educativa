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
		
		cursoId = params.cursoId
		
		[temaInstanceList: Tema.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			temaInstanceCount: Tema.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId]
    }

    def show(Tema temaInstance) {
        respond temaInstance, model:[cursoId: cursoId]
    }

    def create() {
		cursoId = params.cursoId
        respond new Tema(params), params:['cursoId': cursoId], model: [cursoId: cursoId]
    }

    //@Transactional
    def save(Tema temaInstance) {
        if (temaInstance == null) {
            notFound()
            return
        }
		
		cursoId = temaInstance.curso.id
		temaInstance.foro = new ForoTema(nombre: "Foro del tema ${temaInstance} del curso ${Curso.get(cursoId)}")

        if (temaInstance.hasErrors()) {
            respond temaInstance.errors, view: "create", params:['cursoId': cursoId], model: [cursoId: cursoId]
            return
        }

		def temaExistente = Tema.findByCursoAndTitulo(Curso.get(cursoId), temaInstance.titulo)
		println temaExistente
		
		if (temaExistente) {
			flash.message = "Ya existe el tema ${temaInstance.titulo} del curso ${Curso.get(cursoId)}"
			redirect action: "create", params:['cursoId': cursoId]
			return
		}
		
        temaInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'temaInstance.label', default: 'Tema'), temaInstance.id])
                redirect action: "index", params:['cursoId': cursoId]
            }
            '*' { respond temaInstance, [status: CREATED] }
        }
    }

    def edit(Tema temaInstance) {
		cursoId = params.cursoId
        respond temaInstance, params:['cursoId': cursoId], model: [cursoId: cursoId]
    }

    //@Transactional
    def update(Tema temaInstance) {

        if (temaInstance == null) {
            notFound()
            return
        }
		
        if (temaInstance.hasErrors()) {
            respond temaInstance.errors, view: "edit"//, params:['cursoId': cursoId]
            return
        }

        temaInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Tema.label', default: 'Tema'), temaInstance.id])
				println "request with format update"
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

		cursoId = params.cursoId
		
        temaInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Tema.label', default: 'Tema'), temaInstance.id])
                redirect action:"index", params:['cursoId': cursoId], method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'temaInstance.label', default: 'Tema'), params.id])
                redirect action: "index", params:['cursoId': cursoId], method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
