package com.material

import com.fiuba.*
import grails.transaction.Transactional

@Transactional
class MaterialCursoService {
	
	def existe(MaterialCurso material, Long cursoId) {
		def materialExistente = MaterialCurso.findByCursoAndTituloAndIdNotEqual(Curso.get(cursoId), material.titulo, material?.id)
		return materialExistente
	}
	
	def guardar(MaterialCurso material, Archivo archivoInstance) {
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
			
	def eliminar(MaterialCurso material) {
		def archivo = Archivo.get(material.idArchivo)
		if (archivo) {
			archivo.delete flush:true
		}
		material.delete flush:true
	}
}
