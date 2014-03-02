package com.fiuba

import grails.transaction.Transactional

@Transactional
class MediadorService {

	def obtenerCandidatos() {
		def ArrayList<Usuario> usuarios = new ArrayList<Usuario>()
		def ArrayList<Miembro> miembros = Miembro.list()

		miembros.each {
			if (!Administrador.findByUsuario(it.usuario)) {
				usuarios.add(it.usuario)
			} 
		}
		return usuarios
	}
		
	def obtenerCursos(Usuario usuario) {

		def ArrayList<Curso> cursosMediador = new ArrayList<Curso>()
		def ArrayList<Mediador> mediadores = Mediador.findAllByUsuario(usuario)

		mediadores.each {
			cursosMediador.add(it.curso)
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

	def eliminar(Mediador mediador) {
		mediador.delete flush:true
	}

	def activarAprendiz(Long aprendizId) {
		def aprendiz = Aprendiz.get(aprendizId)
		aprendiz.participa = true
		aprendiz.cursando = true
		def mail = aprendiz.usuario.email
		def username = aprendiz.usuario.username
		
		if (aprendiz.save(flush: true)) {
			aprendiz.save();
			sendMail {
				to mail
				subject Utilidades.TITULO_RED
				body "Bienvenido aprendiz ${username} al curso ${aprendiz.cuatrimestre.curso} de la Red Social Educativa FIUBA 2014"
			}
			return true
		}
		return false
	}
}
