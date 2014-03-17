package com.cursado

import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class GrupoActividadAprendizController {

    // static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def grupoActividadAprendizService 
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def mostrarAprendiz() {
		[aprendiz: Aprendiz.get(params.aprendizId), gruposActividadAprendiz: GrupoActividadAprendiz.findAllByAprendiz(Aprendiz.get(params.aprendizId)),
			params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]
	}
	
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
		if (!grupoActividadAprendizInstance.validate()) {
			flash.message = "Problemas al calificar al aprendiz ${grupoActividadAprendizInstance.aprendiz}. La nota debe ser un número entre 0 y 10"
			redirect controller: "grupoActividad", action: "grupoMediador", params:['id': grupoActividadAprendizInstance.grupo.id, 'cursoId': params.cursoId, 
				'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId], method:"GET"
			return
		}
		grupoActividadAprendizInstance.calificado = true
		grupoActividadAprendizService.guardar(grupoActividadAprendizInstance)
		flash.message = "Aprendiz ${grupoActividadAprendizInstance.aprendiz} ha sido calificado"
		redirect controller: "grupoActividad", action: "grupoMediador", params:['id': grupoActividadAprendizInstance.grupo.id, 'cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId], method:"GET"
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def cambiarEstado(GrupoActividadAprendiz grupoActividadAprendizInstance) {
		if (grupoActividadAprendizInstance == null) {
			notFound()
			return
		}
		grupoActividadAprendizInstance.cumplio = grupoActividadAprendizInstance.cumplio ? false : true
		if (!grupoActividadAprendizService.guardar(grupoActividadAprendizInstance)) {
			flash.message = "Problemas al calificar al aprendiz ${grupoActividadAprendizInstance.aprendiz}"
			redirect controller: "grupoActividad", action: "grupoMediador", params:['id': grupoActividadAprendizInstance.grupo.id, 'cursoId': params.cursoId, 
				'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId], method:"GET"
			return
		}
		flash.message = "Aprendiz ${grupoActividadAprendizInstance.aprendiz} ha sido calificado"
		redirect controller: "grupoActividad", action: "grupoMediador", params:['id': grupoActividadAprendizInstance.grupo.id, 'cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId], method:"GET"
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
		flash.message = "Aprendiz ${grupoActividadAprendizInstance.aprendiz} eliminado del grupo ${grupoActividadAprendizInstance.grupo}"
		redirect controller: "grupoActividad", action:"grupoMediador", params:['id': grupoActividadAprendizInstance.grupo.id, 
			'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId], method:"GET"
	}

    protected void notFound() {
        flash.message = "No se encuentra ese grupo para un aprendiz"
		redirect controller: "grupoActividad", action: "gruposMediador", params: ['cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId], method: "GET"
    }
}
