package com.fiuba

import grails.transaction.Transactional

@Transactional
class MateriaService {

	def guardar(Materia materia) {

		if (materia.save(flush: true)) {
			return materia
		}

		return null
	}

	def eliminar(Materia materia) {
		materia.delete flush:true
	}
}
