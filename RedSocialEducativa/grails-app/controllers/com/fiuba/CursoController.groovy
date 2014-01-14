package com.fiuba

import org.springframework.security.access.annotation.Secured

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
	
	static scaffold = true
	
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
	
		cursoId = params.id
		
		// println "Revisar rol params: ${params}"
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
					println "Hola mediador ${mediador} ${mediador.jerarquia}"
					redirect(action: "mediador", params: params)
				} else {
					def aprendiz = Aprendiz.findByUsuarioAndCurso(usuario, Curso.get(cursoId))

					if (aprendiz) {
						println "Hola aprendiz ${aprendiz}"
						redirect(action: "aprendiz", params: params)
					} else {
						flash.message = "Hola miembro ${usuario}"
						redirect(action: "miembro")
					}
				}
			}
		}
	}
	
	def general() {
		[curso: Curso.get(cursoId)]
	}
	
	def mediador() {
		
		def ArrayList<Aprendiz> aprendicesInactivos = new ArrayList<Aprendiz>()
		
		// println "params mediador: ${params}"

		if (params.id)
			cursoId = params.id
		
		// println "curso mediador directo ${Curso.get(cursoId)}"
			
		if (Curso.get(cursoId).aprendices) {
			aprendicesInactivos = Curso.get(cursoId).aprendices.findAll {
				if (it.participa == false) {
					it
				}
			}
		}
		// println "aprendices inactivos: ${aprendicesInactivos}"
		
		[aprendices: aprendicesInactivos, materia: Curso.get(cursoId).materia]
	}
	
	def aprendiz() {
		def aprendiz = Aprendiz.findByUsuarioAndCurso(usuarioActual(), Curso.get(params.id))
		// println "Vista del curso para el aprendiz ${aprendiz}"
		// TODO agregar noticias del curso
		def noticiasCurso = NoticiaCurso.findByCurso(Curso.get(params.id))
		// def noticiasCurso
		[aprendiz: aprendiz, noticiasCurso: noticiasCurso]
	}
	
	def miembro() {
		
	}
	
	def solicitarParticipacionEnElCurso() {/*
		// hacer validaciones de algunas campos como dni
		if (params.password != params.passwordConfirmado) {
			flash.message = "El password confirmado es incorrecto"
			redirect(action: "solicitarMembresia")
			return
		}
		def usuario = new Usuario(username: params.username, password: params.password, apellido: params.apellido,
			nombres: params.nombres, legajo: params.legajo, padron: params.padron, email: params.email,
			fechaSolicitud: new Date(), enabled: false)
		
		if(!usuario.validate()) {
			flash.message = "Revise sus parametros"
			respond usuario.errors, view:'solicitarMembresia'
			return
		}
		
		usuario.save()
		flash.message = "Solicitud aceptada. A la brevedad se le enviara un mail de confirmacion"
		redirect(action:"index")*/
		
	}
}
