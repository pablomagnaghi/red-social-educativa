package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class PublicacionGeneralController {

	def springSecurityService
	
	private usuarioActual() {
		if (springSecurityService.principal.enabled)
			return Usuario.get(springSecurityService.principal.id)
		else
			return null
	}

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def nueva() {
		println "nueva params: ${params}"
		println usuarioActual()
		def pubInicialId = params.pubInicialId
		respond new PublicacionGeneral(params), model: [usuario: usuarioActual(), pubInicialId: pubInicialId]
	}
	
	@Transactional
	def guardar(PublicacionGeneral publicacionGeneralInstance) {
		
		println "guardar params: ${params.pubInicialId}"
		
		def pubInicialId = params.pubInicialId
		
		if (publicacionGeneralInstance == null) {
			notFound()
			return
		}
		
		if (publicacionGeneralInstance.hasErrors()) {
			respond publicacionGeneralInstance.errors, view:'nueva',  model: [pubInicialId: pubInicialId, usuario: usuarioActual()]
			return
		}
		
		if (!usuarioActual()) {
			publicacionGeneralInstance.responsable = publicacionGeneralInstance.responsable + " (Visitante)"
		} else {
			if (Administrador.findByUsuario(usuarioActual())) {
				publicacionGeneralInstance.responsable = publicacionGeneralInstance.responsable + " (Administrador)"
			} else { 
				publicacionGeneralInstance.responsable = publicacionGeneralInstance.responsable + " (Miembro)"
			}
		}
	
		if (pubInicialId) {
			println "es una respuesta"
			def publicacion = PublicacionGeneral.get(pubInicialId)
			publicacionGeneralInstance.titulo = "Respuesta a: " + publicacionGeneralInstance.titulo
			publicacion.addToRespuestas(publicacionGeneralInstance)
			publicacion.save flush:true
			flash.message = message(code: 'default.created.message', args: [message(code: 'publicacionGeneralInstance.label', default: 'PublicacionGeneral'), publicacionGeneralInstance.id])
			redirect controller: "foroGeneral", action: "publicaciones", params:['id': pubInicialId]
			return
		} else {
			publicacionGeneralInstance.save flush:true
		}
		
		request.withFormat {
			form {
				flash.message = message(code: 'default.created.message', args: [message(code: 'publicacionGeneralInstance.label', default: 'PublicacionGeneral'), publicacionGeneralInstance.id])
				redirect controller: "foroGeneral", action: "general"
			}
			'*' { respond publicacionGeneralInstance, [status: CREATED] }
		}
	}
	
	@Transactional
	def eliminar() {
		
		println "eliminarPublicacion params: ${params}"
		
		def publicacionId = params.id
		
		println "publicaion id: ${publicacionId}"
		
		def publicacion = PublicacionGeneral.get(publicacionId)

		println "publicacion: ${publicacion}"
				
		if (publicacion == null) {
			flash.message = "No existe esa publicacion"
			redirect controller: "foroGeneral", action: "publicaciones", method: "GET", params:['id': params.pubInicialId]
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


		flash.message = message(code: 'default.deleted.message', args: [message(code: 'PublicacionGeneral.label',
			default: 'PublicacionGeneral'), publicacion.id])

		if (esTema) {
			redirect controller: "foroGeneral", action:"general"
		} else {
			redirect controller: "foroGeneral", action:"publicaciones", method:"GET", params:['id': params.pubInicialId]
		}
	}
	
	def editar(PublicacionGeneral publicacionGeneralInstance) {
		
		println "editar params: ${params}"
		println usuarioActual()
		def pubInicialId = params.pubInicialId
		def publicacionId = params.id
		respond publicacionGeneralInstance, model: [usuario: usuarioActual(), 
			pubInicialId: pubInicialId, publicacionId: publicacionId]
		
	}
	
	@Transactional
	def actualizar(PublicacionGeneral publicacionGeneralInstance) {
		
		println "actualizar params: ${params}"
		
		println "publicacion id: ${params.id}, ${publicacionGeneralInstance.id}"
		
		println "padreId: ${params.pubInicialId}"
		
		println publicacionGeneralInstance.properties
		
		if (publicacionGeneralInstance == null) {
			notFound()
			return
		}

		if (publicacionGeneralInstance.hasErrors()) {
			respond publicacionGeneralInstance.errors, view:'editar', model: [usuario: usuarioActual(),
				pubInicialId: params.pubInicialId, publicacionId: params.id]
			return
		}

		publicacionGeneralInstance.save flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'PublicacionGeneral.label', default: 'PublicacionGeneral'), publicacionGeneralInstance.id])
				redirect controller: "foroGeneral", action: "publicaciones", params: ['id': params.pubInicialId], 
				model: [usuario: usuarioActual(), pubInicialId: params.pubInicialId]
			}
			'*'{ respond publicacionGeneralInstance, [status: OK] }
		}
	}

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'publicacionGeneralInstance.label', default: 'PublicacionGeneral'), params.id])
                redirect controller: "foroGeneral", action: "publicaciones", method: "GET"//,  params:['id': cursoId]
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
