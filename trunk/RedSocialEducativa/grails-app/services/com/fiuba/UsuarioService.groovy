package com.fiuba

import com.cursado.Curso
import com.mensajeria.Carpeta;

import org.springframework.security.access.annotation.Secured
import grails.transaction.Transactional

@Transactional
class UsuarioService {

	def springSecurityService
	
	private enviarEmail(String email, String msj) {
		sendMail {
			to email
			subject Utilidades.TITULO_RED
			body msj
		}
	}
	
	private inhabilitar(Usuario usuario) {
		def administradores = Administrador.findAllByUsuario(usuario)
		administradores.each {
			it.activo = false
			it.save flush: true
		}
		def mediadores = Mediador.findAllByUsuario(usuario)
		mediadores.each {
			it.activo = false
			it.save flush: true
		}
		def aprendices = Aprendiz.findAllByUsuario(usuario)
		aprendices.each {
			it.participa = false
			it.cursando = false
			it.save flush: true
		}
	}
	
	def usuarioActual() {
		return Usuario.get(springSecurityService.principal.id)
	}
	
	def notificar(Usuario usuario) {
		def email = usuario.email
		if (!usuario.enabled) {
			inhabilitar(usuario)
			def mensaje = "${usuario.nombres} ${usuario.apellido} ha dejado de ser miembro"
			enviarEmail(email, mensaje)
			return
		}
		def mensaje = "Usuario ${usuario.nombres} ${usuario.apellido} ha vuelta a ser miembro"
		enviarEmail(email, mensaje)
	}
	
	def obtenerCandidatos() {
		def ArrayList<Usuario> usuarios = new ArrayList<Usuario>()
		def ArrayList<Miembro> miembros = Miembro.list()
		miembros.each {
			if (!Administrador.findByUsuario(it.usuario)) {
				usuarios.add(it.usuario)
			}
		}
		return usuarios
	}
	
	def guardar(Usuario usuario) {
		if (usuario.save(flush: true)) {
			def escritorio = new Carpeta(nombre : "Escritorio", usuario: usuario)
			escritorio.save(flush: true)
			def enviados = new Carpeta(nombre : "Enviados", usuario: usuario)
			enviados.save(flush: true)
			def eliminados = new Carpeta(nombre : "Eliminados", usuario: usuario)
			eliminados.save(flush: true)
			def borradores = new Carpeta(nombre : "Borradores", usuario: usuario)
			borradores.save(flush: true)
			return usuario
		}
		return null
	}
	
	def crearCarpetaCurso(Aprendiz aprendiz, String cursoId){
		Curso curso = Curso.findById(cursoId)
		def nombreCurso = curso.nombre
		def codigoMateria = curso.asignatura.codigo
		def nombreCarpeta = nombreCurso + " - " + codigoMateria
		Usuario usuario = aprendiz.usuario
		def nuevaCarpeta = new Carpeta(nombre : nombreCarpeta, usuario: usuario)
		if(!nuevaCarpeta.save(flush: true)){
			nuevaCarpeta.save(flush: true).errors.each {
				println it
			}
		}
	}
	
	def actualizar(Usuario usuario) {
		if (usuario.save(flush:true)) {
			return usuario
		}
		return null
	}
	
	// Para el refresh de membresias 
	def eliminar(Usuario usuario) {
		usuario.delete flush: true
	}
}
