package com.fiuba

import grails.transaction.Transactional

@Transactional
class EvaluacionService {

	def agregarAprendices(Evaluacion evaluacion, Long cursoId) {
		
		def c = Aprendiz.createCriteria()
		def aprendices = c.list {
			cuatrimestre {
				eq('curso.id', cursoId)
			}
		}
		
		// TODO si se tienen que agregar todos los que cursaron a lo largo de los N cuatrimestres, sacar el it.cursando
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

	def obtenerEvaluacionesPorAprendiz(Aprendiz aprendiz, Long cursoId) {

		if (!aprendiz) {
			return null
		}

		def c = EvaluacionAprendiz.createCriteria()
		def evaluacionesAprendiz = c {
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
