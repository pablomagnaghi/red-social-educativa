package com.fiuba

import grails.transaction.Transactional

@Transactional
class ForoGeneralService {

	def obtenerPublicacionesOrdenadas() {
		def c = PublicacionGeneral.createCriteria() 
		def publicaciones = c.list {
			eq('foro.id', ForoGeneral.first().id)
			isNull('publicacionInicial.id')
			and {
				order('fecha', 'desc')
				order('hora', 'desc')
			}
		}
		
		// TODO probar en detalle despues
		println "publicaciones: ${publicaciones}"
		println "lo anterior: ${PublicacionGeneral.findAllByForoAndPublicacionInicial(ForoGeneral.first(), null)}"
		
		return publicaciones
	}

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
