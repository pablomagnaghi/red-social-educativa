package com.fiuba

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
	
	def cursoId
	
	def revisarRol(){
	
		cursoId = params.id
		if (!session.user) {
			flash.message = "Ingreso como visitante"
			redirect(action: "general")
		} else {
			def usuario = Usuario.findByDni(session.user.dni)
			def administrador = Administrador.findByUsuario(usuario)
			
			if (administrador) {
				flash.message = "Ingreso como administrador"
				redirect(action: "general")
			} else {
				def mediador = Mediador.findByUsuario(usuario)
				
				if (Curso.get(cursoId).mediadores.contains(mediador)) {
					println "Hola mediador ${mediador}"
					redirect(action: "mediador", params: params)
				} else {
					def aprendiz = Aprendiz.findByUsuario(usuario)
					
					if (Curso.get(cursoId).aprendices.contains(aprendiz)) {
						println "Hola aprendiz ${aprendiz}"
						redirect(action: "aprendiz")
					} else {
						flash.message = "Hola miembro ${usuario}"
						redirect(action: "miembro")
					}
				}
				
			}
		}
	}
	
	def general(){
		[materia: Curso.get(cursoId).materia]
	}
	
	def mediador(){
		
		def ArrayList<Aprendiz> aprendicesInactivos = new ArrayList<Aprendiz>()
		
		println "params mediador: ${params}"

		cursoId = params.id
		
		println "curso mediador directo ${Curso.get(cursoId)}"
		
		if (Curso.get(cursoId).aprendices) {
			aprendicesInactivos = Curso.get(cursoId).aprendices.findAll {
				if (it.participa == false) {
					it
				}
			}
		}
		println "aprendices inactivos: ${aprendicesInactivos}"
		
		[aprendices: aprendicesInactivos]
	}
	
	def aprendiz(){
		
	}
	
	def miembro(){
		
	}
	// metodos por defecto, usados en ABM cursos del administrador
	// ver en detalle despues
	
    static scaffold = true
}
