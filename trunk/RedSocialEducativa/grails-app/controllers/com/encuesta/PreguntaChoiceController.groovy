package com.encuesta

import com.cursado.*
import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class PreguntaChoiceController {

    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def preguntaChoiceService

	@Secured("hasRole('ROL_MEDIADOR')")
    def create() {
        respond new PreguntaChoice(params), params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
    }
	
	@Secured("hasRole('ROL_MEDIADOR')")
    def save(PreguntaChoice preguntaChoiceInstance) {
        if (preguntaChoiceInstance == null) {
            notFound()
            return
        }
		if (preguntaChoiceService.existe(preguntaChoiceInstance, params.encuestaId.toLong())) {
			flash.message = "Ya existe esa pregunta choice"
			redirect action: "create", params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
			return
		}
		if (!preguntaChoiceService.guardar(preguntaChoiceInstance)) {
			render view:'create', model: [preguntaChoiceInstance: preguntaChoiceInstance], params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
			return
		}
		flash.message = "Pregunta choice creada"
		redirect controller: "encuesta", action: "show", params:['id': params.encuestaId, 'cursoId': params.cursoId]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
    def edit(PreguntaChoice preguntaChoiceInstance) {
		def pregunta = preguntaChoiceInstance.pregunta
		respond preguntaChoiceInstance, model: [pregunta: pregunta], params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
    def update(PreguntaChoice preguntaChoiceInstance) {
        if (preguntaChoiceInstance == null) {
            notFound()
            return
        }
		if (preguntaChoiceService.existe(preguntaChoiceInstance, params.encuestaId.toLong())) {
			flash.message = "Ya existe esa pregunta choice"
			preguntaChoiceInstance.pregunta = params.preguntaAntigua
			redirect action: "edit", params: params
			return
		}
		if (!preguntaChoiceService.guardar(preguntaChoiceInstance)) {
			render view:'edit', model: [preguntaChoiceInstance: preguntaChoiceInstance], params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
			return
		}
		flash.message = "Pregunta choice actualizada"
		redirect controller: "encuesta", action: "show", params:['id': params.encuestaId, 'cursoId': params.cursoId]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(PreguntaChoice preguntaChoiceInstance) {
		if (preguntaChoiceInstance == null) {
			notFound()
			return
		}
		preguntaChoiceService.eliminar(preguntaChoiceInstance)
		flash.message = "Pregunta choice eliminada"
		redirect controller: "encuesta", action: "show", params:['id': params.encuestaId, 'cursoId': params.cursoId], method:"GET"
	}
	
	protected void notFound() {
		flash.message = "No se encuentra esa pregunta choice"
		redirect controller: "encuesta", action: "show", params:['id': params.encuestaId, 'cursoId': params.cursoId], method: "GET"
	}
}


