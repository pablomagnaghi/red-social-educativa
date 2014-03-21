package com.encuesta

import grails.transaction.Transactional

@Transactional
class PreguntaDesarrolloService {

	def agregarRespuesta(PreguntaDesarrollo pregunta, String respuesta) {
		def respuestaDesarrollo = new RespuestaDesarrollo(respuesta: respuesta)
		pregunta.addToRespuestas(respuestaDesarrollo)
		pregunta.save flush:true
	}
	
    def existe(PreguntaDesarrollo pregunta, Long encuestaId) {
		if (PreguntaChoice.findByEncuestaAndPregunta(Encuesta.get(encuestaId), pregunta.pregunta)) {
			return true
		}
		if (PreguntaPuntaje.findByEncuestaAndPregunta(Encuesta.get(encuestaId), pregunta.pregunta)) {
			return true
		}
		def preguntaExistente = PreguntaDesarrollo.findByEncuestaAndPreguntaAndIdNotEqual(Encuesta.get(encuestaId), pregunta.pregunta, pregunta?.id)
		return preguntaExistente
	}
	
	def guardar(PreguntaDesarrollo pregunta) {
		if (pregunta.save(flush: true)) {
			return pregunta
		}
		return null
	}

	def eliminar(PreguntaDesarrollo pregunta) {
		pregunta.delete flush:true
	}
}
