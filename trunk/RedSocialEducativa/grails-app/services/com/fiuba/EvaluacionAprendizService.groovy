package com.fiuba

import grails.transaction.Transactional

@Transactional
class EvaluacionAprendizService {

	def existe(Evaluacion Evaluacion, Aprendiz aprendiz) {
		return EvaluacionAprendiz.findByEvaluacionAndAprendiz(Evaluacion, aprendiz)
	}
	
	def guardar(EvaluacionAprendiz evaluacionAprendiz) {
		if (evaluacionAprendiz.save(flush: true)) {
			return evaluacionAprendiz
		}
		return null
	}

	def eliminar(EvaluacionAprendiz evaluacionAprendiz) {
		evaluacionAprendiz.delete flush:true
	}
}
