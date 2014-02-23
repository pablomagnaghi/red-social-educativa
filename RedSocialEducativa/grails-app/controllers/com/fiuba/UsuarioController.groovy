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
		respond Usuario.list(params), model:[usuarioInstanceCount: Usuario.count()]
	}

	@Secured("hasRole('ROL_ADMIN')")
	def create() {
		respond new Usuario(params)
	}

	@Secured("hasRole('ROL_ADMIN')")
	def save(Usuario usuarioInstance) {
		if (usuarioInstance == null) {
			notFound()
			return
		}

		if (!usuarioService.guardar(usuarioInstance)) {
			respond usuarioInstance, view:'create'
			return
		}

		if (!redService.activarUsuario(usuarioInstance)) {
			flash.message = "Problemas con la activacion del usuario"
			redirect controller: 'administrador', action: 'index'
			return
		}
		
		def administrador = new Administrador(usuario: usuarioInstance, rol: Rol.findByAuthority(com.fiuba.Utilidades.ROL_ADMIN))
		
		if (!administradorService.guardar(administrador)) {
			flash.message = "Problemas con la creacion del administrador"
			redirect controller: 'administrador', action: 'index'
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'administradorInstance.label', default: 'Administrador'), administrador.id])
		redirect controller: 'administrador', action: 'index'
	}
	
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
	
	@Secured("hasRole('ROL_ADMIN')")
	def delete(Usuario usuarioInstance) {

		if (usuarioInstance == null) {
			notFound()
			return
		}
		
		if (usuarioInstance == usuarioService.usuarioActual()) {
			usuarioService.eliminar(usuarioInstance)
			redirect controller: "red", action: "principal", method:"GET"
			return
		}
		
		usuarioService.eliminar(usuarioInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuarioInstance.id])
		redirect action:"index", method:"GET"
	}

	@Secured("hasRole('ROL_ADMIN')")
	def muestraMenuAdm(Usuario usuarioInstance) {
		respond usuarioInstance
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def muestraMenuMed(Usuario usuarioInstance) {
		respond usuarioInstance
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def muestraMenuAprendiz(Usuario usuarioInstance) {
		respond usuarioInstance
	}
	
	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioInstance.label', default: 'Usuario'), params.id])
		redirect action: "index", method: "GET"
	}
}
