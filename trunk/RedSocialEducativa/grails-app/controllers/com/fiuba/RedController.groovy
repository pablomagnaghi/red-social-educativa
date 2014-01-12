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
	
	def usuario = null

	def index = {
		
		// mostrar todas las materias
		// cartelera general
		// foros de curso
	
		/*def cookies = request.getCookies()
		def sessionCookie = null
		for (item in cookies) {
			if (item.getName() == "session_token" ){
				sessionCookie = item.getValue()
			}
		if (sessionCookie){
			Pattern p = Pattern.compile("^(\\d{8})(.*)")
			Matcher m = p.matcher(sessionCookie)
			def dni, pass
			if (m.find()){
				dni = m.group(1);
				pass = m.group(2);
				usuario = Usuario.findByDni(dni)
				println "usuario cookie: ${usuario} dni: ${dni} pass: ${pass}"
			}
		} else {
			println "no hay usuario cookie"
		}
		println "usuario index: ${usuario}"*/
		
		// Defino que tipo de usuario es
		// Administrador: redirigir a pagina especial
		// Mediador/aprendiz: mostrar la pagina comun, con los cursos en los que es mediador y los cursos en los que es aprendiz.

		
		// FALTA COMPROBAR SI EL MIEMBRO ESTA HABILITADO O ESPERANDO HABILITACION PARA LA RED
		// LO MISMO PARA EL APRENDIZ CON EL CURSO
		def ArrayList<Curso> cursosMediador = new ArrayList<Curso>()
		
		println "usuario: ${usuario}"
		
		def mediador = Mediador.findByUsuario(usuario)
			
		if (mediador) {
			
		//	barsOfAllFoos.collect{ if( it.contains( "baz" ) { it } ) }
			//barsOfAllFoos.findAll { it.contains 'baz' }
			
			cursosMediador = Curso.list().findAll {
				if (it.mediadores.contains(mediador)) {
					it
				}
			}
			
			println "${cursosMediador}"
			
			/*
			println "mediador a buscar: ${mediador}"
			for (c in Curso.list()) {
				if (c.mediadores.contains(mediador)) {
					println "curso agregado: ${c}"
					cursosMediador.add(c)
				}
			}*/
		}

		def ArrayList<Curso> cursosAprendiz = new ArrayList<Curso>()
		
		def aprendiz = Aprendiz.findByUsuario(usuario)
			
		if (aprendiz) {
			println "aprendiz a buscar: ${aprendiz}"
			println "aprendiz participa: ${aprendiz.participa}"
			for (c in Curso.list()) {
				if (c.aprendices.contains(aprendiz)) {
					// falta revisar si el aprendiz esta habilitado o esperando habilitacion
					println "curso agregado: ${c}"
					cursosAprendiz.add(c)
				}
			}
		}
		
		[materias: Materia.findAll(), usuario: usuario, administrador: Administrador.findByUsuario(usuario),
			cursosMediador: cursosMediador, cursosAprendiz: cursosAprendiz]
	}
	
	def solicitarMembresia = {
	}
	
	def autenticacion = {
		usuario = Usuario.findByDniAndPassword(params.dni, params.password.encodeAsMD5())
		
		println "params: ${params}"
		
		if (usuario) {
			if (usuario.membresia) {
				println "ingreso usuario"
				session.user = usuario
			/*if (params.remember_me == 'on'){
				Cookie cookie = new Cookie("session_token", usuario.dni + usuario.password);
				cookie.maxAge = 60000
				response.addCookie(cookie)
			}*/
				
			} else {
				usuario = null
				flash.message = "Todavia no esta habilitado para ingresar. Intente en unos minutos."
			}
		} else
			flash.message = "No existe el miembro. Intente nuevamente."
		redirect(action:"index")
	}
	
	def salir = {
		println "adios session: ${session.user}"
		flash.message = "Goodbye ${session.user.dni}"
		session.user = null
		usuario = null
		//Cookie cookie = new Cookie("session_token", "null");
		//cookie.maxAge = 60000
		//response.addCookie(cookie)
		redirect(action:"index")
	}
	
	def revisarDatosUsuario = {
		// hacer validaciones de algunas campos como dni
		if (params.password != params.passwordConfirmado) {
			flash.message = "El password confirmado es incorrecto"
			redirect(action: "solicitarMembresia")
			return
		}
		def usuarioNuevo = new Usuario(dni: params.dni, password: params.password, apellido: params.apellido,
			nombres: params.nombres, legajo: params.legajo, padron: params.padron, email: params.email, usuario: false,
			fechaSolicitud: new Date())
		
		if(!usuarioNuevo.validate()) {
			flash.message = "Revise sus parametros"
			respond usuarioNuevo.errors, view:'solicitarMembresia'
			return
		}
		
		usuarioNuevo.save()
		flash.message = "Solicitud aceptada. A la brevedad se le enviara un mail de confirmacion"
		redirect(action:"index")
		
	}
	
	def configuracion = {
		def red = Red.get(1)
		println "configuracion controller - red: ${red}, titulo: ${red.titulo}, ciclo: ${red.cicloConservacion}"
		[redInstance: Red.get(1)]
	}
	
	def actualizarConfiguracion = {
		println "params: ${params}"
		
		def red = Red.get(1)
		red.properties = params

		if(!red.validate()) {
			flash.message = "Revise sus parametros"
			respond red.errors, view:'configuracion'
			return
		}
		
		red.save()
		flash.message = "La actualizacion ha sido realizada"
		redirect(action:"configuracion")
	
	}
	
}