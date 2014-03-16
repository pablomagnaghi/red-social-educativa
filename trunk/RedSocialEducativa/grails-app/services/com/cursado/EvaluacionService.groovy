package com.cursado

import com.fiuba.*
import grails.transaction.Transactional

@Transactional
class EvaluacionService {

	def obtenerFecha(Date fecha) {
		Integer anio = fecha.getYear() + Utilidades.ANIO_INICIAL
		Integer mes =  fecha.getMonth() + 1
		Integer dia = fecha.getAt(Calendar.DAY_OF_MONTH)
		Integer fechaNum = 10000 * (anio) + 100 * mes + dia
		return fechaNum
	}
	
	// Se agregan los que cursan el cuatrimestre actual.
	// Esta hecho para el caso del primer parcial, que es obligatorio para todos los alumnos del cuatrimestre.
	def agregarAprendices(Evaluacion evaluacion, Long cursoId) {
		def aprendices = Aprendiz.createCriteria().list {
			cuatrimestre {
				eq('curso.id', cursoId)
			}
		}
		aprendices.each {
			if (it.participa && it.cursando) {
				inscribirAprendiz(evaluacion, it)
			}
		}
	}
	
	def inscribirAprendiz(Evaluacion evaluacion, Aprendiz aprendiz) {
		def evaluacionAprendiz = new EvaluacionAprendiz(evaluacion: evaluacion, aprendiz: aprendiz)
		evaluacionAprendiz.save flush:true
	}
	
	def desinscribirAprendiz(Evaluacion evaluacion, Aprendiz aprendiz) {
		def evaluacionAprendiz = EvaluacionAprendiz.findByEvaluacionAndAprendiz(evaluacion, aprendiz)
		evaluacionAprendiz.delete flush:true
	}

	def obtenerEvaluacionesPorAprendiz(Aprendiz aprendiz, Long cursoId) {
		if (!aprendiz) {
			return null
		}
		def evaluacionesAprendiz = EvaluacionAprendiz.createCriteria().list {
			evaluacion {
				eq('curso.id', cursoId)
			}
			eq('aprendiz.id', aprendiz.id)
		}
		return evaluacionesAprendiz
	}

	def guardar(Evaluacion evaluacion) {
		if (evaluacion.save(flush: true)) {
			return evaluacion
		}
		return null
	}

	def eliminar(Evaluacion evaluacion) {
		evaluacion.delete flush:true
	}
}
