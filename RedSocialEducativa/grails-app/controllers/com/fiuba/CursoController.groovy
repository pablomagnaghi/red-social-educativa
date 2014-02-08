package com.fiuba

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
//@Transactional(readOnly = true)
@Secured('permitAll')
class CursoController {

	// EL visitante puede:
	// * Visualizar información y material de los cursos (foros, temas y material general)
	// * Dejar comentario o mensaje para un curso (foro de curso)
	
	// Mediador
	// 15. Administrar aprendices (aceptar participación y eliminar)
	// 16. Administrar (registrar, actualizar y eliminar) temas en un curso
	// 17. Administrar la cartelera de un curso (publicar, modificar y eliminar anuncios)
	// 18. Administrar fechas de evaluación en un curso
	// 19. Registrar (y modificar) resultados de evaluación de una fecha sólo visibles por el evaluado
	// 20. Administrar actividades de un curso: crear, modificar, eliminar o asignarla al curso completo o a uno
	// o más grupos, y registrar evaluaciones individuales o grupales, si la actividad fuera evaluable
	// 21. Cambiar aprendices de grupo
	// 22. Enviar y recibir mensajes a otro miembro de la red, a un grupo de un curso o al curso (sólo a los
	// mediadores o a todo el curso), y recibir mensajes y además intercambiar mensajes con mediadores
	// de otros cursos
	// 23. Administrar foros de un curso (publicar y eliminar publicaciones)
	// 24. Subir, bajar y eliminar material en un curso o grupo de curso
	// 25. Acceder a información de seguimiento de la participación de aprendices de un curso
	// 26. Consolidar cuatrimestre
	
	// metodos por defecto, usados en ABM cursos del administrador
	// ver en detalle despues
	
	// Metodos nuevos
	def seguridadService
	def cursoService
	def cursoId
	
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
					
					def aprendiz = cursoService.obtenerAprendizCurso(seguridadService.usuarioActual(), cursoId)

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
	def general() {
		[curso: Curso.get(cursoId), cursoId: cursoId, cuatrimestre: cursoService.obtenerCuatrimestreActual(cursoId)]
	}
	
	def miembro() {
		//println "miembro: ${cursoId}"
		//println params
		cursoId = params.cursoId
		
		def miembro = Miembro.findByUsuario(seguridadService.usuarioActual())
		
		[miembro: miembro, cursoId: cursoId]
	}
	
	def aprendiz() {
		println "CURSO|aprendiz: ${params}"
		
		cursoId = params.cursoId
		
		/*
		println "usuario actual"
		println "${seguridadService.usuarioActual()}"
		println "curso: ${Curso.get(cursoId)}"*/
	
		def aprendiz = cursoService.obtenerAprendizCurso(seguridadService.usuarioActual(), cursoId)
		def cuatrimestre = cursoService.obtenerCuatrimestreActual(cursoId)
		def noticiasCurso = NoticiaCurso.findAllByCuatrimestre(cuatrimestre)
		def cursando = false // para diferenciar entre aprendiz cuatrimestre y aprendiz curso
			
		/*println "cuatrimestre actual: ${cursoService.obtenerCuatrimestreActual(cursoId)}"
		println "aprendiz cuatrimestre: ${aprendiz.cuatrimestre} ${aprendiz.cuatrimestre.anio}-${aprendiz.cuatrimestre.numero}"*/
		
		if (aprendiz.cuatrimestre.equals(cuatrimestre)) {
			println "Hola aprendiz ${aprendiz}"
			println "aprendiz cuatrimestre: ${cuatrimestre}"
			cursando = true
		}
		
		def nombreobtenerAprendizCurso
		
		[aprendiz: aprendiz, cursando: cursando, cursoId: cursoId, cuatrimestreId: cuatrimestre?.id, noticiasCurso: noticiasCurso]
	}
	
	def mediador() {
		
		println "mediador params: ${params}"
		
		cursoId = params.cursoId
		
		def mediador = Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(cursoId))
		
		println mediador
		
		def cuatrimestre = cursoService.obtenerCuatrimestreActual(cursoId)
		
