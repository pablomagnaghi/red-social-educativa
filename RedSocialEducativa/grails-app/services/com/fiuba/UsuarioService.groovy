package com.fiuba

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
	
	def usuarioActual() {
		return Usuario.get(springSecurityService.principal.id)
	}

	def notificar(Usuario usuario) {
		def email = usuario.email
		if (!usuario.enabled) {
			def mensaje = "${usuario.nombres} ${usuario.apellido} ha dejado de ser miembro"
			enviarEmail(email, mensaje)
			return
		}
		def mensaje = "Usuario ${usuario.nombres} ${usuario.apellido} se ha converitdo en miembro"
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
	
	def actualizar(Usuario usuario) {
		if (usuario.save(flush:true)) {
			return usuario
		}
		return null
	}
	
	def deshabilitar(Usuario usuario) {
		usuario.enabled = false
		usuario.save flush:true
	}
	
	def eliminar(Usuario usuario) {
		usuario.delete flush: true
	}
}
