package com.cursado

import com.fiuba.*
import grails.transaction.Transactional

@Transactional
class CuatrimestreService {

	def consolidar(Cuatrimestre cuatrimestre) {
		def ArrayList<Aprendiz> aprendices = Aprendiz.findAllByCuatrimestre(cuatrimestre)
		aprendices.each {
			if (it.participa) {
				it.cursando = false
				it.save flush:true
			}
		}
	}
	
	def obtenerAnio() {
		def anio = Utilidades.ANIO
		def calendario = Calendario.findByAnio(Utilidades.ANIO)
		if (Utilidades.FECHA < calendario.inicioPrimerCuatrimestre) {
			anio--
		}
		return anio
	}
	
	def obtenerNumero() {
		def calendario = Calendario.findByAnio(Utilidades.ANIO)
		def numero = 2
		if ((Utilidades.FECHA > calendario.inicioPrimerCuatrimestre) && ( Utilidades.FECHA < calendario.inicioSegundoCuatrimestre)) {
			numero = 1
		}
		return numero
	}
	
	// Revisa en que periodo del año estamos, para determinar que cuatrimestre tendria que existir en el curso
	def obtenerCuatrimestreActual(Long cursoId) {
		def anio = obtenerAnio()
		def numero = obtenerNumero()
		def curso = Curso.get(cursoId)
		def cuatrimestre = Cuatrimestre.findByCursoAndAnioAndNumero(curso, anio, numero)
		return cuatrimestre
	}
	
	def obtenerCuatrimestresOrdenados(Long cursoId) {
		def cuatrimestres = Cuatrimestre.withCriteria {
			eq('curso.id', cursoId)
			and {
				order('anio', 'desc')
				order('numero', 'desc')
			}
		}
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
