package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class GrupoActividadController {

	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def seguridadService
	def grupoActividadService
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def menuAprendiz() {
		params.max = Utilidades.MAX_PARAMS
		
		[aprendices: grupoActividadService.obtenerAprendicesPorActividadPaginado(params.actividadId.toLong(), params.max, params. offset),
			aprendicesCant: grupoActividadService.obtenerAprendicesPorActividad(params.actividadId.toLong()).size(), 
			params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def menuMed() {
		
		params.max = Utilidades.MAX_PARAMS
		
		[aprendices: grupoActividadService.obtenerAprendicesPorActividadPaginado(params.actividadId.toLong(), params.max, params. offset),
			aprendicesCant: grupoActividadService.obtenerAprendicesPorActividad(params.actividadId.toLong()).size(),
			params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def crear() {

		def aprendizId = Aprendiz.findByUsuarioAndCuatrimestre(seguridadService.usuarioActual(), Cuatrimestre.get(params.cuatrimestreId)).id
		def grupoActividadAprendiz = grupoActividadService.obtenerGrupoAprendiz(aprendiz, params.actividadId.toLong())
		def actividad = Actividad.get(params.actividadId)
		
		if (grupoActividadAprendiz) {
			flash.message = "Usted ya pertenece al grupo ${grupoActividadAprendiz.grupo} en la actividad ${actividad}"
			redirect action: "menuAprendiz", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
				'actividadId': params.actividadId]
			return
		}
		
		def numGrupo = GrupoActividad.findAllByActividad(actividad).size() + 1
		
		respond new GrupoActividad(params), model:[numGrupo: numGrupo], params:['cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def muestraAprendiz(GrupoActividad grupoActividadInstance) {	
		def aprendizId = Aprendiz.findByUsuarioAndCuatrimestre(seguridadService.usuarioActual(), Cuatrimestre.get(params.cuatrimestreId)).id

		def participa = grupoActividadService.aprendizParcipa(grupoActividadInstance, aprendiz)
		
		respond grupoActividadInstance, model: [participa: participa], params: ['cursoId' :params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
			'actividadId': params.actividadId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def muestraMed(GrupoActividad grupoActividadInstance) {	
		respond grupoActividadInstance, params: ['cursoId' :params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
			'actividadId': params.actividadId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def cambios() {
		[aprendices: grupoActividadService.obtenerAprendicesPorActividad(params.actividadId.toLong()), params: ['cursoId' :params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def realizarCambio() {

		Integer grupoActividadAprendizId = params.grupoActividadAprendizId.toInteger()
		Integer numeroGrupo = params.numero.size() ? params.numero.toInteger(): 0
		
		def grupoActividadAprendiz = GrupoActividadAprendiz.get(grupoActividadAprendizId)
		
		if (grupoActividadAprendiz.grupo.numero == numeroGrupo) {
			flash.message = "El aprendiz ${grupoActividadAprendiz.aprendiz} ya pertenece al grupo ${numeroGrupo}"
			redirect action: "cambios", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
				'actividadId': params.actividadId]
			return
		}
		
		def actividad = Actividad.get(params.actividadId)
		
		if (!grupoActividadService.realizarCambio(grupoActividadAprendiz, grupoActividadService.obtenerGrupoPorNumero(actividad, numeroGrupo))) {
			flash.message = "El numero de grupo no es válido. Los posibles numeros son ${grupoActividadService.obtenerGrupos(actividad)}"
			redirect action: "cambios", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}
			
		flash.message = "El aprendiz ${grupoActividadAprendiz.aprendiz} ahora pertenece al grupo ${numeroGrupo}"
		redirect action: "menuMed", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
			'actividadId': params.actividadId]
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
			redirect action:"menuAprendiz", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
				'actividadId': params.actividadId], method:"GET"
			return
		}
		
		if (!grupoActividadService.agregarAprendiz(grupoActividadInstance, seguridadService.usuarioActual(), params.cuatrimestreId.toLong())) {
			render view:'crear', model: [grupoCursoInstance: grupoCursoInstance, numGrupo: params.numGrupo],
				params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
			return
		}
		
		flash.message = message(code: 'default.created.message', args: [message(code: 'grupoActividadInstance.label', default: 'GrupoActividad'), grupoActividadInstance.id])
		redirect action: "menuAprendiz", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
			'actividadId': params.actividadId]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def agregarme(GrupoActividad grupoActividadInstance) {		
		if (grupoActividadInstance == null) {
			notFound()
			redirect action:"menuAprendiz", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
				'actividadId': params.actividadId], method:"GET"
			return
		}
	
		def grupoActividadAprendiz = grupoActividadService.obtenerGrupoAprendiz(aprendiz.id, params.actividadId.toLong())
		def actividad = Actividad.get(params.actividadId)
		
		if (grupoActividadAprendiz) {
			flash.message = "Usted ya pertenece al grupo ${grupoActividadAprendiz.grupo} en la actividad ${actividad}"
			redirect action: "menuAprendiz", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId,
				'actividadId': params.actividadId]
			return
		}
		
		if (grupoActividadService.agregarAprendiz(grupoActividadInstance, seguridadService.usuarioActual(), params.cuatrimestreId.toLong())) {
			render view:'mostrarGrupo', model: [grupoActividadInstance: grupoActividadInstance], params:['id': params.id, 'cursoId': params.cursoId, 
				'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'GrupoActividad.label', default: 'GrupoActividad'), grupoActividadInstance.id])
		redirect action: "muestraAprendiz", params:['id': params.id, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
			'actividadId': params.actividadId]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def editarNombre(GrupoActividad grupoActividadInstance) {

		if (grupoActividadInstance == null) {
			notFound()
			redirect action: "menuAprendiz", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId,
				'actividadId': params.actividadId], method: "GET"
			return
		}
		
		if (!grupoActividadService.guardar(grupoActividadInstance)) {
			render view:'editar', model: [grupoActividadInstance: grupoActividadInstance], params:['id': params.id, 'cursoId': params.cursoId, 
				'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadId]
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'GrupoActividad.label', default: 'GrupoActividad'), grupoActividadInstance.id])
		redirect action: "muestraAprendiz", params:['id': params.id, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
			'actividadId': params.actividadId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def eliminar(GrupoActividad grupoActividadInstance) {

		if (grupoActividadInstance == null) {
			notFound()
			redirect action: "menuMed", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
				'actividadId': params.actividadId], method:"GET"
			return
		}
		
		grupoActividadService.eliminar(grupoActividadInstance)
		
		flash.message = message(code: 'default.deleted.message', args: [message(code: 'GrupoActividad.label', default: 'GrupoActividad'), grupoActividadInstance.id])
		redirect action:"menuMed", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
			'actividadId': params.actividadId], method:"GET"
	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupoActividadInstance.label', default: 'GrupoActividad'), params.id])
	}
}
