package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class MaterialCursoController {
	
	// metodos nuevos
	def cursoId
	
	def general() {
		params.max = 5
		
		println "general materiaCurso"
		println params
				
		cursoId = params.cursoId
					
		[materialesCurso: MaterialCurso.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			materialesCursoCant: MaterialCurso.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId]
	}
	
	// metodos por defecto

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MaterialCurso.list(params), model:[materialCursoInstanceCount: MaterialCurso.count()]
    }

    def show(MaterialCurso materialCursoInstance) {
        respond materialCursoInstance
    }

    def create() {
        respond new MaterialCurso(params)
    }

    @Transactional
    def save(MaterialCurso materialCursoInstance) {
        if (materialCursoInstance == null) {
            notFound()
            return
        }

        if (materialCursoInstance.hasErrors()) {
            respond materialCursoInstance.errors, view:'create'
            return
        }

        materialCursoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'materialCursoInstance.label', default: 'MaterialCurso'), materialCursoInstance.id])
                redirect materialCursoInstance
            }
            '*' { respond materialCursoInstance, [status: CREATED] }
        }
    }

    def edit(MaterialCurso materialCursoInstance) {
        respond materialCursoInstance
    }

    @Transactional
    def update(MaterialCurso materialCursoInstance) {
        if (materialCursoInstance == null) {
            notFound()
            return
        }

        if (materialCursoInstance.hasErrors()) {
            respond materialCursoInstance.errors, view:'edit'
            return
        }

        materialCursoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MaterialCurso.label', default: 'MaterialCurso'), materialCursoInstance.id])
                redirect materialCursoInstance
            }
            '*'{ respond materialCursoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(MaterialCurso materialCursoInstance) {

        if (materialCursoInstance == null) {
            notFound()
            return
        }

        materialCursoInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MaterialCurso.label', default: 'MaterialCurso'), materialCursoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialCursoInstance.label', default: 'MaterialCurso'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
