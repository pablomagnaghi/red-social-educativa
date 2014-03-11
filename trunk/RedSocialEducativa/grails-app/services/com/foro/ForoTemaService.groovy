package com.foro

import com.fiuba.*
import grails.transaction.Transactional

@Transactional
class ForoTemaService {

	def obtenerPublicacionesOrdenadas(ForoTema foro) {
		def c = PublicacionTema.createCriteria()
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
		println "lo anterior: ${PublicacionTema.findAllByForoAndPublicacionInicial(foro,null)}"
		
		return publicaciones
	}
	
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
