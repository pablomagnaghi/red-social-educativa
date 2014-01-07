package com.fiuba

import static org.springframework.http.HttpStatus.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.servlet.http.Cookie

class RedController {
	// El visitante puede: 
	// * 2 - Acceder a la cartelera general
	// * 4 - Dejar comentario o mensaje en un foro general de la red
	// * 5 - Solicitar membresia
	// * 6 - Conectarse
	
	
	// sacar cuando se active el combo de salir de sesion una vez iniciada
	def sesionIniciada = false
	
	def membresia
	
    def index = { 
		// mostrar todas las materias
		// cartelera general
		// foros de curso
		
		def cookies = request.getCookies()
		def sessionCookie = null
		for (item in cookies) { 
			if (item.getName() == "session_token" ){
				sessionCookie = item.getValue()
			}
		}
		if (sessionCookie){
			Pattern p = Pattern.compile("^(\\d{8})(.*)")
			Matcher m = p.matcher(sessionCookie)
			def dni, pass
			if (m.find()){
				dni = m.group(1);
				pass = m.group(2);
			}
			def membresia = Membresia.findByDni(dni)
			println membresia
		} else {
			println "no hay usuario"
		}
		[materias: Materia.findAll(), membresia: membresia]
	}
	
	def solicitarMembresia = {
	}
	
	def autenticacion = {
		membresia = Membresia.findByDni(params.dni)
		
		if (membresia) {
			if (params.password.encodeAsMD5()==membresia.password){
				session.user = membresia
				if (params.remember_me == 'on'){
					Cookie cookie = new Cookie("session_token", membresia.dni + membresia.password);
					cookie.maxAge = 60000
					response.addCookie(cookie)
				}				
			
				// Defino que tipo de usuario es
				// Administrador: redirigir a pagina especial
				// Mediador y/o aprendiz: mostrar la pagina comun, con los cursos
				// en los que es mediador y los cursos en los que es aprendiz.
				def administradores = Administrador.findAll();
				def esAdm = false
				
				administradores.each {
					if(it.membresia.dni == membresia.dni){
						redirect(controller:"administrador", action:"index")
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
			Cookie cookie = new Cookie("session_token", "null");
			cookie.maxAge = 60000
			response.addCookie(cookie)
		} else {
			flash.message = "No hay iniciado sesion todavia"
		}
		sesionIniciada = false
		membresia = null
		redirect(action:"index")
	}
	
	def revisarDatosMembresia = {
		// hacer validaciones de algunas campos como dni
		
		def membresia = new Membresia(dni: params.dni, password: params.password, apellido: params.apellido,
			nombres: params.nombres, legajo: params.legajo, padron: params.padron, email: params.email, membresia: false,
			fechaSolicitud: new Date())
	
		if(!membresia.validate()) {
			flash.message = "Revise sus parametros"
			respond membresia.errors, view:'solicitarMembresia'
			return
		} else {
			membresia.save()
			flash.message = "Solicitud aceptada. A la brevedad se le enviara un mail de confirmacion"
			redirect(action:"index")
		}
	}
}
