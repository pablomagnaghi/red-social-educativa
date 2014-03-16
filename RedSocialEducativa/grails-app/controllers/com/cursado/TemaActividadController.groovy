package com.cursado

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class TemaActividadController {

	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def temaActividadService

	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {
		respond new TemaActividad(params), model: [temas: Tema.findAllByCurso(Curso.get(params.cursoId))], params: ['cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def save(TemaActividad temaActividadInstance) {
		if (temaActividadInstance == null) {
			notFound()
			return
		}
		if (temaActividadService.existe(temaActividadInstance.actividad, temaActividadInstance.tema)) {
			flash.message = "La actividad ${temaActividadInstance.actividad} ya esta asociada con el tema ${temaActividadInstance.tema}"
			redirect action: "create", params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
			return
		}
		if (!temaActividadService.guardar(temaActividadInstance)) {
			flash.message = "Problemas al guardar la asociación"
			redirect action: "create", params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
			return
		}
		flash.message = "Tema ${temaActividadInstance.tema} asociado a la actividad ${temaActividadInstance.actividad}"
		redirect controller:"actividad", action:"index", params:['id': params.actividadId, 'cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(TemaActividad temaActividadInstance) {
		if (temaActividadInstance == null) {
			notFound()
			return
		}
		temaActividadService.eliminar(temaActividadInstance)
		flash.message = "Tema ${temaActividadInstance.tema} desasociado de la actividad ${temaActividadInstance.actividad}"
		redirect controller:"actividad", action:"index", params:['cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId], method:"GET"
	}

	protected void notFound() {
		flash.message = "No se encuentra esa asociación entre tema y actividad"
		redirect controller: "actividad", action:"show", params:['id': params.actividadId, 'cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId], method: "GET"
	}
}
