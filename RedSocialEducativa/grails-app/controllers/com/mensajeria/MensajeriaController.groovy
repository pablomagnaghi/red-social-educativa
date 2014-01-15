package com.mensajeria

import org.springframework.security.access.annotation.Secured
import com.fiuba.Usuario
import com.mensajeria.Conversacion

import com.fiuba.RedController;

@Secured('permitAll')
class MensajeriaController {
	
	def springSecurityService
	
    def index() {
		def usuario = Usuario.get(springSecurityService.principal.id)
		def etiquetasCarpetas = getCarpetas(usuario)
		def conversacion = Conversacion.findAllByPadre(Carpeta.findByUsuarioAndNombre(usuario, "Escritorio"))
		[etiquetasCarpetas: etiquetasCarpetas, conversacionesEscr : conversacion]
	}

	def nuevaCarpeta() {
		def usuario = Usuario.get(springSecurityService.principal.id)
		def nuevaCarpeta = new Carpeta(nombre : params.nombre, usuario: usuario)
		nuevaCarpeta.save(failOnError: true)
		
		def etiquetasCarpetas = getCarpetas(usuario)
		render(template:"carpetas",model:[etiquetasCarpetas: etiquetasCarpetas])
	}
	
	private getCarpetas(Usuario usuario){
		def carpetas = Carpeta.findAllByUsuario(usuario, [sort:"id", order:"asc"]);
		def etiquetasCarpetas = [];
		carpetas.each {
			def cantMsg = Mensaje.getNewMessages(usuario, it.nombre	)
			if (cantMsg > 0){
				etiquetasCarpetas.add(it.nombre + " (" + cantMsg + ")")
			} else {
				etiquetasCarpetas.add(it.nombre)
			}
		}
		return etiquetasCarpetas
	}
}
