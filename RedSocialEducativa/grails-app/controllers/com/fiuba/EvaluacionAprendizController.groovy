package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class EvaluacionAprendizController {

    // static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def evaluacionAprendizService
	def aprendizService
	
	@Secured("hasRole('ROL_MEDIADOR')")
    def mostrarEvaluacion() {
		[evaluacion: Evaluacion.get(params.evaluacionId), evaluaciones: EvaluacionAprendiz.findAllByEvaluacion(Evaluacion.get(params.evaluacionId)), 
			params: ['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId]]
    }
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def mostrarAprendiz() {
		[aprendiz: Aprendiz.get(params.aprendizId), evaluaciones: EvaluacionAprendiz.findAllByAprendiz(Aprendiz.get(params.aprendizId)),
			params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]
	}
	
	// TODO Ver a partir de aca
	@Secured("hasRole('ROL_MEDIADOR')")
    def create() {
        respond new EvaluacionAprendiz(params), model: [aprendices: aprendizService.obtenerTodosAprendicesDeCurso(params.cursoId.toLong()), 
			params: ['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def calificar(EvaluacionAprendiz evaluacionAprendizInstance) {
		println "calificar params: ${params}"
		respond evaluacionAprendizInstance, params: ['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId, 'aprendizId': params.aprendizId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def guardarCalificacion(EvaluacionAprendiz evaluacionAprendizInstance) {

		if (evaluacionAprendizInstance == null) {
			notFound()
			return
		}
		
		if (!evaluacionAprendizService.guardar(evaluacionAprendizInstance)) {
			render view:'show', model: [evaluacionAprendizInstance: evaluacionAprendizInstance], params: ['cursoId': params.cursoId,
				'evaluacionId': params.evaluacionId, 'aprendizId': params.aprendizId]
			return
		}

		if (params.aprendizId) {
			println "DESVIOOOO"
			redirect action:"mostrarAprendiz", params:['cursoId': params.cursoId, 'aprendizId': params.aprendizId], method: "GET"
			return
		}		
		flash.message = message(code: 'default.deleted.message', args: [message(code: 'EvaluacionAprendiz.label', default: 'EvaluacionAprendiz'), evaluacionAprendizInstance.id])
		redirect action:"mostrarEvaluacion", params:['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId], method: "GET"
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
    def save(EvaluacionAprendiz evaluacionAprendizInstance) {
        if (evaluacionAprendizInstance == null) {
            notFound()
            return
        }
		
		if ( evaluacionAprendizService.existe( evaluacionAprendizInstance.evaluacion, evaluacionAprendizInstance.aprendiz)) {
			flash.message = "El aprendiz ${ evaluacionAprendizInstance.aprendiz} ya esta anotado en la evaluacion ${ evaluacionAprendizInstance.evaluacion}"
			redirect action: "create", params: ['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId]
			return
		}
		
		if (! evaluacionAprendizService.guardar( evaluacionAprendizInstance)) {
			render view:'create', model: [evaluacionAprendizInstance: evaluacionAprendizInstance, 
				aprendices: aprendizService.obtenerTodosAprendicesDeCurso(params.cursoId.toLong())], params: ['cursoId': params.cursoId, 
				'evaluacionId': params.evaluacionId]
			return
		}

        flash.message = message(code: 'default.created.message', args: [message(code: 'evaluacionAprendizInstance.label', default: 'EvaluacionAprendiz'), evaluacionAprendizInstance.id])
		redirect controller: "evaluacion", action:"show", params:['id': params.evaluacionId, 'cursoId': params.cursoId]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
    def delete(EvaluacionAprendiz evaluacionAprendizInstance) {

        if (evaluacionAprendizInstance == null) {
            notFound()
            return
        }

		evaluacionAprendizService.eliminar(evaluacionAprendizInstance)
		
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'EvaluacionAprendiz.label', default: 'EvaluacionAprendiz'), evaluacionAprendizInstance.id])
		redirect action:"mostrarEvaluacion", params:['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId], method: "GET"
  
    }

    protected void notFound() {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'evaluacionAprendizInstance.label', default: 'EvaluacionAprendiz'), params.id])
        redirect controller: "evaluacion", action:"show", params:['id': params.evaluacionId, 'cursoId': params.cursoId], method: "GET"
    }
}
