package com.fiuba

import grails.transaction.Transactional

@Transactional
class PublicacionTemaService {

	private asignarResponsable(PublicacionTema publicacion, Usuario usuario, Long cursoId) {

		if (Mediador.findByUsuarioAndCurso(usuario, Curso.get(cursoId))) {
			publicacion.responsable = publicacion.responsable + " " + Utilidades.MEDIADOR
			return
		}

		publicacion.responsable = publicacion.responsable + " " + Utilidades.APRENDIZ
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
