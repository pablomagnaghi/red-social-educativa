package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)


import org.springframework.security.access.annotation.Secured

@Secured('permitAll')

class ForoGeneralController {

	def springSecurityService
	
	private usuarioActual() {
		if (springSecurityService.principal.enabled)
			return Usuario.get(springSecurityService.principal.id)
		else
			return null
	}
	
	def general() {
		
		params.max = 10 //Math.min(max ?: 10, 100)

		println PublicacionGeneral.findAllByForoAndPublicacionPadre(ForoGeneral.first(), 
			null)
		
		[publicaciones: PublicacionGeneral.findAllByForoAndPublicacionPadre(ForoGeneral.first(), 
			null, [max: params.max, offset: params.offset]),
		publicacionesCant: PublicacionGeneral.findAllByForoAndPublicacionPadre(ForoGeneral.first(), null).size()]
	}
	
	def publicaciones() {
		println "foro, publicacion, params ${params}"
		
		params.max = 2
		
		def publicacionId = params.id
		def publicacion = PublicacionGeneral.get(publicacionId)
		
		def List<PublicacionGeneral> respuestas = new ArrayList<PublicacionGeneral>()

		println publicacion
		
		while (publicacion.respuesta) {
			println "entro"
			println publicacion.respuesta
			println "pub"
			publicacion = publicacion.respuesta
			respuestas.add(publicacion)
		}

		[publicacion: PublicacionGeneral.get(publicacionId), padreId: PublicacionGeneral.get(publicacionId).id,
			respuestas: respuestas,
			respuestasCant: respuestas.size(),
			usuario: Usuario.findByUsername(usuarioActual()?.username)]
		
	}
	
	// metodos por defecto
	
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ForoGeneral.list(params), model:[foroGeneralInstanceCount: ForoGeneral.count()]
    }

    def show(ForoGeneral foroGeneralInstance) {
        respond foroGeneralInstance
    }

    def create() {
        respond new ForoGeneral(params)
    }

    @Transactional
    def save(ForoGeneral foroGeneralInstance) {
        if (foroGeneralInstance == null) {
            notFound()
            return
        }

        if (foroGeneralInstance.hasErrors()) {
            respond foroGeneralInstance.errors, view:'create'
            return
        }

        foroGeneralInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'foroGeneralInstance.label', default: 'ForoGeneral'), foroGeneralInstance.id])
                redirect foroGeneralInstance
            }
            '*' { respond foroGeneralInstance, [status: CREATED] }
        }
    }

    def edit(ForoGeneral foroGeneralInstance) {
        respond foroGeneralInstance
    }

    @Transactional
    def update(ForoGeneral foroGeneralInstance) {
        if (foroGeneralInstance == null) {
            notFound()
            return
        }

        if (foroGeneralInstance.hasErrors()) {
            respond foroGeneralInstance.errors, view:'edit'
            return
        }

        foroGeneralInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ForoGeneral.label', default: 'ForoGeneral'), foroGeneralInstance.id])
                redirect foroGeneralInstance
            }
            '*'{ respond foroGeneralInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ForoGeneral foroGeneralInstance) {

        if (foroGeneralInstance == null) {
            notFound()
            return
        }

        foroGeneralInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ForoGeneral.label', default: 'ForoGeneral'), foroGeneralInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'foroGeneralInstance.label', default: 'ForoGeneral'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
