package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class MaterialGrupoActividadController {
	
	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def usuarioService
	def materialGrupoActividadService
	def grupoActividadService
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def muestraMediador (MaterialGrupoActividad materialGrupoActividadInstance) {
		respond materialGrupoActividadInstance, params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId,
			'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def show(MaterialGrupoActividad materialGrupoActividadInstance) {
		respond materialGrupoActividadInstance, params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId,
			'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def create() {
		println "CREATET"
		def aprendiz = Aprendiz.findByUsuarioAndCuatrimestre(usuarioService.usuarioActual(), Cuatrimestre.get(params.cuatrimestreId))
		def grupoActividadAprendiz = grupoActividadService.aprendizParticipa(GrupoActividad.get(params.grupoActividadId), aprendiz)
		
		respond new MaterialGrupoActividad(params), model:[aprendiz: grupoActividadAprendiz.aprendiz], params:['cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def save(MaterialGrupoActividad materialGrupoActividadInstance) {
		if (materialGrupoActividadInstance == null) {
			notFound()
			return
		}

		if (materialGrupoActividadService.existe(materialGrupoActividadInstance, params.grupoActividadId.toLong())) {
			flash.message = "Ya existe el material ${materialGrupoActividadInstance.titulo} en el grupo ${GrupoActividad.get(params.grupoActividadId)}"
			redirect action: "create", params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
				'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]
			return
		}
		
		if (!materialGrupoActividadService.guardar(materialGrupoActividadInstance)) {
			def aprendiz = Aprendiz.findByUsuarioAndCuatrimestre(usuarioService.usuarioActual(), Cuatrimestre.get(params.cuatrimestreId))
			def grupoActividadAprendiz = grupoActividadService.aprendizParticipa(GrupoActividad.get(params.grupoActividadId), aprendiz)
			
			render view:'create', model: [materialGrupoActividadInstance: materialGrupoActividadInstance, aprendiz: grupoActividadAprendiz.aprendiz],
				params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, 
					'grupoActividadId': params.grupoActividadId]
			return
		}
		
		flash.message = message(code: 'default.created.message', args: [message(code: 'materialGrupoActividadInstance.label', default: 'MaterialGrupoActividad'), materialGrupoActividadInstance.id])
		redirect controller:"grupoActividad", action:"muestraAprendiz", params:['id': params.grupoActividadId,  'cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def edit(MaterialGrupoActividad materialGrupoActividadInstance) {
		respond materialGrupoActividadInstance, params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
			'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def update(MaterialGrupoActividad materialGrupoActividadInstance) {
		if (materialGrupoActividadInstance == null) {
			notFound()
			return
		}

		if (!materialGrupoActividadService.guardar(materialGrupoActividadInstance)) {
			render view:'edit', model: [materialGrupoActividadInstance: materialGrupoActividadInstance],
				params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId,
					'grupoActividadId': params.grupoActividadId]
			return
		}
		
		flash.message = message(code: 'default.updated.message', args: [message(code: 'MaterialGrupoActividad.label', default: 'MaterialGrupoActividad'), materialGrupoActividadInstance.id])
		redirect action:"show", params:['id': materialGrupoActividadInstance.id, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
			'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def delete(MaterialGrupoActividad materialGrupoActividadInstance) {

		if (materialGrupoActividadInstance == null) {
			notFound()
			return
		}

		materialGrupoActividadService.eliminar(materialGrupoActividadInstanc)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'MaterialGrupoActividad.label', default: 'MaterialGrupo'), materialGrupoActividadInstance.id])
		redirect controller:"grupoActividad", action:"muestraAprendiz", params:['id': params.grupoActividadId, 'cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId], method:"GET"
	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialGrupoActividadInstance.label', default: 'MaterialGrupoActividad'), params.id])
		redirect controller: "grupoActividad", action:"mostrar", params:['id': params.grupoActividadId, 
			'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId], method: "GET"
	}
}
