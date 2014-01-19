package com.fiuba

import static org.springframework.http.HttpStatus.*

import java.util.regex.Matcher
import java.util.regex.Pattern

import javax.servlet.http.Cookie

import com.mensajeria.Mensaje;
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
	
	//Red.withCriteria(uniqueResult:true){ eq: 'titulo', 'Red Social Educativa del Departamento de Computacion de la Fiuba" }//
	
    def principal() { 
		
		params.max = 3
		
		def ArrayList<Curso> cursosMediador = new ArrayList<Curso>()
		def ArrayList<Mediador> mediadores = Mediador.findAllByUsuario(usuarioActual())
		
		if (mediadores){
			for(int i = 0; i<mediadores.size(); i++){
				//println "${mediadores.get(i).curso}, ${mediadores.get(i).usuario}, ${mediadores.get(i).jerarquia}"
				cursosMediador.add(mediadores.get(i).curso)
			}
		}
		//println "Cursos del mediador ${usuarioActual()}: ${cursosMediador}"
		
		def ArrayList<Curso> cursosAprendiz = new ArrayList<Curso>()
		def ArrayList<Aprendiz> aprendices = Aprendiz.findAllByUsuario(usuarioActual())
		def mensajes = Mensaje.findAllByReceptorAndLeido(usuarioActual(), Boolean.FALSE)
		if (aprendices){
			for(int i = 0; i<aprendices.size(); i++){
				//println "${aprendices.get(i).curso}, ${aprendices.get(i).usuario}, ${aprendices.get(i).participa}"
				if (aprendices.get(i).participa) {
					cursosAprendiz.add(aprendices.get(i).curso)
				}
			}
		}
		//println "Cursos del aprendiz participando ${usuarioActual()}: ${cursosAprendiz}"
		
		// poner un maximo y un orden a la cantidad de noticias a mostrar en la cartelera
		
		[cursos: Curso.list(params), noticiasRed: NoticiaRed.list(), cursoCant: Curso.count(), 
			administrador: Administrador.findByUsuario(usuarioActual()),
			cursosMediador: cursosMediador, cursosAprendiz: cursosAprendiz, cantMensajes: mensajes.size()]
	}
	
	// TODO analogia con los controladores scaffold respond new Class(params)
	def solicitarMembresia = {
		respond new Usuario(params)
	}
	
	def revisarDatosUsuario(Usuario usuario) {
		// hacer validaciones de algunas campos como dni
		if (params.password != params.passwordConfirmado) {
			flash.message = "El password confirmado es incorrecto"
			redirect(action: "solicitarMembresia")
			return
		} 
		/*def usuario = new Usuario(username: params.username, password: params.password, apellido: params.apellido,
			nombres: params.nombres, legajo: params.legajo, padron: params.padron, email: params.email,
			fechaSolicitud: new Date(), enabled: false)*/
		
		usuario.fechaSolicitud = new Date()
		usuario.enabled = false
		
		if(!usuario.validate()) {
			flash.message = "Revise sus parametros"
			respond usuario.errors, view:'solicitarMembresia'
			return
		} 
		
		usuario.save()
		flash.message = "Solicitud aceptada. A la brevedad se le enviara un mail de confirmacion"
		redirect(action:"principal")
		
	}
	
	def configuracion = {
		[redInstance: Red.first()]
	}
	
	def actualizarConfiguracion = {
		println "params: ${params}"
		
		def red = Red.first()
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
