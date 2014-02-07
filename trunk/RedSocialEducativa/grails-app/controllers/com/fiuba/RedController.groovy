package com.fiuba

import static org.springframework.http.HttpStatus.*

import com.mensajeria.Mensaje;

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class RedController {
	// El visitante puede: 
	// * 2 - Acceder a la cartelera general
	// * 4 - Dejar comentario o mensaje en un foro general de la red
	// * 5 - Solicitar membresia
	// * 6 - Conectarse
	
	def seguridadService
	
	//Red.withCriteria(uniqueResult:true){ eq: 'titulo', 'Red Social Educativa del Departamento de Computacion de la Fiuba" }//
	
    def principal() { 
		
		params.max = 3 //Math.min(max ?: 10, 100)
		
		def ArrayList<Curso> cursosMediador = new ArrayList<Curso>()
		def ArrayList<Mediador> mediadores = Mediador.findAllByUsuario(seguridadService.usuarioActual())

		mediadores.each {
			cursosMediador.add(it.curso)
		}
		
		def ArrayList<Curso> cursosAprendiz = new ArrayList<Curso>()
		def ArrayList<Aprendiz> aprendices = Aprendiz.findAllByUsuario(seguridadService.usuarioActual())

		aprendices.each {
			if (it.participa) {
				cursosAprendiz.add(it.cuatrimestre.curso)
			}
		}
		
		def mensajes = Mensaje.findAllByReceptorAndLeido(seguridadService.usuarioActual(), Boolean.FALSE)
		
		// poner un maximo y un orden a la cantidad de noticias a mostrar en la cartelera
		
		[cursos: Curso.list(params), noticiasRed: NoticiaRed.list(), cursoCant: Curso.count(), 
			administrador: Administrador.findByUsuario(seguridadService.usuarioActual()),
			cursosMediador: cursosMediador, cursosAprendiz: cursosAprendiz, 
			cantMensajes: mensajes.size()]
	}
	
	// TODO analogia con los controladores scaffold respond new Class(params)
	def solicitarMembresia() {
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
