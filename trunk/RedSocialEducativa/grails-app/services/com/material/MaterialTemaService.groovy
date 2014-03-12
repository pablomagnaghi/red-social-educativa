package com.material

import com.fiuba.*
import grails.transaction.Transactional

@Transactional
class MaterialTemaService {

	def existe(MaterialTema material, Long temaId) {
		
		def materialExistente = MaterialTema.findByTemaAndTitulo(Tema.get(temaId), material.titulo)
	
		return materialExistente
	}
	
	def guardar(MaterialTema material) {

		if (material.save(flush: true)) {
			return material
		}

		return null
	}

	def eliminar(MaterialTema material) {
		material.delete flush:true
	}
}
