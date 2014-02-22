package com.fiuba

import grails.transaction.Transactional

@Transactional
class AprendizService {

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

	def obtenerPorCurso(Long usuarioId, Long cursoId) {
		def c = Aprendiz.createCriteria()
		def aprendiz = c.get {
			cuatrimestre {
				eq('curso.id', cursoId)
			}
			eq('usuario.id', usuarioId)
			eq('participa', true)
		}
		return aprendiz
	}
	
	def obtenerTodosAprendicesDeCurso(Long cursoId) {
		def c = Aprendiz.createCriteria()
		def aprendices = c {
			cuatrimestre {
				eq('curso.id', cursoId)
			}
			eq('participa', true)
		}
		return aprendices
	}

	// Obtener el grupo en el que participa el aprendiz en esa actividad
	def obtenerGrupoPorActividad(Usuario usuario, Long cuatrimestreId, Long actividadId) {

		Long aprendizId = Aprendiz.findByUsuarioAndCuatrimestre(usuario, Cuatrimestre.get(cuatrimestreId))?.id

		def grupoActividadAprendiz = null

		if (aprendizId) {
			def c = GrupoActividadAprendiz.createCriteria()
			grupoActividadAprendiz = c.get {
				grupo {
					eq('actividad.id', actividadId)
				}
				eq('aprendiz.id', aprendizId)
			}
		}
		return grupoActividadAprendiz
	}

	def pertenece(Long aprendizId, Integer numeroGrupo) {
		def aprendiz = Aprendiz.get(aprendizId)
		return (aprendiz.grupo.numero == numeroGrupo)
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
