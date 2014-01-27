package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class MaterialTemaController {

	def springSecurityService
	
	private usuarioActual() {
		return Usuario.get(springSecurityService.principal.id)
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
		
		[materiales: MaterialTema.findAllByTema(tema, [max: params.max, offset: params.offset]),
			materialesCant: MaterialTema.findAllByTema(tema).size(),
			tema: tema, cursoId: cursoId, temaId: temaId,
			contenidos: Contenido.findAllByTema(tema, [max: params.max, offset: params.offset]),
			contenidosCant: Contenido.findAllByTema(tema).size()]
	}

	// metodos por defecto
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MaterialTema.list(params), model:[materialTemaInstanceCount: MaterialTema.count()]
    }

    def show(MaterialTema materialTemaInstance) {
		
		println "material tema show: cursoId: ${params.cursoId}, tema Id. ${params.temaId}"
		println "material Id: ${params.id}"
		
		cursoId = params.cursoId
		temaId = params.temaId
		
        respond materialTemaInstance, model: [cursoId: cursoId, temaId: temaId]
    }

    def create() {
		cursoId = params.cursoId
		temaId = params.temaId
		def mediador = Mediador.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))
        respond new MaterialTema(params), params:['cursoId': cursoId], 
			model:[cursoId: cursoId, temaId: temaId, mediador: mediador]
    }

    @Transactional
    def save(MaterialTema materialTemaInstance) {
        if (materialTemaInstance == null) {
            notFound()
            return
        }

		cursoId = params.cursoId
		temaId = params.temaId
		
        if (materialTemaInstance.hasErrors()) {
            respond materialTemaInstance.errors, view:'create', params: ['cursoId': cursoId, 'temaId': temaId],
				model: [cursoId: cursoId, temaId: temaId]
			return
        }

        materialTemaInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'materialTemaInstance.label', default: 'MaterialTema'), materialTemaInstance.id])
                redirect controller:"tema", action:"show", params:['id': temaId, 'cursoId': cursoId, 'temaId': temaId]
            }
            '*' { respond materialTemaInstance, [status: CREATED] }
        }
    }

    def edit(MaterialTema materialTemaInstance) {
		println "edit material tema para,s: ${params}"
		
		cursoId = params.cursoId
		temaId = params.temaId
		respond materialTemaInstance, model: [cursoId: cursoId, temaId: temaId]
    }

    @Transactional
    def update(MaterialTema materialTemaInstance) {
		
		println "update material tema para,s: ${params}"
		
		cursoId = params.cursoId
		temaId = params.temaId
		
        if (materialTemaInstance == null) {
            notFound()
            return
        }

        if (materialTemaInstance.hasErrors()) {
            respond materialTemaInstance.errors, view:'edit', params:['cursoId': cursoId, 'temaId': temaId], 
				model: [cursoId: cursoId, temaId: temaId]
            return
        }

        materialTemaInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MaterialTema.label', default: 'MaterialTema'), materialTemaInstance.id])
                redirect action:"show", params:['id': materialTemaInstance.id, 'cursoId': cursoId, 'temaId': temaId]
            }
            '*'{ respond materialTemaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(MaterialTema materialTemaInstance) {

        if (materialTemaInstance == null) {
            notFound()
            return
        }
		
		cursoId = params.cursoId
		temaId = params.temaId

        materialTemaInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MaterialTema.label', default: 'MaterialTema'), materialTemaInstance.id])
                redirect controller:"tema", action:"show", params:['id': temaId], method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialTemaInstance.label', default: 'MaterialTema'), params.id])
                redirect controller: "tema", action:"show", params:['id': temaId], method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
