package com.cursado

import grails.transaction.Transactional

@Transactional
class ContenidoService {

	def existe(Contenido contenido, Long temaId) {
		
		def contenidoExistente = Contenido.findByTemaAndTitulo(Tema.get(temaId), contenido.titulo)
	
		return contenidoExistente
	}
	
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
