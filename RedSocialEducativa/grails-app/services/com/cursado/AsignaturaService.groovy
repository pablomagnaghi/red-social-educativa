package com.cursado

import grails.transaction.Transactional

@Transactional
class AsignaturaService {

	def existe(Asignatura asignatura) {
		def asignaturaExistente = Asignatura.findByCodigoAndIdNotEqual(asignatura.codigo, asignatura?.id)
		return asignaturaExistente
	}
	
	def guardar(Asignatura asignatura) {
		if (asignatura.save(flush: true)) {
			return asignatura
		}
		return null
	}

	def eliminar(Asignatura asignatura) {
		asignatura.delete flush:true
	}
}
