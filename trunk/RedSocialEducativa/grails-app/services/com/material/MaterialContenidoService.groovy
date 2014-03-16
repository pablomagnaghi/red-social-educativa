package com.material

import com.cursado.*
import grails.transaction.Transactional

@Transactional
class MaterialContenidoService {

	def existe(MaterialContenido material, Long contenidoId) {
		def materialExistente = MaterialContenido.findByContenidoAndTituloAndIdNotEqual(Contenido.get(contenidoId), material.titulo, material?.id)
		return materialExistente
	}
	
	def guardar(MaterialContenido material) {
		if (material.save(flush: true)) {
			return material
		}
		return null
	}

	def eliminar(MaterialContenido material) {
		material.delete flush:true
	}
}
