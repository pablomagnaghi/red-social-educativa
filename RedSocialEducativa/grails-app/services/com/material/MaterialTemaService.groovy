package com.material

import com.fiuba.*
import grails.transaction.Transactional

@Transactional
class MaterialTemaService {

	def existe(MaterialTema material, Long temaId) {
		def materialExistente = MaterialTema.findByTemaAndTituloAndIdNotEqual(Tema.get(temaId), material.titulo, material?.id)
		return materialExistente
	}
	
	def guardar(MaterialTema material, Archivo archivoInstance) {
		if (archivoInstance) {
			if (!archivoInstance.save(flush:true)) {
				return null
			}
			material.idArchivo = archivoInstance.id
		}
		
		if (!material.save(flush: true)) {
			return null
		}
		return material
	}

	def eliminar(MaterialTema material) {
		def archivo = Archivo.get(material.idArchivo)
		if (archivo) {
			archivo.delete flush:true
		}
		material.delete flush:true
	}
}
