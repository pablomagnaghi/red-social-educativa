package com.encuesta

import grails.transaction.Transactional

@Transactional
class PreguntaChoiceService {

	def agregarRespuesta(PreguntaChoice pregunta, String opcion) {
		def opcionChoice = OpcionChoice.findByPreguntaAndOpcion(pregunta, opcion)
		opcionChoice.cantRespuestas++
		opcionChoice.save flush:true
	}
	
	def existe(PreguntaChoice pregunta, Long encuestaId) {
		if (PreguntaDesarrollo.findByEncuestaAndPregunta(Encuesta.get(encuestaId), pregunta.pregunta)) {
			return true
		}
		if (PreguntaPuntaje.findByEncuestaAndPregunta(Encuesta.get(encuestaId), pregunta.pregunta)) {
			return true
		}
		def preguntaExistente = PreguntaChoice.findByEncuestaAndPreguntaAndIdNotEqual(Encuesta.get(encuestaId), pregunta.pregunta, pregunta?.id)
		return preguntaExistente
	}
	
	def guardar(PreguntaChoice pregunta) {
		if (pregunta.save(flush: true)) {
			return pregunta
		}
		return null
	}

	def eliminar(PreguntaChoice pregunta) {
		pregunta.delete flush:true
	}
}
