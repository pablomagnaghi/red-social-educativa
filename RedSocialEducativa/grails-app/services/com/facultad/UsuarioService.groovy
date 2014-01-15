package com.facultad

import com.fiuba.Usuario
import com.mensajeria.Carpeta;

import grails.transaction.Transactional

@Transactional
class UsuarioService {
	def guardar(Usuario user) {
		if (!user.validate()){
			println user.errors
		} else {
			user.save(failOnError: true)
			def escritorio = new Carpeta(nombre : "Escritorio", usuario: user)
			escritorio.save(failOnError: true)
			def enviados = new Carpeta(nombre : "Enviados", usuario: user)
			enviados.save(failOnError: true)
			def eliminados = new Carpeta(nombre : "Eliminados", usuario: user)
			eliminados.save(failOnError: true)
			def borradores = new Carpeta(nombre : "Borradores", usuario: user)
			borradores.save()
			println "Usuarios agregados a la bbdd:"
			println user.username
		}
	}
}
