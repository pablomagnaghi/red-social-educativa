package com.fiuba

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional;
//import grails.transaction.Transactional

//@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class AprendizController {

	def cursoId 
	def cuatrimestreId
	
	// estadisticas de los participantes del cuatrimestre
	def estadisticas(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		
		println "index aprendiz"
		println params
		
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
		def cuatrimestre = Cuatrimestre.get(cuatrimestreId)
			
		[aprendizInstanceList: Aprendiz.findAllByCuatrimestreAndParticipa(cuatrimestre, true, [max: params.max, offset: params.offset]),
			aprendizInstanceCount: Aprendiz.findAllByCuatrimestreAndParticipa(cuatrimestre, true).size(), 
			cursoId: cursoId, cuatrimestreId: cuatrimestreId]
	}

	// metodos para ABM aprendices del menu de mediador
	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
    def index(Integer max) {
        params.max = 4 //Math.min(max ?: 10, 100)
		
		println "index aprendiz"
		println params
		
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
		def cuatrimestre = Cuatrimestre.get(cuatrimestreId)
			
		[aprendizInstanceList: Aprendiz.findAllByCuatrimestre(cuatrimestre,[max: params.max, offset: params.offset]), 
			aprendizInstanceCount: Aprendiz.findAllByCuatrimestre(cuatrimestre).size(), cursoId: cursoId, cuatrimestreId: cuatrimestreId]
    }

    def create() {
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
		
        respond new Aprendiz(params), model:[cursoId: cursoId, cuatrimestreId: cuatrimestreId]
    }
	
    //@Transactional
    def save(Aprendiz aprendizInstance) {
        if (aprendizInstance == null) {
            notFound()
            return
        }

		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId
		
        if (aprendizInstance.hasErrors()) {
            respond aprendizInstance.errors, view:'create', params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]
            return
        }

		println "save params: ${params}"
		
		def curso = Curso.get(cursoId)
		def cuatrimestre = Cuatrimestre.get(cuatrimestreId)
		
		def usuario = Usuario.get(aprendizInstance.usuario.id)
		
		println "curso: ${curso}"
		println "usuario: ${usuario}"
		
		def mediador = Mediador.findByUsuarioAndCurso(usuario, curso)
		def aprendiz = Aprendiz.findByUsuarioAndCuatrimestre(usuario, cuatrimestre)
		
		if (mediador) {
			flash.message = "El miembro ${usuario} ya es mediador en el curso ${curso}. No puede ser aprendiz en el mismo"
			redirect action: "create", params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]
			return
		}
		
		if (aprendiz) {
			flash.message = "${aprendiz} ya es aprendiz en el curso ${curso} durante el cuatrimestre ${cuatrimestre}"
			redirect action: "create", params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]
			return
		}

        aprendizInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'aprendizInstance.label', default: 'Aprendiz'), aprendizInstance.id])
                redirect action: "index", params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]
            }
			'*' { respond aprendizInstance, [status: CREATED] }
        }
    }

    //@Transactional
    def delete(Aprendiz aprendizInstance) {

        if (aprendizInstance == null) {
            notFound()
            return
        }
		
		cursoId = params.cursoId
		cuatrimestreId = params.cuatrimestreId

        aprendizInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Aprendiz.label', default: 'Aprendiz'), aprendizInstance.id])
                redirect action:"index", params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId], method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
		println "not found params: ${params}"
		
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'aprendizInstance.label', default: 'Aprendiz'), params.id])
                redirect action: "index", params:['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId], method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}



