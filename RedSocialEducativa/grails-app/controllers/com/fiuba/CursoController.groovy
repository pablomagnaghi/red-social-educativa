package com.fiuba

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
//@Transactional(readOnly = true)
@Secured('permitAll')
class CursoController {

	// EL visitante puede:
	// * 1 - Visualizar información y material de los cursos (foros, temas y material general)
	// * 3 - Dejar comentario o mensaje para un curso (foro de curso)
	
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
	def springSecurityService
	
	private usuarioActual() {
		if (springSecurityService.principal.enabled)
			return Usuario.get(springSecurityService.principal.id)
		else
			return null
	}
	
	def cursoId
	
	def revisarRol() {
	
		cursoId = params.cursoId
		
		//params.cursoId = cursoId
		
		println "Revisar rol params: ${params}"
		//  [id:2, action:mediador, format:null, controller:curso]

		def usuario = usuarioActual()
		
		if (!usuario) {
			flash.message = "Ingreso como visitante"
			redirect(action: "general")
		} else {
			def administrador = Administrador.findByUsuario(usuario)
			
			if (administrador) {
				flash.message = "Ingreso como administrador"
				redirect(action: "general")
			} else {
				def mediador = Mediador.findByUsuarioAndCurso(usuario, Curso.get(cursoId))
				
				if (mediador) {
					//println "Hola mediador ${mediador} ${mediador.jerarquia}"
					redirect(action: "mediador", params: params)
				} else {
					def aprendiz = Aprendiz.findByUsuarioAndCurso(usuario, Curso.get(cursoId))

					if (aprendiz) {
						//println "Hola aprendiz ${aprendiz}"
						redirect(action: "aprendiz", params: params)
					} else {
						flash.message = "Hola miembro ${usuario}"
						redirect(action: "miembro", params: params)
					}
				}
			}
		}
	}
	
	// Para visitantes y administradores
	def general() {
		[curso: Curso.get(cursoId), cursoId: cursoId]
	}
	
	def miembro() {
		//println "cursoID miembro: ${cursoId}"
		//println params
		cursoId = params.cursoId
		
		def miembro = Miembro.findByUsuario(usuarioActual())
		
		[miembro: miembro, cursoId: cursoId]
	}
	
	def aprendiz() {
		println "params aprendiz: ${params}"
		
		cursoId = params.cursoId
		
		println "usuario actual"
		println "${usuarioActual()}"
		println "curso: ${Curso.get(cursoId)}"
		def aprendiz = Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))

		println "aprendiz"
		println aprendiz
		
		
		def noticiasCurso = NoticiaCurso.findAllByCurso(Curso.get(cursoId))

		[aprendiz: aprendiz, noticiasCurso: noticiasCurso, cursoId: cursoId]
	}
	
	def mediador() {
		
		println "mediador params: ${params}"
		
		cursoId = params.cursoId
		
		def mediador = Mediador.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))
		
		println mediador
		
		[materia: Curso.get(cursoId).materia, cursoId: cursoId, mediador: mediador,
			noticiasCurso: NoticiaCurso.findAllByCurso(Curso.get(cursoId))]
	}
	
	def menuMediador() {
		
		println "params menu mediador: ${params}"
		
		cursoId = params.cursoId

		[materia: Curso.get(cursoId).materia, cursoId: cursoId]
	}
	
	def material() {
		params.max = 5
		
		println "action materias controller curso"
		println params
				
		cursoId = params.cursoId

		[materialesCurso: MaterialCurso.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			materialesCursoCant: MaterialCurso.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId,
			mediador: Mediador.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId)),
			aprendiz: Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))]
	}
	
	def temas() {
		params.max = 5
		
		println "action temas controller curso"
		println params
				
		cursoId = params.cursoId

		[temasCurso: Tema.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			temasCursoCant: Tema.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId,
			mediador: Mediador.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId)),
			aprendiz: Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId)),
			cursoId: cursoId]
	}
	
	def actividades() {
		params.max = 5
		
		println "action actividades controller curso"
		println params
				
		cursoId = params.cursoId

		[actividades: Actividad.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			actividadesCant: Actividad.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId,
			mediador: Mediador.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId)),
			aprendiz: Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))]
	}
	
	def evaluaciones() {
		params.max = 5
		
		println "action actividades controller curso"
		println params
				
		cursoId = params.cursoId

		[evaluaciones: Evaluacion.findAllByCurso(Curso.get(cursoId),[max: params.max, offset: params.offset]),
			evaluacionesCant: Evaluacion.findAllByCurso(Curso.get(cursoId)).size(), cursoId: cursoId,
			mediador: Mediador.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId)),
			aprendiz: Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(cursoId))]
	}
	
	def solicitarParticipacionEnElCurso() {
		
		def aprendiz = new Aprendiz(usuario: usuarioActual(), rol: Rol.findByAuthority('ROL_APRENDIZ'), participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0")
		
		// println "solicitar participacion params: ${params}"
		
		cursoId = params.cursoId
		
		Curso.get(cursoId).addToAprendices(aprendiz)
		
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

		def cursoExistente = Curso.findByMateriaAndNroRelativo(Materia.get(cursoInstance.materia.id), cursoInstance.nroRelativo)
		println cursoExistente
		
		if (cursoExistente) {
			flash.message = "Ya existe el curso ${cursoInstance.nroRelativo}"
			redirect action: "create"
			return
		}
		
		cursoInstance.foro = new ForoCurso(nombre: "Foro general del curso ${cursoInstance.nroRelativo}")
		
		if (! cursoInstance.save(flush:true)) {
			respond cursoInstance.errors, view:'create'
			return
		}

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
