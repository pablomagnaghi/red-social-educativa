package com.encuesta

import grails.transaction.Transactional

@Transactional
class PreguntaChoiceService {

	def existe(PreguntaChoice pregunta, Long encuestaId) {
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
