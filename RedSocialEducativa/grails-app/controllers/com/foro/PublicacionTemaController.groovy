package com.foro

import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured('isFullyAuthenticated()')
class PublicacionTemaController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def usuarioService
	def publicacionTemaService

	def nueva() {
		respond new PublicacionTema(params), model: [usuario: usuarioService.usuarioActual(), 
			params:['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]]
	}

	def guardar(PublicacionTema publicacionTemaInstance) {
		if (publicacionTemaInstance == null) {
			notFound()
			return
		}
		if (params.pubInicialId) {
			publicacionTemaService.guardarRespuesta(publicacionTemaInstance, params.pubInicialId.toLong(), 
				usuarioService.usuarioActual(), params.cursoId.toLong()) 
			redirect controller: "foroTema", action: "publicaciones", params: ['id': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]
			return
		}
		if	(!publicacionTemaService.guardar(publicacionTemaInstance, usuarioService.usuarioActual(), params.cursoId.toLong())) {
			render view:'nueva', model: [publicacionTemaInstance: publicacionTemaInstance, usuario: usuarioService.usuarioActual()],
					params: ['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]
			return
		}
		flash.message = "Tema ${publicacionTemaInstance} creado"
		redirect controller: "foroTema", action: "general", params: ['cursoId': params.cursoId, 'temaId': params.temaId]
	}

	def eliminar(PublicacionTema publicacion) {
		if (publicacion == null) {
			notFound()
			return
		}
		def esTema = false
		if (!publicacion.publicacionInicial) {
			esTema = true
		}
		publicacionTemaService.eliminar(publicacion)
		if (esTema) {
			flash.message = "Tema ${publicacion} eliminado"
			redirect controller: "foroTema", action:"general", params:['cursoId': params.cursoId, 'temaId': params.temaId]
			return
		} 
		redirect controller: "foroTema", action:"publicaciones", method:"GET",
		params:['id': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]
	}

	def editar(PublicacionTema publicacionTemaInstance) {
		respond publicacionTemaInstance, model: [usuario: usuarioService.usuarioActual()],
			params: [ 'publicacionId': params.id, 'pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temad': params.temaId]
	}
	
	def actualizar(PublicacionTema publicacionTemaInstance) {
		if (publicacionTemaInstance == null) {
			notFound()
			return
		}
		if (!publicacionTemaService.guardar(publicacionTemaInstance, usuarioService.usuarioActual(), params.cursoId.toLong())) {
			render view:'editar', model: [publicacionTemaInstance: publicacionTemaInstance, usuario: usuarioService.usuarioActual()]
				params: ['publicacionId': params.id, 'pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]
			return
		}
		redirect controller: "foroTema", action: "publicaciones", params: ['id': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]
	}
	
	protected void notFound() {
		flash.message = "No se encuentra esa publicación"
		redirect controller: "foroTema", action: "general", params:['cursoId': params.cursoId, 'temaId': params.temaId], method: "GET"
	}
}
