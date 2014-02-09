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
	def cuatrimestreId
	// TODO
	// metodos para el ABM cartelera del curso en menu del mediador
    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = 2/* Math.min(max ?: 10, 100)*/

		println "index noticiaCurso"
		println params
		
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
		def cuatrimestre = Cuatrimestre.get(cuatrimestreId)
			
		[noticiaCursoInstanceList: NoticiaCurso.findAllByCuatrimestre(cuatrimestre,[max: params.max, offset: params.offset]),
			noticiaCursoInstanceCount: NoticiaCurso.findAllByCuatrimestre(cuatrimestre).size(), 
			cursoId: cursoId, cuatrimestreId: cuatrimestreId]
    }

    def show(NoticiaCurso noticiaCursoInstance) {
		println "show params: ${params}"
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
        respond noticiaCursoInstance, model:[cursoId: cursoId, cuatrimestreId: cuatrimestreId]
    }

    def create() {
		
		println "create noticia curso params: ${params}"
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
		def mediadorId = Mediador.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId)).id
		
		println "usaurio actual id: ${mediadorId}"
		
        respond new NoticiaCurso(params), params:['cursoId': cursoId], model:[cursoId: cursoId, 
			cuatrimestreId: cuatrimestreId, mediadorId: mediadorId]
    }

    //@Transactional
    def save(NoticiaCurso noticiaCursoInstance) {
        if (noticiaCursoInstance == null) {
            notFound()
            return
        }
		
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId

        if (noticiaCursoInstance.hasErrors()) {
			def mediadorId = Mediador.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId)).id
            respond noticiaCursoInstance.errors, view:'create', params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId], 
				model: [cursoId: cursoId, cuatrimestreId: cuatrimestreId, mediadorId: mediadorId]
            return
        }

        noticiaCursoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'noticiaCursoInstance.label', default: 'NoticiaCurso'), noticiaCursoInstance.id])
                redirect action: "index", params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]
            }
            '*' { respond noticiaCursoInstance, [status: CREATED] }
        }
    }

	// TODO: revisar a fondo por si falta actualizar el cursoId
    def edit(NoticiaCurso noticiaCursoInstance) {
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
        respond noticiaCursoInstance, params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId], 
			model:[cursoId: cursoId, cuatrimestreId: cuatrimestreId, usuario: usuarioActual()]
    }

	// TODO revisar a fondo por si falta actualizar el cursoId
    //@Transactional
    def update(NoticiaCurso noticiaCursoInstance) {
		
		// TODO ver aca
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
		
        if (noticiaCursoInstance == null) {
            notFound()
            return
        }

        if (noticiaCursoInstance.hasErrors()) {
            respond noticiaCursoInstance.errors, view:'edit', params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId],
				model: [cursoId: cursoId, cuatrimestreId: cuatrimestreId]
            return
        }

        noticiaCursoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'NoticiaCurso.label', default: 'NoticiaCurso'), noticiaCursoInstance.id])
                respond noticiaCursoInstance, view:"show", params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId], 
					model:[cursoId: cursoId, cuatrimestreId: cuatrimestreId]
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
		cuatrimestreId = params.cuatrimestreId
		
		println "noticia curso delete, params: ${params}"
		
        noticiaCursoInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'NoticiaCurso.label', default: 'NoticiaCurso'), noticiaCursoInstance.id])
                redirect action:"index", params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId], method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'noticiaCursoInstance.label', default: 'NoticiaCurso'), params.id])
                redirect action: "index", params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId], method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
