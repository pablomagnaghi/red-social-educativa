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
	
	def crearGrupo(GrupoCurso grupo, Usuario usuario, Long cuatrimestreId) {
		
		def aprendiz = Aprendiz.findByUsuarioAndCuatrimestre(usuario, Cuatrimestre.get(cuatrimestreId))
		
		grupo.addToAprendices(aprendiz)
		
		return guardar(grupo)
	}
	
	def agregarAprendiz(GrupoCurso grupo, Aprendiz aprendiz) {
		
		grupo.addToAprendices(aprendiz)
		
		if (grupo.save(flush: true)) {
			return grupo
		}

		return null
	}

	def obtenerGrupoPorNumero(Long cuatrimestreId, Integer numeroGrupo) {
		def cuatrimestre = Cuatrimestre.get(cuatrimestreId)
		
		return GrupoCurso.findByCuatrimestreAndNumero(cuatrimestre, numeroGrupo)
	}

	def obtenerGrupos(Long cuatrimestreId) {
		def cuatrimestre = Cuatrimestre.get(cuatrimestreId)
		
		return cuatrimestre.grupos
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
