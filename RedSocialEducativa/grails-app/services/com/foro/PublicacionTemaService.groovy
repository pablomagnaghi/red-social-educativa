package com.foro

import com.fiuba.*
import grails.transaction.Transactional

@Transactional
class PublicacionTemaService {

	def aprendizService
	
	private asignarResponsable(PublicacionTema publicacion, Usuario usuario, Long cursoId) {

		if (Administrador.findByUsuario(usuario)) {
			publicacion.responsable = publicacion.responsable + " " + Utilidades.ADMINISTRADOR
			return
		}

		if (Mediador.findByUsuarioAndCurso(usuario, Curso.get(cursoId))) {
			publicacion.responsable = publicacion.responsable + " " + Utilidades.MEDIADOR
			return
		}
		
		if (aprendizService.obtenerPorCurso(usuario, cursoId)) {
			publicacion.responsable = publicacion.responsable + " " + Utilidades.APRENDIZ
			return
		}

		publicacion.responsable = publicacion.responsable + " " + Utilidades.MIEMBRO
	}

	def guardarRespuesta(PublicacionTema publicacion, Long pubInicialId, Usuario usuario, Long cursoId) {

		if (!publicacion.validate()) {
			return null
		}
					
		asignarResponsable(publicacion, usuario, cursoId)
						
		def publicacionPadre = PublicacionTema.get(pubInicialId)
		publicacion.titulo = "Respuesta a: " + publicacion.titulo
		publicacionPadre.addToRespuestas(publicacion)

		if (!publicacionPadre.save(flush:true)) {
			return null
		}

		return publicacionPadre
	}

	def guardar(PublicacionTema publicacion, Usuario usuario, Long cursoId) {

		if (publicacion.validate()) {
			asignarResponsable(publicacion, usuario, cursoId)
			publicacion.save flush:true
			return publicacion
		}
		
		return null
	}

	def eliminar(PublicacionTema publicacion) {
		publicacion.delete flush:true
	}
}
