package com.fiuba

import grails.transaction.Transactional

@Transactional
class ContenidoService {

    def guardar(Contenido contenido) {
		
		if (contenido.save(flush: true)) {
			return contenido
		}
		
		return null
	}
			
	def eliminar(Contenido contenido) {
		contenido.delete flush:true
	}
}
