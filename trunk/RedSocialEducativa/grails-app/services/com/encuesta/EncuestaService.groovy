package com.encuesta

import com.cursado.*
import com.fiuba.*
import grails.transaction.Transactional

@Transactional
class EncuestaService {

	def crearAgregarAprendiz(Encuesta encuesta, Aprendiz aprendiz) {
		def encuestaAprendiz = new EncuestaAprendiz(encuesta: encuesta, aprendiz: aprendiz)
		encuestaAprendiz.save flush: true
	}
	
	def cantidadPreguntas(Encuesta encuesta) {
		Integer cantChoice = PreguntaChoice.findAllByEncuesta(encuesta).size() 
		Integer cantDesarrollo = PreguntaDesarrollo.findAllByEncuesta(encuesta).size() 
		Integer cantPuntaje = PreguntaPuntaje.findAllByEncuesta(encuesta).size()
		Integer cant = cantChoice + cantDesarrollo + cantPuntaje
		return cant
	}
	
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
