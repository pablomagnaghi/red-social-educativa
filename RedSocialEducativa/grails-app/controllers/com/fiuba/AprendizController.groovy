package com.fiuba

import static org.springframework.http.HttpStatus.*
//import grails.transaction.Transactional

//@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class AprendizController {

	def cursoId 
	
	def estadisticas(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		
		println "index aprendiz"
		println params
		
		cursoId = params.cursoId
			
		[aprendizInstanceList: Aprendiz.findAllByCursoAndParticipa(Curso.get(cursoId), true, [max: params.max, offset: params.offset]),
			aprendizInstanceCount: Aprendiz.findAllByCursoAndParticipa(Curso.get(cursoId), true).size(), cursoId: cursoId]
	}
	
	// metodos para ABM aprendices del menu de mediador
	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
    def index(Integer max) {
        params.max = 2 //Math.min(max ?: 10, 100)
		
		println "index aprendiz"
		println params
		
		//if (params.cursoId)
			cursoId = params.cursoId
			
		[aprendizInstanceList: Aprendiz.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]), 
			aprendizInstanceCount: Aprendiz.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId]
    }

    def create() {
        respond new Aprendiz(params), model:[cursoId: cursoId]
    }

    //@Transactional
    def save(Aprendiz aprendizInstance) {
        if (aprendizInstance == null) {
            notFound()
            return
        }

		cursoId = aprendizInstance.curso.id
		
        if (aprendizInstance.hasErrors()) {
            respond aprendizInstance.errors, view:'create', params:['cursoId': cursoId]
            return
        }

		println "save params: ${params}"
		
		def curso = Curso.get(cursoId)
		
		def usuario = Usuario.get(aprendizInstance.usuario.id)
		
		println "curso: ${curso}"
		println "usuario: ${usuario}"
		
		def mediador = Mediador.findByUsuarioAndCurso(usuario, curso)
		def aprendiz = Aprendiz.findByUsuarioAndCurso(usuario, curso)
		
		if (mediador) {
			flash.message = "El miembro ${usuario} ya es mediador en el curso ${curso}. No puede ser aprendiz en el mismo"
			redirect action: "create", params:['cursoId': cursoId]
			return
		}
		
		if (aprendiz) {
			flash.message = "${aprendiz} ya es aprendiz en el curso ${curso}"
			redirect action: "create", params:['cursoId': cursoId]
			return
		}

        aprendizInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'aprendizInstance.label', default: 'Aprendiz'), aprendizInstance.id])
                redirect action: "index", params:['cursoId': cursoId]
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

        aprendizInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Aprendiz.label', default: 'Aprendiz'), aprendizInstance.id])
                redirect action:"index", params:['cursoId': cursoId], method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
		println "not found params: ${params}"
		
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'aprendizInstance.label', default: 'Aprendiz'), params.id])
                redirect action: "index", params:['cursoId': cursoId], method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
