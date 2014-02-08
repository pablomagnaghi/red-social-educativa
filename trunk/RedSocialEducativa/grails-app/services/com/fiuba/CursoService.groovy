package com.fiuba

import grails.transaction.Transactional

@Transactional
class CursoService {
	
	// TODO cuando un aprendiz pierde la cursada, reprueba tres veces el final o se le vence la materia
	// participa pasa a ser false o agregar atributo estado en aprendiz
	// Estado [cursando/curso aprobado/]
	
	
	def obtenerAprendizCurso(Usuario usuario, String cursoId) {
		def c = Aprendiz.createCriteria()
		def aprendiz = c {
			cuatrimestre {
				eq('curso.id', cursoId as long)
			}
			eq('usuario', usuario)
		}
		
		if (aprendiz) {
			aprendiz.first()
		}
	}
	
	def obtenerCuatrimestreActual(String cursoId) {
		
		// Reviso en que periodo del año estamos, para determinar
		// que cuatrimestre tendria que existir en el curso
		
		// Anio del cuatrimestre
		def anio = Utilidades.ANIO
		// Primer o segundo cuatrimestre
		def numero = 2
		
		if ((Utilidades.FECHA > Utilidades.INICIO_PRIMER_CUATRIMESTRE) && (Utilidades.FECHA < Utilidades.INICIO_SEGUNDO_CUATRIMESTRE )) {
			numero = 1
		} 
		
		if (Utilidades.FECHA < Utilidades.INICIO_PRIMER_CUATRIMESTRE) {
			anio--
		}
		
		def curso = Curso.get(cursoId)
		def cuatrimestre = Cuatrimestre.findByCursoAndAnioAndNumero(curso, anio, numero)
		
		return cuatrimestre	
	}
}
