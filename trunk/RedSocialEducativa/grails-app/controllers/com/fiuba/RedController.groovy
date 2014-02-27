package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

import com.mensajeria.Mensaje;

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
			redirect uri: "/"
			return
		}

		if (!usuarioService.guardar(usuario)) {
			flash.message = "Revise el formulario"
			redirect uri: "/"
			return
		}
		
		if (!redService.activarUsuario(usuario)) {
			flash.message = "Problemas al activar crear la cuenta"
			redirect uri: "/"
			return
		}
		
		flash.message = "Solicitud aceptada. A la brevedad se le enviara un mail de confirmacion"
		redirect uri: "/"
	}

	@Secured('isFullyAuthenticated()') 
	def cursos() {
		model: [cursos: Curso.list(params), cursoCant: Curso.count()]
	}

	@Secured("hasRole('ROL_ADMIN')")
	def administrador() {
		def mensajes = Mensaje.findAllByReceptorAndLeido(usuarioService.usuarioActual(), Boolean.FALSE)	
		model: [noticiasRed: noticiaRedService.obtenerNoticiasOrdenadas()]
	}
	
	@Secured("hasAnyRole('ROL_MEDIADOR', 'ROL_APRENDIZ', 'ROL_MIEMBRO')")
	def miembro() {
		model: [noticiasRed: noticiaRedService.obtenerNoticiasOrdenadas()]
	}
	/*
	@Secured("hasAnyRole('ROL_MEDIADOR', 'ROL_APRENDIZ', 'ROL_MIEMBRO')")
	def miembro() {
		def mensajes = Mensaje.findAllByReceptorAndLeido(usuarioService.usuarioActual(), Boolean.FALSE)
		model: [noticiasRed: NoticiaRed.list(), cantMensajes: mensajes.size()]
	}*/

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
	
	@Secured("hasRole('ROL_ADMIN')")
	def configuracion() {
		[redInstance: Red.instance]
	}

	@Secured("hasRole('ROL_ADMIN')")
	def actualizarConfiguracion() {
		Red.instance.properties = params

		if (!redService.guardar(Red.instance)) {
			respond Red.instance, view:'configuracion'
			return
		}

		flash.message = "La actualizacion ha sido realizada"
		redirect(action:"configuracion")
	}
}
