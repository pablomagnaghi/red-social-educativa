package com.fiuba

import grails.transaction.Transactional

@Transactional
class ActividadService {

	def asignarAlCurso(Actividad actividad, Long cursoId) {
		
		def c = Aprendiz.createCriteria()
		def aprendices = c.list {
			cuatrimestre {
				eq('curso.id', cursoId)
			}
		}
		
		Integer numGrupo = 0
		
		aprendices.each {
			if (it.participa && it.cursando) {
				numGrupo++	
				def grupo = new GrupoActividad(numero: numGrupo, nombre: "grupo ${numGrupo}", actividad: actividad)
				if (!grupo.save(flush:true)) {
					println grupo.errors
					
				}
				def grupoActividadAprendiz = new GrupoActividadAprendiz(aprendiz: it, grupo: grupo)
				if (!grupoActividadAprendiz.save(flush:true)) {
					println grupoActividadAprendiz.errors
				}
			}
		}
	}
	
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
