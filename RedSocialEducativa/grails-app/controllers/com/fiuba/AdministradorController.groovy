package com.fiuba

import grails.plugin.mail.*

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class AdministradorController {

	// Tareas:
	// * Registrar y eliminar materias y cursos de materias
	// * Administrar cartelera general (publicar, modificar y eliminar anuncios)
	// * Administrar parámetros de configuración de toda la red (duración del ciclo de conservación de calificaciones)
	// * Crear y administrar (depurar y eliminar) foros generales
	// * Aceptar miembros
	// * Asignar y revocar roles de mediador en cursos
	// * Visualizar información y material de los cursos (foros, temas y material general)
	
	def administradorService
	
	def general = {
	}

	def activarUsuario() {
		
		if (administradorService.activarUsuario(params.id)) {
			flash.message = "Autorización enviada"
			administradorService.crearMiembro(params.id)
			redirect(action: "index", controller: "usuario")
		} else {
			flash.message = "Problemas con el usuario"
			redirect(action: "general")
		}
	}
}
