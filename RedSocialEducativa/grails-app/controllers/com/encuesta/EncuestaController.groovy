package com.encuesta

import com.cursado.*
import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class EncuestaController {

    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def encuestaService
	def usuarioService
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def encuestasCurso() {
		params.max = Utilidades.MAX_PARAMS
		[encuestaInstanceList: Encuesta.findAllByCurso(Curso.get(params.cursoId)), params: ['cursoId': params.cursoId]]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def encuestaCurso(Encuesta encuestaInstance) {
		respond encuestaInstance, params:['cursoId': params.cursoId]
	}
	
	// TODO falta el metodo para guardar respuestas de la encuesta contestada por el aprendiz
	@Secured("hasRole('ROL_APRENDIZ')")
	def responder(Encuesta encuestaInstance) {
		if (encuestaInstance == null) {
			notFound()
			return
		}
		println "PARAMETROS"
		
		
		encuestaInstance.preguntasChoice.each {
			println it
		}
		
		encuestaInstance.preguntasDesarrollo.each {
			println it
		}
		
		encuestaInstance.preguntasPuntaje.each {
			println it
		}
		
		println params
		/*
		// REVISAR SI RESPONDIO LA ENCUESTA
		if (encuestaService.existe(encuestaInstance, params.cursoId.toLong())) {
			flash.message = "Ya existe la encuesta ${encuestaInstance}"
			redirect action: "create", params: ['cursoId': params.cursoId]
			return
		}
		if (!encuestaService.guardar(encuestaInstance)) {
			render view:'create', model: [encuestaInstance: encuestaInstance], params:['cursoId': params.cursoId]
			return
		}*/
		flash.message = "Encuesta ${encuestaInstance} creada"
		redirect action: "encuestasCurso", params:['cursoId': params.cursoId]
	}
	
	
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def estadisticas(Encuesta encuestaInstance) {
		respond encuestaInstance, params:['cursoId': params.cursoId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def habilitar(Encuesta encuestaInstance) {
		if (encuestaInstance == null) {
			notFound()
			return
		}
		encuestaInstance.habilitada = true
		if (!encuestaService.guardar(encuestaInstance)) {
			flash.message = "Problemas al habilitar la encuesta ${encuestaInstance}"
			redirect action: "index", params:['cursoId': params.cursoId]
		}
		flash.message = "Encuesta ${encuestaInstance} creada"
		redirect action: "index", params:['cursoId': params.cursoId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
    def index() {
        params.max = Utilidades.MAX_PARAMS
        [encuestaInstanceList: Encuesta.findAllByCurso(Curso.get(params.cursoId)), params: ['cursoId': params.cursoId]]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
    def show(Encuesta encuestaInstance) {
        respond encuestaInstance, params:['cursoId': params.cursoId]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
    def create() {
        respond new Encuesta(params), params:['cursoId': params.cursoId]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
	def save(Encuesta encuestaInstance) {
		if (encuestaInstance == null) {
			notFound()
			return
		}
		if (encuestaService.existe(encuestaInstance, params.cursoId.toLong())) {
			flash.message = "Ya existe la encuesta ${encuestaInstance}"
			redirect action: "create", params: ['cursoId': params.cursoId]
			return
		}
		if (!encuestaService.guardar(encuestaInstance)) {
			render view:'create', model: [encuestaInstance: encuestaInstance], params:['cursoId': params.cursoId]
			return
		}
		flash.message = "Encuesta ${encuestaInstance} creada"
		redirect action: "index", params:['cursoId': params.cursoId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
    def edit(Encuesta encuestaInstance) {
		def nombre = encuestaInstance.nombre
        respond encuestaInstance, model: [nombre: nombre],  params:['cursoId': params.cursoId]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
    def update(Encuesta encuestaInstance) {
        if (encuestaInstance == null) {
            notFound()
            return
        }
		if (encuestaService.existe(encuestaInstance, params.cursoId.toLong())) {
			flash.message = "Ya existe la encuesta ${encuestaInstance.nombre}"
			encuestaInstance.nombre = params.nombreAntiguo
			redirect action: "edit", params: params
			return
		}
		if (!encuestaService.guardar(encuestaInstance)) {
			render view:'edit', model: [encuestaInstance: encuestaInstance], params:['cursoId': params.cursoId]
			return
		}
		flash.message = "Encuesta ${encuestaInstance} actualizada"
		redirect action: "index", params:['cursoId': params.cursoId]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
    def delete(Encuesta encuestaInstance) {
        if (encuestaInstance == null) {
            notFound()
            return
        }
		encuestaService.eliminar(encuestaInstance)
		flash.message = "Encuesta ${encuestaInstance} eliminada"
		redirect action:"index", params:['cursoId': params.cursoId], method:"DELETE"
    }
	
    protected void notFound() {
		flash.message = "No se encuentra esa encuesta"
		redirect action: "index", params:['cursoId': params.cursoId], method: "GET"
    }
}
