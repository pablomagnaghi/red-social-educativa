package com.foro

import grails.transaction.Transactional

@Transactional
class ForoGeneralService {
	
	def obtenerRespuestas(Long publicacionId, Integer max, Integer offset) {

		def c = PublicacionGeneral.createCriteria()
		def respuestas = c.list([max: max, offset: offset]){
			or {
				eq("id", publicacionId)
				eq("publicacionInicial.id", publicacionId)
			}
		}
		return respuestas
	}
}
