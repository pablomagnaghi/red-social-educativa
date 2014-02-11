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

		if (!seguridadService.usuarioActual()) {
			flash.message = "Ingreso como visitante"
			redirect(action: "general")
		} else {
			def administrador = Administrador.findByUsuario(seguridadService.usuarioActual())

			if (administrador) {
				flash.message = "Ingreso como administrador"
				redirect(action: "general")
			} else {
				def mediador = Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(cursoId))

				if (mediador) {
					redirect(action: "mediador", params: params)
				} else {

					def aprendiz = aprendizService.obtenerPorCurso(seguridadService.usuarioActual().id, cursoId)

					if (aprendiz) {
						println "Hola aprendiz ${aprendiz}"

						redirect(action: "aprendiz", params: params)
					} else {
						flash.message = "Hola miembro ${seguridadService.usuarioActual()}"
						redirect(action: "miembro", params: params)
					}
				}
			}
		}
	}

	// Para visitantes y administradores
	// Si el curso no se dicta ese cuatrimestre: Solo se muestran los temas y el material general
	@Secured('permitAll')
	def general() {
		[curso: Curso.get(cursoId), cursoId: cursoId, cuatrimestre: cuatrimestreService.obtenerCuatrimestreActual(cursoId)]
	}

	@Secured('permitAll')
	def miembro() {
		//println "miembro: ${cursoId}"
		//println params
		cursoId = params.cursoId

		def miembro = Miembro.findByUsuario(seguridadService.usuarioActual())

		[miembro: miembro, cursoId: cursoId]
	}

	@Secured('permitAll')
	def aprendiz() {
		println "CURSO|aprendiz: ${params}"

		cursoId = params.cursoId

		/*
		 println "usuario actual"
		 println "${seguridadService.usuarioActual()}"
		 println "curso: ${Curso.get(cursoId)}"*/

		def aprendiz = aprendizService.obtenerPorCurso(seguridadService.usuarioActual().id, cursoId)
		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(cursoId)
		def noticiasCurso = NoticiaCurso.findAllByCuatrimestre(cuatrimestre)
		def cursando = false // para diferenciar entre aprendiz cuatrimestre y aprendiz curso

		/*println "cuatrimestre actual: ${cuatrimestreService.obtenerCuatrimestreActual(cursoId)}"
		 println "aprendiz cuatrimestre: ${aprendiz.cuatrimestre} ${aprendiz.cuatrimestre.anio}-${aprendiz.cuatrimestre.numero}"*/

		if (aprendiz.cuatrimestre.equals(cuatrimestre)) {
			println "Hola aprendiz ${aprendiz}"
			println "aprendiz cuatrimestre: ${cuatrimestre}"
			cursando = true
		}

		def nombreobtenerAprendizCurso

		[aprendiz: aprendiz, cursando: cursando, cursoId: cursoId, cuatrimestreId: cuatrimestre?.id, noticiasCurso: noticiasCurso]
	}

	@Secured('permitAll')
	def mediador() {

		println "mediador params: ${params}"

		cursoId = params.cursoId

		def mediador = Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(cursoId))

		println mediador

		def cuatrimestre = cuatrimestreService.obtenerCuatrimestreActual(cursoId)

		[materia: Curso.get(cursoId).materia, cursoId: cursoId, mediador: mediador, cuatrimestreId: cuatrimestre?.id,
			noticiasCurso: NoticiaCurso.findAllByCuatrimestre(cuatrimestre)]
	}

	@Secured('permitAll')
	def menuMediador() {

		println "params menu mediador: ${params}"

		cursoId = params.cursoId
		def cuatrimestreId = cuatrimestreService.obtenerCuatrimestreActual(cursoId).id

		[materia: Curso.get(cursoId).materia, cursoId: cursoId, cuatrimestreId: cuatrimestreId]
	}

	@Secured('permitAll')
	def material() {
		params.max = 5

		println "action materias controller curso"
		println params

		cursoId = params.cursoId

		[materialesCurso: MaterialCurso.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			materialesCursoCant: MaterialCurso.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId,
			mediador: Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(cursoId)),
			aprendiz: aprendizService.obtenerPorCurso(seguridadService.usuarioActual().id, cursoId)]
	}

	@Secured('permitAll')
	def temas() {
		params.max = 5

		println "action temas controller curso"
		println params

		cursoId = params.cursoId

		[temasCurso: Tema.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			temasCursoCant: Tema.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId,
			mediador: Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(cursoId)),
			aprendiz: aprendizService.obtenerPorCurso(seguridadService.usuarioActual().id, cursoId),
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
			aprendiz: aprendizService.obtenerPorCurso(seguridadService.usuarioActual().id, cursoId)]
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
