package com.foro

import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured("hasAnyRole('ROL_MEDIADOR', 'ROL_APRENDIZ')")
class PublicacionCursoController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def usuarioService
	def publicacionCursoService

	def nueva() {
		respond new PublicacionCurso(params), model: [usuario: usuarioService.usuarioActual(), 
			params:['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]
	}
	
	def guardar(PublicacionCurso publicacionCursoInstance) {
		if (publicacionCursoInstance == null) {
			notFound()
			return
		}
		if (params.pubInicialId) {
			if (!publicacionCursoService.guardarRespuesta(publicacionCursoInstance, params.pubInicialId.toLong(), 
				usuarioService.usuarioActual(), params.cursoId.toLong())) {
				redirect controller: "foroCurso", action: "publicaciones", params: ['id': params.pubInicialId, 'pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 
					'cuatrimestreId': params.cuatrimestreId]
				return
			}
			redirect controller: "foroCurso", action: "publicaciones", params: ['id': params.pubInicialId, 'cursoId': 
				params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}
		if	(!publicacionCursoService.guardar(publicacionCursoInstance, usuarioService.usuarioActual(), params.cursoId.toLong())) {
			render view:'nueva', model: [publicacionCursoInstance: publicacionCursoInstance, usuario: usuarioService.usuarioActual()],
					params: ['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}
		flash.message = "Tema ${publicacionCursoInstance} creado"
		redirect controller: "foroCurso", action: "general", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}
	
	def eliminar(PublicacionCurso publicacion) {
		if (publicacion == null) {
			notFound()
			return
		}
		def esTema = false
		if (!publicacion.publicacionInicial) {
			esTema = true
		} 
		publicacionCursoService.eliminar(publicacion)
		if (esTema) {
			flash.message = "Tema ${publicacion} eliminado"
			redirect controller: "foroCurso", action:"general", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}
		redirect controller: "foroCurso", action:"publicaciones", method:"GET", 
			params:['id': params.pubInicialId, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}
	
	def editar(PublicacionCurso publicacionCursoInstance) {
		respond publicacionCursoInstance, model: [usuario: usuarioService.usuarioActual()], params: [ 'publicacionId': params.id, 'pubInicialId': params.pubInicialId, 
			'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}
	
	def actualizar(PublicacionCurso publicacionCursoInstance) {
		if (publicacionCursoInstance == null) {
			notFound()
			return
		}
		if (!publicacionCursoService.guardar(publicacionCursoInstance, usuarioService.usuarioActual(), params.cursoId.toLong())) {
			render view:'editar', model: [publicacionCursoInstance: publicacionCursoInstance, usuario: usuarioService.usuarioActual()]
				params: ['publicacionId': params.id, 'pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 
					'cuatrimestreId': params.cuatrimestreId]
			return
		}
		redirect controller: "foroCurso", action: "publicaciones", model: [usuario: usuarioService.usuarioActual(), pubInicialId: params.pubInicialId], 
			params: ['id': params.pubInicialId, 'cursoId': params.cursoId, 'cuatrimestreId':  params.cuatrimestreId]
	}

	protected void notFound() {
		flash.message = "No se encuentra esa publicación"
		redirect controller: "foroCurso", action: "general", params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId], method: "GET"
	}
}
