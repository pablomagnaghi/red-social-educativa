package com.cursado

import grails.transaction.Transactional

@Transactional
class TemaService {

    def existe(Tema tema, Long cursoId) {
		
		def temaExistente = Tema.findByCursoAndTitulo(Curso.get(cursoId), tema.titulo)
	
		return temaExistente
	}
	
	def guardar(Tema tema) {
	
		if (tema.save(flush: true)) {
			return tema
		}
		
		return null
	}
			
	def eliminar(Tema tema) {
		tema.delete flush:true
	}
}
