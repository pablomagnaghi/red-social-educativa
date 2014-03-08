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

		if (!usuario.validate()) {
			flash.message = "Revise el formulario"
			render view: "solicitarMembresia", model: [usuarioInstance: usuario]
			return
		}
		if (params.password != params.passwordConfirmado) {
			flash.message = "El password confirmado es incorrecto"
			render view: "solicitarMembresia", model: [usuarioInstance: usuario]
			return
		}
		
		usuarioService.guardar(usuario)
		def email = usuario.email

		sendMail {
			to email
			subject Utilidades.TITULO_CONFIRMACION
			html g.render(template: "mailActivacion", model: [codigo: usuario.codigoConfirmacion, username: usuario.username])
		}	
		println "asfadfasdasdasda"
		def mensaje = "Revisa tu dirección de email para activarla"
		render view: "/login/auth", model: [mensajeCreacionCuenta: mensaje]
	}
	
	@Secured('permitAll')
	def revisarUsername() {

		Usuario usuario = Usuario.findByUsername(params.username)
		if(!usuario) {
			flash.message = "No existe usuario"
			redirect uri: "/", model: [mensaje: "Problema al activar la cuenta"]
			return
		}
		if (!usuario.enabled) {
			flash.message = "Todavia no esta en condiciones de cambiar su clave, primero debe activar su cuenta"
			redirect uri: "/", model: [mensaje: "Su cuenta ya ha sido activada"]
			return
		}
		
		usuario.codigoConfirmacion = UUID.randomUUID().toString()
		usuarioService.actualizar(usuario)
		def email = usuario.email

		sendMail {
			to email
			subject Utilidades.CLAVE_CONFIRMACION
			html g.render(template: "mailClave", model: [codigo: usuario.codigoConfirmacion, username: usuario.username])
		}
		flash.message = "Revisa tu dirección de email para cambiar tu contraseña"
		redirect uri: "/", model: [mensaje: "Revisa tu dirección de email para cambiar tu contraseña"]
	}
	
	@Secured('permitAll')
	def cambiarClave(String id) {

		println "ENTROOO"
		
		Usuario usuario = Usuario.findByCodigoConfirmacion(id)
		if(!usuario) {
			flash.message = "Problemas con la cuenta"
			redirect uri: "/", model: [mensaje: "Problema al activar la cuenta"]
			return
		}
		if (!usuario.enabled) {
			flash.message = "Todavia no esta en condiciones de cambiar su clave, primero debe activar su cuenta"
			redirect uri: "/", model: [mensaje: "Su cuenta ya ha sido activada"]
			return
		}
	}

	@Secured('permitAll')
	def revisarClave(String id) {

		Usuario usuario = Usuario.findByCodigoConfirmacion(id)
		
		if (params.password != params.passwordConfirmado) {
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
		redirect uri: "/", model: [mensaje: "Tu contraseña ha sido actualizada"]
	}

	@Secured('permitAll')
	def activarCuenta(String id) {

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
