package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured
@Secured('permitAll')

class CuatrimestreController {

	// metodos nuevos
	
	// TODO agregar curso id en todos los metodos
	
	def cursoId
	
	def historial(Cuatrimestre cuatrimestreInstance) {
		respond cuatrimestreInstance, model: [cursoId: cursoId]
	}
	
	// metodos viejos
	
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		
		cursoId = params.cursoId
		
		[cuatrimestreInstanceList: Cuatrimestre.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			cuatrimestreInstanceCount: Cuatrimestre.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId]
    }

    def show(Cuatrimestre cuatrimestreInstance) {
        respond cuatrimestreInstance, model: [cursoId: cursoId]
    }

    def create() {
		cursoId = params.cursoId
        respond new Cuatrimestre(params), params:['cursoId': cursoId], model:[cursoId: cursoId]
    }


    @Transactional
    def save(Cuatrimestre cuatrimestreInstance) {
        if (cuatrimestreInstance == null) {
            notFound()
            return
        }

		cursoId = params.cursoId

		def cuatrimestreExistente = MaterialCurso.findByCursoAndAnioAndNumero(Curso.get(cursoId),
			cuatrimestreInstance.anio, cuatrimestreInstance.numero)
		println cuatrimestreExistente
		
		if (cuatrimestreExistente) {
			flash.message = "Ya existe el cuatrimestre ${cuatrimestreExistente} del curso ${Curso.get(cursoId)}"
			redirect action: "create", params:['cursoId': cursoId]
			return
		}
		
		def foro = new ForoCurso(nombre: "Foro general del curso ${cuatrimestreInstance.curso} durante el cuatrimestre {cuatrimestreInstance}")
		
		cuatrimestreInstance.foro = foro
		
		if (! cuatrimestreInstance.save(flush:true)) {
			respond cuatrimestrInstance.errors, view:'create',  params:['cursoId': cursoId], model: [cursoId: cursoId]
			return
		}

        cuatrimestreInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cuatrimestreInstance.label', default: 'Cuatrimestre'), cuatrimestreInstance.id])
                edirect action: "index", params:['cursoId': cursoId]
            }
            '*' { respond cuatrimestreInstance, [status: CREATED] }
        }
    }
	
    def edit(Cuatrimestre cuatrimestreInstance) {
		cursoId = params.cursoId
		respond cuatrimestreInstance, params:['cursoId': cursoId], model:[cursoId: cursoId]
    }

    @Transactional
    def update(Cuatrimestre cuatrimestreInstance) {
		
		cursoId = params.cursoId
		
        if (cuatrimestreInstance == null) {
            notFound()
            return
        }

        if (cuatrimestreInstance.hasErrors()) {
            respond cuatrimestreInstance.errors, view:'edit', params:['cursoId': cursoId], model: [cursoId: cursoId]
            return
        }

        cuatrimestreInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Cuatrimestre.label', default: 'Cuatrimestre'), cuatrimestreInstance.id])
                redirect cuatrimestreInstance
            }
            '*'{ respond cuatrimestreInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Cuatrimestre cuatrimestreInstance) {

        if (cuatrimestreInstance == null) {
            notFound()
            return
        }
		
		cursoId = params.cursoId
		
        cuatrimestreInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Cuatrimestre.label', default: 'Cuatrimestre'), cuatrimestreInstance.id])
                redirect action:"index", params:['cursoId': cursoId], method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuatrimestreInstance.label', default: 'Cuatrimestre'), params.id])
                redirect action: "index", params:['cursoId': cursoId], method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}


