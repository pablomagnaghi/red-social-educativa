package com.material

import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class MaterialGrupoActividadController {
	
	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def usuarioService
	def materialGrupoActividadService
	def grupoActividadService
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def materialAprendiz (MaterialGrupoActividad materialGrupoActividadInstance) {
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
		flash.message = "Material ${materialGrupoActividadInstance} creado"
		redirect controller:"grupoActividad", action:"gruposAprendiz", params:['id': params.grupoActividadId,  'cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def edit(MaterialGrupoActividad materialGrupoActividadInstance) {
		def aprendiz = Aprendiz.findByUsuarioAndCuatrimestre(usuarioService.usuarioActual(), Cuatrimestre.get(params.cuatrimestreId))
		def grupoActividadAprendiz = grupoActividadService.aprendizParticipa(GrupoActividad.get(params.grupoActividadId), aprendiz)
		respond materialGrupoActividadInstance,  model:[aprendiz: grupoActividadAprendiz.aprendiz], params: ['cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def update(MaterialGrupoActividad materialGrupoActividadInstance) {
		if (materialGrupoActividadInstance == null) {
			notFound()
			return
		}
		if (!materialGrupoActividadService.guardar(materialGrupoActividadInstance)) {
			def aprendiz = Aprendiz.findByUsuarioAndCuatrimestre(usuarioService.usuarioActual(), Cuatrimestre.get(params.cuatrimestreId))
			def grupoActividadAprendiz = grupoActividadService.aprendizParticipa(GrupoActividad.get(params.grupoActividadId), aprendiz)
			render view:'edit', model: [materialGrupoActividadInstance: materialGrupoActividadInstance, aprendiz: grupoActividadAprendiz.aprendiz],
				params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId,
					'grupoActividadId': params.grupoActividadId]
			return
		}
		flash.message = "Material ${materialGrupoActividadInstance} actualizado"
		redirect action:"show", params:['id': materialGrupoActividadInstance.id, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
			'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def delete(MaterialGrupoActividad materialGrupoActividadInstance) {
		if (materialGrupoActividadInstance == null) {
			notFound()
			return
		}
		materialGrupoActividadService.eliminar(materialGrupoActividadInstance)
		flash.message = "Material ${materialGrupoActividadInstance} eliminado"
		redirect controller:"grupoActividad", action:"gruposAprendiz", params:['cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId], method:"GET"
	}

	protected void notFound() {
		flash.message = "No se encuentra ese material"
		redirect controller: "grupoActividad", action:"mostrar", params:['id': params.grupoActividadId, 
			'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId], method: "GET"
	}
}
