package com.fiuba

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class ActividadController {

	def springSecurityService
	
	private usuarioActual() {
		return Usuario.get(springSecurityService.principal.id)
	}
	
	// metodos nuevos
	def cursoId
	
	def general() {
		params.max = 5 // Math.min(max ?: 10, 100)

		println "actividad CURSOID: ${params.cursoId}"
		
		cursoId = params.cursoId
		def actividadId = params.id
				
		def actividad = Actividad.get(actividadId)
		
		def aprendizId = Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))?.id
		
		def aprendiz = null
		
		if (aprendizId) {
			def c = GrupoActividadAprendiz.createCriteria()
			aprendiz = c {
				grupo {
					eq('actividad.id', actividadId as long)
				}
				eq('aprendiz.id', aprendizId as long)
			}
		} 
		
		[cursoId: cursoId, actividadId: actividadId, actividad: actividad, aprendiz: aprendiz]
	}
	
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		
		println "index actividad"
		println params
		
		cursoId = params.cursoId
			
		[actividadInstanceList: Actividad.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			actividadInstanceCount: Actividad.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId]
		
    }

    def show(Actividad actividadInstance) {
		
		println "actividad show params: ${params}"
		
        respond actividadInstance, model: [cursoId: cursoId]
    }
	

    def create() {
		
		println "create actividad curso params: ${params}"
		cursoId = params.cursoId
		
		respond new Actividad(params), params:['cursoId': cursoId], model:[cursoId: cursoId]
    }

    @Transactional
    def save(Actividad actividadInstance) {
        if (actividadInstance == null) {
            notFound()
            return
        }

		println "actividad save: params: ${params}"
		
		cursoId = params.cursoId
		
        if (actividadInstance.hasErrors()) {
            respond actividadInstance.errors, view:'create', params:['cursoId': cursoId], model: [cursoId: cursoId]
            return
        }

        actividadInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'actividadInstance.label', default: 'Actividad'), actividadInstance.id])
                redirect action: "index", params:['cursoId': cursoId]
            }
            '*' { respond actividadInstance, [status: CREATED] }
        }
    }

    def edit(Actividad actividadInstance) {
		cursoId = params.cursoId
        respond actividadInstance, params:['cursoId': cursoId], model:[cursoId: cursoId]
    }


    @Transactional
    def update(Actividad actividadInstance) {
        
		cursoId = params.cursoId
		
		if (actividadInstance == null) {
            notFound()
            return
        }

        if (actividadInstance.hasErrors()) {
            respond actividadInstance.errors, view:'edit', params:['cursoId': cursoId], model: [cursoId: cursoId]
            return
        }

        actividadInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Actividad.label', default: 'Actividad'), actividadInstance.id])
                redirect actividadInstance
            }
            '*'{ respond actividadInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Actividad actividadInstance) {

        if (actividadInstance == null) {
            notFound()
            return
        }
		
		cursoId = params.cursoId
		
        actividadInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Actividad.label', default: 'Actividad'), actividadInstance.id])
                redirect action:"index", params:['cursoId': cursoId], method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'actividadInstance.label', default: 'Actividad'), params.id])
                redirect action: "index", params:['cursoId': cursoId], method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
