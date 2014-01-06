package com.fiuba



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
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
			redirect(action: "indexGeneral")
		} else {
			def administrador = Administrador.findByMembresia(session.user)
			
			if (administrador) {
				flash.message = "Ingreso como administrador"
				redirect(action: "indexGeneral")
			} else {
				/*def mediador = Mediador.findByMembresia(session.user)
	
				if (Curso.get(cursoId).mediadores.contains(mediador)) {
					println "Hola mediador ${session.user}"
					redirect(action: "indexMediador")
				} else {
					def aprendiz = Aprendiz.findByMembresia(session.user)
					redirect(action: "indexAprendiz")
				}*/
				flash.message = "falta termianr"
				redirect(action: "indexMediador")
			}
		}
	}
	
	def indexGeneral() {
		[materia:  Curso.get(cursoId).materia]
	}
	
	// metodos por defecto, usados en ABM cursos del administrador
	// ver en detalle despues
	
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Curso.list(params), model:[cursoInstanceCount: Curso.count()]
    }

    def show(Curso cursoInstance) {
        respond cursoInstance
    }

    def create() {
        respond new Curso(params)
    }

    @Transactional
    def save(Curso cursoInstance) {
        if (cursoInstance == null) {
            notFound()
            return
        }

        if (cursoInstance.hasErrors()) {
            respond cursoInstance.errors, view:'create'
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

    @Transactional
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

    @Transactional
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
