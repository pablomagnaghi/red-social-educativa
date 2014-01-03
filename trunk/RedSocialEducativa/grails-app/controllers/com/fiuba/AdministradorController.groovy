package com.fiuba

import grails.plugin.mail.*

class AdministradorController {

	def index = {
		def membresia = false;
		[membresias: Membresia.findAllByMembresia(membresia)]
	}

	def activarMiembro(final int id){
		def membresia = Membresia.findById(id)
		membresia.membresia = true
		membresia.fechaMemb = new Date()
		def mail = membresia.email
		def dni = membresia.dni
		if (membresia.hasErrors()){
			println membresia.errors
			flash.message = "Problemas con la membresia"
			redirect(action: "index")
			return
		} else {
			membresia.save();
			sendMail {
				to mail
				subject "Red Social Educativa"
				body "Bienvenido ${dni} a la Red Social Educativa FIUBA 2014"
			}
			flash.message = "Autorización enviada para el miembro con dni: ${membresia.dni}"
		}
		
		def miembro = new Miembro(membresia: membresia, rol: Rol.findByNombre("Miembro"))
		if (!miembro.validate()){
			println miembro.errors
		} 
		miembro.save()
		redirect(action: "index")
	}
	
	def salir = {
		flash.message = "Goodbye ${session.user.dni}"
		session.user = null
		redirect(controller: "Red", action:"index")
	}
}
