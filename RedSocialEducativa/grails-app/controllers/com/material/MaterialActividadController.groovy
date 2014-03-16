package com.material

import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class MaterialActividadController {

	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def usuarioService
	def materialActividadService

	@Secured("hasAnyRole('ROL_MEDIADOR', 'ROL_APRENDIZ')")
	def descargar(Long id) {
		Archivo archivoInstance = Archivo.get(id)
		if (archivoInstance == null) {
			flash.message = "Archivo no encontrado"
			redirect controller:"actividad", action: "index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
		} else {
			response.setContentType("APPLICATION/OCTET-STREAM")
			response.setHeader("Content-Disposition", "Attachment;Filename=\"${archivoInstance.filename}\"")
			def outputStream = response.getOutputStream()
			outputStream << archivoInstance.filedata
			outputStream.flush()
			outputStream.close()
		}
	}
	/*
	@Secured("hasRole('ROL_APRENDIZ')")
	def materialAprendiz() {
		params.max = Utilidades.MAX_PARAMS
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		[materiales: MaterialActividad.findAllByActividad(Actividad.get(params.actividadId)), mediador: mediador,
			params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]]
	}*/

	@Secured("hasAnyRole('ROL_MEDIADOR', 'ROL_APRENDIZ')")
	def show(MaterialActividad materialActividadInstance) {
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		respond materialActividadInstance, model: [mediador: mediador, params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId,
			'actividadId': params.actividadId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {
		respond new MaterialActividad(params), params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def save(MaterialActividad materialActividadInstance) {
		if (materialActividadInstance == null) {
			notFound()
			return
		}
		if (materialActividadService.existe(materialActividadInstance, params.actividadId.toLong())) {
			flash.message = "Ya existe el material ${materialActividadInstance.titulo} en la actividad ${Actividad.get(params.actividadId)}"
			redirect action: "create", params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
			return
		}
		def file = request.getFile('archivoSubido')
		if(file.empty) {
			flash.message = "El archivo esta vacío"
			render view:'create', params:['cursoId': params.cursoId, 'temaId': params.temaId]
			return
		}
		def archivoInstance = new ArchivoActividad()
		archivoInstance.filename = file.originalFilename
		archivoInstance.filedata = file.getBytes()
		materialActividadInstance.archivo = archivoInstance
		if (!materialActividadService.guardar(materialActividadInstance)) {
			render view:'create', model: [materialActividadInstance: materialActividadInstance], params: ['cursoId': params.cursoId, 
				'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
			return
		}
		flash.message = "Material ${materialActividadInstance} creado"
		redirect controller:"actividad", action:"index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def edit(MaterialActividad materialActividadInstance) {
		//def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		def titulo = materialActividadInstance.titulo
		respond materialActividadInstance, model: [titulo: titulo, params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
			'actividadId': params.actividadId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def update(MaterialActividad materialActividadInstance) {
		if (materialActividadInstance == null) {
			notFound()
			return
		}
		if (materialActividadService.existe(materialActividadInstance, params.actividadId.toLong())) {
			flash.message = "Ya existe el material ${materialActividadInstance.titulo} en la actividad ${Actividad.get(params.actividadId)}"
			materialActividadInstance.titulo = params.tituloAnterior
			redirect action: "edit", params: params
			return
		}
		def file = request.getFile('archivoSubido')
		if(!file.empty) {
			if (!materialActividadInstance?.archivo) {
				materialActividadInstance.archivo = new ArchivoActividad()
			}
			materialActividadInstance.archivo.filename = file.originalFilename
			materialActividadInstance.archivo.filedata = file.getBytes()
		}
		if (!materialActividadService.guardar(materialActividadInstance)) {
			render view:'edit', model: [materialActividadInstance: materialActividadInstance], params: ['cursoId': params.cursoId, 
				'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]
			return
		}
		flash.message = "Material ${materialActividadInstance} actualizado"
		redirect controller:"actividad", action:"index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(MaterialActividad materialActividadInstance) {
		if (materialActividadInstance == null) {
			notFound()
			return
		}
		materialActividadService.eliminar(materialActividadInstance)
		flash.message = "Material ${materialActividadInstance} eliminado"
		redirect controller:"actividad", action:"index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId], method:"GET"
	}

	protected void notFound() {
		flash.message = "No se encuentra ese material"
		redirect controller: "actividad", action:"index", params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId], method: "GET"
	}
}
