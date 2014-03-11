package com.cartelera

import grails.transaction.Transactional

@Transactional
class NoticiaCursoService {

	def obtenerNoticiasOrdenadas(Long cuatrimestreId) {
		def noticias = NoticiaCurso.withCriteria {
			eq('cuatrimestre.id', cuatrimestreId)
			and {
				order('fecha', 'desc')
				order('hora', 'desc')
			}
		}
		return noticias
	}

    def guardar(NoticiaCurso noticia) {
		
		if (noticia.save(flush: true)) {
			return noticia
		}
		
		return null
    }
	
	def eliminar(NoticiaCurso noticia) {
		noticia.delete flush:true
	}
}
