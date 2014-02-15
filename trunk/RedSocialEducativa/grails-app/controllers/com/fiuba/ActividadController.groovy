package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class ActividadController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def seguridadService
	def actividadService
	def aprendizService

	@Secured('permitAll')
	def general() {

		def actividad = Actividad.get(params.id)

		def grupoActividadAprendiz = aprendizService.obtenerGrupoPorActividad(seguridadService.usuarioActual(), params.cuatrimestreId.toLong(), 
			params.id.toLong())

		[actividad: actividad, grupoActividadAprendiz: grupoActividadAprendiz,
			params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.id]]
	}


	@Secured("hasRole('ROL_MEDIADOR')")
	def index() {
		params.max = Utilidades.MAX_PARAMS

		[actividadInstanceList: Actividad.findAllByCuatrimestre(Cuatrimestre.get(params.cuatrimestreId),[max: params.max, offset: params.offset]),
			actividadInstanceCount: Actividad.findAllByCuatrimestre(Cuatrimestre.get(params.cuatrimestreId)).size(),
			params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]

	}
	@Secured("hasRole('ROL_MEDIADOR')")
	def show(Actividad actividadInstance) {
		respond actividadInstance, params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {	
		respond new Actividad(params), params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def save(Actividad actividadInstance) {
		if (actividadInstance == null) {
			notFound()
			return
		}
		
		if (actividadService.existe(actividadInstance, params.cuatrimestreId.toLong())) {
			flash.message = "Ya existe la actividad ${actividadInstance.titulo} en el cuatrimestre ${Cuatrimestre.get(params.cuatrimestreId)}"
			redirect action: "create", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}

		actividadInstance.fechaFinalizacion = params.fechaFinalizacionDate.format(Utilidades.FORMATO_FECHA_NUMERICO)
		
		if (!actividadService.guardar(actividadInstance)) {
			render view:'create', model: [actividadInstance: actividadInstance], 
				params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'actividadInstance.label', default: 'Actividad'), actividadInstance.id])
		redirect action: "index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def edit(Actividad actividadInstance) {
		respond actividadInstance, params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def update(Actividad actividadInstance) {
		if (actividadInstance == null) {
			notFound()
			return
		}
	
		actividadInstance.fechaFinalizacion = params.fechaFinalizacionDate.format(Utilidades.FORMATO_FECHA_NUMERICO)
		
		if (!actividadService.guardar(actividadInstance)) {
			render view:'edit', model: [actividadInstance: actividadInstance],
				params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}
		
		flash.message = message(code: 'default.updated.message', args: [message(code: 'Actividad.label', default: 'Actividad'), actividadInstance.id])
		respond actividadInstance, view:"show", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(Actividad actividadInstance) {

		if (actividadInstance == null) {
			notFound()
			return
		}

		actividadService.eliminar(actividadInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'Actividad.label', default: 'Actividad'), actividadInstance.id])
		redirect action:"index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId], method:"GET"
	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'actividadInstance.label', default: 'Actividad'), params.id])
		redirect action: "index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId], method: "GET"
	}
}
