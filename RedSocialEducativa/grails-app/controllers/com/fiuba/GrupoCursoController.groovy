package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class GrupoCursoController {

	def springSecurityService
	
	private usuarioActual() {
		return Usuario.get(springSecurityService.principal.id)
	}
	
	def cursoId
	
	def general(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		println "index grupo"
		println params
		
		cursoId = params.cursoId

		[aprendizInstanceList: Aprendiz.findAllByCursoAndParticipaAndGrupoIsNotNull(Curso.get(cursoId), true, [max: params.max, offset: params.offset]),
			aprendizInstanceCount: Aprendiz.findAllByCursoAndParticipaAndGrupoIsNotNull(Curso.get(cursoId), true).size(), cursoId: cursoId]
	}
	
	def menuMediador(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		println "index grupo"
		println params
		
		cursoId = params.cursoId

		[aprendizInstanceList: Aprendiz.findAllByCursoAndParticipaAndGrupoIsNotNull(Curso.get(cursoId), true, [max: params.max, offset: params.offset]),
			aprendizInstanceCount: Aprendiz.findAllByCursoAndParticipaAndGrupoIsNotNull(Curso.get(cursoId), true).size(), cursoId: cursoId]
	}
	
	
	def crear() {
		println "CREARRRRRR"
		println "create grupo curso params: ${params}"
		cursoId = params.cursoId
		
		println "cantidad de grupos del curso: ${Curso.get(cursoId)}"
		println GrupoCurso.findAllByCurso(Curso.get(cursoId)).size()
		
		def numGrupo = GrupoCurso.findAllByCurso(Curso.get(cursoId)).size() + 1
		
		def aprendiz = Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))
		
		if (aprendiz?.grupo) {
			flash.message = "Usted ya pertenece al grupo ${aprendiz.grupo}"
			redirect action: "general", params:['cursoId': cursoId]
			return
		}
		
		respond new GrupoCurso(params), params:['cursoId': cursoId], model:[cursoId: cursoId, numGrupo: numGrupo]
	}

	def mostrar(GrupoCurso grupoCursoInstance) {
		
		println "params: ${params}"
		
		cursoId = params.cursoId
		
		def aprendiz = Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))
		
		
		println "mostrar: grupo: ${GrupoCurso.get(params.id)}"
		
		println "aprendiz: ${aprendiz}"
		
		println "mostrar: contiene: ${GrupoCurso.get(params.id).aprendices.contains(aprendiz)}"
		
		def participa = false
		
		// Si participa en el grupo puede editar el nombre del mismo
		if (GrupoCurso.get(params.id).aprendices.contains(aprendiz)) {
			participa = true
			
		}
		
		respond grupoCursoInstance, model: [cursoId: cursoId, participa: participa]
	}
	
	def muestraMediador(GrupoCurso grupoCursoInstance) {
		
		println "params: ${params}"
		
		cursoId = params.cursoId
		
		respond grupoCursoInstance, model: [cursoId: cursoId]
	}
	
	def menuCambios() {
		
		cursoId = params.cursoId
		
		println "menu cambiso: curso ID: ${cursoId}"
		
		[aprendices: Aprendiz.findAllByCursoAndParticipaAndGrupoIsNotNull(Curso.get(cursoId), true), 
			cursoId:cursoId]
			
	}
	
	def realizarCambio() {
		
		cursoId = params.cursoId
		Integer aprendizId = params.aprendizId.toInteger()
		
		if (params.numero.size()) {
			Integer numeroGrupo = params.numero.toInteger()
			
			Integer cantGrupos = GrupoCurso.findAllByCurso(Curso.get(cursoId)).size()
			
			def curso = Curso.get(cursoId)
			
			println "REALIZAR CAMBIOS"
			
			println "numero de grupo: ${numeroGrupo}"
			println "cantGrupos: ${cantGrupos}"
			println "aprendizId: ${aprendizId}"
			
			println "aprendiz: ${Aprendiz.get(aprendizId)}"
			
			
			if ((numeroGrupo > cantGrupos) || (numeroGrupo < 1)) {
				flash.message = "El numero de grupo ingresado no existe." + 
				"Los numero de grupo van del 1 hasta el ${cantGrupos}"
				redirect action: "menuCambios", params:['cursoId': cursoId]
				return
			}
			
			def aprendiz = Aprendiz.get(aprendizId)
			println "aprendizGrupoNUmero: ${aprendiz.grupo.numero}"
			if (aprendiz.grupo.numero == numeroGrupo) {
				flash.message = "El aprendiz ${aprendiz} ya pertenece al grupo ${numeroGrupo}"
				redirect action: "menuCambios", params:['cursoId': cursoId]
				return
			}
			
			// TODO ver
			def grupoCurso = GrupoCurso.findByCursoAndNumero(curso, numeroGrupo)
			
			println "grupoCurso: ${grupoCurso}"
		
			println "HASSSSSSSSSSSSSSSSSSSSSTA ACA"
	
			println "grupo previo"
			println aprendiz.grupo
			
			aprendiz.grupo = grupoCurso
			
			println "COMO QUEDARIA"
			println "grupo curso"
			println aprendiz.grupo
			println "grupo curso"
			println aprendiz
			
			aprendiz.save flush:true
			
			println "cambios realizados"
			
			flash.message = "El aprendiz ${Aprendiz.get(aprendizId)} " +
			"ahora pertenece al grupo ${numeroGrupo}"
			
		
		} else {
			flash.message = "No existe ese numero ese numero de grupo"
			redirect action: "menuCambios", params:['cursoId': cursoId]
			return
		}
		
		redirect action: "menuMediador", params:['cursoId': cursoId]
			
	}
	
	
	def editar(GrupoCurso grupoCursoInstance) {
		cursoId = params.cursoId
		println grupoCursoInstance.aprendices
		
		respond grupoCursoInstance, params:['cursoId': cursoId], model:[cursoId: cursoId]
	}
	
	@Transactional
	def guardar(GrupoCurso grupoCursoInstance) {
		if (grupoCursoInstance == null) {
			notFound()
			return
		}
		
		println "Save grupo"
		println "params: ${params}"
		println "properties grupoCursoInstance.properties"
		
		cursoId = params.cursoId
		def numGrupo = params.numGrupo
		
		println "LLEGO 1"
		
		if (grupoCursoInstance.hasErrors()) {
			respond grupoCursoInstance.errors, view:'crear', params:['cursoId': cursoId],
				model: [cursoId: cursoId, numGrupo: numGrupo]
			println grupoCursoInstance.errors
			return
		}
		
		def aprendiz = Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))
		
		grupoCursoInstance.addToAprendices(aprendiz)
		
		grupoCursoInstance.save flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.created.message', args: [message(code: 'grupoCursoInstance.label', default: 'GrupoCurso'), grupoCursoInstance.id])
				redirect action: "general", params:['cursoId': cursoId]
			}
			'*' { respond grupoCursoInstance, [status: CREATED] }
		}
	}

	@Transactional
	def agregarme(GrupoCurso grupoCursoInstance) {
		
		cursoId = params.cursoId
		def aprendiz = Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))
		if (aprendiz?.grupo) {
			flash.message = "Usted ya pertenece al grupo ${aprendiz.grupo}"
			redirect action: "mostrar", params:['id': params.id, 'cursoId': cursoId]
			return
		}
		
		println "udpate"
		
		println grupoCursoInstance.properties
		println grupoCursoInstance.aprendices
		
		
		if (grupoCursoInstance == null) {
			notFound()
			return
		}
		
		println "aprendiz: ${aprendiz}"
		
		grupoCursoInstance.addToAprendices(aprendiz)
		
		if (grupoCursoInstance.hasErrors()) {
			respond grupoCursoInstance.errors, view:'mostrar', params:['id': params.id, 'cursoId': cursoId], model: [cursoId: cursoId]
			return
		}

		grupoCursoInstance.save flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'GrupoCurso.label', default: 'GrupoCurso'), grupoCursoInstance.id])
				redirect action: "mostrar", params:['id': params.id, 'cursoId': cursoId]
			}
			'*'{ respond grupoCursoInstance, [status: OK] }
		}
	}

	@Transactional
	def editarNombre(GrupoCurso grupoCursoInstance) {
		
		println "editar nombre"
		
		println grupoCursoInstance.properties
		println grupoCursoInstance.aprendices
		
		cursoId = params.cursoId

		if (grupoCursoInstance == null) {
			notFound()
			return
		}
		
		if (grupoCursoInstance.hasErrors()) {
			respond grupoCursoInstance.errors, view:'editar', params:['id': params.id, 'cursoId': cursoId], 
				model: [cursoId: cursoId]
			return
		}

		println "prueba"
		println grupoCursoInstance.nombre
		
		if (grupoCursoInstance.nombre) {
			println "NULLL"
		}
		
		grupoCursoInstance.save flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'GrupoCurso.label', default: 'GrupoCurso'), grupoCursoInstance.id])
				redirect action: "mostrar", params:['id': params.id, 'cursoId': cursoId]
			}
			'*'{ respond grupoCursoInstance, [status: OK] }
		}
	}
		
	// metodos por defecto
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	// TODO por ahora no se contempla la opcion de elimnar curso
	
    @Transactional
    def delete(GrupoCurso grupoCursoInstance) {

        if (grupoCursoInstance == null) {
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'GrupoCurso.label', 
				default: 'GrupoCurso'), grupoCursoInstance.id])
			redirect action:"menuMediador", params:['cursoId': cursoId], method:"GET"
            return
        }

		println "delete"
		
		println "grupo  a eliminar: ${GrupoCurso.get(params.id)}, id: ${params.id}"
		
		cursoId = params.cursoId
		
		// TODO fundamental para borrar las claves foraneas en aprendiz.curso
		// Porque no esta el belongsTo: no queremos borrar a los aprendices
		// Solo borramos el curso que tenian asignado
		def aprendices = grupoCursoInstance.aprendices
		
		println "aprendices: ${aprendices}"
		println "aprendices: ${aprendices.size()}"
		
		for(int i = 0; i<aprendices.size(); i++){
			println "${aprendices[i].grupo}"
			grupoCursoInstance.aprendices[i].grupo = null
			println "${despues: aprendices[i].grupo}"
			
		}
		
		
		grupoCursoInstance.delete flush:true

		
		
        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'GrupoCurso.label', default: 'GrupoCurso'), grupoCursoInstance.id])
                redirect action:"menuMediador", params:['cursoId': cursoId], method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupoCursoInstance.label', default: 'GrupoCurso'), params.id])
                redirect action: "general", params:['cursoId': cursoId], method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
