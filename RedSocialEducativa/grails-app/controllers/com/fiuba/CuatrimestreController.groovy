package com.fiuba

import com.foro.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class CuatrimestreController {

	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def cuatrimestreService
	def cursoService

	@Secured("hasRole('ROL_MEDIADOR')")
	def indexHistoriales(Integer max) {
		params.max = Utilidades.MAX_PARAMS
		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(params.cursoId.toLong())
		def cuatrimestres = cuatrimestreService.obtenerCuatrimestresOrdenados(params.cursoId.toLong())
		[cuatrimestreInstanceList: Cuatrimestre.findAllByCursoAndIdNotEqual(Curso.get(params.cursoId), cuatrimestre?.id,[sort: "numero", order: "asc"]),
			params: [cursoId: params.cursoId]]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def historial(Cuatrimestre cuatrimestreInstance) {
		respond cuatrimestreInstance, params: [cursoId: params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def consolidar() {
		// Si no se dicta ese cuatrimestre salgo
		if (!cursoService.seDicta(params.cursoId.toLong())) {
			flash.message = "El curso se dicta durante el cuatrimestre ${Curso.get(params.cursoId).cuatDict}"
			redirect controller: "curso", action: "mediador", params:['cursoId': params.cursoId]
			return
		}
		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(params.cursoId.toLong())
		// Si ya se dicta ese cuatrimestre salgo
		if (cuatrimestre) {
			flash.message = "Para consolidar el ${cuatrimestre} del curso ${Curso.get(params.cursoId)} debe esperar a la finalizacion del cuatrimestre"
			redirect controller: "curso", action: "mediador", params:['cursoId': params.cursoId]
			return
		}
		Integer anio = cuatrimestreService.obtenerAnio()
		Integer numero = cuatrimestreService.obtenerNumero()
		def foro = new ForoCurso(nombre: "Foro general del curso ${Curso.get(params.cursoId)} durante el cuatrimestre ${anio} - ${numero}")
		def cuatrimestreInstance = new Cuatrimestre(anio: anio, numero: numero, foro: foro, curso: Curso.get(params.cursoId))
		// Obtengo el ultimo cuatrimestre
		def cuatrimestres = cuatrimestreService.obtenerCuatrimestresOrdenados(params.cursoId.toLong())
		def cuatrimestreUltimo
		if (cuatrimestres) {
			cuatrimestreUltimo = cuatrimestres.first()
		}
		if (!cuatrimestreService.guardar(cuatrimestreInstance)) {
			flash.message = "Problemas al consolidar el cuatrimestre ${cuatrimestreInstance}"	
			redirect controller: "curso", action: "mediador", params:['cursoId': params.cursoId]
			return
		}
		// Se creo el nuevo cuatrimestre => Pasar a cursando false a todos los aprendices del cuatrimestre anterior
		if (cuatrimestreUltimo) {
			cuatrimestreService.consolidar(cuatrimestreUltimo)
		}
		flash.message = "Cuatrimestre ${cuatrimestreInstance} creado"
		redirect controller: "curso", action: "mediador", params:['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(Cuatrimestre cuatrimestreInstance) {
		if (cuatrimestreInstance == null) {
			notFound()
			return
		}
		cuatrimestreService.eliminar(cuatrimestreInstance)
		flash.message = "Cuatrimestre ${cuatrimestreInstance} eliminado"
		redirect action:"indexHistoriales", params:['cursoId': params.cursoId], method:"GET"
	}

	protected void notFound() {
		flash.message = "No se encuentra ese cuatrimestre"
		redirect action: "indexHistoriales", params:['cursoId': params.cursoId], method: "GET"
	}
}

