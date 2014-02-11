package com.fiuba

import grails.transaction.Transactional

@Transactional
class PublicacionGeneralService {

	def asignarResponsable(PublicacionGeneral publicacion, Usuario usuario) {

		if (!usuario) {
			publicacion.responsable = publicacion.responsable + " " + Utilidades.VISITANTE
			return
		}

		if (Administrador.findByUsuario(usuario)) {
			publicacion.responsable = publicacion.responsable + " " + Utilidades.ADMINISTRADOR
		} else {
			publicacion.responsable = publicacion.responsable + " " + Utilidades.MIEMBRO
		}
	}


	def guardarRespuesta(PublicacionGeneral publicacion, Long pubInicialId) {

		def publicacionPadre = PublicacionGeneral.get(pubInicialId)
		publicacion.titulo = "Respuesta a: " + publicacion.titulo
		publicacionPadre.addToRespuestas(publicacion)

		if (publicacionPadre.save(flush:true)) {
			return publicacionPadre
		}

		return null
	}

	def guardar(PublicacionGeneral publicacion) {

		if (publicacion.save(flush:true)) {
			return publicacion
		}

		return null
	}

	def eliminar(PublicacionGeneral publicacion) {
		publicacion.delete flush:true
	}
}
