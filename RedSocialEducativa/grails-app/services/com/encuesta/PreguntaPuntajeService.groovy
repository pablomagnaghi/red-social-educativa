package com.encuesta

import grails.transaction.Transactional

@Transactional
class PreguntaPuntajeService {

	def obtenerCantidadRespuestas(PreguntaPuntaje puntaje, Short i) {
		Integer cant = 0
		puntaje.respuestas.each {
			if (it.puntaje == i) {
				cant++
			}
		}
		return cant
	}	
	
    def existe(PreguntaPuntaje pregunta, Long encuestaId) {	
		if (PreguntaChoice.findByEncuestaAndPregunta(Encuesta.get(encuestaId), pregunta.pregunta)) {
			return true
		}
		if (PreguntaDesarrollo.findByEncuestaAndPregunta(Encuesta.get(encuestaId), pregunta.pregunta)) {
			return true
		}
		def preguntaExistente = PreguntaPuntaje.findByEncuestaAndPreguntaAndIdNotEqual(Encuesta.get(encuestaId), pregunta.pregunta, pregunta?.id)
		return preguntaExistente
	}
	
	def guardar(PreguntaPuntaje pregunta) {
		if (pregunta.save(flush: true)) {
			return pregunta
		}
		return null
	}

	def eliminar(PreguntaPuntaje pregunta) {
		pregunta.delete flush:true
	}
}
