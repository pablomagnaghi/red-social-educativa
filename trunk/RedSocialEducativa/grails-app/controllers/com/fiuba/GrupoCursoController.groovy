package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class GrupoCursoController {

	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def seguridadService
	def grupoCursoService
	def aprendizService

	@Secured("hasRole('ROL_APRENDIZ')")
	def menuAprendiz() {
		
		params.max = Utilidades.MAX_PARAMS

		def cuatrimestre = Cuatrimestre.get(params.cuatrimestreId)

		[aprendizInstanceList: Aprendiz.findAllByCuatrimestreAndParticipaAndGrupoIsNotNull(cuatrimestre, true, [max: params.max, offset: params.offset]),
			aprendizInstanceCount: Aprendiz.findAllByCuatrimestreAndParticipaAndGrupoIsNotNull(cuatrimestre, true).size(), 
			params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def menuMed() {
		
		params.max = Utilidades.MAX_PARAMS
	
		def cuatrimestre = Cuatrimestre.get(params.cuatrimestreId)

		[aprendizInstanceList: Aprendiz.findAllByCuatrimestreAndParticipaAndGrupoIsNotNull(cuatrimestre, true, [max: params.max, offset: params.offset]),
			aprendizInstanceCount: Aprendiz.findAllByCuatrimestreAndParticipaAndGrupoIsNotNull(cuatrimestre, true).size(), 
			params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def crear() {
	
		def aprendiz = Aprendiz.findByUsuarioAndCuatrimestre(seguridadService.usuarioActual(), Cuatrimestre.get(params.cuatrimestreId))
		
		if (aprendiz?.grupo) {
			flash.message = "Usted ya pertenece al grupo ${aprendiz.grupo}"
			redirect action: "menuAprendiz", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}
		
		def numGrupo = GrupoCurso.findAllByCuatrimestre(Cuatrimestre.get(params.cuatrimestreId)).size() + 1

		println "numGRUOP: ${numGrupo}"
		
		respond new GrupoCurso(params), model: [numGrupo: numGrupo], params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def muestraAprendiz(GrupoCurso grupoCursoInstance) {
			
		def aprendiz = Aprendiz.findByUsuarioAndCuatrimestre(seguridadService.usuarioActual(), Cuatrimestre.get(params.cuatrimestreId))	
		def participa = false
		
		// Si participa en el grupo puede editar el nombre del mismo
		if (grupoCursoInstance.aprendices.contains(aprendiz)) {
			participa = true
		}
	
		respond grupoCursoInstance, model: [participa: participa], params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def muestraMed(GrupoCurso grupoCursoInstance) {
				
		respond grupoCursoInstance, params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def cambios() {

		[aprendices: Aprendiz.findAllByCuatrimestreAndParticipaAndGrupoIsNotNull(Cuatrimestre.get(params.cuatrimestreId), true), 
			params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def realizarCambio() {

		Integer numeroGrupo = params.numero.size() ? params.numero.toInteger(): 0
		Integer aprendizId = params.aprendizId.toInteger()

		if (aprendizService.pertenece(aprendizId, numeroGrupo)) {
			flash.message = "El aprendiz ya pertenece al grupo ${numeroGrupo}"
			redirect action: "cambios", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}

		if (!aprendizService.realizarCambio(aprendizId, grupoCursoService.obtenerGrupoPorNumero(params.cuatrimestreId.toLong(), numeroGrupo))) {
			flash.message = "El numero de grupo no es válido. " + 
				"Los posibles numeros son ${grupoCursoService.obtenerGrupos(params.cuatrimestreId.toLong())}"
			redirect action: "cambios", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}
		
		flash.message = "El aprendiz ahora pertenece al grupo ${numeroGrupo}"
		redirect action: "menuMed", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def editar(GrupoCurso grupoCursoInstance) {
		respond grupoCursoInstance, params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def guardar(GrupoCurso grupoCursoInstance) {
		if (grupoCursoInstance == null) {
			notFound()
			return
		}

		if (!grupoCursoService.crearGrupo(grupoCursoInstance, seguridadService.usuarioActual(), params.cuatrimestreId.toLong())) {
			render view:'crear', model: [grupoCursoInstance: grupoCursoInstance, numGrupo: params.numGrupo],
				params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}
		
		flash.message = message(code: 'default.created.message', args: [message(code: 'grupoCursoInstance.label', default: 'GrupoCurso'), grupoCursoInstance.id])
		redirect action: "menuAprendiz", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def agregarme(GrupoCurso grupoCursoInstance) {
		
		if (grupoCursoInstance == null) {
			notFound()
			return
		}
		
		def aprendiz = Aprendiz.findByUsuarioAndCuatrimestre(seguridadService.usuarioActual(), Cuatrimestre.get(params.cuatrimestreId))
		if (aprendiz?.grupo) {
			flash.message = "Usted ya pertenece al grupo ${aprendiz.grupo}"
			redirect action: "muestraAprendiz", params:['id': params.id, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}
		
		if (!grupoCursoService.agregarAprendiz(grupoCursoInstance, aprendiz)) {
			render view:'muestraAprendiz', model: [grupoCursoInstance: grupoCursoInstance],
				params:['id': params.id, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}
		
		flash.message = message(code: 'default.updated.message', args: [message(code: 'GrupoCurso.label', default: 'GrupoCurso'), grupoCursoInstance.id])
		redirect action: "muestraAprendiz", params:['id': params.id, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def editarNombre(GrupoCurso grupoCursoInstance) {

		if (grupoCursoInstance == null) {
			notFound()
			return
		}

		if (!grupoCursoService.guardar(grupoCursoInstance)) {
			render view:'editar', model: [grupoCursoInstance: grupoCursoInstance],
			params:['id': params.id, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'GrupoCurso.label', default: 'GrupoCurso'), grupoCursoInstance.id])
		redirect action: "muestraAprendiz", params:['id': params.id, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
    def eliminar(GrupoCurso grupoCursoInstance) {

        if (grupoCursoInstance == null) {
			notFound()
			redirect action:"menuMed", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId], method:"GET"	
            return
        }

		grupoCursoService.eliminar(grupoCursoInstance)
		
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'GrupoCurso.label', default: 'GrupoCurso'), grupoCursoInstance.id])
        redirect action:"menuMed", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId], method:"GET"
    }

    protected void notFound() {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupoCursoInstance.label', default: 'GrupoCurso'), params.id])
    }
}
