package com.fiuba

import grails.transaction.Transactional

@Transactional
class GrupoCursoService {
	
    def guardar(GrupoCurso grupo) {
		
		if (grupo.save(flush:true)) {
			return grupo
		}
		
		return null
    }
	
	def agregarAprendiz(GrupoCurso grupo, Aprendiz aprendiz) {
		
		grupo.addToAprendices(aprendiz)
		
		return guardar(grupo)
	}

	def obtenerGrupoPorNumero(Cuatrimestre cuatrimestre, Integer numeroGrupo) {

		return GrupoCurso.findByCuatrimestreAndNumero(cuatrimestre, numeroGrupo)
	}

	def obtenerGrupos(Cuatrimestre cuatrimestre) {
	
		return GrupoCurso.findAllByCuatrimestre(cuatrimestre)
	}
	
	def eliminar(GrupoCurso grupo) {
		// Fundamental para borrar las claves foraneas en aprendiz.curso
		def aprendices = grupo.aprendices

		for(int i = 0; i<aprendices.size(); i++){
			grupo.aprendices[i].grupo = null
		}
		
		grupo.delete flush:true
	}
}
