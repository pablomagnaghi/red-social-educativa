package com.fiuba

import grails.transaction.Transactional

@Transactional
class TemaActividadService {

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
