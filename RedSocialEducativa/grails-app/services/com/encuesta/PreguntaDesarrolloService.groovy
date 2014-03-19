package com.encuesta

import grails.transaction.Transactional

@Transactional
class PreguntaDesarrolloService {

    def existe(PreguntaDesarrollo pregunta, Long encuestaId) {
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
