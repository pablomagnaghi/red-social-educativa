package com.cursado

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class GrupoActividadAprendizController {

    // static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def grupoActividadAprendizService 
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def calificar(GrupoActividadAprendiz grupoActividadAprendizInstance) {
		respond grupoActividadAprendizInstance, params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
			'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def guardarCalificacion(GrupoActividadAprendiz grupoActividadAprendizInstance) {

		if (grupoActividadAprendizInstance == null) {
			notFound()
			return
		}

		if (!grupoActividadAprendizService.guardar(grupoActividadAprendizInstance)) {
			render view:'show', model: [grupoActividadAprendizInstanceInstance: grupoActividadAprendizInstanceInstance], 
				params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId,
				'grupoActividadId': params.grupoActividadId]
			return
		}
		
		flash.message = message(code: 'default.updated.message', args: [message(code: 'GrupoActividadAprendiz.label', default: 'GrupoActividadAprendiz'), grupoActividadAprendizInstance.id])
		redirect controller: "grupoActividad", action: "gruposMediador", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId,
			'actividadId': params.actividadId], method:"GET"
		/*
		flash.message = message(code: 'default.updated.message', args: [message(code: 'GrupoActividadAprendiz.label', default: 'GrupoActividadAprendiz'), grupoActividadAprendizInstance.id])
        redirect action: "show", params: [id: params.id, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId,
			'grupoActividadId': params.grupoActividadId], method: "GET"*/
	}

	@Secured("hasRole('ROL_MEDIADOR')")
    def show(GrupoActividadAprendiz grupoActividadAprendizInstance) {	
        respond grupoActividadAprendizInstance, params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
			'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]
    }
	
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(GrupoActividadAprendiz grupoActividadAprendizInstance) {

		if (grupoActividadAprendizInstance == null) {
			notFound()
			redirect controller: "grupoActividad", action: "gruposMediador", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId,
				'actividadId': params.actividadId], method:"GET"
			return
		}
		
		grupoActividadAprendizService.eliminar(grupoActividadAprendizInstance)
		
		flash.message = message(code: 'default.deleted.message', args: [message(code: 'GrupoActividad.label', default: 'GrupoActividad'), grupoActividadAprendizInstance.id])
		redirect controller: "grupoActividad", action:"gruposMediador", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId,
			'actividadId': params.actividadId], method:"GET"
	}

    protected void notFound() {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupoActividadAprendizInstance.label', default: 'GrupoActividadAprendiz'), params.id])
		redirect controller: "grupoActividad", action: "show", params: ['id': params.grupoActividadId, 'cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId], method: "GET"
    }
}
