package com.fiuba

import grails.transaction.Transactional

@Transactional
class AprendizService {

	private enviarEmail(String email, String msj) {
		sendMail {
			to email
			subject Utilidades.TITULO_RED
			body msj
		}
	}
	
	def obtenerCursos(Usuario usuario) {
		def cursosAprendiz = Aprendiz.createCriteria().list {
			eq('usuario.id', usuario.id)
			eq('participa', true)
			cuatrimestre {	
				order('anio', 'desc')
				order('numero', 'desc')
			}
		}
		return cursosAprendiz
	}

	def obtenerPorCurso(Long usuarioId, Long cursoId) {
		def aprendiz = Aprendiz.createCriteria().get {
			cuatrimestre {
				eq('curso.id', cursoId)
			}
			eq('usuario.id', usuarioId)
			eq('participa', true)
		}
		return aprendiz
	}
	
	// Para evaluacionAprendiz
	def obtenerTodosAprendicesDeCurso(Long cursoId) {
		def aprendices = Aprendiz.createCriteria().list {
			cuatrimestre {
				eq('curso.id', cursoId)
			}
			eq('participa', true)
		}
		return aprendices
	}

	// Para aprendiz
	def obtenerAprendices(Long cursoId) {
		def aprendices = Aprendiz.createCriteria().list {
			cuatrimestre {
				eq('curso.id', cursoId)
			}
		}
		return aprendices
	}

	// Obtener el grupo en el que participa el aprendiz en esa actividad
	def obtenerGrupoPorActividad(Usuario usuario, Long cuatrimestreId, Long actividadId) {
		Long aprendizId = Aprendiz.findByUsuarioAndCuatrimestre(usuario, Cuatrimestre.get(cuatrimestreId))?.id
		def grupoActividadAprendiz = null
		if (aprendizId) {
			grupoActividadAprendiz = GrupoActividadAprendiz.createCriteria().get {
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

	def notificar(Aprendiz aprendiz) {
		def email = aprendiz.usuario.email
		if (!aprendiz.participa) {
			def mensaje = "Aprendiz ${aprendiz.usuario.nombres} ${aprendiz.usuario.apellido} ha dejado de participar en el curso ${aprendiz.cuatrimestre.curso}"
			enviarEmail(email, mensaje)
			return
		}
		def mensaje = "Miembro ${aprendiz.usuario.nombres} ${aprendiz.usuario.apellido} se ha converitdo en aprendiz del curso ${aprendiz.cuatrimestre.curso} "
		mensaje += "en el cuatrimestre ${aprendiz.cuatrimestre}"
		enviarEmail(email, mensaje)
	}
}
