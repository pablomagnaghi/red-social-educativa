package com.fiuba

import grails.transaction.Transactional

@Transactional
class CursoService {

	def cuatrimestreService
	
	def agregarAprendiz(Usuario usuario, Long cursoId) {
		
		def aprendiz = new Aprendiz(usuario: usuario, rol: Rol.findByAuthority('ROL_APRENDIZ'), participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", cursando: false)
		
		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(cursoId)
		cuatrimestre.addToAprendices(aprendiz)

		if (aprendiz.save(flush: true)) {
			return aprendiz
		}
		
		return null
	}
	
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
