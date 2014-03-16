package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class EvaluacionController {

    // static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def usuarioService
	def evaluacionService
	def aprendizService
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def evaluacionesCurso() {
		[evaluaciones: Evaluacion.findAllByCurso(Curso.get(params.cursoId)),
			aprendiz: aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, params.cursoId.toLong()),
			params: ['cursoId': params.cursoId]]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def inscribirme(Evaluacion evaluacion) {
		if (evaluacion == null) {
			flash.message = "No se encuentra esa evaluación"
			redirect action: "evaluacionesCurso", params:['cursoId': params.cursoId], method: "GET"
			return
		}
		def evaluacionesCurso = aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, params.cursoId.toLong())
		evaluacionService.inscribirAprendiz(evaluacion, evaluacionesCurso)
		flash.message = "La inscripcion ha sido realizada exitosamente"
		redirect action: "evaluacionesCurso", params:['cursoId': params.cursoId]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def desinscribirme(Evaluacion evaluacion) {
		if (evaluacion == null) {
			flash.message = "No se encuentra esa evaluación"
			redirect action: "evaluacionesCurso", params:['cursoId': params.cursoId], method: "GET"
			return
		}
		def evaluacionesCurso = aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, params.cursoId.toLong())
		evaluacionService.desinscribirAprendiz(evaluacion, evaluacionesCurso)
		flash.message = "La desinscripcion ha sido realizada exitosamente"
		redirect action: "evaluacionesCurso", params:['cursoId': params.cursoId]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def evaluacionesAprendiz(Evaluacion evaluacion) {
		def evaluacionesCurso = aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, params.cursoId.toLong())
		def evaluacionesAprendiz = evaluacionService.obtenerEvaluacionesPorAprendiz(evaluacionesCurso, params.cursoId.toLong())
		[evaluacionesAprendiz: evaluacionesAprendiz, params: ['cursoId': params.cursoId]]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
    def index() {
		params.max = Utilidades.MAX_PARAMS
		[evaluacionInstanceList: Evaluacion.findAllByCurso(Curso.get(params.cursoId)), params: ['cursoId': params.cursoId]]
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
		if (params.fechaDate) {
			evaluacionInstance.fecha = evaluacionService.obtenerFecha(params.fechaDate)
			if (evaluacionInstance.fecha < Utilidades.FECHA) {
				flash.message = "La fecha no puede ser menor a ${Utilidades.FECHA}"
				render view:'create', model: [evaluacionInstance: evaluacionInstance], params:['cursoId': params.cursoId]
				return
			}
		}
		if (!evaluacionService.guardar(evaluacionInstance)) {
			render view:'create', model: [evaluacionInstance: evaluacionInstance], params:['cursoId': params.cursoId]
			return
		}
		// Una vez que esta habilitada, solo se puede editar el aula, asi que anoto a todos los alumnos del cuatrimestre
		if (evaluacionInstance.habilitada && evaluacionInstance.obligatoria) {
			evaluacionService.agregarAprendices(evaluacionInstance, params.cursoId.toLong())
		}
		flash.message = "Evaluacion ${evaluacionInstance} creada"
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
		if (params.fechaDate) {
			evaluacionInstance.fecha = evaluacionService.obtenerFecha(params.fechaDate)//.format(Utilidades.FORMATO_FECHA)
			if (evaluacionInstance.fecha < Utilidades.FECHA) {
				flash.message = "La fecha no puede ser menor a ${Utilidades.FECHA}"
				render view:'edit', model: [evaluacionInstance: evaluacionInstance], params:['cursoId': params.cursoId]
				return
			}
		}
        if (!evaluacionService.guardar(evaluacionInstance)) {
            render view:'edit', model: [evaluacionInstance: evaluacionInstance], params:['cursoId': params.cursoId]
            return
        }
		// Una vez que esta habilitada, solo se puede editar el aula, asi que anoto a todos los alumnos del cuatrimestre
		if (evaluacionInstance.habilitada && evaluacionInstance.obligatoria) {
			evaluacionService.agregarAprendices(evaluacionInstance, params.cursoId.toLong())
		}
        flash.message = "Evaluacion ${evaluacionInstance} actualizada"
		redirect action:"index", params:['cursoId': params.cursoId]
    }

    @Secured("hasRole('ROL_MEDIADOR')")
    def delete(Evaluacion evaluacionInstance) {
        if (evaluacionInstance == null) {
            notFound()
            return
        }
		evaluacionService.eliminar(evaluacionInstance)
        flash.message = "Evaluacion ${evaluacionInstance} eliminada"
		redirect action:"index", params:['cursoId': params.cursoId], method:"GET"
    }

    protected void notFound() {
        flash.message = "No se encuentra esa evaluación"
        redirect action: "index", params:['cursoId': params.cursoId], method: "GET"
    }
}
