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
	def mensajeService
	def conversacionService

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
		//si el usuario es null
		def usuario = Usuario.get(springSecurityService.principal.id)
		def idConversacion = params.conversacion
		println idConversacion
		def nombreCarpeta = params.carpeta
		def matcher = /([^=]+)=/
		def nombreFormateado = ""
		nombreCarpeta.eachMatch(matcher) {
			nombreFormateado = it[1]
		}
		def carpeta = Carpeta.findByNombreAndUsuario(nombreFormateado, usuario)
		def conversacion = Conversacion.findById(idConversacion)
		/*println conversacion
		println carpeta*/
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
		def usuario = Usuario.get(springSecurityService.principal.id)
		def para = params.para
		def asunto = params.asunto
		def texto = params.mensaje
		def matcher = /\s*([^\s]*)\s*([^<]*)<([^>]*)>,/
		Hilo hilo = new Hilo()
		hilo.save(flush: true)
		para.eachMatch(matcher) {
			def nombres = it[1]
			def apellido = it[2]
			def email = it[3]
			def receptor = Usuario.findByNombresAndApellidoAndEmail(nombres, apellido, email)
			mensajeService.sendMessage(para, asunto, texto, usuario, receptor, hilo)
		}
		redirect(action: 'index')
	}
	
	def responderMensaje(){
		def mensajeOriginal = Mensaje.findById(params.respuestaId)
		def para = params.respuestaPara
		def asunto = params.respuestaAsunto
		def texto = params.respuestaCuerpo
		def matcher = /\s*([^\s]*)\s*([^<]*)<([^>]*)>,?/
		para.eachMatch(matcher) {
			def nombres = it[1]
			def apellido = it[2]
			def email = it[3]
			def receptor = Usuario.findByNombresAndApellidoAndEmail(nombres, apellido, email)
			mensajeService.reply(para, asunto, texto, mensajeOriginal.receptor, receptor, mensajeOriginal.hilo)
		}
		flash.message = "Mensaje Enviado"
		render(action:'index')
	}
	
	def traerDatosCurso(Integer idCurso){
		def mediadores = Mediador.findAllByCurso(Curso.findById(idCurso))
		render(template: "datosCurso", model: [mediadores : mediadores])
	}
	
	def conversacion(){
		def conversacion = Conversacion.findById(params.id)
		def mensajes = conversacion.mensajes
		[mensajes : mensajes, conversacionId : params.id]
	}

	def conversacionAPdf= {
		def conversacion = Conversacion.findById(params.pdfId)
		def mensajes = conversacion.mensajes
		render(template: "mensajesPdf", model: [mensajes: mensajes])
	}

	def buscar_mensajes(){
		def usuario = Usuario.get(springSecurityService.principal.id)
		def de = null	
		def para = null
		def regex = /\s*([^\s]*)\s*([^<]*)<([^>]*)>,?/
		def mensajes = []
		if (!params.de.trim().empty){
			def matcher = (params.de =~ regex)
			def nombres = matcher[0][1]
			def apellido = matcher[0][2]
			def email = matcher[0][3]
			de = Usuario.findByNombresAndApellidoAndEmail(nombres, apellido, email)
		} else 	if (!params.para.trim().empty){
			def matcher = (params.para =~ regex)
			def nombres = matcher[0][1]
			def apellido = matcher[0][2]
			def email = matcher[0][3]
			para = Usuario.findByNombresAndApellidoAndEmail(nombres, apellido, email)
		}
		if (de == null && para != null){
			mensajes = Mensaje.findAllByEmisorAndReceptor(usuario, para)
		} else if (para == null && de != null){
			mensajes = Mensaje.findAllByEmisorAndReceptor(de, usuario)
		}
		def conversaciones = []
		mensajes.each {
			def conversacion = conversacionService.findConversacionByMessage(it, usuario)
			conversaciones.add(conversacion)
		}
		render(template: "conversaciones", model: [conversaciones: conversaciones])
	}	
}
