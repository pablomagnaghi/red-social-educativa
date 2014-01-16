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
		[etiquetasCarpetas: etiquetasCarpetas, conversacionesEscr : conversacion, carpetaSeleccionada: "Escritorio"]
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
		def matcher = /([^=]+)=/
		def nombreFormateado = ""
		nombreCarpeta.eachMatch(matcher) {
			nombreFormateado = it[1]
		}
		def conversacion = []
		if (nombreFormateado.equals("Enviados")){
			def mensajes = Mensaje.findAllByEmisor(usuario)
			mensajes.each {
				def conv = new Conversacion()
				conv.addToMensajes(it)
				conversacion.add(conv)
			}
		} else {
			conversacion = Conversacion.findAllByPadre(Carpeta.findByUsuarioAndNombre(usuario, nombreFormateado))
		}
		render(template:"panelMensajeria",model:[conversaciones: conversacion, etiquetasCarpetas:  getCarpetas(usuario), carpetaSeleccionada : nombreFormateado])
	}

	def cambiarConversacion(){
		def usuario = Usuario.get(springSecurityService.principal.id)
		def idConversacion = params.conversacion
		def nombreCarpeta = params.carpeta
		def matcher = /([^=]+)=/
		def nombreFormateado = ""
		nombreCarpeta.eachMatch(matcher) {
			nombreFormateado = it[1]
		}
		def carpeta = Carpeta.findByNombreAndUsuario(nombreFormateado, usuario)
		def conversacion = Conversacion.findById(idConversacion)
		conversacion.padre = carpeta;
		conversacion.save(flush: true)
		def conversaciones = Conversacion.findAllByPadre(Carpeta.findByUsuarioAndNombre(usuario, nombreFormateado))
		render(template: "panelMensajeria", model: [conversaciones : conversaciones, etiquetasCarpetas:  getCarpetas(usuario), carpetaSeleccionada : nombreFormateado])
	}

	private getCarpetas(Usuario usuario){
		def carpetas = Carpeta.findAllByUsuario(usuario, [sort:"id", order:"asc"]);
		def etiquetasCarpetas = [:];
		carpetas.each {
			def cantMsg = Mensaje.getNewMessages(usuario, it.nombre	)
			etiquetasCarpetas.put(it.nombre, cantMsg)
		}
		return etiquetasCarpetas
	}
}
