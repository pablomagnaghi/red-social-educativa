package com.fiuba

import org.springframework.security.access.annotation.Secured

// Elementos necesarios para la creacion del mediador
// *usuario
// *rol
// *jerarquia

@Secured('permitAll')
class MediadorController {

    static scaffold = true
	
	// Metodos nuevos
	
	def activarAprendiz() {
		def aprendiz = Aprendiz.get(params.id)
		//println "activarAprendiz params: ${params}"
		//println "${aprendiz}, ${aprendiz.id}, ${aprendiz.participa}"
	
		aprendiz.participa = true
		def mail = aprendiz.usuario.email
		def username = aprendiz.usuario.username
		if (aprendiz.hasErrors()){
			println aprendiz.errors
			flash.message = "Problemas con el aprendiz"
			redirect(controller: "curso", action: "mediador")
			return
		} else {
			aprendiz.save();
			sendMail {
				to mail
				subject "Red Social Educativa"
				body "Bienvenido aprendiz ${username} al curso ${aprendiz.curso} de la Red Social Educativa FIUBA 2014"
			}
			flash.message = "Autorización enviada para el aprendiz ${username} del curso ${aprendiz.curso}"
		}
		
		redirect(controller: "curso", action: "mediador")
	}
}
