package com.fiuba

import com.fiuba.Usuario
import com.mensajeria.Carpeta;

import grails.transaction.Transactional

@Transactional
class UsuarioService {

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
}

