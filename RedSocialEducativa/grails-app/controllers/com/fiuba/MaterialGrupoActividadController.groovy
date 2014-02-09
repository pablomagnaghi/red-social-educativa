package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class MaterialGrupoActividadController {
	
	def springSecurityService
	
	private usuarioActual() {
		return Usuario.get(springSecurityService.principal.id)
	}
	
	def cursoId
	def cuatrimestreId
	def actividadId
	def grupoActividadId
	
	def muestraMediador (MaterialGrupoActividad materialGrupoActividadInstance) {
		
		println "material grupo show: cursoId: ${params.cursoId}, tema Id. ${params.temaId}"
		println "material grupo Id: ${params.id}"
		
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
		actividadId = params.actividadId
		grupoActividadId = params.grupoActividadId
		
		respond materialGrupoActividadInstance, model: [cursoId: cursoId, cuatrimestreId: cuatrimestreId,
			actividadId: actividadId, grupoActividadId: grupoActividadId]
	}
	
	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def show(MaterialGrupoActividad materialGrupoActividadInstance) {
		
		println "material grupo show: cursoId: ${params.cursoId}, tema Id. ${params.temaId}"
		println "material grupo Id: ${params.id}"
		
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
		actividadId = params.actividadId
		grupoActividadId = params.grupoActividadId
		
		respond materialGrupoActividadInstance, model: [cursoId: cursoId, cuatrimestreId: cuatrimestreId, 
			actividadId: actividadId, grupoActividadId: grupoActividadId]
	}


	def create() {
		
		println "material grupo params: ${params}"
		
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
		actividadId = params.actividadId
		grupoActividadId = params.grupoActividadId
		
		def aprendizId = Aprendiz.findByUsuarioAndCuatrimestre(usuarioActual(), Cuatrimestre.get(cuatrimestreId)).id
		
		def c = GrupoActividadAprendiz.createCriteria()
		def grupoActividadAprendiz = c {
			eq('grupo.id', grupoActividadId as long)
			eq('aprendiz.id', aprendizId as long)
		}
		
		println "aprendiz del create grupo actividad aprendiz"
		println grupoActividadAprendiz
		
		//def aprendiz = Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))
		respond new MaterialGrupoActividad(params), params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId, 'actividadId': actividadId],
			model:[cursoId: cursoId, cuatrimestreId: cuatrimestreId, actividadId: actividadId, grupoActividadId: grupoActividadId, 
				aprendiz: grupoActividadAprendiz.first().aprendiz]
	}
	
	@Transactional
	def save(MaterialGrupoActividad materialGrupoActividadInstance) {
		if (materialGrupoActividadInstance == null) {
			notFound()
			return
		}

		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
		actividadId = params.actividadId
		grupoActividadId = params.grupoActividadId
		
		
		if (materialGrupoActividadInstance.hasErrors()) {
			respond materialGrupoActividadInstance.errors, view:'create', params: ['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId, 'grupoActividadId': grupoActividadId],
			model: [cursoId: cursoId, cuatrimestreId: cuatrimestreId, actividadId: actividadId, grupoActividadId: grupoActividadId]
			return
		}

		materialGrupoActividadInstance.save flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.created.message', args: [message(code: 'materialGrupoActividadInstance.label', 
					default: 'MaterialGrupoActividad'), materialGrupoActividadInstance.id])
				redirect controller:"grupoActividad", action:"mostrarGrupo", params:['id': grupoActividadId, 
					'cursoId': cursoId, 'cuatrimestreId': cuatrimestreId, 'actividadId': actividadId, 'grupoActividadId': grupoActividadId]
			}
			'*' { respond materialGrupoInstance, [status: CREATED] }
		}
	}

	def edit(MaterialGrupoActividad materialGrupoActividadInstance) {
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
		actividadId = params.actividadId
		grupoActividadId = params.grupoActividadId
		
		respond materialGrupoActividadInstance, model: [cursoId: cursoId, cuatrimestreId: cuatrimestreId, actividadId: actividadId, 
			grupoActividadId: grupoActividadId]
	}

	@Transactional
	def update(MaterialGrupoActividad materialGrupoActividadInstance) {
			
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
		actividadId = params.actividadId
		grupoActividadId = params.grupoActividadId
		
		if (materialGrupoActividadInstance == null) {
			notFound()
			return
		}

		if (materialGrupoActividadInstance.hasErrors()) {
			respond materialGrupoActividadInstance.errors, view:'edit', params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId, 
				'actividadId': actividadId, 'grupoActividadId': grupoActividadId],
				model: [cursoId: cursoId, cuatrimestreId: cuatrimestreId, actividadId: actividadId, grupoActividadId: grupoActividadId]
			return
		}

		materialGrupoActividadInstance.save flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'MaterialGrupoActividad.label', 
					default: 'MaterialGrupoActividad'), materialGrupoActividadInstance.id])
				redirect action:"show", params:['id': materialGrupoActividadInstance.id, 'cursoId': cursoId, 'cuatrimestreId': cuatrimestreId, 
					'actividadId': actividadId, 'grupoActividadId': grupoActividadId]
			}
			'*'{ respond materialGrupoActividadInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(MaterialGrupoActividad materialGrupoActividadInstance) {

		if (materialGrupoActividadInstance == null) {
			notFound()
			return
		}

		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
		actividadId = params.actividadId
		grupoActividadId = params.grupoActividadId
		
		materialGrupoActividadInstance.delete flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'MaterialGrupoActividad.label', 
					default: 'MaterialGrupo'), materialGrupoActividadInstance.id])
				redirect controller:"grupoActividad", action:"mostrarGrupo", params:['id': grupoActividadId, 
					'cursoId': cursoId, 'cuatrimestreId': cuatrimestreId, 'actividadId': actividadId], method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialGrupoActividadInstance.label', 
					default: 'MaterialGrupoActividad'), params.id])
				redirect controller: "grupoActividad", action:"mostrar", params:['id': grupoActividadId, 
					'cursoId': cursoId, 'cuatrimestreId': cuatrimestreId, 'actividadId': actividadId], method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}

