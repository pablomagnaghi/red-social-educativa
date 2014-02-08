package com.fiuba

import grails.transaction.Transactional

@Transactional
class AdministradorService {

    def activarUsuario(String usuarioId) {
		
		def usuario = Usuario.get(usuarioId)
		println "${usuario}, ${usuario.id}, ${usuario.enabled}, ${usuario.fechaMemb}"
	
		usuario.enabled = true
		usuario.fechaMemb = new Date()
		def mail = usuario.email
		def username = usuario.username
		if (usuario.hasErrors()){
			println usuario.errors
			return false
		} else {
			usuario.save();
			sendMail {
				to mail
				subject "Red Social Educativa"
				body "Bienvenido ${username} a la Red Social Educativa FIUBA 2014"
			}
			return true
		}

    }
	
	def crearMiembro(String usuarioId) {
		
		def usuario = Usuario.get(usuarioId)
		def miembro = new Miembro(usuario: usuario, rol: Rol.findByAuthority("ROL_MIEMBRO"))
		
		if (!miembro.validate()){
			println miembro.errors
		}
		
		miembro.save()
	}
}
