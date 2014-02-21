package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class EvaluacionController {

    // static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def usuarioService
	def evaluacionService
	def aprendizService
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def menuAprendiz(Evaluacion evaluacion) {
		
		def aprendiz = aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, params.cursoId.toLong())
		def evaluacionAprendiz = EvaluacionAprendiz.findByAprendizAndEvaluacion(aprendiz, evaluacion)
		
		[evaluacion: evaluacion, evaluacionAprendiz: evaluacionAprendiz, params: ['cursoId': params.cursoId]]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def inscribirme(Evaluacion evaluacion) {
		if (evaluacion == null) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'evaluacionInstance.label', default: 'Evaluacion'), params.id])
			redirect action: "menuAprendiz", params:['id': params.id, 'cursoId': params.cursoId], method: "GET"
			return
		}
		
		def aprendiz = aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, params.cursoId.toLong())
		evaluacionService.inscribirAprendiz(evaluacion, aprendiz)

		flash.message = "La inscripcion ha sido realizada exitosamente"
		redirect action: "menuAprendiz", params:['id': params.id, 'cursoId': params.cursoId]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def evaluacionesAprendiz(Evaluacion evaluacion) {
		
		def aprendiz = aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, params.cursoId.toLong())
		
		def evaluacionesAprendiz = evaluacionService.obtenerEvaluacionesPorAprendiz(aprendiz, params.cursoId.toLong())

		[evaluacionesAprendiz: evaluacionesAprendiz, params: ['cursoId': params.cursoId]]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
    def index() {
		
		params.max = Utilidades.MAX_PARAMS

		[evaluacionInstanceList: Evaluacion.findAllByCurso(Curso.get(params.cursoId),[max: params.max, offset: params.offset]),
			evaluacionInstanceCount: Evaluacion.findAllByCurso(Curso.get(params.cursoId)).size(), params: ['cursoId': params.cursoId]]
    }
	
	@Secured("hasRole('ROL_MEDIADOR')")
    def show(Evaluacion evaluacionInstance) {
		respond evaluacionInstance, params:['cursoId': params.cursoId]
    }
	
	@Secured("hasRole('ROL_MEDIADOR')")
    def create() {
		respond new Evaluacion(params), params:['cursoId': params.cursoId]
    }

    @Secured("hasRole('ROL_MEDIADOR')")
    def save(Evaluacion evaluacionInstance) {
        if (evaluacionInstance == null) {
            notFound()
            return
        }

		evaluacionInstance.fecha = params.fechaDate.format(Utilidades.FORMATO_FECHA)
		evaluacionInstance.horario = params.fechaDate.getTimeString()

		if (!evaluacionService.guardar(evaluacionInstance)) {
			render view:'create', model: [evaluacionInstance: evaluacionInstance], params:['cursoId': params.cursoId]
			return
		}
		
		if (evaluacionInstance.obligatoria) {
			evaluacionService.agregarAprendices(evaluacionInstance, params.cursoId.toLong())
		}
		
		flash.message = message(code: 'default.created.message', args: [message(code: 'evaluacionInstance.label', default: 'Evaluacion'), evaluacionInstance.id])
		redirect action: "index", params:['cursoId': params.cursoId]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
    def edit(Evaluacion evaluacionInstance) {
		respond evaluacionInstance, params:['cursoId': params.cursoId]
    }
	
    @Secured("hasRole('ROL_MEDIADOR')")
    def update(Evaluacion evaluacionInstance) {
        if (evaluacionInstance == null) {
            notFound()
            return
        }

        if (!evaluacionService.guardar(evaluacionInstance)) {
            render view:'edit', model: [evaluacionInstance: evaluacionInstance], params:['cursoId': params.cursoId]
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'Evaluacion.label', default: 'Evaluacion'), evaluacionInstance.id])
		redirect action: "show", params:['id': evaluacionInstance.id, 'cursoId': params.cursoId]
    }

    @Secured("hasRole('ROL_MEDIADOR')")
    def delete(Evaluacion evaluacionInstance) {

        if (evaluacionInstance == null) {
            notFound()
            return
        }

		evaluacionService.eliminar(evaluacionInstance)
		
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'Evaluacion.label', default: 'Evaluacion'), evaluacionInstance.id]) 
		redirect action:"index", params:['cursoId': params.cursoId], method:"GET"
    }

    protected void notFound() {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'evaluacionInstance.label', default: 'Evaluacion'), params.id])
        redirect action: "index", params:['cursoId': params.cursoId], method: "GET"
    }
}

