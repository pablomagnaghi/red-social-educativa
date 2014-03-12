package com.foro

import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured('isFullyAuthenticated()')
class PublicacionGeneralController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def usuarioService
	def publicacionGeneralService

	def nueva() {
		respond new PublicacionGeneral(params), model: [usuario: usuarioService.usuarioActual()], params: ['pubInicialId': params.pubInicialId]
	}

	def guardar(PublicacionGeneral publicacionGeneralInstance) {
		def pubInicialId = params.pubInicialId
		if (publicacionGeneralInstance == null) {
			notFound()
			return
		}
		if (params.pubInicialId) {
			publicacionGeneralService.guardarRespuesta(publicacionGeneralInstance, params.pubInicialId.toLong(), usuarioService.usuarioActual())	
			redirect controller: "foroGeneral", action: "publicaciones", params:['id': params.pubInicialId]
			return
		}
		if	(!publicacionGeneralService.guardar(publicacionGeneralInstance, usuarioService.usuarioActual())) {
			render view:'nueva', model: [publicacionGeneralInstance: publicacionGeneralInstance, usuario: usuarioService.usuarioActual()],
				params: ['pubInicialId': params.pubInicialId]
			return
		}
		flash.message = "Tema ${publicacionGeneralInstance} creado"
		redirect controller: "foroGeneral", action: "general"
	}

	def eliminar(PublicacionGeneral publicacion) {
		if (publicacion == null) {
			notFound()
			return
		}
		def esTema = false
		if (!publicacion.publicacionInicial) {
			esTema = true
		}
		publicacionGeneralService.eliminar(publicacion)
		if (esTema) {
			flash.message = "Tema ${publicacion} eliminado"
			redirect controller: "foroGeneral", action:"general"
			return
		}
		redirect controller: "foroGeneral", action:"publicaciones", method:"GET", params:['id': params.pubInicialId]
	}

	def editar(PublicacionGeneral publicacionGeneralInstance) {
		respond publicacionGeneralInstance, model: [usuario: usuarioService.usuarioActual()], 
			params: ['publicacionId': params.id, 'pubInicialId': params.pubInicialId]
	}

	def actualizar(PublicacionGeneral publicacionGeneralInstance) {
		if (publicacionGeneralInstance == null) {
			notFound()
			return
		}
		if (!publicacionGeneralService.guardar(publicacionGeneralInstance, usuarioService.usuarioActual())) {
			render view:'editar', model: [publicacionGeneralInstance: publicacionGeneralInstance, usuario: usuarioService.usuarioActual()]
				params: ['publicacionId': params.id, 'pubInicialId': params.pubInicialId]
			return
		}
		redirect controller: "foroGeneral", action: "publicaciones", params: ['id': params.pubInicialId, 'pubInicialId': params.pubInicialId]
	}

	protected void notFound() {
		flash.message = "No se encuentra esa publicación"
		redirect controller: "foroGeneral", action: "general", method: "GET"
	}
}
