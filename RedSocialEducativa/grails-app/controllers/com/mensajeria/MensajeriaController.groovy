package com.mensajeria

import java.util.regex.Matcher
import java.util.regex.Pattern

import org.springframework.security.access.annotation.Secured

import com.fiuba.Usuario
import com.mensajeria.Conversacion
import com.fiuba.RedController;

@Secured('permitAll')
class MensajeriaController {
	
	def springSecurityService
	
    def index() {
		def usuario = Usuario.get(springSecurityService.principal.id)
		def etiquetasCarpetas = getCarpetas(usuario)
		def conversacion = Conversacion.findAllByPadre(Carpeta.findByUsuarioAndNombre(usuario, "Escritorio"))
		[etiquetasCarpetas: etiquetasCarpetas, conversacionesEscr : conversacion]
	}

	def nuevaCarpeta() {
		def usuario = Usuario.get(springSecurityService.principal.id)
		def nuevaCarpeta = new Carpeta(nombre : params.nombre, usuario: usuario)
		nuevaCarpeta.save(failOnError: true)
		
		def etiquetasCarpetas = getCarpetas(usuario)
		render(template:"carpetas",model:[etiquetasCarpetas: etiquetasCarpetas])
	}
	
	def mostrarMensajes(String nombreCarpeta){
		def usuario = Usuario.get(springSecurityService.principal.id)
		def matcher = /([^(]+)(?:\([\d]+\))?$/
		def nombreFormateado = ""
		nombreCarpeta.eachMatch(matcher) {
			nombreFormateado = it[1]
		}
		def conversacion = []
		if (nombreFormateado.equals("Enviados")){
			def mensajes = Mensaje.findAllByEmisor(usuario)
			print "hola"
			mensajes.each {
				def conv = new Conversacion()
				conv.addToMensajes(it)
				conversacion.add(conv)
			}
		} else {
			conversacion = Conversacion.findAllByPadre(Carpeta.findByUsuarioAndNombre(usuario, nombreFormateado))
		}
		render(template:"conversaciones",model:[conversaciones: conversacion])
	}
	
	private getCarpetas(Usuario usuario){
		def carpetas = Carpeta.findAllByUsuario(usuario, [sort:"id", order:"asc"]);
		def etiquetasCarpetas = [];
		carpetas.each {
			def cantMsg = Mensaje.getNewMessages(usuario, it.nombre	)
			if (cantMsg > 0){
				etiquetasCarpetas.add(it.nombre + " (" + cantMsg + ")")
			} else {
				etiquetasCarpetas.add(it.nombre)
			}
		}
		return etiquetasCarpetas
	}
}
