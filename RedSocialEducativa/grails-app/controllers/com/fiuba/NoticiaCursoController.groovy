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
		
		cursoId = params.cursoId
			
		[noticiaCursoInstanceList: NoticiaCurso.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			noticiaCursoInstanceCount: NoticiaCurso.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId]
    }

    def show(NoticiaCurso noticiaCursoInstance) {
		println "show params: ${params}"
        respond noticiaCursoInstance, model:[cursoId: cursoId]
    }

    def create() {
		
		println "create noticia curso params: ${params}"
		cursoId = params.cursoId
		def mediadorId = Mediador.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId)).id
		
		println "usaurio actual id: ${mediadorId}"
		
        respond new NoticiaCurso(params), params:['cursoId': cursoId], model:[cursoId: cursoId, mediadorId: mediadorId]
    }

    //@Transactional
    def save(NoticiaCurso noticiaCursoInstance) {
        if (noticiaCursoInstance == null) {
            notFound()
            return
        }
		
		cursoId = noticiaCursoInstance.curso.id
		/*
		println "save noticia curso"
		println "curso.id"
		println noticiaCursoInstance.curso.id
		println "texto"
		noticiaCursoInstance.texto
		println "titulo"
		noticiaCursoInstance.titulo
		println "responsable"
		noticiaCursoInstance.mediador.id
		*/
		// TODO
        if (noticiaCursoInstance.hasErrors()) {
			def mediadorId = Mediador.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId)).id
            respond noticiaCursoInstance.errors, view:'create', params:['cursoId': cursoId], 
				model: [cursoId: cursoId, mediadorId: mediadorId]
            return
        }
		
		def noticiaCursoExistente = NoticiaCurso.findByCursoAndTitulo(Curso.get(cursoId), 
			noticiaCursoInstance.titulo)
		println noticiaCursoExistente
		
		if (noticiaCursoExistente) {
			flash.message = "Ya existe la noticia ${noticiaCursoInstance.titulo} del curso ${Curso.get(cursoId)}"
			redirect action: "create", params:['cursoId': cursoId]
			return
		}

        noticiaCursoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'noticiaCursoInstance.label', default: 'NoticiaCurso'), noticiaCursoInstance.id])
                redirect action: "index", params:['cursoId': cursoId]
            }
            '*' { respond noticiaCursoInstance, [status: CREATED] }
        }
    }

	// TODO: revisar a fondo por si falta actualizar el cursoId
    def edit(NoticiaCurso noticiaCursoInstance) {
		cursoId = params.cursoId
        respond noticiaCursoInstance, params:['cursoId': cursoId], model:[cursoId: cursoId, usuario: usuarioActual()]
    }

	// TODO revisar a fondo por si falta actualizar el cursoId
    //@Transactional
    def update(NoticiaCurso noticiaCursoInstance) {
		
		// TODO ver aca
		cursoId = params.cursoId
		
        if (noticiaCursoInstance == null) {
            notFound()
            return
        }

        if (noticiaCursoInstance.hasErrors()) {
            respond noticiaCursoInstance.errors, view:'edit', params:['cursoId': cursoId],
				model: [cursoId: cursoId]
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
		
		cursoId = params.cursoId

        noticiaCursoInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'NoticiaCurso.label', default: 'NoticiaCurso'), noticiaCursoInstance.id])
                redirect action:"index", params:['cursoId': cursoId], method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'noticiaCursoInstance.label', default: 'NoticiaCurso'), params.id])
                redirect action: "index", params:['cursoId': cursoId], method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
