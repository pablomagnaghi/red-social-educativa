package com.fiuba

import grails.transaction.Transactional

@Transactional
class MaterialCursoService {
	
	def existe(MaterialCurso material, Long cursoId) {
		
		def materialCursoExistente = MaterialCurso.findByCursoAndTitulo(Curso.get(cursoId), material.titulo)
	
		return materialCursoExistente
	}
	
	def guardar(MaterialCurso material) {
		
		if (material.save(flush: true)) {
			return material
		}
		
		return null
	}
			
	def eliminar(MaterialCurso material) {
		material.delete flush:true
	}
}
