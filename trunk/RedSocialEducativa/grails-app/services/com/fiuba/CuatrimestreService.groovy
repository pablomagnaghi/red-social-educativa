package com.fiuba

import grails.transaction.Transactional

@Transactional
class CuatrimestreService {

	def obtenerCuatrimestreActual(Long cursoId) {

		// Reviso en que periodo del año estamos, para determinar que cuatrimestre tendria que existir en el curso

		// Anio del cuatrimestre
		def anio = Utilidades.ANIO
		// Primer o segundo cuatrimestre
		def numero = 2

		def calendario = Calendario.findByAnio(Utilidades.ANIO)

		if ((Utilidades.FECHA > calendario.inicioPrimerCuatrimestre) && (Utilidades.FECHA < calendario.inicioSegundoCuatrimestre)) {
			numero = 1
		}

		if (Utilidades.FECHA < calendario.inicioPrimerCuatrimestre) {
			anio--
		}

		def curso = Curso.get(cursoId)
		def cuatrimestre = Cuatrimestre.findByCursoAndAnioAndNumero(curso, anio, numero)

		return cuatrimestre
	}

	def existe(Cuatrimestre cuatrimeste, Long cursoId) {

		def curso = Curso.get(cursoId)
		def cuatrimestreExistente = Cuatrimestre.findByCursoAndAnioAndNumero(curso, cuatrimestre.anio, cuatrimestre.numero)

		return cuatrimestreExistente
	}

	def guardar(Cuatrimestre cuatrimestre) {

		if (cuatrimestre.save(flush:true)) {
			return cuatrimestre
		}

		return null
	}

	def eliminar(Cuatrimestre cuatrimestre) {
		cuatrimestre.delete flush:true
	}
}
