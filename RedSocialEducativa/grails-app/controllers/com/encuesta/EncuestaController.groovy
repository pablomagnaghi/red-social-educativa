package com.encuesta

import com.cursado.*
import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class EncuestaController {

    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def encuestaService
	def preguntaChoiceService
	def preguntaDesarrolloService
	def preguntaPuntajeService
	def usuarioService
	def aprendizService
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def misEncuestas() {
		params.max = Utilidades.MAX_PARAMS
		def aprendiz = aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, params.cursoId.toLong())
		[aprendiz: aprendiz, encuestasAprendiz: EncuestaAprendiz.findAllByAprendiz(aprendiz), params: ['cursoId': params.cursoId]]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def aprendicesEncuesta() {
		params.max = Utilidades.MAX_PARAMS
		[encuesta: Encuesta.get(params.encuestaId), encuestasAprendiz: EncuestaAprendiz.findAllByEncuesta(Encuesta.get(params.encuestaId)),
			params: ['cursoId': params.cursoId, 'encuestaId': params.encuestaId]]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def encuestasAprendiz() {
		params.max = Utilidades.MAX_PARAMS
		[aprendiz: Aprendiz.get(params.aprendizId), encuestasAprendiz: EncuestaAprendiz.findAllByAprendiz(Aprendiz.get(params.aprendizId)), 
			params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def respuestasDesarrollo() {
		params.max = Utilidades.MAX_PARAMS
		[respuestas: PreguntaDesarrollo.get(params.id).respuestas, params: ['cursoId': params.cursoId, 'encuestaId': params.encuestaId]]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def encuestasCurso() {
		params.max = Utilidades.MAX_PARAMS
		def aprendiz = aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, params.cursoId.toLong())
		[encuestaInstanceList: Encuesta.findAllByCurso(Curso.get(params.cursoId)), aprendiz: aprendiz, params: ['cursoId': params.cursoId]]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def encuestaCurso(Encuesta encuestaInstance) {
		respond encuestaInstance, params:['cursoId': params.cursoId]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def responder(Encuesta encuestaInstance) {
		if (encuestaInstance == null) {
			notFound()
			return
		}
		def respuestas = [:]
		def cantRespuestas = 0
		// Se filtran los parametros de mas, los nulos y los que exceden el tamaño maximo
		for (p in params) {
			String respuesta = p.value
			if (!(p.key == "id" || p.key == "cursoId" || p.key == "action" || p.key == "controller" ||  p.key == "format" || (respuesta.size() > Utilidades.MAX_SIZE))) {
				respuestas["${p.key}"] = "${p.value}"
				if (p.value) {
					cantRespuestas++
				}
			}
		}
		if (encuestaService.cantidadPreguntas(encuestaInstance) != cantRespuestas) {
			flash.message = "Revise la encuesta. No contesto todas las preguntas o se excedio en el desarrollo [1024 caracteres}"
			redirect action: "encuestaCurso", params:['id': encuestaInstance.id, 'cursoId': params.cursoId]
			return
		}
		for (r in respuestas) {
			def preguntaChoice = PreguntaChoice.findByEncuestaAndPregunta(encuestaInstance, r.key)
			if (preguntaChoice) {
				preguntaChoiceService.agregarRespuesta(preguntaChoice, r.value)
			} else {
				def preguntaDesarrollo = PreguntaDesarrollo.findByEncuestaAndPregunta(encuestaInstance, r.key)
				if (preguntaDesarrollo) {
					preguntaDesarrolloService.agregarRespuesta(preguntaDesarrollo, r.value)
				} else {
					def preguntaPuntaje = PreguntaPuntaje.findByEncuestaAndPregunta(encuestaInstance, r.key)
					if (preguntaPuntaje) {
						String puntaje = r.value
						def respuestaPuntaje = new RespuestaPuntaje(puntaje: puntaje.toShort())
						preguntaPuntaje.addToRespuestas(respuestaPuntaje)
						preguntaPuntajeService.guardar(preguntaPuntaje)
					}
				}
			}
		}
		def aprendiz = aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, params.cursoId.toLong())
		encuestaService.crearAgregarAprendiz(encuestaInstance, aprendiz)
		flash.message = "Encuesta respondida"
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
