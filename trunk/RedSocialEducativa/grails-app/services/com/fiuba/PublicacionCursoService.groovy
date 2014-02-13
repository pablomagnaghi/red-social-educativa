package com.fiuba

import grails.transaction.Transactional

@Transactional
class PublicacionCursoService {

   private asignarResponsable(PublicacionCurso publicacion, Usuario usuario) {

		if (!usuario) {
			publicacion.responsable = publicacion.responsable + " " + Utilidades.VISITANTE
			return
		}

		if (Administrador.findByUsuario(usuario)) {
			publicacion.responsable = publicacion.responsable + " " + Utilidades.ADMINISTRADOR
			return
		}
		
		publicacion.responsable = publicacion.responsable + " " + Utilidades.MIEMBRO
	}

	def guardarRespuesta(PublicacionCurso publicacion, Long pubInicialId, Usuario usuario) {

		if (!publicacion.validate()) {
			return null
		}
					
		asignarResponsable(publicacion, usuario)
						
		def publicacionPadre = PublicacionCurso.get(pubInicialId)
		publicacion.titulo = "Respuesta a: " + publicacion.titulo
		publicacionPadre.addToRespuestas(publicacion)

		if (!publicacionPadre.save(flush:true)) {
			return null
		}

		return publicacionPadre
	}

	def guardar(PublicacionCurso publicacion, Usuario usuario) {

		if (publicacion.validate()) {
			asignarResponsable(publicacion, usuario)
			publicacion.save flush:true
			return publicacion
		}
		
		return null
	}

	def eliminar(PublicacionCurso publicacion) {
		publicacion.delete flush:true
	}
}
