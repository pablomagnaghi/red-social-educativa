package com.cursado

import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class EvaluacionAprendizController {

    // static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def pdfRenderingService
	def evaluacionAprendizService
	def aprendizService
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def renderPDF(){
		def evaluacion = Evaluacion.get(params.id)
		def args = [template:"pdf", model:[evaluacion: evaluacion, evaluaciones:  EvaluacionAprendiz.findAllByEvaluacion(evaluacion)]]
		pdfRenderingService.render(args+[controller:this],response)
	}
	
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
	
	@Secured("hasRole('ROL_MEDIADOR')")
    def create() {
        respond new EvaluacionAprendiz(params), params: ['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def calificar(EvaluacionAprendiz evaluacionAprendizInstance) {
		respond evaluacionAprendizInstance, params: ['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId, 'aprendizId': params.aprendizId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def guardarCalificacion(EvaluacionAprendiz evaluacionAprendizInstance) {
		if (evaluacionAprendizInstance == null) {
			notFound()
			return
		}
		if (!evaluacionAprendizInstance.validate()) {
			flash.message = "Problemas al calificar al aprendiz ${evaluacionAprendizInstance.aprendiz}. La nota debe ser un número entre 0 y 10"
			redirect action: "mostrarEvaluacion", params:['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId], method: "GET"
			return
		}
		evaluacionAprendizInstance.calificado = true
		evaluacionAprendizService.guardar(evaluacionAprendizInstance)
		flash.message = "Aprendiz ${evaluacionAprendizInstance.aprendiz} ha sido calificado"
		redirect action:"mostrarEvaluacion", params:['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId], method: "GET"
	}

	@Secured("hasRole('ROL_MEDIADOR')")
    def save(EvaluacionAprendiz evaluacionAprendizInstance) {
        if (evaluacionAprendizInstance == null) {
            notFound()
            return
        }
		if (evaluacionAprendizService.existe( evaluacionAprendizInstance.evaluacion, evaluacionAprendizInstance.aprendiz)) {
			flash.message = "El aprendiz ${evaluacionAprendizInstance.aprendiz.usuario} ya esta anotado en la evaluacion ${ evaluacionAprendizInstance.evaluacion}"
			render view: "create", params: ['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId]
			return
		}
		if (!evaluacionAprendizService.guardar(evaluacionAprendizInstance)) {
			render view:'create', model: [evaluacionAprendizInstance: evaluacionAprendizInstance, 
				aprendices: aprendizService.obtenerTodosAprendicesDeCurso(params.cursoId.toLong())], params: ['cursoId': params.cursoId, 
				'evaluacionId': params.evaluacionId]
			return
		}
        flash.message = "Aprendiz ${evaluacionAprendizInstance.aprendiz} agregado a la evaluacion ${evaluacionAprendizInstance.evaluacion}"
		redirect action:"mostrarEvaluacion", params:['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
    def delete(EvaluacionAprendiz evaluacionAprendizInstance) {
        if (evaluacionAprendizInstance == null) {
            notFound()
            return
        }
		evaluacionAprendizService.eliminar(evaluacionAprendizInstance)
        flash.message = "Aprendiz ${evaluacionAprendizInstance.aprendiz} eliminado de la evaluacion ${evaluacionAprendizInstance.evaluacion}"
		redirect action:"mostrarEvaluacion", params:['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId], method: "GET"
    }

    protected void notFound() {
        flash.message = "No se encuentra esa evaluación para un aprendiz"
        redirect controller: "evaluacion", action:"show", params:['id': params.evaluacionId, 'cursoId': params.cursoId], method: "GET"
    }
}
