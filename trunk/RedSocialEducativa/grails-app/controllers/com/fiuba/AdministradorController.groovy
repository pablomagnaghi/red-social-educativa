package com.fiuba

import grails.plugin.mail.*

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class AdministradorController {

	// Administrador
	// 7. Registrar y eliminar materias y cursos de materias
	// 8. Administrar cartelera general (publicar, modificar y eliminar anuncios)
	// 9. Administrar parámetros de configuración de toda la red (duración del ciclo de conservación de
	// calificaciones)
	// 10. Crear y administrar (depurar y eliminar) foros generales
	// 11. Aceptar miembros
	// 12. Asignar y revocar roles de mediador en cursos
	// 13. Visualizar información y material de los cursos (foros, temas y material general)
	
	def general = {
		[usuarios: Usuario.findAllByEnabled(false)]
	}

	def activarUsuario() {
		def usuario = Usuario.get(params.id)
		println "${usuario}, ${usuario.id}, ${usuario.enabled}, ${usuario.fechaMemb}"
	
		usuario.enabled = true
		usuario.fechaMemb = new Date()
		def mail = usuario.email
		def username = usuario.username
		if (usuario.hasErrors()){
			println usuario.errors
			flash.message = "Problemas con el usuario"
			redirect(action: "general")
			return
		} else {
			usuario.save();
			sendMail {
				to mail
				subject "Red Social Educativa"
				body "Bienvenido ${username} a la Red Social Educativa FIUBA 2014"
			}
			flash.message = "Autorización enviada para el miembro con dni: ${usuario.username}"
		}
		
		def miembro = new Miembro(usuario: usuario, rol: Rol.findByAuthority("ROL_MIEMBRO"))
		if (!miembro.validate()){
			println miembro.errors
		} 
		miembro.save()
		redirect(action: "general")
	}
}
