package com.material

import com.cursado.*
import grails.transaction.Transactional

@Transactional
class MaterialActividadService {

	def existe(MaterialActividad material, Long actividadId) {
		def materialExistente = MaterialActividad.findByActividadAndTituloAndIdNotEqual(Actividad.get(actividadId), material.titulo, material?.id)
		return materialExistente
	}
	
	def guardar(MaterialActividad material) {
		if (material.save(flush: true)) {
			return material
		}
		return null
	}

	def eliminar(MaterialActividad material) {
		material.delete flush:true
	}
}
