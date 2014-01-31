package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class MaterialGrupoController {
	
	def springSecurityService
	
	private usuarioActual() {
		return Usuario.get(springSecurityService.principal.id)
	}
	
	def cursoId
	def grupoId
	
    def muestraMediador (MaterialGrupo materialGrupoInstance) {
		
		println "material grupo show: cursoId: ${params.cursoId}, tema Id. ${params.temaId}"
		println "material grupo Id: ${params.id}"
		
		cursoId = params.cursoId
		grupoId = params.grupoId
		
		respond materialGrupoInstance, model: [cursoId: cursoId, grupoId: grupoId]
    }
	
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show(MaterialGrupo materialGrupoInstance) {
		
		println "material grupo show: cursoId: ${params.cursoId}, tema Id. ${params.temaId}"
		println "material grupo Id: ${params.id}"
		
		cursoId = params.cursoId
		grupoId = params.grupoId
		
		respond materialGrupoInstance, model: [cursoId: cursoId, grupoId: grupoId]
    }


    def create() {
		
		println "material grupo params: ${params}"
		
		cursoId = params.cursoId
		grupoId = params.grupoId
		def aprendiz = Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))
		respond new MaterialGrupo(params), params:['cursoId': cursoId],
			model:[cursoId: cursoId, grupoId: grupoId, aprendiz: aprendiz]
    }
	
    @Transactional
    def save(MaterialGrupo materialGrupoInstance) {
        if (materialGrupoInstance == null) {
            notFound()
            return
        }

		cursoId = params.cursoId
		grupoId = params.grupoId
		
		
        if (materialGrupoInstance.hasErrors()) {
            respond materialGrupoInstance.errors, view:'create', params: ['cursoId': cursoId, 'grupoId': grupoId],
			model: [cursoId: cursoId, grupoId: grupoId]
			return
        }

        materialGrupoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'materialGrupoInstance.label', default: 'MaterialGrupo'), materialGrupoInstance.id])
                redirect controller:"grupoCurso", action:"mostrar", params:['id': grupoId, 'cursoId': cursoId, 'grupoId': grupoId]			
            }
            '*' { respond materialGrupoInstance, [status: CREATED] }
        }
    }

    def edit(MaterialGrupo materialGrupoInstance) {
		cursoId = params.cursoId
		grupoId = params.grupoId
		
		respond materialGrupoInstance, model: [cursoId: cursoId, grupoId: grupoId]
    }

    @Transactional
    def update(MaterialGrupo materialGrupoInstance) {
        
		
		
		cursoId = params.cursoId
		grupoId = params.grupoId
		
		if (materialGrupoInstance == null) {
            notFound()
            return
        }

        if (materialGrupoInstance.hasErrors()) {
            respond materialGrupoInstance.errors, view:'edit', params:['cursoId': cursoId, 'grupoId': grupoId],
				model: [cursoId: cursoId, grupoId: grupoId]
            return
        }

        materialGrupoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MaterialGrupo.label', default: 'MaterialGrupo'), materialGrupoInstance.id])
                redirect action:"show", params:['id': materialGrupoInstance.id, 'cursoId': cursoId, 'grupoId': grupoId]
            }
            '*'{ respond materialGrupoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(MaterialGrupo materialGrupoInstance) {

        if (materialGrupoInstance == null) {
            notFound()
            return
        }

		cursoId = params.cursoId
		grupoId = params.grupoId
		
        materialGrupoInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MaterialGrupo.label', default: 'MaterialGrupo'), materialGrupoInstance.id])
                redirect controller:"grupoCurso", action:"mostrar", params:['id': grupoId, 'cursoId': cursoId], method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialGrupoInstance.label', default: 'MaterialGrupo'), params.id])
                redirect controller: "grupoCurso", action:"mostrar", params:['id': grupoId, 'cursoId': cursoId], method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

