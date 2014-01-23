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
	
	def padreId = null
	
	def nueva() {
		println "publicacion general nueva params"
		println "${params}"
		
		respond new PublicacionGeneral(params), model: [usuario: usuarioActual()]
	}
	
	def respuesta() {
		println "publicacion general responder params"
		println "${params}"
		
		padreId = params.padreId
		
		respond new PublicacionGeneral(params), model: [usuario: usuarioActual(), padreId: padreId]
	}
	
	@Transactional
	def guardar(PublicacionGeneral publicacionGeneralInstance) {
		
		println "publicacion general guardar params"
		println "${params}"
		
		if (publicacionGeneralInstance == null) {
			notFound()
			return
		}
		
		if (publicacionGeneralInstance.hasErrors()) {
			respond publicacionGeneralInstance.errors, view:'create'
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
	
		if (params.padreId) {
			println "es una respuesta"
			def publicacion = PublicacionGeneral.get(padreId)
			publicacion.respuesta = publicacionGeneralInstance
			publicacion.save flush:true
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
	
	
	
	
	// metodos por defecto 
	
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PublicacionGeneral.list(params), model:[publicacionGeneralInstanceCount: PublicacionGeneral.count()]
    }

    def show(PublicacionGeneral publicacionGeneralInstance) {
        respond publicacionGeneralInstance
    }

    def create() {
		println "publicacion general create params"
		println "${params}"
		
        respond new PublicacionGeneral(params)
    }

    @Transactional
    def save(PublicacionGeneral publicacionGeneralInstance) {
        if (publicacionGeneralInstance == null) {
            notFound()
            return
        }

        if (publicacionGeneralInstance.hasErrors()) {
            respond publicacionGeneralInstance.errors, view:'create'
            return
        }

        publicacionGeneralInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'publicacionGeneralInstance.label', default: 'PublicacionGeneral'), publicacionGeneralInstance.id])
                redirect publicacionGeneralInstance
            }
            '*' { respond publicacionGeneralInstance, [status: CREATED] }
        }
    }

    def edit(PublicacionGeneral publicacionGeneralInstance) {
        respond publicacionGeneralInstance
    }

    @Transactional
    def update(PublicacionGeneral publicacionGeneralInstance) {
        if (publicacionGeneralInstance == null) {
            notFound()
            return
        }

        if (publicacionGeneralInstance.hasErrors()) {
            respond publicacionGeneralInstance.errors, view:'edit'
            return
        }

        publicacionGeneralInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'PublicacionGeneral.label', default: 'PublicacionGeneral'), publicacionGeneralInstance.id])
                redirect publicacionGeneralInstance
            }
            '*'{ respond publicacionGeneralInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(PublicacionGeneral publicacionGeneralInstance) {

        if (publicacionGeneralInstance == null) {
            notFound()
            return
        }

        publicacionGeneralInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'PublicacionGeneral.label', default: 'PublicacionGeneral'), publicacionGeneralInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'publicacionGeneralInstance.label', default: 'PublicacionGeneral'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
