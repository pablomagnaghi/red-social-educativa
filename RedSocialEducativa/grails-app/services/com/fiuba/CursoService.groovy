package com.fiuba

import grails.transaction.Transactional

@Transactional
class CursoService {
	
	def obtenerAprendiz(Usuario usuario, String cursoId) {
		def c = Aprendiz.createCriteria()
		def aprendiz = c {
			cuatrimestre {
				eq('curso.id', cursoId as long)
			}
			eq('usuario', usuario)
		}
		
		if (aprendiz) {
			aprendiz.first()
		}
	}
}
