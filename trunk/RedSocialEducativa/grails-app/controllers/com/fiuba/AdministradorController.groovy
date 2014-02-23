package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured("hasRole('ROL_ADMIN')")
class AdministradorController {

    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def administradorService
	def usuarioService
	
    def index() {
        params.max = Utilidades.MAX_PARAMS
        respond Administrador.list(params), model:[administradorInstanceCount: Administrador.count()]
    }

    def delete(Administrador administradorInstance) {

        if (administradorInstance == null) {
            notFound()
            return
        }
		
		if (administradorInstance.usuario == usuarioService.usuarioActual()) {
			administradorService.eliminar(administradorInstance)
			redirect controller: "red", action: "principal", method:"GET"
			return
		}

        administradorService.eliminar(administradorInstance)

        flash.message = message(code: 'default.deleted.message', args: [message(code: 'Administrador.label', default: 'Administrador'), administradorInstance.id])
		// TODO probar esto
		redirect action: "index", method:"GET"
    }

    protected void notFound() {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'administradorInstance.label', default: 'Administrador'), params.id])
		redirect action: "index", method: "GET"
    }
}
