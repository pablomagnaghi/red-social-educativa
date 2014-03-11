package com.fiuba

import grails.transaction.Transactional

@Transactional
class MediadorService {
	
	private enviarEmail(String email, String msj) {
		sendMail {
			to email
			subject Utilidades.TITULO_RED
			body msj
		}
	}
	
	def obtenerMediadoresDeCursoOrdenados(Long cursoId) {
		def mediadores = Mediador.createCriteria().list {
			eq('curso.id', cursoId)
			and {
				order('curso', 'asc')
				order('jerarquia', 'asc')
			}
		}
	}
	
	def obtenerMediadoresOrdenados() {
		def mediadores = Mediador.createCriteria().list {
			and {
				order('curso', 'asc')
				order('jerarquia', 'asc')
			}
		}
	}
	
	def obtenerCursos(Usuario usuario) {
		def cursosMediador = Mediador.createCriteria().list {
			eq('usuario.id', usuario.id)
			eq('activo', true)
			curso {
				order('asignatura', 'asc')
				order('nroRelativo', 'asc')
			}
		}
		return cursosMediador
	}

	def existe(Mediador mediador) {
		def curso = Curso.get(mediador.curso.id)
		def usuario = Usuario.get(mediador.usuario.id)
		def mediadorExistente = Mediador.findByUsuarioAndCurso(usuario, curso)
		return mediadorExistente
	}

	def guardar(Mediador mediador) {
		if (mediador.save(flush: true)) {
			return mediador
		}
		return null
	}

	def notificar(Mediador mediador) {
		def email = mediador.usuario.email
		if (!mediador.activo) {
			def mensaje = "Mediador ${mediador.usuario.nombres} ${mediador.usuario.apellido} ha dejado de participar en el curso ${mediador.curso}"
			enviarEmail(email, mensaje)
			return
		}
		def mensaje = "Miembro ${mediador.usuario.nombres} ${mediador.usuario.apellido} se ha converitdo en mediador del curso ${mediador.curso}"
		enviarEmail(email, mensaje)
	}
	
	// TODO VER DESPUES
	def activarAprendiz(Long aprendizId) {
		def aprendiz = Aprendiz.get(aprendizId)
		aprendiz.participa = true
		aprendiz.cursando = true
		def email = aprendiz.usuario.email
		
		if (aprendiz.save(flush: true)) {
			aprendiz.save();
			sendMail {
				to email
				subject Utilidades.TITULO_RED
				body "Bienvenido aprendiz ${aprendiz.usuario.username} al curso ${aprendiz.cuatrimestre.curso} de la Red Social Educativa FIUBA 2014"
			}
			return true
		}
		return false
	}
}
