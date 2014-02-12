package com.fiuba

import grails.transaction.Transactional

@Transactional
class NoticiaCursoService {

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
