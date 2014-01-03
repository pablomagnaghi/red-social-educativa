package com.fiuba

class RedController {

	// sacar cuando se active el combo de salir de sesion una vez iniciada
	def sesionIniciada = false
	
    def index = { 
		// mostrar todas las materias
		// cartelera general
		// foros de curso
		[materias: Materia.findAll()]
	}
	
	def solicitarMembresia = {
	}
	
	def autenticacion = {
		def membresia = Membresia.findByDni(params.dni)
		
		if (membresia) {
			if (params.password.encodeAsMD5()==membresia.password){
				session.user = membresia
				flash.message = "Hola ${membresia.dni}!"
			
				// Defino que tipo de usuario es
				// Administrador: redirigir a pagina especial
				// Mediador y/o aprendiz: mostrar la pagina comun, con los cursos
				// en los que es mediador y los cursos en los que es aprendiz.
				def administradores = Administrador.findAll();
				def esAdm = false
				
				administradores.each {
					if(it.membresia.dni == membresia.dni){
						redirect(controller:"Administrador", action:"index")
						println "Ingreso del administrador ${membresia.dni}"
						esAdm = true
					}
				} 
				if (!esAdm) {
					// aca poner la vista especial para mediador y aprendiz
					redirect(action: "index")
				}
			} else {
				flash.message = "El password es incorrecto ${params.dni}. Intente nuevamente."
				redirect(action:"index")
			}
		} else {
			flash.message = "No existe un miembro con dni: ${params.dni}. Intente nuevamente."
			redirect(action:"index")
		}
	}
	
	def salir = {
		if (sesionIniciada) {
			flash.message = "Goodbye ${session.user.dni}"
			session.user = null
		} else {
			flash.message = "No hay iniciado sesion todavia"
		}
		sesionIniciada = false
		redirect(action:"index")
	}
	
	def revisarDatosMembresia = {
		// hacer validaciones de algunas campos como dni
		
		def membresia = new Membresia(dni: params.dni, password: params.password, apellido: params.apellido,
			nombres: params.nombres, legajo: params.legajo, padron: params.padron, email: params.email, membresia: false,
			fechaSolicitud: new Date())
	
		if(!membresia.validate()) {
			println membresia.errors
			flash.message = "Revise sus parametros"
			redirect (action:"solicitarMembresia")
			
		} else {
			membresia.save()
			flash.message = "Solicitud aceptada. A la brevedad se le enviara un mail de confirmacion"
			redirect(action:"index")
		}
	}
}
