package com.fiuba

import grails.transaction.Transactional

@Transactional
class ForoTemaService {

	def obtenerRespuestas(Tema tema, Long publicacionId, Integer max, Integer offset) {

		Long foroId = tema.foro.id

		def c = PublicacionTema.createCriteria()
		def respuestas = c.list([max: max, offset: offset]){
			and {
				or {
					eq("id", publicacionId)
					eq("publicacionInicial.id", publicacionId)
				}
				eq("foro.id", foroId)
			}
		}
	}
}
