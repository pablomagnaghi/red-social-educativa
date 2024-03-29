package com.foro

import com.fiuba.*
import grails.transaction.Transactional

@Transactional
class PublicacionGeneralService {

	private asignarResponsable(PublicacionGeneral publicacion, Usuario usuario) {

		if (Administrador.findByUsuario(usuario)) {
			publicacion.responsable = publicacion.responsable + " " + Utilidades.ADMINISTRADOR
			return
		} 
		
		publicacion.responsable = publicacion.responsable + " " + Utilidades.MIEMBRO
	}

	def guardarRespuesta(PublicacionGeneral publicacion, Long pubInicialId, Usuario usuario) {

		if (!publicacion.validate()) {
			return null
		}
		
		asignarResponsable(publicacion, usuario)
		
		def publicacionPadre = PublicacionGeneral.get(pubInicialId)
		publicacion.titulo = Utilidades.RESPUESTA + publicacion.titulo
		publicacionPadre.addToRespuestas(publicacion)

		if (!publicacionPadre.save(flush:true)) {
			return null
		}
		
		return publicacionPadre
	}

	def guardar(PublicacionGeneral publicacion, Usuario usuario) {

		if (publicacion.validate()) {
			asignarResponsable(publicacion, usuario)
			publicacion.save flush:true
			return publicacion
		}
		
		return null
	}

	def eliminar(PublicacionGeneral publicacion) {
		publicacion.delete flush:true
	}
}
