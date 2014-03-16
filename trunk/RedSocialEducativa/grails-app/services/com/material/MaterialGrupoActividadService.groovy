package com.material

import com.cursado.*
import grails.transaction.Transactional

@Transactional
class MaterialGrupoActividadService {

	def existe(MaterialGrupoActividad material, Long grupoId) {
		def materialExistente = MaterialGrupoActividad.findByGrupoAndTituloAndIdNotEqual(GrupoActividad.get(grupoId), material.titulo, material?.id)
		return materialExistente
	}
	
	def guardar(MaterialGrupoActividad material) {
		if (material.save(flush: true)) {
			return material
		}
		return null
	}
	
	def eliminar(MaterialGrupoActividad material) {
		material.delete flush:true
	}
}
