package com.encuesta

import com.cursado.*
import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class PreguntaPuntajeController {

    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def preguntaPuntajeService

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
			flash.message = "Ya existe la pregunta en la encuesta"
			redirect action: "create", params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
			return
		}
		if (!preguntaPuntajeService.guardar(preguntaPuntajeInstance)) {
			render view:'create', model: [preguntaPuntajeInstance: preguntaPuntajeInstance], params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
			return
		}
		flash.message = "Pregunta de puntaje creada"
		redirect controller: "encuesta", action: "show", params:['id': params.encuestaId, 'cursoId': params.cursoId]
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
			flash.message = "Ya existe la pregunta en la encuesta"
			preguntaPuntajeInstance.pregunta = params.preguntaAntigua
			redirect action: "edit", params: params
			return
		}
		if (!preguntaPuntajeService.guardar(preguntaPuntajeInstance)) {
			render view:'edit', model: [preguntaPuntajeInstance: preguntaPuntajeInstance], params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
			return
		}
		flash.message = "Pregunta de puntaje actualizada"
		redirect controller: "encuesta", action: "show", params:['id': params.encuestaId, 'cursoId': params.cursoId]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(PreguntaPuntaje preguntaPuntajeInstance) {
		if (preguntaPuntajeInstance == null) {
			notFound()
			return
		}
		preguntaPuntajeService.eliminar(preguntaPuntajeInstance)
		flash.message = "Pregunta de puntaje eliminada"
		redirect controller: "encuesta", action: "show", params:['id': params.encuestaId, 'cursoId': params.cursoId], method:"GET"
	}
	
	protected void notFound() {
		flash.message = "No se encuentra esa pregunta de puntaje"
		redirect controller: "encuesta", action: "show", params:['id': params.encuestaId, 'cursoId': params.cursoId], method: "GET"
	}
}
