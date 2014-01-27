package com.fiuba

import static org.springframework.http.HttpStatus.*

import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class TemaController {

	def springSecurityService
	
	private usuarioActual() {
		if (springSecurityService.principal.enabled)
			return Usuario.get(springSecurityService.principal.id)
		else
			return null
	}
	
	def cursoId
	def temaId
	
	def general() {
		params.max = 5
		
		println "general tema: params"
		println params
				
		cursoId = params.cursoId
		temaId = params.id
		//contenido
		//material tema
		//foro
		
		[contenidos: Contenido.findAllByTema(Tema.get(temaId)),
			materiales: MaterialTema.findAllByTema(Tema.get(temaId)),
			cursoId: cursoId, temaId: temaId, tema: Tema.get(temaId)]
	}
	
	// TODO metodos para el ABM temas del menu mediador
	
    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		
		println "index tema"
		println params
		
		cursoId = params.cursoId
		
		[temaInstanceList: Tema.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			temaInstanceCount: Tema.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId]
    }

    def show(Tema temaInstance) {
		println "tema show params: ${params}"
        respond temaInstance, model:[cursoId: cursoId]
    }

    def create() {
		cursoId = params.cursoId
        respond new Tema(params), params:['cursoId': cursoId], model: [cursoId: cursoId]
    }

    @Transactional
    def save(Tema temaInstance) {
        if (temaInstance == null) {
            notFound()
            return
        }
		
		println "params save: ${params}"
		
		cursoId = temaInstance.curso.id
		
		def curso = Curso.get(cursoId)
		
		//println curso
		
		/*
		temaInstance.foro = new ForoTema(nombre: "Foro del tema ${temaInstance} del curso ${Curso.get(cursoId)}")

        if (temaInstance.hasErrors()) {
            respond temaInstance.errors, view: "create", params:['cursoId': cursoId], model: [cursoId: cursoId]
            return
        }*/
		
		def temaExistente = Tema.findByCursoAndTitulo(curso, temaInstance.titulo)
			
		println "tema existente"
		println temaExistente
			
			
		if (temaExistente) {
			println "tema existe"
			flash.message = "Ya existe el tema ${temaInstance.titulo} del curso ${curso}"
			redirect action: "create", params:['cursoId': cursoId]
			return
		}
		
        //temaInstance.save flush:true

		
		temaInstance.foro = new ForoTema(nombre: "Foro del tema ${temaInstance} del curso ${curso}")
		
		if (! temaInstance.save(flush: true)) {
			respond temaInstance.errors, view: "create", params:['cursoId': cursoId], model: [cursoId: cursoId]
			return
		}	
		
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
