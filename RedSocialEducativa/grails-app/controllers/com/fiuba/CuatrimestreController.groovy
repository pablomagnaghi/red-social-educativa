package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class CuatrimestreController {

	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def cuatrimestreService
	def cursoService

	@Secured("hasRole('ROL_ADMIN')")
	def muestraMenuAdm(Cuatrimestre cuatrimestreInstance) {
		respond cuatrimestreInstance, params: [cursoId: params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def indexHistoriales(Integer max) {
		params.max = Utilidades.MAX_PARAMS

		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(params.cursoId.toLong())
		
		[cuatrimestreInstanceList: Cuatrimestre.findAllByCursoAndIdNotEqual(Curso.get(params.cursoId), cuatrimestre?.id,[max: params.max, offset: params.offset]),
			cuatrimestreInstanceCount: Cuatrimestre.findAllByCursoAndIdNotEqual(Curso.get(params.cursoId), cuatrimestre?.id).size(), 
			params: [cursoId: params.cursoId]]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def historial(Cuatrimestre cuatrimestreInstance) {
		respond cuatrimestreInstance, params: [cursoId: params.cursoId]
	}

	// TODO: por ahora no se usa	
	@Secured("hasRole('ROL_MEDIADOR')")
	def show(Cuatrimestre cuatrimestreInstance) {
		respond cuatrimestreInstance, params:['cursoId': params.cursoId]
	}

	// TODO: consolidar cuatrimestre
	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {
		
		// Si no se dicta ese cuatrimestre salgo
		if (!cursoService.seDicta(params.cursoId.toLong())) {
			flash.message = "El curso se dicta durante el cuatrimestre ${Curso.get(params.cursoId).cuatDict}"
			redirect controller: "curso", action: "menuMediador", params:['cursoId': params.cursoId]
			return
		}
		
		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(params.cursoId.toLong())
	
		// Si ya se dicta ese cuatrimestre salgo
		if (cuatrimestre) {
			flash.message = "Ya existe el cuatrimestre ${cuatrimestre} del curso ${Curso.get(params.cursoId)}"
			redirect controller: "curso", action: "menuMediador", params:['cursoId': params.cursoId]
			return
		}
		
		Integer anio = Utilidades.ANIO
		Integer numero = cuatrimestreService.obtenerNumero()
	
	
		println "Cuatrimestre actual"
		println "${cuatrimestre}"
		
		respond new Cuatrimestre(params), model: [anio: anio, numero: numero], params:['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def save(Cuatrimestre cuatrimestreInstance) {
		if (cuatrimestreInstance == null) {
			notFound()
			return
		}
		
		def foro = new ForoCurso(nombre: "Foro general del curso ${cuatrimestreInstance.curso} durante el cuatrimestre {cuatrimestreInstance}")
		cuatrimestreInstance.foro = foro
	
		// Obtengo el ultimo cuatrimestre 
		def cuatrimestres = Cuatrimestre.findAllByCurso(Curso.get(params.cursoId), [sort: 'anio', order: 'desc'])
		println "cuatrimestres: ${cuatrimestres}"
		// el orden es [2013 - 2, 2013 - 1, 2012 - 2, 2012 - 1, 2011 - 2, 2011 - 1, 2010 - 2]
		def cuatrimestreUltimo = cuatrimestres.first()
		println "cuatrimestre ultimo del curso"
		println "${cuatrimestreUltimo}"
		
		if (!cuatrimestreService.guardar(cuatrimestreInstance)) {
			render view:'create', model: [cuatrimestreInstance: cuatrimestreInstance], params:['cursoId': params.cursoId]
			return
		}
	
		// Se creo el nuevo cuatrimestre => Pasar a cursando false a todos los aprendices del cuatrimestre anterior
		cuatrimestreService.consolidar(cuatrimestreUltimo)

		println "Se crea el cuatrimestre ${cuatrimestreInstance.anio}-${cuatrimestreInstance.numero}"
		
		flash.message = message(code: 'default.created.message', args: [message(code: 'cuatrimestreInstance.label', default: 'Cuatrimestre'), cuatrimestreInstance.id])
		redirect controller: "curso", action: "menuMediador", params:['cursoId': params.cursoId]
	}

	// TODO: por ahora no se usa
	@Secured("hasRole('ROL_MEDIADOR')")
	def edit(Cuatrimestre cuatrimestreInstance) {
		respond cuatrimestreInstance, params:['cursoId': params.cursoId]
	}

	// TODO: por ahora no se usa
	@Secured("hasRole('ROL_MEDIADOR')")
	def update(Cuatrimestre cuatrimestreInstance) {

		if (cuatrimestreInstance == null) {
			notFound()
			return
		}

		if (!cuatrimestreService.guardar(cuatrimestreInstance)) {
			render view:'edit', model: [cuatrimestrInstance: cuatrimestreInstance], params:['cursoId': params.cursoId]
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'Cuatrimestre.label', default: 'Cuatrimestre'), cuatrimestreInstance.id])
		redirect action: "show", params:['id': cuatrimestreInstance.id, 'cursoId': params.cursoId]
	}

	// TODO: por ahora no se usa
	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(Cuatrimestre cuatrimestreInstance) {

		if (cuatrimestreInstance == null) {
			notFound()
			return
		}

		cuatrimestreService.eliminar(cuatrimestreInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'Cuatrimestre.label', default: 'Cuatrimestre'), cuatrimestreInstance.id])
		redirect action:"indexHistoriales", params:['cursoId': params.cursoId], method:"GET"
	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuatrimestreInstance.label', default: 'Cuatrimestre'), params.id])
		redirect action: "indexHistoriales", params:['cursoId': params.cursoId], method: "GET"
	}
}

