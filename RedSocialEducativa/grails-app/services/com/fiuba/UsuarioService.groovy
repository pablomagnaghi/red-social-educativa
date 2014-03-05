package com.fiuba

import com.mensajeria.Carpeta;

import org.springframework.security.access.annotation.Secured
import grails.transaction.Transactional

@Transactional
class UsuarioService {

	def springSecurityService
	
	def usuarioActual() {
		return Usuario.get(springSecurityService.principal.id)
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
	
	def eliminar(Usuario usuario) {
		
		def ArrayList<Administrador> administradores = Administrador.findAllByUsuario(usuario)
		administradores.each {
			println "se elimina el administrador ${it}"
			it.delete flush:true
		}
		
		def ArrayList<Mediador> mediadores = Mediador.findAllByUsuario(usuario)
		mediadores.each {
			println "se elimina el mediador ${it}"
			it.delete flush:true
		}
		
		def ArrayList<Aprendiz> aprendices = Aprendiz.findAllByUsuario(usuario)
		aprendices.each {
			println "se elimina el aprendiz ${it}"
			it.delete flush:true
		}
		
		def ArrayList<Miembro> miembros = Miembro.findAllByUsuario(usuario)
		miembros.each {
			println "se elimina el miembro ${it}"
			it.delete flush:true
		}
		
		// TODO esto es lo que va, cuando se haga la mensajeria
		//Administrador.removeAll(usuario)
		//Mediador.removeAll(usuario)
		//Aprendiz.removeAll(usuario)
		//Miembro.removeAll(usuario)
		// TODO ver la eliminacion de todas las carpetas del usuario
		//usuario.delete flush:true
	}
}
