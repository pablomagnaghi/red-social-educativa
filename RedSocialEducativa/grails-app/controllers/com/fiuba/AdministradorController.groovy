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

	def create() {
		respond new Administrador(params)
	}

	def save(Administrador administradorInstance) {
		if (administradorInstance == null) {
			notFound()
			return
		}
		if (Mediador.findAllByUsuario(administradorInstance.usuario) || Aprendiz.findAllByUsuario(administradorInstance.usuario)) {
			flash.message = "${administradorInstance.usuario} ya tiene rol aprendiz/mediador en la red, no puede ser administrador"
			redirect action: "create"
			return
		}
		if (!administradorService.guardar(administradorInstance)) {
			flash.message = "Problemas al crear administrador"
			respond administradorInstance, view:'create'
			return
		}
		administradorService.notificar(administradorInstance)
		flash.message = "Administrador ${administradorInstance.usuario} creado"
		redirect action:"index"
	}
	
	def cambiarEstado(Administrador administradorInstance) {
		if (administradorInstance == null) {
			notFound()
			return
		}
		administradorInstance.activo = administradorInstance.activo ? false : true
		if (!administradorService.guardar(administradorInstance)) {
			flash.message = "Problemas al cambiar el estado"
			redirect action:"index", method:"GET"
			return
		}
		administradorService.notificar(administradorInstance)
		if ((!administradorInstance.activo) && (administradorInstance.usuario == usuarioService.usuarioActual())) {
			redirect controller:"red", action:"revisarRol",  method:"GET"
			return
		}
		flash.message = "Administrador ${administradorInstance.usuario} actualizado"
		redirect action:"index", method:"GET"
		return
	}

    protected void notFound() {
        flash.message = "No se encuentra ese administrador"
		redirect action: "index", method: "GET"
    }
}
