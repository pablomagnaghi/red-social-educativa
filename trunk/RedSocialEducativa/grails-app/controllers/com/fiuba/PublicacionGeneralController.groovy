package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class PublicacionGeneralController {

	def seguridadService
	def publicacionGeneralService
	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def nueva() {
		println "nueva params: ${params}"
		def pubInicialId = params.pubInicialId
		respond new PublicacionGeneral(params), model: [usuario: seguridadService.usuarioActual(), pubInicialId: pubInicialId]
	}

	def guardar(PublicacionGeneral publicacionGeneralInstance) {

		println "guardar params: ${params}"

		def pubInicialId = params.pubInicialId

		if (publicacionGeneralInstance == null) {
			notFound()
			return
		}

		if (publicacionGeneralInstance.hasErrors()) {
			respond publicacionGeneralInstance.errors, view:'nueva',  model: [pubInicialId: pubInicialId, usuario: seguridadService.usuarioActual()]
			return
		}

		publicacionGeneralService.asignarResponsable(publicacionGeneralInstance, seguridadService.usuarioActual())

		if (pubInicialId) {
			if (!publicacionGeneralService.guardarRespuesta(publicacionGeneralInstance, pubInicialId.toLong())) {
				respond publicacionGeneralInstance, view:'nueva',
				model: [pubInicialId: pubInicialId, usuario: seguridadService.usuarioActual()]
				return
			}
			flash.message = message(code: 'default.created.message', args: [message(code: 'publicacionGeneralInstance.label',
				default: 'PublicacionGeneral'), publicacionGeneralInstance.id])
			redirect controller: "foroGeneral", action: "publicaciones", params:['id': pubInicialId]
			return
		}

		if	(!publicacionGeneralService.guardar(publicacionGeneralInstance)) {
			respond publicacionGeneralInstance, view:'nueva',
			model: [pubInicialId: pubInicialId, usuario: seguridadService.usuarioActual()]
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'publicacionGeneralInstance.label', default: 'PublicacionGeneral'), publicacionGeneralInstance.id])
		redirect controller: "foroGeneral", action: "general"
	}

	def eliminar(PublicacionGeneral publicacion) {

		println "eliminarPublicacion params: ${params}"

		def publicacionId = params.id

		if (publicacion == null) {
			flash.message = "No existe esa publicacion"
			redirect controller: "foroGeneral", action: "publicaciones", method: "GET", params:['id': params.pubInicialId]
			return
		}

		def esTema = false
		if (!publicacion.publicacionInicial) {
			esTema = true
		}

		publicacionGeneralService.eliminar(publicacion)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'PublicacionGeneral.label',
			default: 'PublicacionGeneral'), publicacion.id])

		if (esTema) {
			redirect controller: "foroGeneral", action:"general"
			return
		}

		redirect controller: "foroGeneral", action:"publicaciones", method:"GET", params:['id': params.pubInicialId]
	}

	def editar(PublicacionGeneral publicacionGeneralInstance) {

		def pubInicialId = params.pubInicialId
		def publicacionId = params.id
		respond publicacionGeneralInstance, model: [usuario: seguridadService.usuarioActual(),
			pubInicialId: pubInicialId, publicacionId: publicacionId]
	}

	def actualizar(PublicacionGeneral publicacionGeneralInstance) {

		if (publicacionGeneralInstance == null) {
			notFound()
			return
		}

		if (!publicacionGeneralService.guardar(publicacionGeneralInstance)) {
			respond publicacionGeneralInstance, view:'editar', model: [usuario: usuarioActual(),
				pubInicialId: params.pubInicialId, publicacionId: params.id]
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'PublicacionGeneral.label', default: 'PublicacionGeneral'), publicacionGeneralInstance.id])
		redirect controller: "foroGeneral", action: "publicaciones", params: ['id': params.pubInicialId],
		model: [usuario: seguridadService.usuarioActual(), pubInicialId: params.pubInicialId]

	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'publicacionGeneralInstance.label', default: 'PublicacionGeneral'), params.id])
		redirect controller: "foroGeneral", action: "publicaciones", method: "GET"
	}
}
