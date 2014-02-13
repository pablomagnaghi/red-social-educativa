package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class MaterialGrupoController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def seguridadService
	def materialGrupoService

	@Secured("hasRole('ROL_MEDIADOR')")
	def muestraMediador (MaterialGrupo materialGrupoInstance) {
		respond materialGrupoInstance, params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'grupoId': params.grupoId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def show(MaterialGrupo materialGrupoInstance) {
		respond materialGrupoInstance, params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'grupoId': params.grupoId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def create() {

		def aprendiz = Aprendiz.findByUsuarioAndCuatrimestre(seguridadService.usuarioActual(), Cuatrimestre.get(params.cuatrimestreId))
		
		respond new MaterialGrupo(params), model:[aprendiz: aprendiz],
			 params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'grupoId': params.grupoId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def save(MaterialGrupo materialGrupoInstance) {
		if (materialGrupoInstance == null) {
			notFound()
			return
		}
		
		if (!materialGrupoService.guardar(materialGrupoInstance)) {
			render view:'create', model: [materialGrupoInstance: materialGrupoInstance],
				params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'grupoId': params.grupoId]
			return
		}
		
		flash.message = message(code: 'default.created.message', args: [message(code: 'materialGrupoInstance.label', default: 'MaterialGrupo'), materialGrupoInstance.id])
		redirect controller:"grupoCurso", action:"muestraAprendiz", 
			params:['id': params.grupoId, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def edit(MaterialGrupo materialGrupoInstance) {
		respond materialGrupoInstance, params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'grupoId': params.grupoId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def update(MaterialGrupo materialGrupoInstance) {

		if (materialGrupoInstance == null) {
			notFound()
			return
		}

		if (!materialGrupoService.guardar(materialGrupoInstance)) {
			render view:'edit', model: [materialGrupoInstance: materialGrupoInstance],
				params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'grupoId': params.grupoId]
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'MaterialGrupo.label', default: 'MaterialGrupo'), materialGrupoInstance.id])
		redirect action:"show", params:['id': materialGrupoInstance.id, 'cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId, 'grupoId': params.grupoId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def delete(MaterialGrupo materialGrupoInstance) {

		if (materialGrupoInstance == null) {
			notFound()
			return
		}

		materialGrupoService.eliminar(materialGrupoInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'MaterialGrupo.label', default: 'MaterialGrupo'), materialGrupoInstance.id])
		redirect controller:"grupoCurso", action:"muestraAprendiz", 
			params:['id': params.grupoId, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId], method:"GET"

	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialGrupoInstance.label', default: 'MaterialGrupo'), params.id])
		redirect controller: "grupoCurso", action:"muestraAprendiz", 
			params:['id': params.grupoId, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId], method: "GET"
	}
}

