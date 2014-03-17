package com.cursado

import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class ActividadController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def usuarioService
	def actividadService
	def aprendizService
	def cuatrimestreService

	@Secured("hasAnyRole('ROL_MEDIADOR', 'ROL_APRENDIZ')")
	def index() {
		params.max = Utilidades.MAX_PARAMS
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		def aprendiz = Aprendiz.findByUsuarioAndCuatrimestre(usuarioService.usuarioActual(), Cuatrimestre.get(params.cuatrimestreId))
		[actividadInstanceList: Actividad.findAllByCuatrimestre(Cuatrimestre.get(params.cuatrimestreId)), mediador: mediador, aprendiz: aprendiz,
			params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]
	}
	
	@Secured("hasAnyRole('ROL_MEDIADOR', 'ROL_APRENDIZ')")
	def show(Actividad actividadInstance) {
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		respond actividadInstance, model: [mediador: mediador], params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
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
		if (params.fechaFinalizacionDate) {
			actividadInstance.fechaFinalizacion = actividadService.obtenerFecha(params.fechaFinalizacionDate)
			if (actividadInstance.fechaFinalizacion < Utilidades.FECHA) {
				flash.message = "La fecha no puede ser menor a ${Utilidades.FECHA}"
				render view:'create', model: [actividadInstance: actividadInstance], params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
				return
			}
		}
		if (!actividadService.guardar(actividadInstance)) {
			render view:'create', model: [actividadInstance: actividadInstance], params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}
		// Una vez que esta habilitada, no se puede editar mas
		if (actividadInstance.visibilidad && !actividadInstance.grupal) {
			actividadService.asignarAlCurso(actividadInstance, params.cursoId.toLong())
		}
		flash.message = "Actividad ${actividadInstance} creada"
		redirect action: "index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def edit(Actividad actividadInstance) {
		def titulo = actividadInstance.titulo
		respond actividadInstance, model: [titulo: titulo], params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def update(Actividad actividadInstance) {
		if (actividadInstance == null) {
			notFound()
			return
		}
		if (actividadService.existe(actividadInstance, params.cuatrimestreId.toLong())) {
			flash.message = "Ya existe la actividad ${actividadInstance.titulo} en el cuatrimestre ${Cuatrimestre.get(params.cuatrimestreId)}"
			actividadInstance.titulo = params.tituloAnterior
			redirect action: "edit", params: params
			return
		}
		if (params.fechaFinalizacionDate) {
			actividadInstance.fechaFinalizacion = actividadService.obtenerFecha(params.fechaFinalizacionDate)
			if (actividadInstance.fechaFinalizacion < Utilidades.FECHA) {
				flash.message = "La fecha no puede ser menor a ${Utilidades.FECHA}"
				render view: "edit", model: [actividadInstance: actividadInstance], params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
				return
			}
		}
		if (!actividadService.guardar(actividadInstance)) {
			render view:'edit', model: [actividadInstance: actividadInstance], params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}
		// Una vez que esta habilitada, no se puede editar mas
		if (actividadInstance.visibilidad && !actividadInstance.grupal) {
			actividadService.asignarAlCurso(actividadInstance, params.cursoId.toLong())
		}
		flash.message = "Actividad ${actividadInstance} actualizada"
		redirect action: "index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(Actividad actividadInstance) {
		if (actividadInstance == null) {
			notFound()
			return
		}
		actividadService.eliminar(actividadInstance)
		flash.message = "Actividad ${actividadInstance} eliminada"
		redirect action:"index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId], method:"GET"
	}

	protected void notFound() {
		flash.message = "No se encuentra esa actividad"
		redirect action: "index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId], method: "GET"
	}
}
