package com.fiuba

import grails.transaction.Transactional

@Transactional
class NoticiaRedService {

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
