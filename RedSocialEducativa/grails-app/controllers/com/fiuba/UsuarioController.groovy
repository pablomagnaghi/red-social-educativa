package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class UsuarioController {
	
	def usuarioService
	def redService
	def administradorService
	
	@Secured("hasRole('ROL_ADMIN')")
	def index() {
		params.max = Utilidades.MAX_PARAMS
		respond Usuario.list(params)
	}
	
	// Solo para los que solicitaron membresía y después de una semana no activaron su cuenta vía email
	@Secured("hasRole('ROL_ADMIN')")
	def actualizarMembresias() {
		Usuario.list().each {
			if ((!it.fechaMembresia) && (it.fechaSolicitud > Utilidades.FECHA_PROXIMA_SEMANA)) {
				usuarioService.eliminar(it)
			}
		}
		flash.message = "Actualización exitosa"
		redirect action:"index"
	}
	/*
	@Secured("hasRole('ROL_MIEMBRO')")
	def salir(Usuario usuarioInstance) {
		if (usuarioInstance == null) {
			notFound()
			return
		}
		usuarioInstance.enabled = false 
		if (!usuarioService.guardar(usuarioInstance)) {
			flash.message = "Problemas al cambiar el estado"
			redirect controller:"red", action:"revisarRol",  method:"GET"
			return
		}
		usuarioService.notificar(usuarioInstance)
		redirect controller:"logout", method:"GET"
		return
	}
	*/
	@Secured("hasRole('ROL_ADMIN')")
	def show(Usuario usuarioInstance) {
		respond usuarioInstance, params: ['cursoId': params.cursoId]
	}
	
	@Secured('isFullyAuthenticated()')
	def cambiarEstado(Usuario usuarioInstance) {
		if (usuarioInstance == null) {
			notFound()
			return
		}
		usuarioInstance.enabled = usuarioInstance.enabled ? false : true
		if (!usuarioService.guardar(usuarioInstance)) {
			flash.message = "Problemas al cambiar el estado"
			if (usuarioInstance == usuarioService.usuarioActual()) {
				redirect controller:"red", action:"revisarRol",  method:"GET"
				return
			}
			redirect action:"index", method:"GET"
			return
		}
		usuarioService.notificar(usuarioInstance)
		if ((!usuarioInstance.enabled) && (usuarioInstance == usuarioService.usuarioActual())) {
			redirect controller:"logout", method:"GET"
			return
		}
		flash.message = "usuario ${usuarioInstance} actualizado"
		redirect action:"index", method:"GET"
		return
	}
	
	@Secured("hasRole('ROL_MIEMBRO')")
	def edit(Usuario usuarioInstance) {
		respond usuarioInstance
	}

	@Secured("hasRole('ROL_MIEMBRO')")
	def update(Usuario usuarioInstance) {
		if (usuarioInstance == null) {
			notFound()
			return
		}
		if (!usuarioService.actualizar(usuarioInstance)) {
			flash.message = "Revise el perfil"
			respond usuarioInstance, view:'edit'
			return
		}
		redirect controller:"red", action:"revisarRol"
	}
	
	protected void notFound() {
		flash.message = "No se encuentra ese usuario"
		redirect action: "index", method: "GET"
	}
}
