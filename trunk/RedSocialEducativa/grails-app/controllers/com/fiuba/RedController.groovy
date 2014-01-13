package com.fiuba

import static org.springframework.http.HttpStatus.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.servlet.http.Cookie

import com.sun.corba.se.spi.orbutil.fsm.Action;

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class RedController {
	// El visitante puede: 
	// * 2 - Acceder a la cartelera general
	// * 4 - Dejar comentario o mensaje en un foro general de la red
	// * 5 - Solicitar membresia
	// * 6 - Conectarse
	
	def springSecurityService
	
	private usuarioActual() {
		if (springSecurityService.principal.enabled)
			return Usuario.get(springSecurityService.principal.id)
		else
			return null
	}
	
    def index = { 

		// FALTA COMPROBAR SI EL APRENDIZ ESTA HABILITADO CON EL CURSO 
		println "Usuario actual: ${usuarioActual()}"
		
		def ArrayList<Curso> cursosMediador = new ArrayList<Curso>()

		def mediador = Mediador.findByUsuario(usuarioActual())
			
		if (mediador) {
			cursosMediador = Curso.list().findAll {
				if (it.mediadores.contains(mediador)) {
					it
				}
			}
			println "${cursosMediador}"
		}

		def ArrayList<Curso> cursosAprendiz = new ArrayList<Curso>()
		
		def aprendiz = Aprendiz.findByUsuario(usuarioActual())
			
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
		
		[materias: Materia.findAll(), administrador: Administrador.findByUsuario(usuarioActual()),
			cursosMediador: cursosMediador, cursosAprendiz: cursosAprendiz]
	}
	
	def solicitarMembresia = {
	}
	
	def revisarDatosUsuario = {
		// hacer validaciones de algunas campos como dni
		if (params.password != params.passwordConfirmado) {
			flash.message = "El password confirmado es incorrecto"
			redirect(action: "solicitarMembresia")
			return
		} 
		def usuario = new Usuario(username: params.username, password: params.password, apellido: params.apellido,
			nombres: params.nombres, legajo: params.legajo, padron: params.padron, email: params.email,
			fechaSolicitud: new Date(), enabled: false)
		
		if(!usuario.validate()) {
			flash.message = "Revise sus parametros"
			respond usuario.errors, view:'solicitarMembresia'
			return
		} 
		
		usuario.save()
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
