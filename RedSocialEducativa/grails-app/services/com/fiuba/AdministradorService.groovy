package com.fiuba

import grails.plugin.mail.*
import grails.transaction.Transactional

@Transactional
class AdministradorService {

	def guardar(Administrador administrador) {
		if (administrador.save(flush: true)) {
			return administrador
		}
		return null
	}

	def eliminar(Administrador administrador) {
		println administrador.usuario
		def miembro = Miembro.findByUsuario(administrador.usuario)
		miembro.delete flush:true
		administrador.delete flush:true
		
		// TODO esto es lo que va, cuando se haga la mensajeria
		//Administrador.removeAll(usuario)
		//Miembro.removeAll(usuario)
	}	
}
