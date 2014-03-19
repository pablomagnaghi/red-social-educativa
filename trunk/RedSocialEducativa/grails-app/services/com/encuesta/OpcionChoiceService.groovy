package com.encuesta

import grails.transaction.Transactional

@Transactional
class OpcionChoiceService {

    def existe(OpcionChoice opcion, Long preguntaId) {
		def opcionExistente = OpcionChoice.findByPreguntaAndOpcionAndIdNotEqual(PreguntaChoice.get(preguntaId), opcion.opcion, opcion?.id)
		return opcionExistente
	}
	
	def guardar(OpcionChoice opcion) {
		if (opcion.save(flush: true)) {
			return opcion
		}
		return null
	}

	def eliminar(OpcionChoice opcion) {
		opcion.delete flush:true
	}
}
