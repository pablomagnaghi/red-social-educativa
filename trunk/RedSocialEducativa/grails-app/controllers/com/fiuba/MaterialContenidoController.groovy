package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class MaterialContenidoController {

	def springSecurityService
	
	private usuarioActual() {
		return Usuario.get(springSecurityService.principal.id)
	}
	
	def cursoId
	def temaId
	def contenidoId
		
	// metodos por defecto
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show(MaterialContenido materialContenidoInstance) {
		
		println "material Contenido show: cursoId: ${params.cursoId}, tema Id. ${params.temaId}, contenidoID ${params.contenidoId}"
		println "contenido Id: ${params.id}"
		
		cursoId = params.cursoId
		temaId = params.temaId
		contenidoId = params.contenidoId
		
        respond materialContenidoInstance, model: [cursoId: cursoId, temaId: temaId, contenidoId: contenidoId]
    }

    def create() {
		println "create material contenido params: ${params}"
		
		cursoId = params.cursoId
		temaId = params.temaId
		contenidoId = params.contenidoId
		
		def mediador = Mediador.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))
        respond new MaterialContenido(params), params:['cursoId': cursoId], 
			model:[cursoId: cursoId, temaId: temaId, contenidoId: contenidoId, mediador: mediador]
    }

    @Transactional
    def save(MaterialContenido materialContenidoInstance) {
        if (materialContenidoInstance == null) {
            notFound()
            return
        }

		println "save material contenido params: ${params}"
		
		cursoId = params.cursoId
		temaId = params.temaId
		contenidoId = params.contenidoId
		
        if (materialContenidoInstance.hasErrors()) {
            respond materialContenidoInstance.errors, view:'create', params: ['cursoId': cursoId, 'temaId': temaId, 'contenidoId': contenidoId],
				model: [cursoId: cursoId, temaId: temaId, contenidoId: contenidoId]
			return
        }

        materialContenidoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'materialContenidoInstance.label', default: 'MaterialContenido'), materialContenidoInstance.id])
                redirect controller:"contenido", action:"show", params:['id': contenidoId, 'cursoId': cursoId, 'temaId': temaId, 'contenidoId': contenidoId]
            }
            '*' { respond materialContenidoInstance, [status: CREATED] }
        }
    }

    def edit(MaterialContenido materialContenidoInstance) {
		println "edit material Contenido para,s: ${params}"
		
		cursoId = params.cursoId
		temaId = params.temaId
		contenidoId = params.contenidoId
		
		respond materialContenidoInstance, model: [cursoId: cursoId, temaId: temaId, contenidoId: contenidoId]
    }

    @Transactional
    def update(MaterialContenido materialContenidoInstance) {
		
		println "update material Contenido para,s: ${params}"
		
		cursoId = params.cursoId
		temaId = params.temaId
		contenidoId = params.contenidoId
		
        if (materialContenidoInstance == null) {
            notFound()
            return
        }

        if (materialContenidoInstance.hasErrors()) {
            respond materialContenidoInstance.errors, view:'edit', params:['cursoId': cursoId, 'temaId': temaId, 'contenidoId': contenidoId], 
				model: [cursoId: cursoId, temaId: temaId, contenidoId: contenidoId]
            return
        }

        materialContenidoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MaterialContenido.label', default: 'MaterialContenido'), materialContenidoInstance.id])
                redirect action:"show", params:['id': materialContenidoInstance.id, 'cursoId': cursoId, 'temaId': temaId, 'contenidoId': contenidoId]
            }
            '*'{ respond materialContenidoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(MaterialContenido materialContenidoInstance) {

        if (materialContenidoInstance == null) {
            notFound()
            return
        }
		
		cursoId = params.cursoId
		temaId = params.temaId
		contenidoId = params.contenidoId
		
        materialContenidoInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MaterialContenido.label', default: 'MaterialContenido'), materialContenidoInstance.id])
                redirect controller:"contenido", action:"show", params:['id': contenidoId, 'cursoId': cursoId, 'temaId': temaId], method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialContenidoInstance.label', default: 'MaterialContenido'), params.id])
                redirect controller: "contenido", action:"show", params:['id': contenidoId, 'cursoId': cursoId, 'temaId': temaId], method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

