package com.encuesta

import com.cursado.*
import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class PreguntaPuntajeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def preguntaPuntajeService

	@Secured("hasRole('ROL_MEDIADOR')")
	def index(Integer max) {
		params.max = Utilidades.MAX_PARAMS
		[preguntaPuntajeInstanceList: PreguntaPuntaje.findAllByEncuesta(Encuesta.get(params.encuestaId)), params: ['cursoId': params.cursoId,	
			'encuestaId': params.encuestaId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
    def show(PreguntaPuntaje preguntaPuntajeInstance) {
        respond preguntaPuntajeInstance, params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
    def create() {
        respond new PreguntaPuntaje(params), params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
    }
	
	@Secured("hasRole('ROL_MEDIADOR')")
    def save(PreguntaPuntaje preguntaPuntajeInstance) {
        if (preguntaPuntajeInstance == null) {
            notFound()
            return
        }
		if (preguntaPuntajeService.existe(preguntaPuntajeInstance, params.encuestaId.toLong())) {
			flash.message = "Ya existe la pregunta de puntaje ${preguntaPuntajeInstance}"
			redirect action: "create", params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
			return
		}
		if (!preguntaPuntajeService.guardar(preguntaPuntajeInstance)) {
			render view:'create', model: [preguntaPuntajeInstance: preguntaPuntajeInstance], params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
			return
		}
		flash.message = "Pregunta de puntaje ${preguntaPuntajeInstance} creada"
		redirect action: "index", params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
    def edit(PreguntaPuntaje preguntaPuntajeInstance) {
		def pregunta = preguntaPuntajeInstance.pregunta
		respond preguntaPuntajeInstance, model: [pregunta: pregunta], params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
    def update(PreguntaPuntaje preguntaPuntajeInstance) {
        if (preguntaPuntajeInstance == null) {
            notFound()
            return
        }
		if (preguntaPuntajeService.existe(preguntaPuntajeInstance, params.encuestaId.toLong())) {
			flash.message = "Ya existe la pregunta de puntaje ${preguntaPuntajeInstance}"
			preguntaPuntajeInstance.pregunta = params.preguntaAntigua
			redirect action: "edit", params: params
			return
		}
		if (!preguntaPuntajeService.guardar(preguntaPuntajeInstance)) {
			render view:'edit', model: [preguntaPuntajeInstance: preguntaPuntajeInstance], params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
			return
		}
		flash.message = "Pregunta de puntaje ${preguntaPuntajeInstance} actualizada"
		redirect action: "index", params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(PreguntaPuntaje preguntaPuntajeInstance) {
		if (preguntaPuntajeInstance == null) {
			notFound()
			return
		}
		preguntaPuntajeService.eliminar(preguntaPuntajeInstance)
		flash.message = "Pregunta de puntaje ${preguntaPuntajeInstance} eliminada"
		redirect action:"index", params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId], method:"GET"
	}
	
	protected void notFound() {
		flash.message = "No se encuentra esa pregunta de puntaje"
		redirect action: "index", params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId], method: "GET"
	}
}
