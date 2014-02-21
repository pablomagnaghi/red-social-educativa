package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class CursoController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def usuarioService
	def cursoService
	def mediadorService
	def aprendizService
	def cuatrimestreService

	def cursoId

	@Secured('permitAll')
	def revisarRol() {
		println "Revisar rol params: ${params}"

		def usuario = usuarioService.usuarioActual()
		def curso = Curso.get(params.cursoId)
		
		if (!usuarioService.usuarioActual()) {
			flash.message = "Ingreso como visitante"
			redirect(action: "general", params: params)
			return
		} 
		
		if (Administrador.findByUsuario(usuario)) {
			flash.message = "Ingreso como administrador"
			redirect(action: "general", params: params)
			return
		} 
		
		if (Mediador.findByUsuarioAndCurso(usuario, curso)) {
			redirect(action: "mediador", params: params)
			return
		} 
		
		if (aprendizService.obtenerPorCurso(usuario.id, params.cursoId.toLong())) {
			println "Hola aprendiz ${usuario}"
			redirect(action: "aprendiz", params: params)
			return 
		} 
		
		flash.message = "Hola miembro ${usuario}"
		redirect(action: "miembro", params: params)
	}

	@Secured("hasRole('ROL_ADMIN')")
	def general() {
		[dictaCuatrimestre: cursoService.seDicta(params.cursoId.toLong()), 
			cuatrimestre: cuatrimestreService.obtenerCuatrimestreActual(params.cursoId.toLong()), params: ['cursoId': params.cursoId]]
	}

	@Secured("hasRole('ROL_MIEMBRO')")
	def miembro() {
		def miembro = Miembro.findByUsuario(usuarioService.usuarioActual())
		[miembro: miembro, dictaCuatrimestre: cursoService.seDicta(params.cursoId.toLong()),
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

	// TODO
	@Secured('permitAll')
	def material() {
		params.max = Utilidades.MAX_PARAMS

		println "action materias controller curso params: ${params}"

		cursoId = params.cursoId

		[materialesCurso: MaterialCurso.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			materialesCursoCant: MaterialCurso.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId,
			mediador: Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(cursoId)),
			aprendiz: aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, cursoId.toLong())]
	}

	@Secured('permitAll')
	def temas() {
		params.max = Utilidades.MAX_PARAMS

		println "action temas controller curso params: ${params}"

		cursoId = params.cursoId

		[temasCurso: Tema.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			temasCursoCant: Tema.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId,
			mediador: Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(cursoId)),
			aprendiz: aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, cursoId.toLong()),
			cursoId: cursoId]
	}

	@Secured('permitAll')
	def actividades() {
		params.max = 5

		println "action actividades controller curso"
		println params

		cursoId = params.cursoId
		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(params.cursoId.toLong())

		[actividades: Actividad.findAllByCuatrimestre(cuatrimestre,[max: params.max, offset: params.offset]),
			actividadesCant: Actividad.findAllByCuatrimestre(cuatrimestre).size(), cursoId: cursoId,
			cuatrimestreId: cuatrimestre?.id,
			mediador: Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(cursoId)),
			aprendiz: aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, params.cursoId.toLong())]
	}

	@Secured('permitAll')
	def evaluaciones() {
		params.max = 5

		println "action actividades controller curso"
		println params

		cursoId = params.cursoId

		[evaluaciones: Evaluacion.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			evaluacionesCant: Evaluacion.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId,
			mediador: Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(cursoId)),
			aprendiz: aprendizService.obtenerPorCurso(usuarioService.usuarioActual().id, cursoId.toLong())]
	}

	@Secured("hasRole('ROL_MIEMBRO')")
	def solicitarParticipacionEnElCurso() {

		def aprendiz = new Aprendiz(usuario: usuarioActual(), rol: Rol.findByAuthority('ROL_APRENDIZ'), participa: false, msjEnviados: "0",
		msjLeidos: "0", pubForos: "0", descMaterial: "0", cursando: false)
		
		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(params.cursoId)
		cuatrimestre.addToAprendices(aprendiz)

		if(!aprendiz.validate()) {
			flash.message = "Problemas con la solitud de participacion"
			println aprendiz.errors
			redirect(action:"aprendiz", params:['cursoId': params.cursoId])
			return
		}

		aprendiz.save()
		flash.message = "Solicitud aceptada. A la brevedad se le enviara un mail de confirmacion"
		redirect(action:"aprendiz", params:['cursoId': params.cursoId])
	}

	// TODO: Metodos para ABM de cursos en menu administrador

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
