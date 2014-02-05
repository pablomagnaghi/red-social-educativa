package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class EvaluacionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def springSecurityService
	
	private usuarioActual() {
		return Usuario.get(springSecurityService.principal.id)
	}
	
	def cursoId
	
	def general() {
		params.max = 5 // Math.min(max ?: 10, 100)

		println "actividad CURSOID: ${params}"
		
		cursoId = params.cursoId
		def evaluacionId = params.id
		
		println "evaluacionId 1: ${evaluacionId}"
		
		def evaluacion = Evaluacion.get(evaluacionId)
		
		def aprendiz = Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))
		
		def evaluacionAprendiz = EvaluacionAprendiz.findByAprendizAndEvaluacion(aprendiz, evaluacion)
		
		println "hay evaluacion"
		println evaluacionAprendiz
		
		println "evaluacion ID: ${evaluacion.id}"
		
		[cursoId: cursoId, evaluacion: evaluacion, evaluacionAprendiz: evaluacionAprendiz]
	}
	
	def inscribirme() {
		cursoId = params.cursoId
		def evaluacionId = params.id
		
		println "INSCRIBIRME"
		println "curso: ${cursoId}"
		println "evaluacionIn: ${evaluacionId}"
		
		def evaluacion = Evaluacion.get(evaluacionId)
		def aprendiz = Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))
		
		def evaluacionAprendiz = new EvaluacionAprendiz(evaluacion: evaluacion, aprendiz: aprendiz)
		
		evaluacionAprendiz.save flush:true
		
		flash.message = "${evaluacionAprendiz.aprendiz} ya ha sido inscripto en la evaluacion ${evaluacionAprendiz.evaluacion}"
		redirect action: "general", params:['id': evaluacionId, 'cursoId': cursoId]
	}
	
	def mostrar() {
		params.max = 5 // Math.min(max ?: 10, 100)

		println "actividad CURSOID: ${params.cursoId}"
		
		cursoId = params.cursoId
		
		def aprendizId = Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))?.id
		
		def evaluacionesAprendiz = null
		
		if (aprendizId) {
			def c = EvaluacionAprendiz.createCriteria()
			evaluacionesAprendiz = c {
				evaluacion {
					eq('curso.id', cursoId as long)
				}
				eq('aprendiz.id', aprendizId as long)
			}
		}
		
		[cursoId: cursoId, aprendizId: aprendizId, evaluacionesAprendiz: evaluacionesAprendiz]
	}
	
	// Metodos para el mediador
    def index(Integer max) {
		
		params.max = 2/* Math.min(max ?: 10, 100)*/
		
		println "evaluacion index"
		println params
			
		cursoId = params.cursoId
				
		[evaluacionInstanceList: Evaluacion.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			evaluacionInstanceCount: Evaluacion.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId]
    }

    def show(Evaluacion evaluacionInstance) {
		println "evaluacion params: ${params}"
		respond evaluacionInstance, model:[cursoId: cursoId]
    }

    def create() {
        respond new Evaluacion(params)
		
		println "evaluacion create curso params: ${params}"
		cursoId = params.cursoId

		respond new Evaluacion(params), params:['cursoId': cursoId], model:[cursoId: cursoId]
    }

    @Transactional
    def save(Evaluacion evaluacionInstance) {
        if (evaluacionInstance == null) {
            notFound()
            return
        }

		cursoId = evaluacionInstance.curso.id
		
        if (evaluacionInstance.hasErrors()) {
            respond evaluacionInstance.errors, view:'create', params:['cursoId': cursoId], model: [cursoId: cursoId]
            return
        }

        evaluacionInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'evaluacionInstance.label', 
					default: 'Evaluacion'), evaluacionInstance.id])
                redirect action: "index", params:['cursoId': cursoId]
            }
            '*' { respond evaluacionInstance, [status: CREATED] }
        }
    }

    def edit(Evaluacion evaluacionInstance) {
		cursoId = params.cursoId
		respond evaluacionInstance, params:['cursoId': cursoId], model:[cursoId: cursoId]
    }
	
    @Transactional
    def update(Evaluacion evaluacionInstance) {
        if (evaluacionInstance == null) {
            notFound()
            return
        }

		cursoId = params.cursoId
		
        if (evaluacionInstance.hasErrors()) {
            respond evaluacionInstance.errors, view:'edit', params:['cursoId': cursoId], model: [cursoId: cursoId]
            return
        }

        evaluacionInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Evaluacion.label', 
					default: 'Evaluacion'), evaluacionInstance.id])
                redirect evaluacionInstance
            }
            '*'{ respond evaluacionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Evaluacion evaluacionInstance) {

        if (evaluacionInstance == null) {
            notFound()
            return
        }
		
		cursoId = params.cursoId
		
        evaluacionInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Evaluacion.label', 
					default: 'Evaluacion'), evaluacionInstance.id])
                redirect action:"index", params:['cursoId': cursoId], method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'evaluacionInstance.label', 
					default: 'Evaluacion'), params.id])
                redirect action: "index", params:['cursoId': cursoId], method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

