package com.cursado

import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class GrupoActividadController {

	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def usuarioService
	def grupoActividadService
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def gruposAprendiz() {
		params.max = Utilidades.MAX_PARAMS
		def aprendiz = Aprendiz.findByUsuarioAndCuatrimestre(usuarioService.usuarioActual(), Cuatrimestre.get(params.cuatrimestreId))
		[gruposActividad: GrupoActividad.findAllByActividad(Actividad.get(params.actividadId)), aprendiz: aprendiz,
			params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def grupoAprendiz(GrupoActividad grupoActividadInstance) {
		def aprendiz = Aprendiz.findByUsuarioAndCuatrimestre(usuarioService.usuarioActual(), Cuatrimestre.get(params.cuatrimestreId))
		respond grupoActividadInstance, params: ['cursoId' :params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def gruposMediador() {
		params.max = Utilidades.MAX_PARAMS
		[gruposActividad: GrupoActividad.findAllByActividad(Actividad.get(params.actividadId)),
			params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def grupoMediador(GrupoActividad grupoActividadInstance) {
		def medidaor = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		respond grupoActividadInstance, params: ['cursoId' :params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def crearGrupo() {
		def aprendiz = Aprendiz.findByUsuarioAndCuatrimestre(usuarioService.usuarioActual(), Cuatrimestre.get(params.cuatrimestreId))
		def grupoActividadAprendiz = grupoActividadService.obtenerGrupoAprendiz(aprendiz, params.actividadId.toLong())
		def actividad = Actividad.get(params.actividadId)
		if (grupoActividadAprendiz) {
			flash.message = "Usted ya pertenece al grupo ${grupoActividadAprendiz.grupo} en la actividad ${actividad}"
			redirect action: "gruposAprendiz", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
			return
		}
		def numGrupo = GrupoActividad.findAllByActividad(actividad).size() + 1
		respond new GrupoActividad(params), model:[numGrupo: numGrupo], params:['cursoId': params.cursoId,
			'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def cambiarAprendiz() {
		[aprendices: grupoActividadService.obtenerAprendicesPorActividad(params.actividadId.toLong()), params: ['cursoId' :params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def realizarCambio() {
		Integer grupoActividadAprendizId = params.grupoActividadAprendizId.toInteger()
		Integer numeroGrupo = params.numero.size() ? params.numero.toInteger(): 0
		def grupoActividadAprendiz = GrupoActividadAprendiz.get(grupoActividadAprendizId)
		if (grupoActividadAprendiz.grupo.numero == numeroGrupo) {
			flash.message = "El aprendiz ya pertenece al grupo ${numeroGrupo}"
			redirect action: "cambiarAprendiz", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
				'actividadId': params.actividadId]
			return
		}
		def actividad = Actividad.get(params.actividadId)
		if (!grupoActividadService.realizarCambio(grupoActividadAprendiz, grupoActividadService.obtenerGrupoPorNumero(actividad, numeroGrupo))) {
			flash.message = "El numero de grupo no es válido. Los posibles numeros son ${grupoActividadService.obtenerGrupos(actividad)}"
			redirect action: "cambiarAprendiz", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
			return
		}
		flash.message = "El aprendiz ahora pertenece al grupo ${numeroGrupo}"
		redirect action: "gruposMediador", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def editar(GrupoActividad grupoActividadInstance) {
		respond grupoActividadInstance, params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
			'actividadId': params.actividadId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def guardar(GrupoActividad grupoActividadInstance) {
		if (grupoActividadInstance == null) {
			notFound()
			redirect action:"gruposAprendiz", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
				'actividadId': params.actividadId], method:"GET"
			return
		}
		if (!grupoActividadService.agregarAprendiz(grupoActividadInstance, usuarioService.usuarioActual(), params.cuatrimestreId.toLong())) {
			render view:'crearGrupo', model: [grupoActividadInstance: grupoActividadInstance, numGrupo: params.numGrupo],
				params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
			return
		}
		flash.message = "Bienvenido al grupo ${grupoActividadInstance}"
		redirect action: "grupoAprendiz", params:['id': grupoActividadInstance.id, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
			'actividadId': params.actividadId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def agregarme(GrupoActividad grupoActividadInstance) {		
		if (grupoActividadInstance == null) {
			notFound()
			redirect action:"gruposAprendiz", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId], method:"GET"
			return
		}
		def aprendiz = Aprendiz.findByUsuarioAndCuatrimestre(usuarioService.usuarioActual(), Cuatrimestre.get(params.cuatrimestreId))
		def grupoActividadAprendiz = grupoActividadService.obtenerGrupoAprendiz(aprendiz, params.actividadId.toLong())
		def actividad = Actividad.get(params.actividadId)
		if (grupoActividadAprendiz) {
			flash.message = "Usted ya pertenece al grupo ${grupoActividadAprendiz.grupo} en la actividad ${actividad}"
			redirect action: "gruposAprendiz", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
			return
		}
		if (!grupoActividadService.agregarAprendiz(grupoActividadInstance, usuarioService.usuarioActual(), params.cuatrimestreId.toLong())) {
			flash.message = "Problemas al agregarse al grupo ${grupoActividadInstance} en la actividad ${actividad}"
			redirect action: "gruposAprendiz", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
		}
		flash.message = "Bienvenido al grupo ${grupoActividadInstance}"
		redirect action: "grupoAprendiz", params:['id': grupoActividadInstance.id, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
			'actividadId': params.actividadId]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def editarNombre(GrupoActividad grupoActividadInstance) {
		if (grupoActividadInstance == null) {
			notFound()
			redirect action: "grupoAprendiz", params: params, method: "GET"
			return
		}
		if (!grupoActividadService.guardar(grupoActividadInstance)) {
			render view:'editar', model: [grupoActividadInstance: grupoActividadInstance], params: params
			return
		}
		flash.message = "Nombre del grupo ${grupoActividadInstance} actualizado"
		redirect action: "grupoAprendiz", params: params
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def eliminar(GrupoActividad grupoActividadInstance) {
		if (grupoActividadInstance == null) {
			notFound()
			redirect action: "gruposMediador", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
				'actividadId': params.actividadId], method:"GET"
			return
		}
		grupoActividadService.eliminar(grupoActividadInstance)
		flash.message = "Grupo ${grupoActividadInstance} eliminado"
		redirect action:"gruposMediador", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
			'actividadId': params.actividadId], method:"GET"
	}

	protected void notFound() {
		flash.message = "No se encuentra ese grupo"
	}
}
