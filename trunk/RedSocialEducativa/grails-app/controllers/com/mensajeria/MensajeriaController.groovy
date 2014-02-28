package com.mensajeria

import org.springframework.security.access.annotation.Secured


import com.fiuba.Curso;
//import com.fiuba.GrupoCurso;
import com.fiuba.Mediador
import com.fiuba.Usuario
import com.fiuba.Aprendiz
import com.mensajeria.Conversacion
import com.fiuba.RedController;
import com.fiuba.Utilidades;

import grails.converters.JSON

import java.util.regex.Matcher
import java.util.regex.Pattern

@Secured('isFullyAuthenticated()')
class MensajeriaController {

	def springSecurityService
	def mensajeService
	def cuatrimestreService
	def conversacionService

	def index() {
		params.max = Utilidades.MAX_PARAMS
		Integer offset = params.offset?.toInteger() ?: 0
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"principal")
		}
		def etiquetasCarpetas = getCarpetas(usuario)
		def conversacion = Conversacion.findAllByPadre(Carpeta.findByUsuarioAndNombre(usuario, "Escritorio"), [max: params.max, offset: offset])
		def conversacionCount = Conversacion.findAllByPadre(Carpeta.findByUsuarioAndNombre(usuario, "Escritorio")).size()
		
		[etiquetasCarpetas: etiquetasCarpetas, conversaciones : conversacion, 
		conversacionCount: conversacionCount, carpetaSeleccionada: "Escritorio", offset: offset]
	}

	def nuevaCarpeta() {
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"principal")
		}
		def nuevaCarpeta = new Carpeta(nombre : params.nombre, usuario: usuario)
		if(!nuevaCarpeta.save()){
			return
		}
		def etiquetasCarpetas = getCarpetas(usuario)
		redirect(action:"mostrarMensajes", params:[nombreCarpeta: params.nombre])
	}

	def mostrarMensajes(String nombreCarpeta){
		params.max = Utilidades.MAX_PARAMS
		Integer offset = params.offset?.toInteger() ?: 0
		if (!nombreCarpeta){
			nombreCarpeta = params.nombreCarpeta
		}
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"principal")
		}
		def conversacion = []
		def conversacionCount = 0
		if (nombreCarpeta.equals("Enviados")){
			def mensajes = Mensaje.findAllByEmisor(usuario, [max: params.max, offset: offset])
			render(view:"index",model:[mensajes: mensajes, mensajesCount : mensajes.size(), etiquetasCarpetas:  getCarpetas(usuario), carpetaSeleccionada : nombreCarpeta, offset: offset])
			return	
		} else {
			conversacion = Conversacion.findAllByPadre(Carpeta.findByUsuarioAndNombre(usuario, nombreCarpeta), [max: params.max, offset: offset])
			conversacionCount = Conversacion.findAllByPadre(Carpeta.findByUsuarioAndNombre(usuario, nombreCarpeta)).size()
		}
		render(view:"index",model:[conversaciones: conversacion, conversacionCount: conversacionCount, etiquetasCarpetas:  getCarpetas(usuario), carpetaSeleccionada : nombreCarpeta, nombreCarpeta : nombreCarpeta, offset: offset])
	}

	def cambiarConversacion(){
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"principal")
		}
		def idConversacion = params.conversacion
		def nombreFormateado = params.carpeta
		def carpeta = Carpeta.findByNombreAndUsuario(nombreFormateado, usuario)
		def conversacion = Conversacion.findById(idConversacion)
		conversacion.padre = carpeta;
		conversacion.save(flush: true)
		redirect(action:"mostrarMensajes", params:[nombreCarpeta: nombreFormateado])
	}
	
	def eliminarConversacion(){
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"principal")
		}
		def carpeta = Carpeta.findByNombreAndUsuario("Eliminados", usuario)
		def conversacion = Conversacion.findById(params.conversacion)
		conversacion.padre = carpeta;
		conversacion.save(flush: true)
		redirect(action:"mostrarMensajes", params:[nombreCarpeta: "Eliminados"])
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
	
	def redactarMensaje(){
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"principal")
		}
		def mediadores = Mediador.findAllByUsuario(usuario)
		def aprendices = Aprendiz.findAllByUsuario(usuario)
		def cursosAprendiz = []
		def datosCursosAprendiz = [:]
		def cursosMediador = []
		def datosCursosMediador = [:]
		def datosCursos = []
		def datosMediadores = [:]
		mediadores.each {
			def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(it.curso.id)
			def mediadoresCurso = it.curso.mediadores
			cursosMediador.add(it.curso)
			datosCursosMediador.put(it.curso.id + "-cuatrimestreM", cuatrimestre)
			datosCursosMediador.put(it.curso.id	 + "-mediadoresM", mediadoresCurso)
		}
		aprendices.each {
			if (it.participa){
				def cuatrimestre = it.cuatrimestre
				def mediadoresCurso = it.cuatrimestre.curso.mediadores
				cursosAprendiz.add(it.cuatrimestre.curso)
				datosCursosAprendiz.put(it.cuatrimestre.curso.id + "-cuatrimestreM", cuatrimestre)
				datosCursosAprendiz.put(it.cuatrimestre.curso.id + "-mediadoresA", mediadoresCurso)
			}
		}
		if (!cursosMediador.empty){
			datosCursos = Curso.findAll()
			datosCursos.each{
				datosMediadores.put(it.id + "-mediadoresC", it.mediadores)
			}
		}
		def usuarios = Usuario.findByEnabled(true)
		render(template:"redactar", model: [usuarios: usuarios, cursosAprendiz : cursosAprendiz, datosCursosAprendiz : datosCursosAprendiz, 
			cursosMediador : cursosMediador, datosCursosMediador : datosCursosMediador, datosMediadores : datosMediadores, cursosTotales: datosCursos])
	}

	def responder(){
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"principal")
		}
		def mediadores = Mediador.findAllByUsuario(usuario)
		def aprendices = Aprendiz.findAllByUsuario(usuario)
		def cursosAprendiz = []
		def datosCursosAprendiz = [:]
		def cursosMediador = []
		def datosCursosMediador = [:]
		def datosCursos = []
		def datosMediadores = [:]
		mediadores.each {
			def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(it.curso.id)
			def mediadoresCurso = it.curso.mediadores
			cursosMediador.add(it.curso)
			datosCursosMediador.put(it.curso.id + "-cuatrimestreM", cuatrimestre)
			datosCursosMediador.put(it.curso.id	 + "-mediadoresM", mediadoresCurso)
		}
		aprendices.each {
			if (it.participa){
				def cuatrimestre = it.cuatrimestre
				def mediadoresCurso = it.cuatrimestre.curso.mediadores
				cursosAprendiz.add(it.cuatrimestre.curso)
				datosCursosAprendiz.put(it.cuatrimestre.curso.id + "-cuatrimestreA", cuatrimestre)
				datosCursosAprendiz.put(it.cuatrimestre.curso.id + "-mediadoresA", mediadoresCurso)
			}
		}
		if (!cursosMediador.empty){
			datosCursos = Curso.findAll()
			datosCursos.each{
				datosMediadores.put(it.id + "-mediadoresC", it.mediadores)
			}
		}
		def usuarios = Usuario.findByEnabled(true)
		def mensaje = Mensaje.findById(params.id)
		def asunto = "FW: " + mensaje.asunto
		def para = ""
		if (params.tipoRespuesta == 'Responder'){
			asunto = "RE: " + mensaje.asunto
			para = generarDestinatariosRespuesta(mensaje)
		} else 	if (params.tipoRespuesta == 'ResponderTodos'){
			asunto = "RE: " + mensaje.asunto
			para = generarDestinatariosRespuestaATodos(mensaje)
		}
		println para
		render(template:"redactarRespuesta", model: [para : para, asunto : asunto, usuarios: usuarios, cursosAprendiz : cursosAprendiz, datosCursosAprendiz : datosCursosAprendiz,
			cursosMediador : cursosMediador, datosCursosMediador : datosCursosMediador, datosMediadores : datosMediadores, cursosTotales: datosCursos])
		
	}
	
	private def generarDestinatariosRespuesta(Mensaje m){
		return m.emisor.nombres + " " + m.emisor.apellido + " <"+m.emisor.email+">"
	}
	
	private def generarDestinatariosRespuestaATodos(Mensaje m){
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"principal")
		}
		def para = ""
		def receptores = m.para.split(",");
		receptores.each {
			if (!it.empty){
				if (!(it.contains(usuario.nombres) && it.contains(usuario.apellido) && it.contains(usuario.email))){
					para += it + ","
				}
			}
		}
		para += this.generarDestinatariosRespuesta(m)
		return para
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
			companyMap.put("value", it[1] + " " + it[2] + "&lt;" + it[3] + "&gt;")
			listaUsuarios.add(companyMap) // add to the arraylist
		}
		render (listaUsuarios as JSON)
	}
	
	def enviarMensajes(){
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"principal")
		}
		def asunto = params.asunto
		def texto = params.mensaje
		Pattern usuarioPattern = Pattern.compile("(\\d+)")
		Pattern mediadorPattern = Pattern.compile("Mediador-(\\d+)")
		Pattern cursoPattern = Pattern.compile("^Curso-(\\d+)")
		// TODO
		Pattern grupoPattern = Pattern.compile("Grupo-(\\d+)_Curso-(\\d+)")
		Hilo hilo = new Hilo()
		hilo.save(flush: true)
		def paraArray = params.para.split(",")
		def para = "" 
		def usuarios = []
		paraArray.each {
			Matcher m = usuarioPattern.matcher(it.toString());
			if (m.find()){
				def receptor = Usuario.findById(m.group(1))
				usuarios.add(receptor)
				para += (receptor.nombres + " " + receptor.apellido + "<"+receptor.email+">")
			} else {
				m = mediadorPattern.matcher(it.toString());
				if (m.find()){
					def receptor = Mediador.findById(m.group(1))
					usuarios.add(receptor)
					para += (receptor.nombres + " " + receptor.apellido + "<"+receptor.email+">")
				} else {
					m = cursoPattern.matcher(it.toString());
					if (m.find()){
						def curso = Curso.findById(m.group(1))
						def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(curso.id)
						cuatrimestre.aprendices.each{
							def receptor = it.usuario 
							usuarios.add(receptor)
						}
						para += "Curso " + curso.id + ", "
					} else {
						// TODO
						m = grupoPattern.matcher(it.toString());
						if (m.find()){
							def grupo = GrupoCurso.findById(m.group(1))
							def curso = Curso.findById(m.group(2))
							grupo.aprendices.each{
								def receptor = it.usuario
								usuarios.add(receptor)
							}
							para += "Grupo " + grupo.id+",Curso: " + curso.id +", "
						}
					}
				}
			}
		}
		usuarios.each {
			def receptor = it
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
	
	def agregarMensajeABorradores(){
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"principal")
		}
		def mensaje = new Mensaje(para: params.para, asunto: params.asunto, cuerpo: params.cuerpo, emisor: usuario)
		mensajeService.agregarMensajeABorradores(mensaje)
		render (action: 'index')
	}
	
	def traerDatosCurso(Integer idCurso){
		def mediadores = Mediador.findAllByCurso(Curso.findById(idCurso))
		render(template: "datosCurso", model: [mediadores : mediadores])
	}
	
	def mensaje(){
		def responder
		def mensaje = Mensaje.findById(params.id)
		if (params.responder == 'true'){
			responder = true
		} else {
			responder = false
		}
		render (template:"mensaje", model: [mensaje : mensaje, responder: responder])
	}
	
	def mensajeBorradores(){
		def mensaje = Mensaje.findById(params.id)
		render (template:"redactar", model: [para: mensaje.para, asunto: mensaje.asunto, cuerpo: mensaje.cuerpo])
	}

	
	def conversacion(){
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"principal")
		}
		def conversacion = Conversacion.findById(params.id)
		def carpeta = conversacion.padre
		def responder = true
		if (carpeta.nombre.equals("Eliminados")){
			responder = false
		}
		def mensajes = conversacion.mensajes
		def mediadores = Mediador.findAllByUsuario(usuario)
		def aprendices = Aprendiz.findAllByUsuario(usuario)
		def cursosAprendiz = []
		def datosCursosAprendiz = [:]
		def cursosMediador = []
		def datosCursosMediador = [:]
		def datosCursos = []
		def datosMediadores = [:]
		mediadores.each {
			def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(it.curso.id)
			def mediadoresCurso = it.curso.mediadores
			cursosMediador.add(it.curso)
			datosCursosMediador.put(it.curso.id + "-cuatrimestreM", cuatrimestre)
			datosCursosMediador.put(it.curso.id	 + "-mediadoresM", mediadoresCurso)
		}
		aprendices.each {
			if (it.participa){
				def cuatrimestre = it.cuatrimestre
				def mediadoresCurso = it.cuatrimestre.curso.mediadores
				cursosAprendiz.add(it.cuatrimestre.curso)
				datosCursosAprendiz.put(it.cuatrimestre.curso.id + "-cuatrimestreM", cuatrimestre)
				datosCursosAprendiz.put(it.cuatrimestre.curso.id + "-mediadoresA", mediadoresCurso)
			}
		}
		if (!cursosMediador.empty){
			datosCursos = Curso.findAll()
			datosCursos.each{
				datosMediadores.put(it.id + "-mediadoresC", it.mediadores)
			}
		}
		def usuarios = Usuario.findByEnabled(true)
		render (template:"conversacion", model: [mensajes : mensajes, 
			conversacionId : params.id, carpeta : conversacion.padre, 
			responder: responder,
			cursosAprendiz : cursosAprendiz, datosCursosAprendiz : datosCursosAprendiz,
			cursosMediador : cursosMediador, datosCursosMediador : datosCursosMediador, 
			datosMediadores : datosMediadores, cursosTotales: datosCursos])
	}

	def conversacionAPdf= {
		def conversacion = Conversacion.findById(params.pdfId)
		def mensajes = conversacion.mensajes
		render(template: "mensajesPdf", model: [mensajes: mensajes])
	}

	def buscar_mensajes(){
		
		params.max = Utilidades.MAX_PARAMS
		Integer offset = params.offset?.toInteger() ?: 0
		
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"principal")
		}
		def de = null	
		def para = null
		def regex = /\s*([^\s]*)\s*([^<]*)<([^>]*)>,?/
		def mensajes = []
		def deBusqueda = null
		def paraBusqueda = null
		if (!params.de.trim().empty){
			def matcher = (params.de =~ regex)
			if (matcher.matches()){
				def nombres = matcher[0][1]
				def apellido = matcher[0][2]
				def email = matcher[0][3]
				de = Usuario.findByNombresAndApellidoAndEmail(nombres, apellido, email)
				deBusqueda = params.de.trim()
			}
		} else 	if (!params.para.trim().empty){
			def matcher = (params.para =~ regex)
			if (matcher.matches()){
				def nombres = matcher[0][1]
				def apellido = matcher[0][2]
				def email = matcher[0][3]
				para = Usuario.findByNombresAndApellidoAndEmail(nombres, apellido, email)
				paraBusqueda = params.para.trim()
			}
		}
		def conversacionCount = 0
		if (de == null && para != null){
			mensajes = Mensaje.findAllByEmisorAndReceptor(usuario, para, [max: params.max, offset: offset])
			conversacionCount = Mensaje.findAllByEmisorAndReceptor(usuario, para).size()
		} else if (para == null && de != null){
			mensajes = Mensaje.findAllByEmisorAndReceptor(de, usuario, [max: params.max, offset: offset])
			conversacionCount = Mensaje.findAllByEmisorAndReceptor(de, usuario).size()
		} 
		def conversaciones = []
		mensajes.each {
			def conversacion = conversacionService.findConversacionByMessage(it, usuario)
			conversaciones.add(conversacion)
		}
		render(view: "index", model: [deBusqueda: deBusqueda, paraBusqueda: paraBusqueda,
									etiquetasCarpetas : getCarpetas(usuario), 
									carpetaSeleccionada: params.nombreCarpeta, 
									conversaciones: conversaciones, 
									conversacionCount: conversacionCount, offset : offset])
	}	
	
	private usuarioActual() {
		if (springSecurityService.principal.enabled)
			return Usuario.get(springSecurityService.principal.id)
		else
			return null
	}
}
