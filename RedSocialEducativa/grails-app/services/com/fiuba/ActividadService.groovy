package com.fiuba

import grails.transaction.Transactional

@Transactional
class ActividadService {

	def existe(Actividad actividad, Long cuatrimestreId) {
		
		def actividadExistente = Actividad.findByCuatrimestreAndTitulo(Cuatrimestre.get(cuatrimestreId), actividad.titulo)
	
		return actividadExistente
	}
	
    def guardar(Actividad actividad) {
		
		if (actividad.save(flush: true)) {
			return actividad
		}
		
		return null
	}
			
	def eliminar(Actividad actividad) {
		actividad.delete flush:true
	}
}
