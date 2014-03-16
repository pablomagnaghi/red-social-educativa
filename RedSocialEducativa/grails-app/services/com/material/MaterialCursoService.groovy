package com.material

import com.cursado.*
import grails.transaction.Transactional

@Transactional
class MaterialCursoService {
	
	def existe(MaterialCurso material, Long cursoId) {
		def materialExistente = MaterialCurso.findByCursoAndTituloAndIdNotEqual(Curso.get(cursoId), material.titulo, material?.id)
		return materialExistente
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
