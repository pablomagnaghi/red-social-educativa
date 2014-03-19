package com.encuesta

import com.cursado.*
import grails.transaction.Transactional

@Transactional
class EncuestaService {

	def existe(Encuesta encuesta, Long cursoId) {
		def encuestaExistente = Encuesta.findByCursoAndNombreAndIdNotEqual(Curso.get(cursoId), encuesta.nombre, encuesta?.id)
		return encuestaExistente
	}
	
	def guardar(Encuesta encuesta) {
		if (encuesta.save(flush: true)) {
			return encuesta
		}
		return null
	}

	def eliminar(Encuesta encuesta) {
		encuesta.delete flush:true
	}
}
