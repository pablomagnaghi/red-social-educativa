package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured("hasRole('ROL_ADMIN')")
class AdministradorController {

    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def menu() {
	}

    def index() {
        params.max = Utilidades.MAX_PARAMS
        respond Administrador.list(params), model:[administradorInstanceCount: Administrador.count()]
    }

    def show(Administrador administradorInstance) {
        respond administradorInstance
    }

    def delete(Administrador administradorInstance) {

        if (administradorInstance == null) {
            notFound()
            return
        }

        administradorInstance.delete flush:true

        flash.message = message(code: 'default.deleted.message', args: [message(code: 'Administrador.label', default: 'Administrador'), administradorInstance.id])
		redirect action:"index", method:"GET"
    }

    protected void notFound() {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'administradorInstance.label', default: 'Administrador'), params.id])
		redirect action: "index", method: "GET"
    }
}
