package com.fiuba

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class ActividadController {

	// metodos nuevos
	def cursoId
	def cuatrimestreId
	
	def seguridadService

	
	def general() {
		params.max = 5 // Math.min(max ?: 10, 100)

		println "actividad cuatrimestreID: ${params.cuatrimestreId}"
		
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
		def cuatrimestre = Cuatrimestre.get(cuatrimestreId)
		
		def actividadId = params.id
				
		def actividad = Actividad.get(actividadId)
		
		
		def aprendizId = Aprendiz.findByUsuarioAndCuatrimestre(seguridadService.usuarioActual(), cuatrimestre)
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
		
		[cursoId: cursoId, cuatrimestreId: cuatrimestreId, actividadId: actividadId, actividad: actividad, aprendizId: aprendizId,
			grupoActividadAprendiz: grupoActividadAprendiz]
	}
	
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		
		println "index actividad"
		println params
		
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
			
		[actividadInstanceList: Actividad.findAllByCuatrimestre(Cuatrimestre.get(cuatrimestreId),[max: params.max, offset: params.offset]),
			actividadInstanceCount: Actividad.findAllByCuatrimestre(Cuatrimestre.get(cuatrimestreId)).size(), 
			cursoId: cursoId, cuatrimestreId: cuatrimestreId]
		
    }

    def show(Actividad actividadInstance) {
		
		println "actividad show params: ${params}"
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
		
        respond actividadInstance, model: [cursoId: cursoId, cuatrimestreId: cuatrimestreId]
    }
	

    def create() {
		
		println "create actividad cuatrimestre params: ${params}"
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
		
		respond new Actividad(params), params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId], 
			model:[cursoId: cursoId, cuatrimestreId: cuatrimestreId]
    }

    @Transactional
    def save(Actividad actividadInstance) {
        if (actividadInstance == null) {
            notFound()
            return
        }

		println "actividad save: params: ${params}"
		
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
		
        if (actividadInstance.hasErrors()) {
            respond actividadInstance.errors, view:'create', params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId], 
				model: [cursoId: cursoId, cuatrimestreId: cuatrimestreId]
            return
        }

        actividadInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'actividadInstance.label', default: 'Actividad'), actividadInstance.id])
                redirect action: "index", params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]
            }
            '*' { respond actividadInstance, [status: CREATED] }
        }
    }

    def edit(Actividad actividadInstance) {
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
        respond actividadInstance, params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId], 
			model:[cursoId: cursoId, cuatrimestreId: cuatrimestreId]
    }


    @Transactional
    def update(Actividad actividadInstance) {
        
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
		
		if (actividadInstance == null) {
            notFound()
            return
        }

        if (actividadInstance.hasErrors()) {
            respond actividadInstance.errors, view:'edit', params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId], 
				model: [cursoId: cursoId, cuatrimestreId: cuatrimestreId]
            return
        }

        actividadInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Actividad.label', default: 'Actividad'), actividadInstance.id])
				respond actividadInstance, view:"show", params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId],
					model:[cursoId: cursoId, cuatrimestreId: cuatrimestreId]
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
		cuatrimestreId = params.cuatrimestreId
		
        actividadInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Actividad.label', default: 'Actividad'), actividadInstance.id])
                redirect action:"index", params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId], method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'actividadInstance.label', default: 'Actividad'), params.id])
                redirect action: "index", params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId], method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
