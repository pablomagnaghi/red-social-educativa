package com.material

import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class MaterialActividadController {

	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def usuarioService
	def materialActividadService

	@Secured("hasRole('ROL_APRENDIZ')")
	def materialAprendiz() {
		params.max = Utilidades.MAX_PARAMS
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		[materiales: MaterialActividad.findAllByActividad(Actividad.get(params.actividadId)), mediador: mediador,
			params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def show(MaterialActividad materialActividadInstance) {
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		respond materialActividadInstance, model: [mediador: mediador, params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId,
			'actividadId': params.actividadId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		respond new MaterialActividad(params), model:[mediador: mediador], params: ['cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def save(MaterialActividad materialActividadInstance) {
		if (materialActividadInstance == null) {
			notFound()
			return
		}
		if (materialActividadService.existe(materialActividadInstance, params.actividadId.toLong())) {
			flash.message = "Ya existe el material ${materialActividadInstance.titulo} en la actividad ${Actividad.get(params.actividadId)}"
			redirect action: "create", params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
			return
		}
		if (!materialActividadService.guardar(materialActividadInstance)) {
			def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
			render view:'create', model: [materialActividadInstance: materialActividadInstance, mediador: mediador],
			params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
			return
		}
		flash.message = "Material ${materialActividadInstance} creado"
		redirect controller:"actividad", action:"index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def edit(MaterialActividad materialActividadInstance) {
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		respond materialActividadInstance, model: [mediador:mediador, params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
			'actividadId': params.actividadId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def update(MaterialActividad materialActividadInstance) {
		if (materialActividadInstance == null) {
			notFound()
			return
		}
		if (!materialActividadService.guardar(materialActividadInstance)) {
			def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
			render view:'edit', model: [materialActividadInstance: materialActividadInstance, mediador: mediador],
			params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
			return
		}
		flash.message = "Material ${materialActividadInstance} actualizado"
		redirect controller:"actividad", action:"index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(MaterialActividad materialActividadInstance) {
		if (materialActividadInstance == null) {
			notFound()
			return
		}
		materialActividadService.eliminar(materialActividadInstance)
		flash.message = "Material ${materialActividadInstance} eliminado"
		redirect controller:"actividad", action:"index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId], method:"GET"
	}

	protected void notFound() {
		flash.message = "No se encuentra ese material"
		redirect controller: "actividad", action:"index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId], method: "GET"
	}
}
