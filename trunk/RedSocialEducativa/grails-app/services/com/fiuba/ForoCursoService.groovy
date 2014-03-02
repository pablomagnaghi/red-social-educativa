package com.fiuba

import grails.transaction.Transactional

@Transactional
class ForoCursoService {

	def obtenerPublicacionesOrdenadas(ForoCurso foro) {
		def c = PublicacionCurso.createCriteria()
		def publicaciones = c.list {
			eq('foro.id', foro.id)
			isNull('publicacionInicial.id')
			and {
				order('fecha', 'desc')
				order('hora', 'desc')
			}
		}
		
		// TODO probar en detalle despues
		println "publicaciones: ${publicaciones}"
		println "lo anterior: ${PublicacionCurso.findAllByForoAndPublicacionInicial(foro, null)}"
		
		return publicaciones
	}
	
    def obtenerRespuestas(Cuatrimestre cuatrimestre, Long publicacionId, Integer max, Integer offset) {
		
		Long foroId = cuatrimestre.foro.id
		
		def c = PublicacionCurso.createCriteria()
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
