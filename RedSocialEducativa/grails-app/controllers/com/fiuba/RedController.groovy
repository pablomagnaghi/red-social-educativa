package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class RedController {

	// static allowedMethods = [save: "POST", update(actualizar): "PUT", delete: "DELETE"]
	def redService
	def usuarioService
	def mediadorService
	def aprendizService
	def noticiaRedService

	@Secured('permitAll')
	def revisarRol() {
		if (Administrador.findByUsuario(usuarioService.usuarioActual())) {
			redirect action: "administrador"
			return
		}
		redirect action: "miembro"
	}
	
	@Secured('permitAll')
	def solicitarMembresia() {
		respond new Usuario(params)
	}

	@Secured('permitAll')
	def revisarDatosUsuario(Usuario usuario) {
		if (params.password != params.passwordConfirmado) {
			flash.message = "El password confirmado es incorrecto"
			redirect action: "solicitarMembresia"
			return
		}
		if (!usuarioService.guardar(usuario)) {
			flash.message = "Revise el formulario"
			render view: "solicitarMembresia", model: [usuarioInstance: usuario]
			return
		}
		sendMail {
			to params.email
			subject Utilidades.TITULO_CONFIRMACION
			html g.render(template: "mailTemplate", model: [codigo: usuario.codigoConfirmacion])
		}	
		flash.message = "Tu cuenta ha sido creada. Revisa tu dirección de email para activarla."
		redirect uri: "/", model: [mensaje: "Tu cuenta ha sido creada. Revisa tu dirección de email para activarla."]
	}
	
	@Secured('permitAll')
	def confirmacion (String id) {

		Usuario usuario = Usuario.findByCodigoConfirmacion(id)
		if(!usuario) {
			flash.message = "Problema al activar la cuenta"
			redirect uri: "/", model: [mensaje: "Problema al activar la cuenta"]
			return
		}		
		if (usuario.enabled) {
			flash.message = "Su cuenta ya ha sido activada anteriormente"
			redirect uri: "/", model: [mensaje: "Su cuenta ya ha sido activada"]
			return
		}
		if (!redService.activarUsuario(usuario)) {
			flash.message = "Problema al activar la cuenta"
			redirect uri: "/", model: [mensaje: "Problema al activar la cuenta"]
			return
		}	
		flash.message = "Tu cuenta ha sido activada exitosamente"
		redirect uri: "/", model: [mensaje: "Tu cuenta ha sido exitosamente activada"]
	}
	
	@Secured('isFullyAuthenticated()') 
	def cursos() {
		model: [cursos: Curso.list(params), cursoCant: Curso.count()]
	}

	@Secured("hasRole('ROL_ADMIN')")
	def administrador() {
		model: [noticiasRed: noticiaRedService.obtenerNoticiasOrdenadas()]
	}
	
	@Secured("hasAnyRole('ROL_MEDIADOR', 'ROL_APRENDIZ', 'ROL_MIEMBRO')")
	def miembro() {
		model: [noticiasRed: noticiaRedService.obtenerNoticiasOrdenadas()]
	}

	@Secured('isFullyAuthenticated()')
	def revisarRolEnCurso() {
		def usuario = usuarioService.usuarioActual()
		def curso = Curso.get(params.cursoId)
			
		if (Administrador.findByUsuario(usuario)) {
			redirect controller: "curso", action: "administrador", params: params
			return
		}	
		if (Mediador.findByUsuarioAndCurso(usuario, curso)) {
			redirect controller: "curso", action: "mediador", params: params
			return
		}
		if (aprendizService.obtenerPorCurso(usuario.id, params.cursoId.toLong())) {
			redirect controller: "curso", action: "aprendiz", params: params
			return
		}
		redirect controller: "curso", action: "miembro", params: params
	}
}
