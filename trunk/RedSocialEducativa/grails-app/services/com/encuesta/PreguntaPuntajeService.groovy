package com.encuesta

import grails.transaction.Transactional

@Transactional
class PreguntaPuntajeService {

    def existe(PreguntaPuntaje pregunta, Long encuestaId) {
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
