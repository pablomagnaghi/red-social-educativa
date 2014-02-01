package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class MaterialActividadController {


	def springSecurityService
	
	private usuarioActual() {
		return Usuario.get(springSecurityService.principal.id)
	}
	
	def cursoId
	def actividadId
		
	def general() {
		
		params.max = 5 // Math.min(max ?: 10, 100)
		
		println "material curso general CURSOID: ${params.cursoId}"
		println "material curso general actividadId: ${params.actividadId}"
			
		cursoId = params.cursoId
		actividadId = params.actividadId
					
		def actividad = Actividad.get(actividadId)
		
		[materiales: MaterialActividad.findAllByActividad(actividad, [max: params.max, offset: params.offset]),
			materialesCant: MaterialActividad.findAllByActividad(actividad).size(),
			actividad: actividad, cursoId: cursoId, actividadId: actividadId]
	}

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show(MaterialActividad materialActividadInstance) {

		println "material actividad show: cursoId: ${params.cursoId}, tema Id. ${params.actividadId}"
		println "material Id: ${params.id}"
		
		cursoId = params.cursoId
		actividadId = params.actividadId
		
		respond materialActividadInstance, model: [cursoId: cursoId, actividadId: actividadId]
    }
	
	def create() {
		println "create material tema params: ${params}"
		
		cursoId = params.cursoId
		actividadId = params.actividadId
		
		def mediador = Mediador.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))
		respond new MaterialActividad(params), params:['cursoId': cursoId],
			model:[cursoId: cursoId, actividadId: actividadId, mediador: mediador]
	}
	
    @Transactional
    def save(MaterialActividad materialActividadInstance) {
        if (materialActividadInstance == null) {
            notFound()
            return
        }
		
		cursoId = params.cursoId
		actividadId = params.actividadId

        if (materialActividadInstance.hasErrors()) {
            respond materialActividadInstance.errors, view:'create', params: ['cursoId': cursoId, 'actividadId': actividadId],
			model: [cursoId: cursoId, actividadId: actividadId]
            return
        }

        materialActividadInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'materialActividadInstance.label', default: 'MaterialActividad'), materialActividadInstance.id])
                redirect controller:"actividad", action:"show", params:['id': actividadId, 'cursoId': cursoId, 'actividadId': actividadId]
            }
            '*' { respond materialActividadInstance, [status: CREATED] }
        }
    }

    def edit(MaterialActividad materialActividadInstance) {
		println "edit material tema para,s: ${params}"
		
		cursoId = params.cursoId
		actividadId = params.actividadId
		respond materialActividadInstance, model: [cursoId: cursoId, actividadId: actividadId]
    }

    @Transactional
    def update(MaterialActividad materialActividadInstance) {
		
		println "update material actividad params: ${params}"
		
		cursoId = params.cursoId
		actividadId = params.actividadId
		
        if (materialActividadInstance == null) {
            notFound()
            return
        }

        if (materialActividadInstance.hasErrors()) {
            respond materialActividadInstance.errors, view:'edit', params:['cursoId': cursoId, 'actividadId': actividadId],
				model: [cursoId: cursoId, actividadId: actividadId]
            return
        }

        materialActividadInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MaterialActividad.label', default: 'MaterialActividad'), materialActividadInstance.id])
                redirect action:"show", params:['id': materialActividadInstance.id, 'cursoId': cursoId, 'actividadId': actividadId]
            }
            '*'{ respond materialActividadInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(MaterialActividad materialActividadInstance) {

        if (materialActividadInstance == null) {
            notFound()
            return
        }

		cursoId = params.cursoId
		actividadId = params.actividadId
		
        materialActividadInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MaterialActividad.label', default: 'MaterialActividad'), materialActividadInstance.id])
                redirect controller:"actividad", action:"show", params:['id': actividadId], method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialActividadInstance.label', default: 'MaterialActividad'), params.id])
                redirect controller: "actividad", action:"show", params:['id': actividadId], method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
