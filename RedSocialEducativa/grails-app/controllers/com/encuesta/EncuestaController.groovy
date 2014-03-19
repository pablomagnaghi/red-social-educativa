package com.encuesta

import com.cursado.*
import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class EncuestaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def encuestaService
	def usuarioService
	
	@Secured("hasRole('ROL_MEDIADOR')")
    def index(Integer max) {
        params.max = Utilidades.MAX_PARAMS
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
        [encuestaInstanceList: Encuesta.findAllByCurso(Curso.get(params.cursoId)), mediador: mediador, params: ['cursoId': params.cursoId]]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
    def show(Encuesta encuestaInstance) {
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
        respond encuestaInstance, model: [mediador: mediador], params:['cursoId': params.cursoId]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
    def create() {
        respond new Encuesta(params), params:['cursoId': params.cursoId]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
	def save(Encuesta encuestaInstance) {
		if (encuestaInstance == null) {
			notFound()
			return
		}
		if (encuestaService.existe(encuestaInstance, params.cursoId.toLong())) {
			flash.message = "Ya existe la encuesta ${encuestaInstance}"
			redirect action: "create", params: ['cursoId': params.cursoId]
			return
		}
		if (!encuestaService.guardar(encuestaInstance)) {
			render view:'create', model: [encuestaInstance: encuestaInstance], params:['cursoId': params.cursoId]
			return
		}
		flash.message = "Encuesta ${encuestaInstance} creada"
		redirect action: "index", params:['cursoId': params.cursoId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
    def edit(Encuesta encuestaInstance) {
		def nombre = encuestaInstance.nombre
        respond encuestaInstance, model: [nombre: nombre],  params:['cursoId': params.cursoId]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
    def update(Encuesta encuestaInstance) {
        if (encuestaInstance == null) {
            notFound()
            return
        }
		if (encuestaService.existe(encuestaInstance, params.cursoId.toLong())) {
			flash.message = "Ya existe la encuesta ${encuestaInstance}"
			encuestaInstance.nombre = params.nombreAntiguo
			redirect action: "edit", params: params
			return
		}
		if (!encuestaService.guardar(encuestaInstance)) {
			render view:'edit', model: [encuestaInstance: encuestaInstance], params:['cursoId': params.cursoId]
			return
		}
		flash.message = "Encuesta ${encuestaInstance} actualizada"
		redirect action: "index", params:['cursoId': params.cursoId]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
    def delete(Encuesta encuestaInstance) {
        if (encuestaInstance == null) {
            notFound()
            return
        }
		encuestaService.eliminar(encuestaInstance)
		flash.message = "Encuesta ${encuestaInstance} eliminada"
		redirect action:"index", params:['cursoId': params.cursoId], method:"GET"
    }
	
    protected void notFound() {
		flash.message = "No se encuentra esa encuesta"
		redirect action: "index", params:['cursoId': params.cursoId], method: "GET"
    }
}

/*
@Secured("hasRole('ROL_APRENDIZ')")
def evaluacionesCurso() {
	[evaluaciones: Evaluacion.findAllByCurso(Curso.get(params.cursoId)),
		aprendiz: aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, params.cursoId.toLong()),
		params: ['cursoId': params.cursoId]]
}

@Secured("hasRole('ROL_APRENDIZ')")
def evaluacionesAprendiz(Evaluacion evaluacion) {
	def evaluacionesCurso = aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, params.cursoId.toLong())
	def evaluacionesAprendiz = evaluacionService.obtenerEvaluacionesPorAprendiz(evaluacionesCurso, params.cursoId.toLong())
	[evaluacionesAprendiz: evaluacionesAprendiz, params: ['cursoId': params.cursoId]]
}


*/
