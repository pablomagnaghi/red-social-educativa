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
import com.mensajeria.Mensaje;
import grails.converters.JSON

import java.util.regex.Matcher
import java.util.regex.Pattern

@Secured('isFullyAuthenticated()')
class MensajeriaController {

	def springSecurityService
	def mensajeService
	def cuatrimestreService
	def conversacionService
	def pdfRenderingService
	
	def index() {
		params.max = Utilidades.MAX_PAGE_MAIL
		Integer offset = params.offset?.toInteger() ?: 0
		def usuario = this.usuarioActual()
		if (!usuario){
			redirect (controller:"red", action:"revisarRol")
		}
		def etiquetasCarpetas = getCarpetas(usuario)
		def conversacion = null
		def conversacionCount = null
		def conversaciones = this.findConversaciones(usuario, 'Escritorio')
		if (conversaciones.size() > 0){
			def limiteSuperior = offset+(params.max-1)
			if(limiteSuperior > conversaciones.size()){
				limiteSuperior = conversaciones.size()-1
			}
			conversacion=  conversaciones[offset..limiteSuperior]
			conversacionCount = conversaciones.size()
		}
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
		params.max = Utilidades.MAX_PAGE_MAIL
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
			def conversaciones = this.findConversaciones(usuario, nombreCarpeta)
			if (conversaciones.size() > 0){
				def limiteSuperior = offset+(params.max-1)
				if(limiteSuperior > conversaciones.size()){
					limiteSuperior = conversaciones.size()-1
				}
				conversacion =  conversaciones[offset..limiteSuperior]
				conversacionCount = conversaciones.size()
			}
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
				mensajes =  it.mensajes.findAll{it.leido == false && it.emisor != usuario}
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
		paraArray = paraParams.split(",")
		def finalArray = []
		if (mapaOriginal != null){
			paraArray.each {
				String rec = mapaOriginal.get(it.toString().trim())
				if(rec != null){
					finalArray.add(rec)
				} else {
					finalArray.add(it.toString().trim())
				}
			}
		}
		finalArray.each {
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
		def asunto = params.asunto + mensajeOriginal.asunto
		def texto = params.mensaje
		HashMap<String, String> paraMap = new HashMap<String, String>()
		def usuarios = this.getDestinatariosMail(params.para, paraMap, mensajeOriginal.para)
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
			if (it.receptor == usuario){
				mensajeService.marcarMensajeLeido(it)
			}
		}
		
		def carpeta = conversacion.padre
		def responder = true
		if (carpeta.nombre.equals("Eliminados")){
			responder = false
		}
		ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>()
		mensajes = conversacion.mensajes
		Collections.sort(mensajes, new MensajeComparator())
		
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

	def renderPDF(){
		def conversacion = Conversacion.findById(params.id)
		def mensajes = conversacion.mensajes
		def args = [template: "renderPDF", model: [mensajes: mensajes]]
		pdfRenderingService.render(args+[controller:this],response)
	}

	def buscar_mensajes(){
		
		params.max = Utilidades.MAX_PAGE_MAIL
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
			def matcher = (params.de.trim() =~ regex)
			if (matcher.matches()){
				def nombres = matcher[0][1]
				def apellido = matcher[0][2]
				def email = matcher[0][3]
				de = Usuario.findByNombresAndApellidoAndEmail(nombres, apellido, email)
				deBusqueda = params.de.trim()
			}
		} else 	if (!params.para.trim().empty){
			def matcher = (params.para.trim() =~ regex)
			if (matcher.matches()){
				def nombres = matcher[0][1]
				def apellido = matcher[0][2]
				def email = matcher[0][3]
				para = Usuario.findByNombresAndApellidoAndEmail(nombres, apellido, email)
				paraBusqueda = params.para.trim()
			}
		}
		
		def conversacionCount = 0
		def conversaciones = []
		if (de == null && para != null){
			def resultadoBusqueda = this.findConversacionesConParametros(usuario, para, usuario)
			if (resultadoBusqueda.size() > 0){
				def limiteSuperior = offset+(params.max-1)
				if(limiteSuperior > resultadoBusqueda.size()){
					limiteSuperior = resultadoBusqueda.size()-1
				}
				
				conversaciones=  resultadoBusqueda[offset..limiteSuperior]
				conversacionCount = resultadoBusqueda.size()
			}
		} else if (para == null && de != null){
			def resultadoBusqueda = this.findConversacionesConParametros(de, usuario, usuario)
			if (resultadoBusqueda.size() > 0){
				def limiteSuperior = offset+(params.max-1)
				if(limiteSuperior > resultadoBusqueda.size()){
					limiteSuperior = resultadoBusqueda.size()-1
				}
				
				conversaciones =  resultadoBusqueda[offset..limiteSuperior]
				conversacionCount = resultadoBusqueda.size()
			}
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
	
	/**
	 * Buscar las conversaciones correspondientes al Usuario y al nombre de la carpeta
	 * @param usuario
	 * @param string
	 * @return
	 */
	private def findConversaciones(Usuario usuario, String nombreCarpeta){
		def mensajes = Mensaje.findAllByReceptor(usuario)
		ArrayList<Conversacion> conversaciones = new ArrayList<String>()
		mensajes.each {
			def conversacionesMensajes = it.conversaciones.findAll {
				it.padre.usuario == usuario && it.padre.nombre.equals(nombreCarpeta)
			}
			conversacionesMensajes.each {
				if (!conversaciones.contains(it)){
					conversaciones.add(it)
				}
			}
		}
		Collections.sort(conversaciones, new ConversacionesSort())
		return conversaciones
	}
	
	/**
	 * Buscar las conversaciones segun los criterios de busquedas
	 * @param usuario
	 * @param string
	 * @return
	 */
	private def findConversacionesConParametros(Usuario de, Usuario para, Usuario usuario){
		def mensajes = Mensaje.findAllByEmisorAndReceptor(de, para)
		def conversaciones = []
		mensajes.each {
			def conversacionesMensajes = it.conversaciones.findAll {
				it.padre.usuario == usuario
			}
			conversacionesMensajes.each {
				conversaciones.add(it)
			}
		}
		Collections.sort(conversaciones, new ConversacionesSort())
		return conversaciones
	}
	
	static class ConversacionesSort implements Comparator<Conversacion> {

		@Override
		public int compare(Conversacion o1, Conversacion o2) {
			return o2.lastMessageDate().compareTo(o1.lastMessageDate())
		}
	}
	
	static class MensajeComparator implements Comparator<Mensaje> {

		@Override
		public int compare(Mensaje o1, Mensaje o2) {
			return o1.fecha.compareTo(o2.fecha)
		}
	}
}
