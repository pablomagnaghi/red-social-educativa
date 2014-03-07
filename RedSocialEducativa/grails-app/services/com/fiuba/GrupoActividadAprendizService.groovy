package com.fiuba

import grails.transaction.Transactional

@Transactional
class GrupoActividadAprendizService {

    def guardar(GrupoActividadAprendiz grupoActividadAprendiz) {
		if (grupoActividadAprendiz.save(flush:true)) {
			return grupoActividadAprendiz
		}
		return null
	}
	
	def eliminar(GrupoActividadAprendiz grupo) {
		grupo.delete flush:true
	}
}
