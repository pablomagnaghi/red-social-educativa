package com.encuesta

import com.cursado.*
import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class OpcionChoiceController {

    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def opcionChoiceService

	@Secured("hasRole('ROL_MEDIADOR')")
	def show(OpcionChoice opcionChoiceInstance) {
		respond opcionChoiceInstance, params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId, 'preguntaId': params.preguntaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {
		respond new OpcionChoice(params), params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId, 'preguntaId': params.preguntaId]
	}
	
    @Secured("hasRole('ROL_MEDIADOR')")
	def save(OpcionChoice opcionChoiceInstance) {
		if (opcionChoiceInstance == null) {
			notFound()
			return
		}
		if (opcionChoiceService.existe(opcionChoiceInstance, params.preguntaId.toLong())) {
			flash.message = "Ya existe esa opcion choice"
			redirect action: "create", params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId, 'preguntaId': params.preguntaId]
			return
		}
		if (!opcionChoiceService.guardar(opcionChoiceInstance)) {
			render view:'create', model: [opcionChoiceInstance: opcionChoiceInstance], params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId, 
				'preguntaId': params.preguntaId]
			return
		}
		flash.message = "Opcion choice creada"
		redirect controller: "encuesta", action: "show", params:['id': params.encuestaId, 'cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def edit(OpcionChoice opcionChoiceInstance) {
		def opcion = opcionChoiceInstance.opcion
		respond opcionChoiceInstance, model: [opcion: opcion], params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def update(OpcionChoice opcionChoiceInstance) {
		if (opcionChoiceInstance == null) {
			notFound()
			return
		}
		if (opcionChoiceService.existe(opcionChoiceInstance, params.preguntaId.toLong())) {
			flash.message = "Ya existe esa opcion choice"
			opcionChoiceInstance.opcion = params.opcionAntigua
			redirect action: "edit", params: params
			return
		}
		if (!opcionChoiceService.guardar(opcionChoiceInstance)) {
			render view:'edit', model: [opcionChoiceInstance: opcionChoiceInstance], params:['cursoId': params.cursoId, 'encuestaId': params.encuestaId, 
				'preguntaId': params.preguntaId]
			return
		}
		flash.message = "Opcion choice actualizada"
		redirect controller: "encuesta", action: "show", params:['id': params.encuestaId, 'cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(OpcionChoice opcionChoiceInstance) {
		if (opcionChoiceInstance == null) {
			notFound()
			return
		}
		opcionChoiceService.eliminar(opcionChoiceInstance)
		flash.message = "Opcion choice eliminada"
		redirect controller: "encuesta", action: "show", params:['id': params.encuestaId, 'cursoId': params.cursoId], method:"GET"
	}
	
	protected void notFound() {
		flash.message = "No se encuentra esa opcion choice"
		redirect controller: "encuesta", action: "show", params:['id': params.encuestaId, 'cursoId': params.cursoId], method: "GET"
	}
}



