package com.cursado

import com.cartelera.*
import com.fiuba.*
import com.material.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

import com.mensajeria.Mensaje;

class CursoController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def usuarioService
	def cursoService
	def mediadorService
	def aprendizService
	def cuatrimestreService

	@Secured("hasRole('ROL_ADMIN')")
	def administrador() {
		[materiales: MaterialCurso.findAllByCurso(Curso.get(params.cursoId)), temas: Tema.findAllByCurso(Curso.get(params.cursoId)), params: ['cursoId': params.cursoId]]
	}

	@Secured("hasRole('ROL_MIEMBRO')")
	def miembro() {
		// Verifico si el miembro ya pidio la participacion en el curso durante este cuatrimestre
		// Se necesita para decidir si se muestra o no la opcion de solicitar participacion en el curso en la vista
		def usuario = usuarioService.usuarioActual()
		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(params.cursoId.toLong())
		def aprendiz = Aprendiz.findByUsuarioAndCuatrimestreAndParticipa(usuario, cuatrimestre, false)
		[solicitoParticipacion: aprendiz, dictaCuatrimestre: cursoService.seDicta(params.cursoId.toLong()),
			cuatrimestre: cuatrimestreService.obtenerCuatrimestreActual(params.cursoId.toLong()), 
			materiales: MaterialCurso.findAllByCurso(Curso.get(params.cursoId)),
			temas: Tema.findAllByCurso(Curso.get(params.cursoId)),
			params: ['cursoId': params.cursoId]]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def aprendiz() {
		def aprendiz = aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, params.cursoId.toLong())
		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(params.cursoId.toLong())
		[aprendiz: aprendiz, dictaCuatrimestre: cursoService.seDicta(params.cursoId.toLong()), cuatrimestre: cuatrimestre, 
			noticiasCurso: NoticiaCurso.findAllByCuatrimestre(cuatrimestre),
			materiales: MaterialCurso.findAllByCurso(Curso.get(params.cursoId)),
			temas: Tema.findAllByCurso(Curso.get(params.cursoId)),
			params: ['cursoId': params.cursoId],]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def mediador() {
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		def cuatrimestres = cuatrimestreService.obtenerCuatrimestresOrdenados(params.cursoId.toLong())
		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(params.cursoId.toLong())
		[asignatura: Curso.get(params.cursoId).asignatura, mediador: mediador, dictaCuatrimestre: cursoService.seDicta(params.cursoId.toLong()), 
			cuatrimestres: cuatrimestres, cuatrimestre: cuatrimestre, 
			noticiasCurso: NoticiaCurso.findAllByCuatrimestre(cuatrimestre), 
			materiales: MaterialCurso.findAllByCurso(Curso.get(params.cursoId)),
			temas: Tema.findAllByCurso(Curso.get(params.cursoId)),
			params: ['cursoId': params.cursoId]]
	}
	
	@Secured("hasRole('ROL_MIEMBRO')")
	def solicitarParticipacionEnElCurso() {
		if (!cursoService.agregarAprendiz(usuarioService.usuarioActual(), params.cursoId.toLong())) {
			flash.message = "Problemas con la solitud de participacion"
			redirect action: "miembro", params:['cursoId': params.cursoId]
			return
		}
		flash.message = "Solicitud aceptada. A la brevedad se le enviara un mail de confirmacion"
		redirect action: "miembro", params:['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_ADMIN')")
	def index() {
		params.max = Utilidades.MAX_PARAMS
		[cursoInstanceList: cursoService.obtenerCursosOrdenados()]
	}
	
	@Secured("hasRole('ROL_ADMIN')")
	def show(Curso cursoInstance) {
		respond cursoInstance
	}

	@Secured("hasRole('ROL_ADMIN')")
	def create() {
		respond new Curso(params)
	}

	@Secured("hasRole('ROL_ADMIN')")
	def save(Curso cursoInstance) {
		if (cursoInstance == null) {
			notFound()
			return
		}
		if (cursoService.existe(cursoInstance)) {
			flash.message = "Ya existe el curso ${cursoInstance.nroRelativo}"
			redirect action: "create"
			return
		}
		if (!cursoService.guardar(cursoInstance)) {
			respond cursoInstance, view:'create'
			return
		}
		flash.message = "Curso ${cursoInstance} creado"
		redirect action:"index"
	}

	@Secured("hasRole('ROL_ADMIN')")
	def edit(Curso cursoInstance) {
		def numero = Curso.get(cursoInstance.id).nroRelativo
		respond cursoInstance, model: [numero: numero]
	}

	@Secured("hasRole('ROL_ADMIN')")
	def update(Curso cursoInstance) {
		if (cursoInstance == null) {
			notFound()
			return
		}
		if (cursoService.existe(cursoInstance)) {
			flash.message = "Ya existe el curso ${cursoInstance.nroRelativo}"
			cursoInstance.nroRelativo = params.numeroAnterior.toShort()
			redirect action:'edit', params: params
			return	
		}
		if (!cursoService.guardar(cursoInstance)) {
			respond cursoInstance, view:'edit'
			return
		}
		flash.message = "Curso ${cursoInstance} actualizado"
		redirect action:"index"
	}

	@Secured("hasRole('ROL_ADMIN')")
	def delete(Curso cursoInstance) {
		if (cursoInstance == null) {
			notFound()
			return
		}
		cursoService.eliminar(cursoInstance)
		flash.message = "Curso ${cursoInstance} eliminado"
		redirect action:"index", method:"GET"
	}

	protected void notFound() {
		flash.message = "No se encuentra ese curso"
		redirect action: "index", method: "GET"
	}
}
