package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class TemaActividadController {

	def springSecurityService
	
	private usuarioActual() {
		return Usuario.get(springSecurityService.principal.id)
	}
	
	def cursoId
	def actividadId
		
	def general() {
		
		params.max = 5 // Math.min(max ?: 10, 100)
		
		println "foro Tema general CURSOID: ${params.cursoId}"
		println "foro Tema general actividadId: ${params.actividadId}"
			
		cursoId = params.cursoId
		actividadId = params.actividadId
					
		def actividad = Actividad.get(actividadId)
		
		[temas: TemaActividad.findAllByActividad(actividad, [max: params.max, offset: params.offset]),
			temasCant: TemaActividad.findAllByActividad(actividad).size(),
			actividad: actividad, cursoId: cursoId, actividadId: actividadId]
	}

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
    def show(TemaActividad temaActividadInstance) {
		
		println "material actividad show: cursoId: ${params.cursoId}, tema Id. ${params.actividadId}"
		println "material Id: ${params.id}"
		
		cursoId = params.cursoId
		actividadId = params.actividadId
		
		respond temaActividadInstance, model: [cursoId: cursoId, actividadId: actividadId]

    }

    def create() {
       	println "create material tema params: ${params}"
		
		cursoId = params.cursoId
		actividadId = params.actividadId
		
		respond new TemaActividad(params), params:['cursoId': cursoId],
			model:[cursoId: cursoId, actividadId: actividadId]
    }

    @Transactional
    def save(TemaActividad temaActividadInstance) {
        if (temaActividadInstance == null) {
            notFound()
            return
        }

		cursoId = params.cursoId
		actividadId = params.actividadId
		
        if (temaActividadInstance.hasErrors()) {
            respond temaActividadInstance.errors, view:'create', params: ['cursoId': cursoId, 'actividadId': actividadId],
			model: [cursoId: cursoId, actividadId: actividadId]
            return
        }

        temaActividadInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'temaActividadInstance.label', default: 'TemaActividad'), temaActividadInstance.id])
                redirect controller:"actividad", action:"show", params:['id': actividadId, 'cursoId': cursoId, 'actividadId': actividadId]
            }
            '*' { respond temaActividadInstance, [status: CREATED] }
        }
    }

    def edit(TemaActividad temaActividadInstance) {
		println "edit material tema params: ${params}"
		
		cursoId = params.cursoId
		actividadId = params.actividadId
		respond temaActividadInstance, model: [cursoId: cursoId, actividadId: actividadId]
    }

    @Transactional
    def update(TemaActividad temaActividadInstance) {
       
		println "update material actividad params: ${params}"
		
		cursoId = params.cursoId
		actividadId = params.actividadId
		
		if (temaActividadInstance == null) {
            notFound()
            return
        }

        if (temaActividadInstance.hasErrors()) {
            respond temaActividadInstance.errors, view:'edit', params:['cursoId': cursoId, 'actividadId': actividadId],
				model: [cursoId: cursoId, actividadId: actividadId]
            return
        }

        temaActividadInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TemaActividad.label', default: 'TemaActividad'), temaActividadInstance.id])
                redirect action:"show", params:['id': temaActividadInstance.id, 'cursoId': cursoId, 'actividadId': actividadId]
            }
            '*'{ respond temaActividadInstance, [status: OK] }
        }
    }
	
    @Transactional
    def delete(TemaActividad temaActividadInstance) {

        if (temaActividadInstance == null) {
            notFound()
            return
        }
		
		cursoId = params.cursoId
		actividadId = params.actividadId

        temaActividadInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TemaActividad.label', default: 'TemaActividad'), temaActividadInstance.id])
                redirect controller:"actividad", action:"show", params:['id': actividadId], method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'temaActividadInstance.label', default: 'TemaActividad'), params.id])
                redirect controller: "actividad", action:"show", params:['id': actividadId], method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }	
}

