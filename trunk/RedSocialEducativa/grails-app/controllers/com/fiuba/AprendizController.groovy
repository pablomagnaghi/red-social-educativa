package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured("hasRole('ROL_MEDIADOR')")
class AprendizController {
	
	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def seguridadService
	def aprendizService
	def mediadorService
	
	def estadisticas() {
		params.max = Utilidades.MAX_PARAMS
		
		def cuatrimestre = Cuatrimestre.get(params.cuatrimestreId)
			
		[aprendizInstanceList: Aprendiz.findAllByCuatrimestreAndParticipa(cuatrimestre, true, [max: params.max, offset: params.offset]),
			aprendizInstanceCount: Aprendiz.findAllByCuatrimestreAndParticipa(cuatrimestre, true).size(), 
			params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]
	}

    def index() {
        params.max = Utilidades.MAX_PARAMS
		
		def cuatrimestre = Cuatrimestre.get(params.cuatrimestreId)
			
		[aprendizInstanceList: Aprendiz.findAllByCuatrimestre(cuatrimestre, [max: params.max, offset: params.offset]), 
			aprendizInstanceCount: Aprendiz.findAllByCuatrimestre(cuatrimestre).size(), 
			params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]]
    }

    def create() {
        respond new Aprendiz(params), params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
    }
	
    def save(Aprendiz aprendizInstance) {
        if (aprendizInstance == null) {
            notFound()
            return
        }

		def usuario = Usuario.get(aprendizInstance.usuario.id)
		def mediador = Mediador.findByUsuarioAndCurso(usuario, Curso.get(params.cursoId))
		
		if (mediador && mediadorService.existe(mediador)) {
			flash.message = "El miembro ${usuario} ya es mediador en el curso ${mediador.curso}. No puede ser aprendiz en el mismo"
			redirect action: "create", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}
		
		// Verifico si pertence al cuatrimestre acutal
		def cuatrimestre = Cuatrimestre.get(params.cuatrimestreId)
		def aprendiz = Aprendiz.findByUsuarioAndCuatrimestre(usuario, cuatrimestre)
		if (aprendiz) {
			flash.message = "${aprendiz} ya es aprendiz en el curso ${cuatrimestre.curso} durante el cuatrimestre ${cuatrimestre}"
			redirect action: "create", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}

		// TODO ¿verificar si todavia adeuda la materia?
		
		if (!aprendizService.guardar(aprendizInstance)) {
			respond aprendizInstance, view:'create', params:['cursoId': params.CursoId, 'cuatrimestreId': params.cuatrimestreId]
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'aprendizInstance.label', default: 'Aprendiz'), aprendizInstance.id])
		redirect action: "index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
    }

    def delete(Aprendiz aprendizInstance) {

		if (aprendizInstance == null) {
			notFound()
			return
		}

		aprendizService.eliminar(aprendizInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'Aprendiz.label', default: 'Aprendiz'), aprendizInstance.id])
		redirect action:"index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId], method:"GET"

	}

    protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'aprendizInstance.label', default: 'Aprendiz'), params.id])
		redirect action: "index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId], method: "GET"
    }
}
