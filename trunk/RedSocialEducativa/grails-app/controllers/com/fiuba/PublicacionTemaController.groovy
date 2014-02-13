package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured


@Secured("hasAnyRole('ROL_MEDIADOR', 'ROL_APRENDIZ')")
class PublicacionTemaController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def seguridadService
	def publicacionTemaService

	def nueva() {
		respond new PublicacionTema(params), model: [usuario: seguridadService.usuarioActual(), 
			params:['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]]
	}

	def guardar(PublicacionTema publicacionTemaInstance) {

		if (publicacionTemaInstance == null) {
			notFound()
			return
		}

		if (params.pubInicialId) {
			if (!publicacionTemaService.guardarRespuesta(publicacionTemaInstance, params.pubInicialId.toLong(), 
				seguridadService.usuarioActual(), params.cursoId.toLong())) {
					render view:'nueva', model: [publicacionTemaInstance: publicacionTemaInstance, usuario: seguridadService.usuarioActual()],
						params: ['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]
				return
			}
			flash.message = message(code: 'default.created.message', args: [message(code: 'publicacionTemaInstance.label', default: 'PublicacionTemao'), publicacionTemaInstance.id])
			redirect controller: "foroTema", action: "publicaciones", params: ['id': params.pubInicialId, 'cursoId': params.cursoId, 
				'temaId': params.temaId]
			return
		}
	
		if	(!publicacionTemaService.guardar(publicacionTemaInstance, seguridadService.usuarioActual(), params.cursoId.toLong())) {
			render view:'nueva', model: [publicacionTemaInstance: publicacionTemaInstance, usuario: seguridadService.usuarioActual()],
					params: ['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]
			return
		}
		
		flash.message = message(code: 'default.created.message', args: [message(code: 'publicacionTemaInstance.label', default: 'PublicacionTema'), publicacionTemaInstance.id])
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
			redirect controller: "foroTema", action:"general", params:['cursoId': params.cursoId, 'temaId': params.temaId]
			return
		} 
		
		redirect controller: "foroTema", action:"publicaciones", method:"GET",
		params:['id': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]
	}

	def editar(PublicacionTema publicacionTemaInstance) {
		respond publicacionTemaInstance, model: [usuario: seguridadService.usuarioActual()],
			params: [ 'publicacionId': params.id, 'pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temad': params.temaId]
	}
	
	def actualizar(PublicacionTema publicacionTemaInstance) {

		if (publicacionTemaInstance == null) {
			notFound()
			return
		}

		if (!publicacionTemaService.guardar(publicacionTemaInstance, seguridadService.usuarioActual(), params.cursoId.toLong())) {
			render view:'editar', model: [publicacionTemaInstance: publicacionTemaInstance, usuario: seguridadService.usuarioActual()]
				params: ['publicacionId': params.id, 'pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]
			return
		}
		
		flash.message = message(code: 'default.updated.message', args: [message(code: 'PublicacionTema.label', default: 'PublicacionTema'), publicacionTemaInstance.id])
		redirect controller: "foroTema", action: "publicaciones", params: ['id': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]
	}
	
	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'publicacionTemaInstance.label', default: 'PublicacionTema'), params.id])
		redirect controller: "foroTema", action: "general", params:['cursoId': params.cursoId, 'temaId': params.temaId], method: "GET"
	}
}
