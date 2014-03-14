package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured


class AprendizController {
	
	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def aprendizService
	def usuarioService
	def mediadorService
	def cuatrimestreService
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def aprendicesCurso() {
		params.max = Utilidades.MAX_PARAMS	
		[aprendices: aprendizService.obtenerAprendices(params.cursoId.toLong()), cuatrimestre: cuatrimestreService.obtenerCuatrimestreActual(params.cursoId.toLong()),
			params:['cursoId': params.cursoId]]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def estadisticas() {
		params.max = Utilidades.MAX_PARAMS
		def cuatrimestre = Cuatrimestre.get(params.cuatrimestreId)
		[aprendizInstanceList: Aprendiz.findAllByCuatrimestreAndParticipa(cuatrimestre, true), params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
    def index() {
        params.max = Utilidades.MAX_PARAMS
		def cuatrimestre = Cuatrimestre.get(params.cuatrimestreId)
		[aprendizInstanceList: Aprendiz.findAllByCuatrimestre(cuatrimestre), params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
    def create() {
        respond new Aprendiz(params), params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
    }
	
	@Secured("hasRole('ROL_MEDIADOR')")
    def save(Aprendiz aprendizInstance) {
        if (aprendizInstance == null) {
            notFound()
            return
        }
		def usuario = Usuario.get(aprendizInstance.usuario.id)
		def mediador = Mediador.findByUsuarioAndCurso(usuario, Curso.get(params.cursoId))
		if (mediador && mediadorService.existe(mediador)) {
			flash.message = "El miembro ${usuario} ya es mediador en el curso ${mediador.curso}. No puede ser aprendiz en el mismo"
			redirect action: "create", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}
		// Verifico si pertence al cuatrimestre acutal
		def cuatrimestre = Cuatrimestre.get(params.cuatrimestreId)
		def aprendiz = Aprendiz.findByUsuarioAndCuatrimestre(usuario, cuatrimestre)
		if (aprendiz) {
			flash.message = "${aprendiz.usuario} ya es aprendiz en el curso ${cuatrimestre.curso} durante el cuatrimestre ${cuatrimestre}"
			redirect action: "create", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}
		if (!aprendizService.guardar(aprendizInstance)) {
			respond aprendizInstance, view:'create', params:['cursoId': params.CursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}
		aprendizService.notificar(aprendizInstance)
		flash.message = "Aprendiz ${aprendizInstance.usuario} creado"
		redirect action: "index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
    }

	@Secured("hasRole('ROL_MEDIADOR')")
	def cambiarEstado(Aprendiz aprendizInstance) {
		if (aprendizInstance == null) {
			notFound()
			return
		}
		aprendizInstance.cursando = aprendizInstance.participa ? false : true
		aprendizInstance.participa = aprendizInstance.participa ? false : true
		if (!aprendizService.guardar(aprendizInstance)) {
			flash.message = "Problemas al cambiar el estado"
			redirect action:"index", params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId], method:"GET"
			return
		}
		aprendizService.notificar(aprendizInstance)
		if ((!aprendizInstance.participa) && (aprendizInstance.usuario == usuarioService.usuarioActual())) {
			redirect controller:"red", action:"revisarRol",  method:"GET"
			return
		}
		flash.message = "Aprendiz ${aprendizInstance.usuario} actualizado"
		redirect action:"index", params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId], method:"GET"
		return
	}
	/*
	@Secured("hasRole('ROL_APRENDIZ')")
	def salir(Usuario usuarioInstance) {
		if (usuarioInstance == null) {
			notFound()
			return
		}
		usuarioInstance.enabled = false
		if (!usuarioService.guardar(usuarioInstance)) {
			flash.message = "Problemas al cambiar el estado"
			redirect controller:"red", action:"revisarRol",  method:"GET"
			return
		}
		usuarioService.notificar(usuarioInstance)
		redirect controller:"logout", method:"GET"
		return
	}
	*/
    protected void notFound() {
		flash.message = "No se encuentra ese aprendiz"
		redirect action: "index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId], method: "GET"
    }
}
