package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class CursoController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def usuarioService
	def cursoService
	def mediadorService
	def aprendizService
	def cuatrimestreService

	@Secured("hasRole('ROL_ADMIN')")
	def administrador() {
		[usuario: usuarioService.usuarioActual(), dictaCuatrimestre: cursoService.seDicta(params.cursoId.toLong()), 
			cuatrimestre: cuatrimestreService.obtenerCuatrimestreActual(params.cursoId.toLong()), params: ['cursoId': params.cursoId]]
	}

	@Secured("hasRole('ROL_MIEMBRO')")
	def miembro() {
		// Verifico si el miembro ya pidio la participacion en el curso durante este cuatrimestre
		// Se necesita para decidir si se muestra o no la opcion de solicitar participacion en el curso en la vista
		def usuario = usuarioService.usuarioActual()
		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(params.cursoId.toLong())
		
		def aprendiz = Aprendiz.findByUsuarioAndCuatrimestreAndParticipa(usuario, cuatrimestre, false)
		
		[usuario: usuario, solicitoParticipacion: aprendiz, dictaCuatrimestre: cursoService.seDicta(params.cursoId.toLong()),
			cuatrimestre: cuatrimestreService.obtenerCuatrimestreActual(params.cursoId.toLong()), params: ['cursoId': params.cursoId]]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def aprendiz() {
		def aprendiz = aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, params.cursoId.toLong())
		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(params.cursoId.toLong())
		
		[aprendiz: aprendiz, dictaCuatrimestre: cursoService.seDicta(params.cursoId.toLong()), cuatrimestre: cuatrimestre, 
			noticiasCurso: NoticiaCurso.findAllByCuatrimestre(cuatrimestre), params: ['cursoId': params.cursoId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def mediador() {
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(params.cursoId.toLong())

		[mediador: mediador, dictaCuatrimestre: cursoService.seDicta(params.cursoId.toLong()), cuatrimestre: cuatrimestre, 
			noticiasCurso: NoticiaCurso.findAllByCuatrimestre(cuatrimestre), params: ['cursoId': params.cursoId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def menuMediador() {

		def cuatrimestres = cuatrimestreService.obtenerCuatrimestresOrdenados(params.cursoId.toLong())
		def cuatrimestre= cuatrimestreService.obtenerCuatrimestreActual(params.cursoId.toLong())
		
		[materia: Curso.get(params.cursoId).materia, cuatrimestreId: cuatrimestre?.id, cuatrimestres: cuatrimestres, 
			params: ['cursoId': params.cursoId]]
	}

	@Secured('isFullyAuthenticated()')
	def materiales() {
		params.max = Utilidades.MAX_PARAMS

		[materiales: MaterialCurso.findAllByCurso(Curso.get(params.cursoId),[max: params.max, offset: params.offset]),
			materialesCant: MaterialCurso.findAllByCurso(Curso.get(params.cursoId)).size(),
			mediador: Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId)),
			aprendiz: aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, params.cursoId.toLong()), 
			params: ['cursoId': params.cursoId]]
	}

	@Secured('isFullyAuthenticated()')
	def temas() {
		params.max = Utilidades.MAX_PARAMS

		[temas: Tema.findAllByCurso(Curso.get(params.cursoId),[max: params.max, offset: params.offset]),
			temasCant: Tema.findAllByCurso(Curso.get(params.cursoId)).size(),
			mediador: Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId)),
			aprendiz: aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, params.cursoId.toLong()),
			params: ['cursoId': params.cursoId]]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def actividades() {
		params.max = Utilidades.MAX_PARAMS

		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(params.cursoId.toLong())

		[actividades: Actividad.findAllByCuatrimestre(cuatrimestre,[max: params.max, offset: params.offset]),
			actividadesCant: Actividad.findAllByCuatrimestre(cuatrimestre).size(), cuatrimestre: cuatrimestre,
			aprendiz: aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, params.cursoId.toLong()),
			params: ['cursoId': params.cursoId]]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def evaluaciones() {
		params.max = Utilidades.MAX_PARAMS

		[evaluaciones: Evaluacion.findAllByCurso(Curso.get(params.cursoId),[max: params.max, offset: params.offset]),
			evaluacionesCant: Evaluacion.findAllByCurso(Curso.get(params.cursoId)).size(),
			aprendiz: aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, params.cursoId.toLong()),
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
		respond Curso.list(params), model:[cursoInstanceCount: Curso.count()]
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

		flash.message = message(code: 'default.created.message', args: [message(code: 'cursoInstance.label', default: 'Curso'), cursoInstance.id])
		redirect cursoInstance
	}

	@Secured("hasRole('ROL_ADMIN')")
	def edit(Curso cursoInstance) {
		respond cursoInstance
	}

	@Secured("hasRole('ROL_ADMIN')")
	def update(Curso cursoInstance) {
		if (cursoInstance == null) {
			notFound()
			return
		}

		if (!cursoService.guardar(cursoInstance)) {
			respond cursoInstance, view:'edit'
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'Curso.label', default: 'Curso'), cursoInstance.id])
		redirect cursoInstance
	}

	@Secured("hasRole('ROL_ADMIN')")
	def delete(Curso cursoInstance) {

		if (cursoInstance == null) {
			notFound()
			return
		}

		cursoService.eliminar(cursoInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'Curso.label', default: 'Curso'), cursoInstance.id])
		redirect action:"index", method:"GET"

	}

	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'cursoInstance.label', default: 'Curso'), params.id])
		redirect action: "index", method: "GET"
	}
}
