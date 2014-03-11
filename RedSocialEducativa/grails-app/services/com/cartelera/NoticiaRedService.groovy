package com.cartelera

import grails.transaction.Transactional

@Transactional
class NoticiaRedService {

	def obtenerNoticiasOrdenadas() {
		def noticias = NoticiaRed.withCriteria {
			order('fecha', 'desc')
			order('hora', 'desc')
		}
	}
	
	def guardar(NoticiaRed noticia) {
		if (noticia.save(flush: true)) {
			return noticia
		}
		return null
	}

	def eliminar(NoticiaRed noticia) {
		noticia.delete flush:true
	}
}
