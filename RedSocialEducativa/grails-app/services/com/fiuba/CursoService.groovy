package com.fiuba

import grails.transaction.Transactional

@Transactional
class CursoService {

	def existe(Curso curso) {
		def cursoExistente = Curso.findByMateriaAndNroRelativo(Materia.get(curso.materia.id), curso.nroRelativo)

		return cursoExistente
	}

	def guardar(Curso curso) {

		if (curso.save(flush: true)) {
			return curso
		}

		return null
	}

	def eliminar(Curso curso) {
		curso.delete flush:true
	}
}
