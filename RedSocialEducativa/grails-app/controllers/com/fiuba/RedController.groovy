package com.fiuba

import com.mensajeria.Mensaje;

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class RedController {

	// static allowedMethods = [save: "POST", update(actualizar): "PUT", delete: "DELETE"]
	def redService
	def usuarioService
	def mediadorService
	def aprendizService

	@Secured('isFullyAuthenticated()')
	def principal() {

		params.max = Utilidades.MAX_PARAMS
		def cursosMediador = mediadorService.obtenerCursos(usuarioService.usuarioActual())
		def cursosAprendiz = aprendizService.obtenerCursos(usuarioService.usuarioActual())
		def administrador = Administrador.findByUsuario(usuarioService.usuarioActual())
		def mensajes = Mensaje.findAllByReceptorAndLeido(usuarioService.usuarioActual(), Boolean.FALSE)

		[cursos: Curso.list(params), cursoCant: Curso.count(), noticiasRed: NoticiaRed.list(), administrador: administrador,
			cursosMediador: cursosMediador, cursosAprendiz: cursosAprendiz, cantMensajes: mensajes.size()]
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
			respond usuario, view:'solicitarMembresia'
			return
		}
		
		if (!redService.activarUsuario(usuario)) {
			flash.message = "Problemas al activar crear la cuenta"
			redirect controller: "login", action: "auth"
			return
		}
		
		flash.message = "Solicitud aceptada. A la brevedad se le enviara un mail de confirmacion"
		redirect controller: "login", action: "auth"
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
