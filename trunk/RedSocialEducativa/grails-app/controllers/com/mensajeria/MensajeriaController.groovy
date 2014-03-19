package com.mensajeria

import org.springframework.security.access.annotation.Secured


import com.cursado.Curso;
import com.cursado.GrupoActividad;
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
			redirect (controller:"red", action:"revisarRol")
		}
		def etiquetasCarpetas = getCarpetas(usuario)
		def conversacion = Conversacion.findAllByPadre(Carpeta.findByUsuarioAndNombre(usuario, "Escritorio"), [max: params.max, offset: offset])
		conversacion.sort{it.lastMessageDate()}
		conversacion = conversacion.reverse(true)
		def conversacionCount = Conversacion.findAllByPadre(Carpeta.findByUsuarioAndNombre(usuario, "Escritorio")).size()
		
		[etiquetasCarpetas: etiquetasCarpetas, conversaciones : conversacion, 
		conversacionCount: conversacionCount, carpetaSeleccionada: "Escritorio", offset: offset]
	}
	
	/**
	 * Muestra el panel de carpetas
	 * @return
	 */
	def mostrarCarpetas(){
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"revisarRol")
		}
		def etiquetasCarpetas = getCarpetas(usuario)
		render(template: 'panelCarpetas', model: [etiquetasCarpetas: etiquetasCarpetas, carpetaSeleccionada: params.carpetaSeleccionada])
	}

	
	/**
	 * Muestra el div de nueva carpeta
	 * @return
	 */
	def nuevaCarpeta() {
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"revisarRol")
		}
		def nuevaCarpeta = new Carpeta(nombre : params.nombre, usuario: usuario)
		if(!nuevaCarpeta.save()){
			return
		}
		def etiquetasCarpetas = getCarpetas(usuario)
		redirect(action:"mostrarMensajes", params:[nombreCarpeta: params.nombre])
	}

	/**
	 * Cambiar de carpeta y mostrar los mensajes
	 * @param nombreCarpeta
	 * @return
	 */
	def mostrarMensajes(String nombreCarpeta){
		params.max = Utilidades.MAX_PARAMS
		Integer offset = params.offset?.toInteger() ?: 0
		if (!nombreCarpeta){
			nombreCarpeta = params.nombreCarpeta
		}
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"revisarRol")
		}
		def conversacion = []
		def conversacionCount = 0
		if (nombreCarpeta.equals("Enviados")){
			def mensajes = Mensaje.findAllByEmisor(usuario, [max: params.max, offset: offset])
			render(view:"index",model:[mensajes: mensajes, mensajesCount : mensajes.size(), etiquetasCarpetas:  getCarpetas(usuario), carpetaSeleccionada : nombreCarpeta, offset: offset])
			return	
		} else {
			conversacion = Conversacion.findAllByPadre(Carpeta.findByUsuarioAndNombre(usuario, nombreCarpeta), [max: params.max, offset: offset])
			conversacion.sort{it.lastMessageDate()}
			conversacion = conversacion.reverse(true)
			conversacionCount = Conversacion.findAllByPadre(Carpeta.findByUsuarioAndNombre(usuario, nombreCarpeta)).size()
		}
		render(view:"index",model:[conversaciones: conversacion, 
			conversacionCount: conversacionCount, 
			etiquetasCarpetas:  getCarpetas(usuario), 
			carpetaSeleccionada : nombreCarpeta, 
			nombreCarpeta : nombreCarpeta, offset: offset])
	}

	
	/**
	 * Poner conversacion en una nueva carpeta
	 * @return
	 */
	def cambiarConversacion(){
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"revisarRol")
		}
		def idConversacion = params.conversacion
		def nombreFormateado = params.carpeta
		def carpeta = Carpeta.findByNombreAndUsuario(nombreFormateado, usuario)
		def conversacion = Conversacion.findById(idConversacion)
		conversacion.padre = carpeta;
		conversacion.save(flush: true)
		redirect(action:"mostrarMensajes", params:[nombreCarpeta: nombreFormateado])
	}
	
	
	/**
	 * Enviar carpeta a Eliminados
	 * @return
	 */
	def eliminarConversacion(){
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"revisarRol")
		}
		def carpeta = Carpeta.findByNombreAndUsuario("Eliminados", usuario)
		def conversacion = Conversacion.findById(params.conversacion)
		conversacion.padre = carpeta;
		conversacion.save(flush: true)
		redirect(action:"mostrarMensajes", params:[nombreCarpeta: "Eliminados"])
	}

	
	/**
	 * Obtener carpetas para mostrar con sus nuevos mensajes
	 * @param usuario
	 * @return
	 */
	private getCarpetas(Usuario usuario){
		def carpetas = Carpeta.findAllByUsuario(usuario, [sort:"id", order:"asc"]);
		def etiquetasCarpetas = [:];
		carpetas.each {
			def conversaciones = Conversacion.findAllByPadre(it)
			def cant = 0
			conversaciones.each{
				def mensajes = []
				mensajes =  it.mensajes.findAll{it.leido == false}
				cant += mensajes.size()
			}
			etiquetasCarpetas.put(it.nombre, cant)
		}
		return etiquetasCarpetas
	}
	
	/**
	 * Cargar lo datos para completar el redactar (arbol)
	 * @param usuario
	 * @param cursosAprendiz
	 * @param datosCursosAprendiz
	 * @param cursosMediador
	 * @param datosCursosMediador
	 * @param datosCursos
	 * @param datosMediadores
	 * @param usuarios
	 * @return
	 */
	private cargarInputsRedactar(Usuario usuario, cursosAprendiz, datosCursosAprendiz, cursosMediador, datosCursosMediador, datosCursos, datosMediadores, usuarios){
		def mediadores = Mediador.findAllByUsuario(usuario)
		def aprendices = Aprendiz.findAllByUsuario(usuario)
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
		usuarios = Usuario.findByEnabled(true)
	}
	
	
	/**
	 * Enviar Mensaje
	 * @return
	 */
	def redactarMensaje(){
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"revisarRol")
		}
		
		def cursosAprendiz = []
		def datosCursosAprendiz = [:]
		def cursosMediador = []
		def datosCursosMediador = [:]
		def datosCursos = []
		def datosMediadores = [:]
		def usuarios = []
		this.cargarInputsRedactar(usuario, cursosAprendiz, datosCursosAprendiz, cursosMediador, datosCursosMediador, datosCursos, datosMediadores, usuarios)
		
		render(template:"redactar", model: [usuarios: usuarios, cursosAprendiz : cursosAprendiz, datosCursosAprendiz : datosCursosAprendiz, 
			cursosMediador : cursosMediador, datosCursosMediador : datosCursosMediador, datosMediadores : datosMediadores, cursosTotales: datosCursos])
	}

	/**
	 * Vista de respuesta de mensajes
	 * @return
	 */
	def responder(){
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"revisarRol")
		}
		def cursosAprendiz = []
		def datosCursosAprendiz = [:]
		def cursosMediador = []
		def datosCursosMediador = [:]
		def datosCursos = []
		def datosMediadores = [:]
		def usuarios = []
		this.cargarInputsRedactar(usuario, cursosAprendiz, datosCursosAprendiz, cursosMediador, datosCursosMediador, datosCursos, datosMediadores, usuarios)

				
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
		render(template:"redactarRespuesta", model: [para : para, asunto : asunto, usuarios: usuarios, cursosAprendiz : cursosAprendiz, datosCursosAprendiz : datosCursosAprendiz,
			cursosMediador : cursosMediador, datosCursosMediador : datosCursosMediador, datosMediadores : datosMediadores, cursosTotales: datosCursos])
	}
	
	private def generarDestinatariosRespuesta(Mensaje m){
		return m.emisor.nombres + " " + m.emisor.apellido + " <"+m.emisor.email+">"
	}
	
	private def generarDestinatariosRespuestaATodos(Mensaje m){
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"revisarRol")
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
	
	/**
	 * Obtener usuarios para los autocompletar
	 * @return
	 */
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
	
	/**
	 * Enviar mensaje luego del redactar
	 * @return
	 */
	def enviarMensajes(){
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"revisarRol")
		}
		def asunto = params.asunto
		def texto = params.mensaje
		HashMap<String, String> paraMap = new HashMap<String, String>()
		def usuarios = this.getDestinatariosMail(params.para, paraMap, null)
		usuarios.each {
			def receptor = it
			mensajeService.sendMessage(paraMap, asunto, texto, usuario, receptor)
		}
		redirect(action: 'index')
	}
	
	private def getDestinatariosMail(String paraParams, HashMap<String, String> paraMap, HashMap<String, String> mapaOriginal){
		Pattern usuarioPattern = Pattern.compile("^(\\d+)")
		Pattern mediadorPattern = Pattern.compile("Mediador-(\\d+)")
		Pattern cursoPattern = Pattern.compile("^Curso-(\\d+)")
		Pattern grupoPattern = Pattern.compile("Grupo-(\\d+)_Curso-(\\d+)")
		
		def usuarios = []
		def paraArray = []
		def nuevoArray = []
		paraArray = paraParams.split(",")
		if (mapaOriginal != null){
			paraArray.each {
				String rec = mapaOriginal.get(it.toString().trim())
				if(rec != null){
					nuevoArray.add(rec)
				}
			}
		}
		//TODO mergear los dos arreglos
		paraArray.each {
			Matcher m = usuarioPattern.matcher(it.toString());
			if (m.find()){
				def receptor = Usuario.findById(m.group(1))
				usuarios.add(receptor)
				paraMap.put(receptor.nombres + " " + receptor.apellido + "<"+receptor.email+">", it.toString())
			} else {
				m = mediadorPattern.matcher(it.toString());
				if (m.find()){
					def receptor = Mediador.findById(m.group(1)).usuario
					usuarios.add(receptor)
					paraMap.put(receptor.nombres + " " + receptor.apellido + "<"+receptor.email+">", it.toString())
				} else {
					m = cursoPattern.matcher(it.toString());
					if (m.find()){
						def curso = Curso.findById(m.group(1))
						def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(curso.id)
						cuatrimestre.aprendices.each{
							def receptor = it.usuario
							usuarios.add(receptor)
						}
						paraMap.put("Curso " + curso.id, it.toString())
					} else {
						// TODO
						m = grupoPattern.matcher(it.toString());
						if (m.find()){
							def grupo = GrupoActividad.findById(m.group(1))
							def curso = Curso.findById(m.group(2))
							grupo.aprendices.each{
								def receptor = it.aprendiz.usuario
								usuarios.add(receptor)
							}
							paraMap.put("Grupo " + grupo.id+",Curso: " + curso.id, it.toString())
						}
					}
				}
			}
		}
		return usuarios
	}
	
	
	/**
	 * Metodo que recibe la respuesta de los mensajes
	 * @return
	 */
	def responderMensaje(){
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"revisarRol")
		}
		def mensajeOriginal = Mensaje.findById(params.mensajeId)
		def asunto = "Re: " + mensajeOriginal.asunto
		def texto = params.mensaje
		HashMap<String, String> paraMap = new HashMap<String, String>()
		def usuarios = this.getDestinatariosMail(params.para, paraMap, mensajeOriginal.para)
		println usuarios
		usuarios.each {
			def receptor = it
			mensajeService.reply(mensajeOriginal, paraMap, asunto, texto, usuario, receptor)
		}
		flash.message = "Mensaje Enviado"
		redirect(action:'index')
	}
	
	def agregarMensajeABorradores(){
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"revisarRol")
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


	/**
	 * Muestra los mensajes de una conversacion	
	 * @return
	 */
	def conversacion(){
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"revisarRol")
		}
		def conversacion = Conversacion.findById(params.id)
		
		def mensajesConv = conversacion.mensajes.findAll{it.leido == false}
		mensajesConv.each{
			mensajeService.marcarMensajeLeido(it)
		}
		
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
		def usuarios = []
		this.cargarInputsRedactar(usuario, cursosAprendiz, datosCursosAprendiz, cursosMediador, datosCursosMediador, datosCursos, datosMediadores, usuarios)
		
		render (template:"conversacion", model: [mensajes : mensajes, 
			currentUser : usuario,
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
			redirect (controller:"red", action:"revisarRol")
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
