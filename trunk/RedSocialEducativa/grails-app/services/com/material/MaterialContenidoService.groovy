package com.material

import com.fiuba.*
import grails.transaction.Transactional

@Transactional
class MaterialContenidoService {

	def existe(MaterialContenido material, Long contenidoId) {
		
		def materialExistente = MaterialContenido.findByContenidoAndTitulo(Contenido.get(contenidoId), material.titulo)
	
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
