package com.cartelera

import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured("hasRole('ROL_ADMIN')")
class NoticiaRedController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def usuarioService
	def noticiaRedService

	def index() {
		params.max = 100//Utilidades.MAX_PARAMS
		[noticiasRed: noticiaRedService.obtenerNoticiasOrdenadas()]
	}

	def create() {
		[noticiaRedInstance: new NoticiaRed(params), usuario: usuarioService.usuarioActual()]
	}

	def save(NoticiaRed noticiaRedInstance) {
		if (noticiaRedInstance == null) {
			notFound()
			return
		}
		if (!noticiaRedService.guardar(noticiaRedInstance)) {
			respond noticiaRedInstance, view:'create'
			return
		}
		flash.message = "Noticia ${noticiaRedInstance.titulo} creada"
		redirect action: "index"
	}

	def edit(NoticiaRed noticiaRedInstance) {
		respond noticiaRedInstance, model: [usuario: usuarioService.usuarioActual()]
	}

	def update(NoticiaRed noticiaRedInstance) {

		if (noticiaRedInstance == null) {
			notFound()
			return
		}

		if (!noticiaRedService.guardar(noticiaRedInstance)) {
			respond noticiaRedInstance, view:'edit'
			return
		}

		flash.message = "Noticia ${noticiaRedInstance.titulo} actualizada"
		redirect action: "index"
	}

	def cambiarVisibilidad(NoticiaRed noticiaRedInstance) {
		if (noticiaRedInstance == null) {
			notFound()
			return
		}
		noticiaRedInstance.visibilidad = noticiaRedInstance.visibilidad ? false : true
		if (!noticiaRedService.guardar(noticiaRedInstance)) {
			flash.message = "Problemas al cambiar la visibilidad"
			redirect action: "index"
			return
		}
		flash.message = "Visibilidad actualizada"
		redirect action: "index"
	}
	
	def delete(NoticiaRed noticiaRedInstance) {
		if (noticiaRedInstance == null) {
			notFound()
			return
		}
		noticiaRedService.eliminar(noticiaRedInstance)
		flash.message = "Noticia ${noticiaRedInstance.titulo} eliminada"
		redirect action: "index", method:"GET"
	}

	protected void notFound() {
		flash.message = "No se encuentra esa noticia"
		redirect action: "index", method: "GET"
	}
}