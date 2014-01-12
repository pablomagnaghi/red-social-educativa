package com.fiuba

import grails.plugin.mail.*


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
	
	// metodos por defecto
	def index = {
		def membresia = false;
		[usuarios: Usuario.findAllByMembresia(membresia)]
	}

	def activarUsuario(final int id){
		def usuario = Usuario.get(id)
		usuario.membresia = true
		usuario.fechaMemb = new Date()
		def mail = usuario.email
		def dni = usuario.dni
		if (usuario.hasErrors()){
			println usuario.errors
			flash.message = "Problemas con la usuario"
			redirect(action: "index")
			return
		} else {
			usuario.save();
			sendMail {
				to mail
				subject "Red Social Educativa"
				body "Bienvenido ${dni} a la Red Social Educativa FIUBA 2014"
			}
			flash.message = "Autorización enviada para el miembro con dni: ${usuario.dni}"
		}
		
		def miembro = new Miembro(usuario: usuario, rol: Rol.findByNombre("Miembro"))
		if (!miembro.validate()){
			println miembro.errors
		} 
		miembro.save()
		redirect(action: "index")
	}
	
}
