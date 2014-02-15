package com.fiuba

import grails.transaction.Transactional

@Transactional
class EvaluacionService {

	def inscribirAprendiz(evaluacion, aprendiz) {
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
