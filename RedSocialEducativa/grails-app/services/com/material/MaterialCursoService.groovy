package com.material

import com.fiuba.*
import grails.transaction.Transactional

@Transactional
class MaterialCursoService {
	
	def existe(MaterialCurso material, Long cursoId) {
		
		def materialExistente = MaterialCurso.findByCursoAndTitulo(Curso.get(cursoId), material.titulo)
	
		return materialExistente
	}
	
	def guardar(MaterialCurso material, Archivo archivo) {
		def archivoInstance = new Archivo()
		archivoInstance.filename = archivo.originalFilename
		archivoInstance.filedata = archivo.getBytes()
		println "tamaño: ${archivoInstance.filedata.size()}"
		archivoInstance.save flush:true
		
		material.archivo = archivoInstance

		
		
		
		if (material.save(flush: true)) {
			return material
		}
		
		return null
	}
			
	def eliminar(MaterialCurso material) {
		material.delete flush:true
	}
}
