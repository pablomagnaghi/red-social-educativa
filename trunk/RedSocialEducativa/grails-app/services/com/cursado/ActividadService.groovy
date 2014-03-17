package com.cursado

import com.fiuba.*

import java.util.Date;

import grails.transaction.Transactional

@Transactional
class ActividadService {

	def obtenerActividadesPorAprendiz(Aprendiz aprendiz, Long cuatrimestreId) {
		if (!aprendiz) {
			return null
		}
		def actividadesAprendiz = GrupoActividadAprendiz.createCriteria().list {
			grupo {
				actividad {
					eq('cuatrimestre.id', cuatrimestreId)
				}
			}
			eq('aprendiz.id', aprendiz.id)
		}
		return actividadesAprendiz
	}
	
	def obtenerFecha(Date fecha) {
		Integer anio = fecha.getYear() + Utilidades.ANIO_INICIAL
		Integer mes =  fecha.getMonth() + 1
		Integer dia = fecha.getAt(Calendar.DAY_OF_MONTH)
		Integer fechaNum = 10000 * (anio) + 100 * mes + dia
		return fechaNum
	}
	
	def asignarAlCurso(Actividad actividad, Long cursoId) {
		def aprendices = Aprendiz.createCriteria().list {
			cuatrimestre {
				eq('curso.id', cursoId)
			}
		}
		Integer numGrupo = 0
		aprendices.each {
			if (it.participa && it.cursando) {
				numGrupo++	
				def grupo = new GrupoActividad(numero: numGrupo, nombre: "grupo ${numGrupo}", actividad: actividad)
				grupo.save flush:true
				def grupoActividadAprendiz = new GrupoActividadAprendiz(aprendiz: it, grupo: grupo)
				grupoActividadAprendiz.save flush:true
			}
		}
	}
	
	def existe(Actividad actividad, Long cuatrimestreId) {
		def actividadExistente = Actividad.findByCuatrimestreAndTituloAndIdNotEqual(Cuatrimestre.get(cuatrimestreId), actividad.titulo, actividad?.id)
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
