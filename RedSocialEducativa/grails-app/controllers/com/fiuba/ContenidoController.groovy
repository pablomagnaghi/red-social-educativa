package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class ContenidoController {
	
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
		
		params.max = 5 // Math.min(max ?: 10, 100)
		
		println "foro Tema general CURSOID: ${params.cursoId}"
		println "foro Tema general TEMAID: ${params.temaId}"
			
		cursoId = params.cursoId
		temaId = params.temaId
					
		def tema = Tema.get(temaId)
		
		[contenidos: Contenido.findAllByTema(tema, [max: params.max, offset: params.offset]),
			contenidosCant: Contenido.findAllByTema(tema).size(),
			tema: tema, cursoId: cursoId, temaId: temaId]
	}

	// metodos por defecto
			
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Contenido.list(params), model:[contenidoInstanceCount: Contenido.count()]
    }

    def show(Contenido contenidoInstance) {
		
		println "contenido tema show: cursoId: ${params.cursoId}, tema Id. ${params.temaId}"
		println "contenido Id: ${params.id}"
		
		cursoId = params.cursoId
		temaId = params.temaId

        respond contenidoInstance, model: [cursoId: cursoId, temaId: temaId]
    }

    def create() {
        respond new Contenido(params)
    }

    @Transactional
    def save(Contenido contenidoInstance) {
        if (contenidoInstance == null) {
            notFound()
            return
        }

        if (contenidoInstance.hasErrors()) {
            respond contenidoInstance.errors, view:'create'
            return
        }

        contenidoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'contenidoInstance.label', default: 'Contenido'), contenidoInstance.id])
                redirect contenidoInstance
            }
            '*' { respond contenidoInstance, [status: CREATED] }
        }
    }

    def edit(Contenido contenidoInstance) {
        respond contenidoInstance
    }

    @Transactional
    def update(Contenido contenidoInstance) {
        if (contenidoInstance == null) {
            notFound()
            return
        }

        if (contenidoInstance.hasErrors()) {
            respond contenidoInstance.errors, view:'edit'
            return
        }

        contenidoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Contenido.label', default: 'Contenido'), contenidoInstance.id])
                redirect contenidoInstance
            }
            '*'{ respond contenidoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Contenido contenidoInstance) {

        if (contenidoInstance == null) {
            notFound()
            return
        }

        contenidoInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Contenido.label', default: 'Contenido'), contenidoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'contenidoInstance.label', default: 'Contenido'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
