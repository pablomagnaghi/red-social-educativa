package com.material

import com.fiuba.*
import grails.transaction.Transactional

@Transactional
class MaterialContenidoService {

	def existe(MaterialContenido material, Long contenidoId) {
		def materialExistente = MaterialContenido.findByContenidoAndTituloAndIdNotEqual(Contenido.get(contenidoId), material.titulo, material?.id)
		return materialExistente
	}
	
	def guardar(MaterialContenido material, Archivo archivoInstance) {
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

	def eliminar(MaterialContenido material) {
		def archivo = Archivo.get(material.idArchivo)
		if (archivo) {
			archivo.delete flush:true
		}
		material.delete flush:true
	}
}
