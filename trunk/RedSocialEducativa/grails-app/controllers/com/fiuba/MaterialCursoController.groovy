package com.fiuba

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class MaterialCursoController {
	
	def springSecurityService
	
	private usuarioActual() {
		return Usuario.get(springSecurityService.principal.id)
	}
	
	// metodos nuevos
	def cursoId
	
	def general() {
		params.max = 5 // Math.min(max ?: 10, 100)

		println "foro Tema general CURSOID: ${params.cursoId}"
		println "foro Tema general TEMAID: ${params}"
		
		cursoId = params.cursoId
		def materialId = params.id
				
		def material = Material.get(materialId)
		
		[cursoId: cursoId, material: material]
	}
	
	// metodos por defecto

    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		
		println "index noticiaCurso"
		println params
		
		cursoId = params.cursoId
			
		[materialCursoInstanceList: MaterialCurso.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			materialCursoInstanceCount: MaterialCurso.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId]
		
    }
	
    def show(MaterialCurso materialCursoInstance) {
        respond materialCursoInstance, model: [cursoId: cursoId]
    }

    def create() {
		
		println "create noticia curso params: ${params}"
		cursoId = params.cursoId
		def mediador = Mediador.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))
		
		println "usaurio actual: ${mediador}"
		
		respond new MaterialCurso(params), params:['cursoId': cursoId], model:[cursoId: cursoId, mediador: mediador]
		
    }

    @Transactional
    def save(MaterialCurso materialCursoInstance) {
        if (materialCursoInstance == null) {
            notFound()
            return
        }
		
		cursoId = materialCursoInstance.curso.id
		
        if (materialCursoInstance.hasErrors()) {
			def mediador = Mediador.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))
			respond materialCursoInstance.errors, view:'create', params:['cursoId': cursoId],
				model: [cursoId: cursoId, mediador: mediador]
            return
        }
		
		println "LLLLLLLLEGO"
		
		def materialCursoExistente = MaterialCurso.findByCursoAndTitulo(Curso.get(cursoId),
			materialCursoInstance.titulo)
		println materialCursoExistente
		
		if (materialCursoExistente) {
			flash.message = "Ya existe el material ${materialCursoInstance.titulo} del curso ${Curso.get(cursoId)}"
			redirect action: "create", params:['cursoId': cursoId]
			return
		}

        materialCursoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'materialCursoInstance.label', default: 'MaterialCurso'), materialCursoInstance.id])
                redirect action: "index", params:['cursoId': cursoId]
            }
            '*' { respond materialCursoInstance, [status: CREATED] }
        }
    }

    def edit(MaterialCurso materialCursoInstance) {
		cursoId = params.cursoId
        respond materialCursoInstance, params:['cursoId': cursoId], model:[cursoId: cursoId, usuario: usuarioActual()]
    }

    @Transactional
    def update(MaterialCurso materialCursoInstance) {
        
		cursoId = params.cursoId
		
		if (materialCursoInstance == null) {
            notFound()
            return
        }

        if (materialCursoInstance.hasErrors()) {
            respond materialCursoInstance.errors, view:'edit', params:['cursoId': cursoId], model: [cursoId: cursoId]
            return
        }

        materialCursoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MaterialCurso.label', default: 'MaterialCurso'), materialCursoInstance.id])
                redirect materialCursoInstance
            }
            '*'{ respond materialCursoInstance, [status: OK] }
        }
    }
	
    @Transactional
    def delete(MaterialCurso materialCursoInstance) {

        if (materialCursoInstance == null) {
            notFound()
            return
        }

		cursoId = params.cursoId
		
        materialCursoInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MaterialCurso.label', default: 'MaterialCurso'), materialCursoInstance.id])
                redirect action:"index", params:['cursoId': cursoId], method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialCursoInstance.label', default: 'MaterialCurso'), params.id])
                redirect action: "index", params:['cursoId': cursoId], method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
