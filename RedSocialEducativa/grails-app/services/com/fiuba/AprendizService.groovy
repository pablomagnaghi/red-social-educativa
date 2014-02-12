package com.fiuba

import grails.transaction.Transactional

@Transactional
class AprendizService {

	// TODO cuando un aprendiz pierde la cursada, reprueba tres veces el final o se le vence la materia
	// participa pasa a ser false o agregar atributo estado en aprendiz
	// Estado [inactivo/activo/cursada aprobada/curso terminado]

	def obtenerCursos(Usuario usuario) {

		def ArrayList<Curso> cursosAprendiz = new ArrayList<Curso>()
		def ArrayList<Aprendiz> aprendices = Aprendiz.findAllByUsuario(usuario)

		aprendices.each {
			if (it.participa) {
				cursosAprendiz.add(it.cuatrimestre.curso)
			}
		}
		return cursosAprendiz
	}

	// TODO agregar el participa true, porque si ya se le vencio o aprobo pasa a participa false
	def obtenerPorCurso(Long usuarioId, Long cursoId) {
		def c = Aprendiz.createCriteria()
		def aprendiz = c.get {
			cuatrimestre {
				eq('curso.id', cursoId)
			}
			eq('usuario.id', usuarioId)
		}
	}
	
	def guardar(Aprendiz aprendiz) {
		
		if (aprendiz.save(flush:true)) {
			return aprendiz
		}
		
		return null
	}
	
	def eliminar(Aprendiz aprendiz) {
		aprendiz.delete flush:true
	}
}
