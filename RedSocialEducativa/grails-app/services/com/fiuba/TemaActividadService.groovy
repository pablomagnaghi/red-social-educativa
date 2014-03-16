package com.fiuba

import grails.transaction.Transactional

@Transactional
class TemaActividadService {

	def existe(Actividad actividad, Tema tema) {
		return TemaActividad.findByActividadAndTema(actividad, tema)
	}
	
	def guardar(TemaActividad temaActividad) {
		if (temaActividad.save(flush: true)) {
			return temaActividad
		}
		return null
	}

	def eliminar(TemaActividad temaActividad) {
		temaActividad.delete flush:true
	}
}
