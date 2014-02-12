package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class CursoController {

	// Metodos nuevos
	def seguridadService
	def cursoService
	def mediadorService
	def aprendizService
	def cuatrimestreService

	def cursoId

	@Secured('permitAll')
	def revisarRol() {
		println "Revisar rol params: ${params}"

		cursoId = params.cursoId
		def usuario = seguridadService.usuarioActual()
		def curso = Curso.get(cursoId)
		
		if (!seguridadService.usuarioActual()) {
			flash.message = "Ingreso como visitante"
			redirect(action: "general")
			return
		} 
		
		if (Administrador.findByUsuario(usuario)) {
			flash.message = "Ingreso como administrador"
			redirect(action: "general")
			return
		} 
		
		if (Mediador.findByUsuarioAndCurso(usuario, curso)) {
			redirect(action: "mediador", params: params)
			return
		} 
		
		if (aprendizService.obtenerPorCurso(usuario.id, cursoId.toLong())) {
			println "Hola aprendiz ${aprendiz}"
			redirect(action: "aprendiz", params: params)
			return 
		} 
		
		flash.message = "Hola miembro ${usuario}"
		redirect(action: "miembro", params: params)
	}

	// Para visitantes y administradores
	@Secured('permitAll')
	def general() {
		println "general: curso se dicta: ${cursoService.dictandose(cursoId)}"
		[dictaCuatrimestre: cursoService.dictandose(cursoId.toLong()), cursoId: cursoId, 
			cuatrimestre: cuatrimestreService.obtenerCuatrimestreActual(cursoId)]
	}

	@Secured('permitAll')
	def miembro() {
		cursoId = params.cursoId

		println "miembro: curso se dicta: ${cursoService.dictandose(cursoId.toLong())}"
		def miembro = Miembro.findByUsuario(seguridadService.usuarioActual())

		[dictaCuatrimestre: cursoService.dictandose(cursoId.toLong()), miembro: miembro, cursoId: cursoId]
	}

	@Secured('permitAll')
	def aprendiz() {
		println "aprendiz: ${params}"

		cursoId = params.cursoId
		println "aprendiz: curso se dicta: ${cursoService.dictandose(cursoId.toLong())}"
		def aprendiz = aprendizService.obtenerPorCurso(seguridadService.usuarioActual().id, cursoId.toLong())
		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(cursoId.toLong())
		def noticiasCurso = NoticiaCurso.findAllByCuatrimestre(cuatrimestre)
		def cursando = false // para diferenciar entre aprendiz cuatrimestre y aprendiz curso

		if (aprendiz.cuatrimestre.equals(cuatrimestre)) {
			cursando = true
		}

		def nombreobtenerAprendizCurso

		[dictaCuatrimestre: cursoService.dictandose(cursoId.toLong()), aprendiz: aprendiz, 
			cursando: cursando, cursoId: cursoId, cuatrimestreId: cuatrimestre?.id, noticiasCurso: noticiasCurso]
	}

	@Secured('permitAll')
	def mediador() {

		println "mediador params: ${params}"

		cursoId = params.cursoId

		println "mediador: curso se dicta: ${cursoService.dictandose(cursoId.toLong())}"
		
		def mediador = Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(cursoId))

		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(cursoId.toLong())

		[dictaCuatrimestre: cursoService.dictandose(cursoId.toLong()), materia: Curso.get(cursoId).materia, 
			cursoId: cursoId, mediador: mediador, cuatrimestreId: cuatrimestre?.id,
			noticiasCurso: NoticiaCurso.findAllByCuatrimestre(cuatrimestre)]
	}

	@Secured('permitAll')
	def menuMediador() {

		println "params menu mediador: ${params}"

		cursoId = params.cursoId
		def cuatrimestreId = cuatrimestreService.obtenerCuatrimestreActual(cursoId.toLong()).id

		[materia: Curso.get(cursoId).materia, cursoId: cursoId, cuatrimestreId: cuatrimestreId]
	}

	@Secured('permitAll')
	def material() {
		params.max = Utilidades.MAX_PARAMS

		println "action materias controller curso params: ${params}"

		cursoId = params.cursoId

		[materialesCurso: MaterialCurso.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			materialesCursoCant: MaterialCurso.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId,
			mediador: Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(cursoId)),
			aprendiz: aprendizService.obtenerPorCurso(seguridadService.usuarioActual().id, cursoId.toLong())]
	}

	@Secured('permitAll')
	def temas() {
		params.max = Utilidades.MAX_PARAMS

		println "action temas controller curso params: ${params}"

		cursoId = params.cursoId

		[temasCurso: Tema.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			temasCursoCant: Tema.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId,
			mediador: Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(cursoId)),
			aprendiz: aprendizService.obtenerPorCurso(seguridadService.usuarioActual().id, cursoId.toLong()),
			cursoId: cursoId]
	}

	@Secured('permitAll')
	def actividades() {
		params.max = 5

		println "action actividades controller curso"
		println params

		cursoId = params.cursoId
		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(cursoId)

		[actividades: Actividad.findAllByCuatrimestre(cuatrimestre,[max: params.max, offset: params.offset]),
			actividadesCant: Actividad.findAllByCuatrimestre(cuatrimestre).size(), cursoId: cursoId,
			cuatrimestreId: cuatrimestre?.id,
			mediador: Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(cursoId)),
			aprendiz: aprendizService.obtenerPorCurso(seguridadService.usuarioActual().id, cursoId.toLong())]
	}

	@Secured('permitAll')
	def evaluaciones() {
		params.max = 5

		println "action actividades controller curso"
		println params

		cursoId = params.cursoId

		[evaluaciones: Evaluacion.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			evaluacionesCant: Evaluacion.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId,
			mediador: Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(cursoId)),
			aprendiz: aprendizService.obtenerPorCurso(seguridadService.usuarioActual().id, cursoId)]
	}

	@Secured('permitAll')
	def solicitarParticipacionEnElCurso() {

		def aprendiz = new Aprendiz(usuario: usuarioActual(), rol: Rol.findByAuthority('ROL_APRENDIZ'), participa: false, msjEnviados: "0",
		msjLeidos: "0", pubForos: "0", descMaterial: "0")

		// println "solicitar participacion params: ${params}"

		cursoId = params.cursoId
		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(cursoId)
		cuatrimestre.addToAprendices(aprendiz)

		if(!aprendiz.validate()) {
			flash.message = "Problemas con la solitud de participacion"
			println aprendiz.errors
			redirect(action:"aprendiz", params:['cursoId': cursoId])
			return
		}

		aprendiz.save()
		flash.message = "Solicitud aceptada. A la brevedad se le enviara un mail de confirmacion"
		redirect(action:"aprendiz", params:['cursoId': cursoId])
	}

	// TODO: Metodos para ABM de cursos en menu administrador

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

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
