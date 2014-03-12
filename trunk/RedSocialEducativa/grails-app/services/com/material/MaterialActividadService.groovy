package com.material

import com.fiuba.*
import grails.transaction.Transactional

@Transactional
class MaterialActividadService {

	def existe(MaterialActividad material, Long actividadId) {
		
		def materialExistente = MaterialActividad.findByActividadAndTitulo(Actividad.get(actividadId), material.titulo)
	
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
