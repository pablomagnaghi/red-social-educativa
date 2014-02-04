package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class GrupoActividadController {

	def springSecurityService
	
	private usuarioActual() {
		return Usuario.get(springSecurityService.principal.id)
	}
	
	def cursoId
	def actividadId
	
	def general(Integer max) {
		params.max = 5//Math.min(max ?: 10, 100)
		println "index grupo"
		println params
		
		cursoId = params.cursoId
		actividadId = params.actividadId

		def c = GrupoActividadAprendiz.createCriteria()
		def aprendices = c.list([max: params.max, offset: params.offset]) {
			grupo {
				eq('actividad.id', actividadId as long)
			}
		}

		def s = GrupoActividadAprendiz.createCriteria()
		def aprendicesCant = s.list() {
			grupo {
				eq('actividad.id', actividadId as long)
			}
		}

		println "aprendices"
		println aprendices
		
		[aprendices: aprendices, aprendicesCant: aprendicesCant.size(), cursoId: cursoId, actividadId: actividadId]
	}
	

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def menuMediador(Integer max) {
		params.max = 5//Math.min(max ?: 10, 100)
		println "index grupo"
		println params
		
		cursoId = params.cursoId
		actividadId = params.actividadId

		def c = GrupoActividadAprendiz.createCriteria()
		def aprendices = c.list([max: params.max, offset: params.offset]) {
			grupo {
				eq('actividad.id', actividadId as long)
			}
		}

		def s = GrupoActividadAprendiz.createCriteria()
		def aprendicesCant = s.list() {
			grupo {
				eq('actividad.id', actividadId as long)
			}
		}

		println "aprendices"
		println aprendices
		
		[aprendices: aprendices, aprendicesCant: aprendicesCant.size(), cursoId: cursoId, actividadId: actividadId]
	}
	
	def crear() {
		println "create grupo curso params: ${params}"
		cursoId = params.cursoId
		actividadId = params.actividadId
		
		def actividad = Actividad.get(actividadId)
		
		println "cantidad de grupos del curso: ${Curso.get(cursoId)}"
		println GrupoActividad.findAllByActividad(actividad).size()
		
		def numGrupo = GrupoActividad.findAllByActividad(actividad).size() + 1
		def aprendizId = Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId)).id
		
		println "existe aprendiz Id"
		
		println aprendizId
		
		def c = GrupoActividadAprendiz.createCriteria()
		def aprendiz = c {
			grupo {
				eq('actividad.id', actividadId as long)
			}
			eq('aprendiz.id', aprendizId as long)
		}

		if (aprendiz) {
			flash.message = "Usted ya pertenece al grupo ${aprendiz[0].grupo} en la actividad ${Actividad.get(actividadId)}"
			redirect action: "general", params:['cursoId': cursoId, 'actividadId': actividadId]
			return
		}
		
		println "numero de grupo: ${numGrupo}"
		
		respond new GrupoActividad(params), params:['cursoId': cursoId, 'actividadId': actividadId], 
			model:[cursoId: cursoId, numGrupo: numGrupo, actividadId: actividadId]
	}
	
	def mostrarGrupo(GrupoActividad grupoActividadInstance) {
		println "mostrar params: ${params}"
		
		println "aprendiz: ${Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))}"
		cursoId = params.cursoId
		actividadId = params.actividadId
		
		def aprendizId = Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId)).id
		
		def c = GrupoActividadAprendiz.createCriteria()
		def aprendiz = c {
			eq('grupo.id', params.id as long)
			eq('aprendiz.id', aprendizId as long)
		}
		
		println "mostrar: contiene: ${aprendiz}"
		
		def participa = false
		
		// Si participa en el grupo puede editar el nombre del mismo
		if (aprendiz) {
			participa = true
		}
		
		respond grupoActividadInstance, model: [cursoId: cursoId, , actividadId: actividadId, participa: participa]
	}
	
	def muestraMediador(GrupoActividad grupoActividadInstance) {
		println "muestra mediador params: ${params}"
		
		cursoId = params.cursoId
		actividadId = params.actividadId
		
		println "cursoId: ${cursoId}"
		println "actividadId: ${actividadId}"
		
		respond grupoActividadInstance, model: [cursoId: cursoId, actividadId: actividadId]

	}
	
	def menuCambios() {
		cursoId = params.cursoId
		actividadId = params.actividadId
		
		println "menu cambiso: curso ID: ${cursoId}"
		
		def c = GrupoActividadAprendiz.createCriteria()
		def aprendices = c {
			grupo {
				eq('actividad.id', actividadId as long)
			}
		}

		[aprendices: aprendices, cursoId:cursoId, actividadId: actividadId]
	}
	
	def realizarCambio() {
		cursoId = params.cursoId
		actividadId = params.actividadId
		
		if (params.numero.size()) {
		
			Integer grupoActividadAprendizId = params.grupoActividadAprendizId.toInteger()
			Integer numeroGrupo = params.numero.toInteger()
			
			def actividad = Actividad.get(actividadId)
			
			println "cantidad de grupos del curso: ${Curso.get(cursoId)}"
			println GrupoActividad.findAllByActividad(actividad).size()
			
			Integer cantGrupos = GrupoActividad.findAllByActividad(actividad).size() 
			
			println "REALIZAR CAMBIOS"
			
			println "numero de grupo: ${numeroGrupo}"
			println "cantGrupos: ${cantGrupos}"
			println "grupoActividadAprendizId: ${grupoActividadAprendizId}"
			
			println "aprendiz: ${GrupoActividadAprendiz.get(grupoActividadAprendizId)}"
			
			println "grupoId: ${GrupoActividadAprendiz.get(grupoActividadAprendizId).grupo.id}"
			println "aprendizId: ${GrupoActividadAprendiz.get(grupoActividadAprendizId).aprendiz.id}"
			
			
			if ((numeroGrupo > cantGrupos) || (numeroGrupo < 1)) {
				flash.message = "El numero de grupo ingresado no existe." +
				"Los numero de grupo van del 1 hasta el ${cantGrupos}"
				redirect action: "menuCambios", params:['cursoId': cursoId, 'actividadId': actividadId]
				return
			}
		
			def grupoActividadAprendiz = GrupoActividadAprendiz.get(grupoActividadAprendizId)
			
			if (grupoActividadAprendiz.grupo.numero == numeroGrupo) {
				flash.message = "El aprendiz ${grupoActividadAprendiz.aprendiz} ya pertenece al grupo ${numeroGrupo}"
				redirect action: "menuCambios", params:['cursoId': cursoId, 'actividadId': actividadId]
				return
			}
			
			def grupoActividad = GrupoActividad.findByActividadAndNumero(actividad, numeroGrupo)
			
			println "grupoActividad: ${grupoActividad}"
			println "HASSSSSSSSSSSSSSSSSSSSSTA ACA"
	
			println "grupo acitividad previo"
			println grupoActividadAprendiz.grupo
			
			grupoActividadAprendiz.grupo = grupoActividad
			
			println "COMO QUEDARIA"
			println "grupo acitividad"
			println grupoActividadAprendiz.grupo
			println "grupo acitividad aprendiz"
			println grupoActividadAprendiz.aprendiz
		
			grupoActividadAprendiz.save flush:true
			
			println "cambios realizados"
			
			flash.message = "El aprendiz ${grupoActividadAprendiz.aprendiz} " +
			"ahora pertenece al grupo ${numeroGrupo}"
			
		} else {
			flash.message = "No existe ese numero ese numero de grupo"
			redirect action: "menuCambios", params:['cursoId': cursoId, 'actividadId': actividadId]
			return
		}
		redirect action: "menuMediador", params:['cursoId': cursoId, 'actividadId': actividadId]
		
	}
	
	def editar(GrupoActividad grupoActividadInstance) {
		cursoId = params.cursoId
		actividadId = params.actividadId
		println grupoActividadInstance.aprendices
		
		respond grupoActividadInstance, params:['cursoId': cursoId, 'actividadId': actividadId], 
			model:[cursoId: cursoId, actividadId: actividadId]
	}
	
	@Transactional
	def guardar(GrupoActividad grupoActividadInstance) {

		if (grupoActividadInstance == null) {
			notFound()
			return
		}
		
		println "Save grupo"
		println "params: ${params}"
		println "properties grupoCursoInstance.properties"
		
		cursoId = params.cursoId
		actividadId = params.actividadId
		def numGrupo = params.numGrupo
		
		
		if (grupoActividadInstance.hasErrors()) {
			//aprendiz = Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))
			respond grupoActividadInstance.errors, view:'crear', params:['cursoId': cursoId, 'actividadId': actividadId],
				model: [cursoId: cursoId, actividadId: actividadId, numGrupo: numGrupo]
			return
		}

		grupoActividadInstance.save flush:true
		
		def aprendiz = Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))
		
		def grupoActividadAprendiz = new GrupoActividadAprendiz(aprendiz: aprendiz, grupo: grupoActividadInstance)
		
		grupoActividadAprendiz.save flush:true
		
		request.withFormat {
			form {
				flash.message = message(code: 'default.created.message', args: [message(code: 'grupoActividadInstance.label', 
					default: 'GrupoActividad'), grupoActividadInstance.id])
				redirect action: "general", params:['cursoId': cursoId, 'actividadId': actividadId]
			}
			'*' { respond grupoActividadInstance, [status: CREATED] }
		}
	}

	@Transactional
	def agregarme(GrupoActividad grupoActividadInstance) {
		
		cursoId = params.cursoId
		actividadId = params.actividadId
		def aprendizId = Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId)).id
		
		println "existe aprendiz Id"
		println aprendizId
		
		def c = GrupoActividadAprendiz.createCriteria()
		def aprendiz = c {
			grupo {
				eq('actividad.id', actividadId as long)
			}
			eq('aprendiz.id', aprendizId as long)
		}

		if (aprendiz) {
			flash.message = "Usted ya pertenece al grupo ${aprendiz[0].grupo} en la actividad ${Actividad.get(actividadId)}"
			redirect action: "general", params:['cursoId': cursoId, 'actividadId': actividadId]
			return
		}
		
		println "udpate"
		
		println grupoActividadInstance.properties
		println grupoActividadInstance.aprendices
		
		
		if (grupoActividadInstance == null) {
			notFound()
			return
		}

		println "aprendiz: ${aprendiz}"
		
		//grupoActividadInstance.addToAprendices(aprendiz)
		
		if (grupoActividadInstance.hasErrors()) {
			respond grupoActividadInstance.errors, view:'mostrarGrupo', params:['id': params.id, 'cursoId': cursoId, 'actividadId': actividadId], 
				model: [cursoId: cursoId, actividadId: actividadId]
			return
		}

		grupoActividadInstance.save flush:true
		
		def grupoActividadAprendiz = new GrupoActividadAprendiz(aprendiz: Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId)), 
			grupo: grupoActividadInstance)
		
		grupoActividadAprendiz.save flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'GrupoActividad.label', default: 'GrupoActividad'), 
					grupoActividadInstance.id])
				redirect action: "mostrarGrupo", params:['id': params.id, 'cursoId': cursoId, 'actividadId': actividadId]
			}
			'*'{ respond grupoActividadInstance, [status: OK] }
		}
	}

	@Transactional
	def editarNombre(GrupoActividad grupoActividadInstance) {
		println "editar nombre"
		
		cursoId = params.cursoId
		actividadId = params.actividadId
		
		if (grupoActividadInstance == null) {
			notFound()
			return
		}

		if (grupoActividadInstance.hasErrors()) {
			respond grupoActividadInstance.errors, view:'editar', params:['id': params.id, 'cursoId': cursoId, 'actividadId': actividadId], 
				model: [cursoId: cursoId, actividadId: actividadId]
			return
		}

		grupoActividadInstance.save flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'GrupoActividad.label', default: 'GrupoActividad'), 
					grupoActividadInstance.id])
				redirect action: "mostrarGrupo", params:['id': params.id, 'cursoId': cursoId, 'actividadId': actividadId]
			}
			'*'{ respond grupoActividadInstance, [status: OK] }
		}
	}
		
	@Transactional
	def delete(GrupoActividad grupoActividadInstance) {

		if (grupoActividadInstance == null) {
			
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupoActividadInstance.label', 
					default: 'GrupoActividad'), params.id])
			redirect action:"menuMediador", params:['cursoId': cursoId, 'actividadId': actividadId], method:"GET"
			
			return
		}

		println "delete"
		
		println "grupo  a eliminar: "
		
		cursoId = params.cursoId
		actividadId = params.actividadId
		
		println "cursoId: ${cursoId}"
		println "actividadId: ${actividadId}"
		
		grupoActividadInstance.delete flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'GrupoActividad.label', default: 'GrupoActividad'), 
					grupoActividadInstance.id])
				redirect action:"menuMediador", params:['cursoId': cursoId, 'actividadId': actividadId], method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupoActividadInstance.label', 
					default: 'GrupoActividad'), params.id])
				redirect action: "general", params:['cursoId': cursoId, 'actividadId': actividadId], method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
