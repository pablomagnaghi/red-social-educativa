package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class TemaActividadController {

	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def seguridadService
	def temaActividadService

	@Secured('permitAll')
	def general() {

		params.max = Utilidades.MAX_PARAMS

		def actividad = Actividad.get(params.actividadId)

		[temas: TemaActividad.findAllByActividad(actividad, [max: params.max, offset: params.offset]),
			temasCant: TemaActividad.findAllByActividad(actividad).size(), actividad: actividad, 
			params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def show(TemaActividad temaActividadInstance) {
		respond temaActividadInstance, params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
	}

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
			render view:'create', model: [temaActividadInstance: temaActividadInstance, temas: Tema.findAllByCurso(Curso.get(params.cursoId))], params: ['cursoId': params.cursoId, 
				'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'temaActividadInstance.label', default: 'TemaActividad'), temaActividadInstance.id])
		redirect controller:"actividad", action:"show", params:['id': params.actividadId, 'cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(TemaActividad temaActividadInstance) {

		if (temaActividadInstance == null) {
			notFound()
			return
		}

		temaActividadService.eliminar(temaActividadInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'TemaActividad.label', default: 'TemaActividad'), temaActividadInstance.id])
		redirect controller:"actividad", action:"show", params:['id': params.actividadId, 'cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId], method:"GET"
	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'temaActividadInstance.label', default: 'TemaActividad'), params.id])
		redirect controller: "actividad", action:"show", params:['id': params.actividadId, 'cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId], method: "GET"
	}
}

