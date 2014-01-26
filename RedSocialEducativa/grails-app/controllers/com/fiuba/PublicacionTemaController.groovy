package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class PublicacionTemaController {

	def springSecurityService
	
	private usuarioActual() {
		if (springSecurityService.principal.enabled)
			return Usuario.get(springSecurityService.principal.id)
		else
			return null
	}

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def nueva() {
		println "publicacion controller nueva params: ${params}"
		println usuarioActual()
		def pubInicialId = params.pubInicialId
		//def temaId = params.temaId
		def temaId = params.temaId
		respond new PublicacionTema(params), model: [usuario: usuarioActual(), 
			pubInicialId: pubInicialId, temaId: temaId]
	}
	
	@Transactional
	def guardar(PublicacionTema publicacionTemaInstance) {
		
		println "publicacion controller guardar pubInicialId: ${params.pubInicialId}, temaId: ${params.temaId}"
		
		def pubInicialId = params.pubInicialId
		def temaId = params.temaId
		
		if (publicacionTemaInstance == null) {
			notFound()
			return
		}
		
		if (publicacionTemaInstance.hasErrors()) {
			respond publicacionTemaInstance.errors, view:'nueva',  model: [pubInicialId: pubInicialId, 
				usuario: usuarioActual(), temaId: temaId]
			return
		}
		
		if (Administrador.findByUsuario(usuarioActual())) {
			publicacionTemaInstance.responsable = publicacionTemaInstance.responsable + " (Administrador)"
		} else {
			publicacionTemaInstance.responsable = publicacionTemaInstance.responsable + " (Miembro)"
		}
			
		if (pubInicialId) {
			println "es una respuesta"
			def publicacion = PublicacionTema.get(pubInicialId)
			publicacionTemaInstance.titulo = "Respuesta a: " + publicacionTemaInstance.titulo
			publicacion.addToRespuestas(publicacionTemaInstance)
			publicacion.save flush:true
			flash.message = message(code: 'default.created.message', args: [message(code: 'publicacionTemaInstance.label', 
				default: 'PublicacionTema'), publicacionTemaInstance.id])
			redirect controller: "foroTema", action: "publicaciones", params:['id': pubInicialId, 'temaId': temaId]
			return
		} else {
			publicacionTemaInstance.save flush:true
		}
		
		request.withFormat {
			form {
				flash.message = message(code: 'default.created.message', args: [message(code: 'publicacionTemaInstance.label', 
					default: 'PublicacionTema'), publicacionTemaInstance.id])
				redirect controller: "foroTema", action: "general", params:['temaId': temaId]
			}
			'*' { respond publicacionTemaInstance, [status: CREATED] }
		}
	}
	
	@Transactional
	def eliminar() {
		
		println "publicacion controller eliminarPublicacion params: ${params}"
		
		def publicacionId = params.id
		def temaId = params.temaId
		
		println "publicaion id: ${publicacionId}"
		
		def publicacion = PublicacionTema.get(publicacionId)

		println "publicacion: ${publicacion}"
				
		if (publicacion == null) {
			flash.message = "No existe esa publicacion"
			redirect controller: "foroTema", action: "publicaciones", method: "GET", 
				params:['id': params.pubInicialId, 'temaId': temaId]
			return
		}
		
		def esTema = false
		
		if (!publicacion.publicacionInicial) {
			//println "es inicialllllllllll"
			esTema = true
		} else {
			//println "NO ES INICIAL"
			//println publicacion.publicacionInicial
		}
		
		publicacion.delete flush:true


		flash.message = message(code: 'default.deleted.message', args: [message(code: 'PublicacionTema.label',
			default: 'PublicacionTema'), publicacion.id])

		if (esTema) {
			redirect controller: "foroTema", action:"general", params:['temaId': temaId]
		} else {
			redirect controller: "foroTema", action:"publicaciones", method:"GET", 
				params:['id': params.pubInicialId, 'temaId':  temaId]
		}
	}
	
	def editar(PublicacionTema publicacionTemaInstance) {
		
		println "publicacion controller editar params: ${params}"
		println usuarioActual()
		def pubInicialId = params.pubInicialId
		def publicacionId = params.id
		def temaId = params.temaId
		
		respond publicacionTemaInstance, model: [usuario: usuarioActual(),
			pubInicialId: pubInicialId, publicacionId: publicacionId, temaId: temaId]
		
	}
	
	@Transactional
	def actualizar(PublicacionTema publicacionTemaInstance) {

		println "publicacion id: ${params.id}, ${publicacionTemaInstance.id}, temaId: ${params.temaId}"
		
		println "padreId: ${params.pubInicialId}"
		
		println publicacionTemaInstance.properties
		
		if (publicacionTemaInstance == null) {
			notFound()
			return
		}

		if (publicacionTemaInstance.hasErrors()) {
			respond publicacionTemaInstance.errors, view:'editar', model: [usuario: usuarioActual(),
				pubInicialId: params.pubInicialId, publicacionId: params.id, temaId: params.temaId]
			return
		}

		publicacionTemaInstance.save flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'PublicacionTema.label', 
					default: 'PublicacionTema'), publicacionTemaInstance.id])
				redirect controller: "foroTema", action: "publicaciones", params: ['id': params.pubInicialId, 'temaId': params.temaId],
				model: [usuario: usuarioActual(), pubInicialId: params.pubInicialId, temaId: params.temaId]
			}
			'*'{ respond publicacionTemaInstance, [status: OK] }
		}
	}

	protected void notFound() {
		request.withFormat {
			form {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'publicacionTemaInstance.label', 
					default: 'PublicacionTema'), params.id])
				redirect controller: "foroTema", action: "publicaciones", method: "GET",  params:['temaId': temaId]
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}