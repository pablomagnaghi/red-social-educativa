package com.fiuba

import grails.transaction.Transactional

@Transactional
class MaterialGrupoService {

	def guardar(MaterialGrupo material) {

		if (material.save(flush: true)) {
			return material
		}

		return null
	}
	
	def eliminar(MaterialGrupo material) {
		material.delete flush:true
	}
}
