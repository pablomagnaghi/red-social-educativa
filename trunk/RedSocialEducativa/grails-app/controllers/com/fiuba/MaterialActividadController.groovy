package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class MaterialActividadController {

	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def seguridadService
	def materialActividadService

	@Secured('permitAll')
	def general() {

		params.max = Utilidades.MAX_PARAMS
		def actividad = Actividad.get(params.actividadId)

		[materiales: MaterialActividad.findAllByActividad(actividad, [max: params.max, offset: params.offset]),
			materialesCant: MaterialActividad.findAllByActividad(actividad).size(), actividad: actividad,
			params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def show(MaterialActividad materialActividadInstance) {
		respond materialActividadInstance, params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId,
			'actividadId': params.actividadId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {

		def mediador = Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(params.cursoId))

		respond new MaterialActividad(params), model:[mediador: mediador], params: ['cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def save(MaterialActividad materialActividadInstance) {
		if (materialActividadInstance == null) {
			notFound()
			return
		}

		println materialActividadService.existe(materialActividadInstance, params.actividadId.toLong())
		
		if (materialActividadService.existe(materialActividadInstance, params.actividadId.toLong())) {
			flash.message = "Ya existe el material ${materialActividadInstance.titulo} en la actividad ${Actividad.get(params.actividadId)}"
			redirect action: "create", params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
			return
		}

		if (!materialActividadService.guardar(materialActividadInstance)) {
			def mediador = Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(params.cursoId))
			render view:'create', model: [materialActividadInstance: materialActividadInstance, mediador: mediador],
			params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'materialActividadInstance.label', default: 'MaterialActividad'), materialActividadInstance.id])
		redirect controller:"actividad", action:"edit", params:['id': params.actividadId, 'cursoId': params.cursoId, 'cuatrimestreId': 
			params.cuatrimestreId]

	}
	@Secured("hasRole('ROL_MEDIADOR')")
	def edit(MaterialActividad materialActividadInstance) {
		respond materialActividadInstance, params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
			'actividadId': params.actividadId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def update(MaterialActividad materialActividadInstance) {

		if (materialActividadInstance == null) {
			notFound()
			return
		}

		if (!materialActividadService.guardar(materialActividadInstance)) {
			render view:'edit', model: [materialActividadInstance: materialActividadInstance],
			params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'MaterialActividad.label', default: 'MaterialActividad'), materialActividadInstance.id])
		redirect action:"show", params:['id': materialActividadInstance.id, 'cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(MaterialActividad materialActividadInstance) {

		if (materialActividadInstance == null) {
			notFound()
			return
		}

		materialActividadService.eliminar(materialActividadInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'MaterialActividad.label', default: 'MaterialActividad'), materialActividadInstance.id])
		redirect controller:"actividad", action:"show", params:['id': params.actividadId, 'cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId], method:"GET"
	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialActividadInstance.label', default: 'MaterialActividad'), params.id])
		redirect controller: "actividad", action:"show", params:['id': params.actividadId, 'cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId], method: "GET"
	}
}
