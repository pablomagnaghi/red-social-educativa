package com.fiuba

import grails.transaction.Transactional

@Transactional
class CursoService {

	def existe(Curso curso) {
		def cursoExistente = Curso.findByMateriaAndNroRelativo(Materia.get(curso.materia.id), curso.nroRelativo)

		return cursoExistente
	}

	def guardar(Curso curso) {

		if (curso.save(flush: true)) {
			return curso
		}

		return null
	}

	def eliminar(Curso curso) {
		curso.delete flush:true
	}
	
	def seDicta(Long cursoId) {
		
		def curso = Curso.get(cursoId)
		
		if (curso.cuatDict == Utilidades.CUAT_AMBOS) {
			return true
		}
		
		def calendario = Calendario.findByAnio(Utilidades.ANIO)

		if (((Utilidades.FECHA > calendario.inicioPrimerCuatrimestre) && (Utilidades.FECHA < calendario.inicioSegundoCuatrimestre)) && 
			(curso.cuatDict == Utilidades.CUAT_UNO)) {
			return true
		}

		if (((Utilidades.FECHA < calendario.inicioPrimerCuatrimestre) || (Utilidades.FECHA > calendario.inicioSegundoCuatrimestre)) &&
			(curso.cuatDict == Utilidades.CUAT_DOS)) {
			return true
		}

		return false
	}
}
