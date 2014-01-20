package com.mensajeria

import org.springframework.security.access.annotation.Secured

import com.fiuba.Curso;
import com.fiuba.Mediador
import com.fiuba.Usuario
import com.fiuba.Aprendiz
import com.mensajeria.Conversacion
import com.fiuba.RedController;

import grails.converters.JSON

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
	
	def redactar(){
		def usuario = Usuario.get(springSecurityService.principal.id)
		def mediadores = Mediador.findAllByUsuario(usuario)
		def aprendices = Aprendiz.findAllByUsuario(usuario)
		def cursosAprendiz = []
		def cursosMediador = []
		mediadores.each {
			cursosMediador.add(it.curso)
		}
		aprendices.each {
			if (it.participa){
				cursosAprendiz.add(it.curso)
			}
		}
		[cursosAprendiz : cursosAprendiz, cursosMediador : cursosMediador]
	}
	
	def traerUsuariosFormateados(){
		def query = {
			or {
				like("nombres", "${params.term}%") // term is the parameter send by jQuery autocomplete
				like("apellido", "${params.term}%")
				like("email", "${params.term}%")
			}
			projections { // good to select only the required columns.
				property("id")
				property("nombres")
				property("apellido")
				property("email")
			}
		}
		def ulist = Usuario.createCriteria().list(query)
		def listaUsuarios = []
		ulist.each {
			def companyMap = [:] // add to map. jQuery autocomplete expects the JSON object to be with id/label/value.
			companyMap.put("id", it[0])
			companyMap.put("label", it[1] + it[2])
			companyMap.put("value", it[1] + " " + it[2] + "<" + it[3] + ">")
			listaUsuarios.add(companyMap) // add to the arraylist
		}
		render (listaUsuarios as JSON)
	}
	
	def enviarMensajes(){
		println params.para
		println params.asunto
		println params.mensaje
		redirect(action: 'index')
	}
	
	def traerDatosCurso(Integer idCurso){
		def mediadores = Mediador.findAllByCurso(Curso.findById(idCurso))
		render(template: "datosCurso", model: [mediadores : mediadores])
	}
}
