package com.fiuba

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class ActividadController {

	// metodos nuevos
	def cuatrimestreId
	
	def seguridadService

	
	def general() {
		params.max = 5 // Math.min(max ?: 10, 100)

		println "actividad cuatrimestreID: ${params.cuatrimestreId}"
		
		cuatrimestreId = params.cuatrimestreId
		def actividadId = params.id
				
		def actividad = Actividad.get(actividadId)
		
		def aprendizId = Aprendiz.findByUsuarioAndCuatrimestre(seguridadService.usuarioActual(), cuatrimestreId)
		def grupoActividadAprendiz = null
		
		if (aprendizId) {
			def c = GrupoActividadAprendiz.createCriteria()
			grupoActividadAprendiz = c {
				grupo {
					eq('actividad.id', actividadId as long)
				}
				eq('aprendiz.id', aprendizId as long)
			}
		} 
		
		[cuatrimestreId: cuatrimestreId, actividadId: actividadId, actividad: actividad, aprendizId: aprendizId,
			grupoActividadAprendiz: grupoActividadAprendiz]
	}
	
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		
		println "index actividad"
		println params
		
		cuatrimestreId = params.cuatrimestreId
			
		[actividadInstanceList: Actividad.findAllByCuatrimestre(Cuatrimestre.get(cuatrimestreId),[max: params.max, offset: params.offset]),
			actividadInstanceCount: Actividad.findAllByCuatrimestre(Cuatrimestre.get(cuatrimestreId)).size(), cuatrimestreId: cuatrimestreId]
		
    }

    def show(Actividad actividadInstance) {
		
		println "actividad show params: ${params}"
		
        respond actividadInstance, model: [cuatrimestreId: cuatrimestreId]
    }
	

    def create() {
		
		println "create actividad cuatrimestre params: ${params}"
		cuatrimestreId = params.cuatrimestreId
		
		respond new Actividad(params), params:['cuatrimestreId': cuatrimestreId], model:[cuatrimestreId: cuatrimestreId]
    }

    @Transactional
    def save(Actividad actividadInstance) {
        if (actividadInstance == null) {
            notFound()
            return
        }

		println "actividad save: params: ${params}"
		
		cuatrimestreId = params.cuatrimestreId
		
        if (actividadInstance.hasErrors()) {
            respond actividadInstance.errors, view:'create', params:['cuatrimestreId': cuatrimestreId], model: [cuatrimestreId: cuatrimestreId]
            return
        }

        actividadInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'actividadInstance.label', default: 'Actividad'), actividadInstance.id])
                redirect action: "index", params:['cuatrimestreId': cuatrimestreId]
            }
            '*' { respond actividadInstance, [status: CREATED] }
        }
    }

    def edit(Actividad actividadInstance) {
		cuatrimestreId = params.cuatrimestreId
        respond actividadInstance, params:['cuatrimestreId': cuatrimestreId], model:[cuatrimestreId: cuatrimestreId]
    }


    @Transactional
    def update(Actividad actividadInstance) {
        
		cuatrimestreId = params.cuatrimestreId
		
		if (actividadInstance == null) {
            notFound()
            return
        }

        if (actividadInstance.hasErrors()) {
            respond actividadInstance.errors, view:'edit', params:['cuatrimestreId': cuatrimestreId], model: [cuatrimestreId: cuatrimestreId]
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
		
		cuatrimestreId = params.cuatrimestreId
		
        actividadInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Actividad.label', default: 'Actividad'), actividadInstance.id])
                redirect action:"index", params:['cuatrimestreId': cuatrimestreId], method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'actividadInstance.label', default: 'Actividad'), params.id])
                redirect action: "index", params:['cuatrimestreId': cuatrimestreId], method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
