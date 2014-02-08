package com.fiuba

import grails.transaction.Transactional

@Transactional
class CursoService {
	
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
		//agregar si aprobo la cursada o no
		// si aprobo el final o se le vencio
	}
	
	// Si el curso no se dicta ese cuatrimestre: 
	// Solo se muestran los temas y el material general
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
		def cuatrimestre = Cuatrimestre.findAllByCursoAndAnioAndNumero(curso, anio, numero)
		
		return cuatrimestre	
	}
}
