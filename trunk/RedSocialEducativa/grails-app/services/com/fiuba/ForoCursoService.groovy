package com.fiuba

import grails.transaction.Transactional

@Transactional
class ForoCursoService {

    def obtenerRespuestas(Cuatrimestre cuatrimestre, Long publicacion, Integer max, Integer offset) {
		
		Long foroId = cuatrimestre.foro.id
		
		def c = PublicacionCurso.createCriteria()
		def respuestas = c.list([max: params.max, offset: offset]){
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
