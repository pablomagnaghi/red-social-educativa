package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class UsuarioController {
	
	def usuarioService
	def redService
	def administradorService
	
	@Secured("hasRole('ROL_ADMIN')")
	def index() {
		params.max = 100//Utilidades.MAX_PARAMS
		respond Usuario.list(params), model:[usuarioInstanceCount: Usuario.count()]
	}
	
	@Secured("hasRole('ROL_MIEMBRO')")
	def perfil(Usuario usuarioInstance) {
		respond usuarioInstance, params: ['cursoId': params.cursoId]
	}
	
	@Secured("hasRole('ROL_MIEMBRO')")
	def salir(Usuario usuarioInstance) {
		if (usuarioInstance == null) {
			notFound()
			return
		}
		usuarioInstance.enabled = usuarioInstance.enabled ? false : true
		if (!usuarioService.guardar(usuarioInstance)) {
			flash.message = "Problemas al cambiar el estado"
			redirect controller:"red", action:"revisarRol",  method:"GET"
			return
		}
		usuarioService.notificar(usuarioInstance)
		redirect controller:"logout", method:"GET"
		return
	}
	
	@Secured("hasRole('ROL_ADMIN')")
	def show(Usuario usuarioInstance) {
		respond usuarioInstance, params: ['cursoId': params.cursoId]
	}
	
	@Secured("hasAnyRole('ROL_ADMIN')")
	def cambiarEstado(Usuario usuarioInstance) {
		if (usuarioInstance == null) {
			notFound()
			return
		}
		usuarioInstance.enabled = usuarioInstance.enabled ? false : true
		if (!usuarioService.guardar(usuarioInstance)) {
			flash.message = "Problemas al cambiar el estado"
			redirect action:"index", params: ['cursoId': params.cursoId], method:"GET"
			return
		}
		usuarioService.notificar(usuarioInstance)
		if ((!usuarioInstance.enabled) && (usuarioInstance == usuarioService.usuarioActual())) {
			redirect controller:"red", action:"revisarRol",  method:"GET"
			return
		}
		flash.message = "usuario ${usuarioInstance} actualizado"
		redirect action:"index", params: ['cursoId': params.cursoId], method:"GET"
		return
	}
	
/*
	// TODO VER 
	@Secured("hasRole('ROL_ADMIN')")
	def edit(Usuario usuarioInstance) {
		respond usuarioInstance
	}

	// TODO VER
	@Secured("hasRole('ROL_ADMIN')")
	def update(Usuario usuarioInstance) {
		if (usuarioInstance == null) {
			notFound()
			return
		}

		if (!usuarioService.actualizar(usuarioInstance)) {
			respond usuarioInstance, view:'edit'
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'Usuario.label', default: 'Usuario'), usuarioInstance.id])
		redirect usuarioInstance
	}
	*/

	protected void notFound() {
		flash.message = "No se encuentra ese usuario"
		redirect action: "index", method: "GET"
	}
}