		[materia: Curso.get(cursoId).materia, cursoId: cursoId, mediador: mediador, cuatrimestreId: cuatrimestre?.id,
			noticiasCurso: NoticiaCurso.findAllByCuatrimestre(cuatrimestre)]
	}
	
	def menuMediador() {
		
		println "params menu mediador: ${params}"
		
		cursoId = params.cursoId
		def cuatrimestreId = cursoService.obtenerCuatrimestreActual(cursoId).id
		
		[materia: Curso.get(cursoId).materia, cursoId: cursoId, cuatrimestreId: cuatrimestreId]
	}
	
	def material() {
		params.max = 5
		
		println "action materias controller curso"
		println params
				
		cursoId = params.cursoId

		[materialesCurso: MaterialCurso.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			materialesCursoCant: MaterialCurso.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId,
			mediador: Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(cursoId)),
			aprendiz: cursoService.obtenerAprendizCurso(seguridadService.usuarioActual(), cursoId)]
	}
	
	def temas() {
		params.max = 5
		
		println "action temas controller curso"
		println params
				
		cursoId = params.cursoId

		[temasCurso: Tema.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			temasCursoCant: Tema.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId,
			mediador: Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(cursoId)),
			aprendiz: cursoService.obtenerAprendizCurso(seguridadService.usuarioActual(), cursoId),
			cursoId: cursoId]
	}
	
	def actividades() {
		params.max = 5
		
		println "action actividades controller curso"
		println params
				
		cursoId = params.cursoId
		def cuatrimestre = cursoService.obtenerCuatrimestreActual(cursoId)

		[actividades: Actividad.findAllByCuatrimestre(cuatrimestre,[max: params.max, offset: params.offset]),
			actividadesCant: Actividad.findAllByCuatrimestre(cuatrimestre).size(), cursoId: cursoId,
			cuatrimestreId: cuatrimestreId,
			mediador: Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(cursoId)),
			aprendiz: cursoService.obtenerAprendizCurso(seguridadService.usuarioActual(), cursoId)]
	}
	
	def evaluaciones() {
		params.max = 5
		
		println "action actividades controller curso"
		println params
				
		cursoId = params.cursoId

		[evaluaciones: Evaluacion.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			evaluacionesCant: Evaluacion.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId,
			mediador: Mediador.findByUsuarioAndCurso(seguridadService.usuarioActual(), Curso.get(cursoId)),
			aprendiz: cursoService.obtenerAprendizCurso(seguridadService.usuarioActual(), cursoId)]
	}
	
	def solicitarParticipacionEnElCurso() {
		
		def aprendiz = new Aprendiz(usuario: usuarioActual(), rol: Rol.findByAuthority('ROL_APRENDIZ'), participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0")
		
		// println "solicitar participacion params: ${params}"
		
		cursoId = params.cursoId
		def cuatrimestre = cursoService.obtenerCuatrimestreActual(cursoId)
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

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Curso.list(params), model:[cursoInstanceCount: Curso.count()]
    }

    def show(Curso cursoInstance) {
        respond cursoInstance
    }

    def create() {
		println "create"
        respond new Curso(params)
    }

	//@Transactional
    def save(Curso cursoInstance) {
		
		println "save"	
		println cursoInstance.properties
		
        if (cursoInstance == null) {
            notFound()
            return
        }
		
		if (cursoInstance.hasErrors()) {
			respond cursoInstance.errors, view:'create'
			return
		}

		def cursoExistente = Curso.findByMateriaAndNroRelativo(Materia.get(cursoInstance.materia.id), cursoInstance.nroRelativo)
		
		println cursoExistente
		
		if (cursoExistente) {
			flash.message = "Ya existe el curso ${cursoInstance.nroRelativo}"
			redirect action: "create"
			return
		}
		
		cursoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cursoInstance.label', default: 'Curso'), cursoInstance.id])
                redirect cursoInstance
            }
            '*' { respond cursoInstance, [status: CREATED] }
        }
    }

    def edit(Curso cursoInstance) {
        respond cursoInstance
    }

    //@Transactional
    def update(Curso cursoInstance) {
        if (cursoInstance == null) {
            notFound()
            return
        }

        if (cursoInstance.hasErrors()) {
            respond cursoInstance.errors, view:'edit'
            return
        }
		
        cursoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Curso.label', default: 'Curso'), cursoInstance.id])
                redirect cursoInstance
            }
            '*'{ respond cursoInstance, [status: OK] }
        }
    }

    //@Transactional
    def delete(Curso cursoInstance) {

        if (cursoInstance == null) {
            notFound()
            return
        }

        cursoInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Curso.label', default: 'Curso'), cursoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cursoInstance.label', default: 'Curso'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
