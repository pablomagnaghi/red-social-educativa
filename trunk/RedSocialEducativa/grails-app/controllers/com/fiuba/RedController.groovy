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
	def cursoService

	@Secured('permitAll')
	def revisarRol() {
		if (Administrador.findByUsuarioAndActivo(usuarioService.usuarioActual(), true)) {
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
		if (!usuario.validate()) {
			render view: "solicitarMembresia", model: [usuarioInstance: usuario, mensajeError: "Revise el formulario"]
			return
		}
		if (params.password != params.passwordConfirmado) {
			render view: "solicitarMembresia", model: [usuarioInstance: usuario, mensajeError: "El password confirmado es incorrecto"]
			return
		}
		usuarioService.guardar(usuario)
		def email = usuario.email
		sendMail {
			to email
			subject Utilidades.TITULO_CONFIRMACION
			html g.render(template: "mailActivacion", model: [codigo: usuario.codigoConfirmacion, username: usuario.username])
		}	
		flash.message = "Tu cuenta ha sido creada. Revisa tu dirección de email para activarla"
		redirect uri: "/"
	}
	
	@Secured('permitAll')
	def revisarUsername() {
		Usuario usuario = Usuario.findByUsername(params.username)
		if(!usuario) {
			flash.message = "No existe usuario"
			redirect uri: "/"
			return
		}
		if (!usuario.enabled) {
			flash.message = "Todavia no esta en condiciones de cambiar su clave, primero debe activar su cuenta"
			redirect uri: "/"
			return
		}
		usuario.codigoConfirmacion = UUID.randomUUID().toString()
		usuarioService.actualizar(usuario)
		def email = usuario.email
		sendMail {
			to email
			subject Utilidades.TITULO_CAMBIO_CLAVE 
			html g.render(template: "mailClave", model: [codigo: usuario.codigoConfirmacion, username: usuario.username])
		}
		flash.message = "Revisa tu dirección de email para cambiar tu contraseña"
		redirect uri: "/"
	}
	
	@Secured('permitAll')
	def cambiarClave(String id) {
		Usuario usuario = Usuario.findByCodigoConfirmacion(id)
		if(!usuario) {
			flash.message = "Problemas con la cuenta"
			redirect uri: "/"
			return
		}
		if (!usuario.enabled) {
			flash.message = "Todavia no esta en condiciones de cambiar su clave, primero debe activar su cuenta"
			redirect uri: "/"
			return
		}
	}

	@Secured('permitAll')
	def revisarClave(String id) {
		Usuario usuario = Usuario.findByCodigoConfirmacion(id)
		if ((!params.password) || (params.password != params.passwordConfirmado)) {
			flash.message = "El password confirmado es incorrecto"
			redirect action: "cambiarClave", params: params
			return
		}
		usuario.password = params.password
		if (!usuarioService.actualizar(usuario)) {
			flash.message = "Problemas al actualizar la contraseña"
			render action: "cambiarClave", params: params
			return
		}
		flash.message = "Tu contraseña ha sido actualizada"
		redirect uri: "/"
	}

	@Secured('permitAll')
	def activarCuenta(String id) {
		Usuario usuario = Usuario.findByCodigoConfirmacion(id)
		if(!usuario) {
			flash.message = "Problema al activar la cuenta"
			redirect uri: "/"
			return
		}		
		if (usuario.enabled) {
			flash.message = "Su cuenta ya ha sido activada anteriormente"
			redirect uri: "/"
			return
		}
		if (!redService.activarUsuario(usuario)) {
			flash.message = "Problema al activar la cuenta"
			redirect uri: "/"
			return
		}	
		flash.message = "Tu cuenta ha sido activada exitosamente"
		redirect uri: "/"
	}
	
	@Secured('isFullyAuthenticated()') 
	def cursos() {
		model: [cursos: cursoService.obtenerCursosOrdenados()]
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
		if (Mediador.findByUsuarioAndCursoAndActivo(usuario, curso, true)) {
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
