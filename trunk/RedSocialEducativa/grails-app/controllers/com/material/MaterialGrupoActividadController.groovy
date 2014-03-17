package com.material

import com.cursado.*
import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class MaterialGrupoActividadController {
	
	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def usuarioService
	def materialGrupoActividadService
	def grupoActividadService
	
	@Secured("hasAnyRole('ROL_MEDIADOR', 'ROL_APRENDIZ')")
	def descargar(Long id) {
		ArchivoGrupoActividad archivoInstance = ArchivoGrupoActividad.get(id)
		if (archivoInstance == null) {
			flash.message = "Archivo no encontrado"
			redirect controller:"actividad", action: "index", params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId,
				'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]
		} else {
			response.setContentType("APPLICATION/OCTET-STREAM")
			response.setHeader("Content-Disposition", "Attachment;Filename=\"${archivoInstance.filename}\"")
			def outputStream = response.getOutputStream()
			outputStream << archivoInstance.filedata
			outputStream.flush()
			outputStream.close()
		}
	}
	
	@Secured("hasAnyRole('ROL_MEDIADOR', 'ROL_APRENDIZ')")
	def show(MaterialGrupoActividad materialGrupoActividadInstance) {
		def aprendiz = Aprendiz.findByUsuarioAndCuatrimestre(usuarioService.usuarioActual(), Cuatrimestre.get(params.cuatrimestreId))
		respond materialGrupoActividadInstance, model: [aprendiz: aprendiz], params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId,
			'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def create() {
		respond new MaterialGrupoActividad(params), params:['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
			'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]
	}
	
	@Secured("hasRole('ROL_APRENDIZ')")
	def save(MaterialGrupoActividad materialGrupoActividadInstance) {
		if (materialGrupoActividadInstance == null) {
			notFound()
			return
		}
		if (materialGrupoActividadService.existe(materialGrupoActividadInstance, params.grupoActividadId.toLong())) {
			flash.message = "Ya existe el material ${materialGrupoActividadInstance.titulo} en el grupo ${GrupoActividad.get(params.grupoActividadId)}"
			redirect action: "create", params: ['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
				'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]
			return
		}
		def file = request.getFile('archivoSubido')
		if(file.empty) {
			flash.message = "El archivo esta vacío"
			render view:'create', params:['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]
			return
		}
		def archivoInstance = new ArchivoGrupoActividad()
		archivoInstance.filename = file.originalFilename
		archivoInstance.filedata = file.getBytes()
		materialGrupoActividadInstance.archivo = archivoInstance
		if (!materialGrupoActividadService.guardar(materialGrupoActividadInstance)) {
			render view:'create', model: [materialGrupoActividadInstance: materialGrupoActividadInstance], params: ['cursoId': params.cursoId, 
				'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]
			return
		}
		flash.message = "Material ${materialGrupoActividadInstance} creado"
		redirect controller: "grupoActividad", action:"grupoAprendiz", params:['id': params.grupoActividadId, 
			'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]	
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def edit(MaterialGrupoActividad materialGrupoActividadInstance) {
		def titulo = materialGrupoActividadInstance.titulo
		respond materialGrupoActividadInstance,  model:[titulo: titulo], params: ['cursoId': params.cursoId, 
			'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def update(MaterialGrupoActividad materialGrupoActividadInstance) {
		if (materialGrupoActividadInstance == null) {
			notFound()
			return
		}
		if (materialGrupoActividadService.existe(materialGrupoActividadInstance, params.grupoActividadId.toLong())) {
			flash.message = "Ya existe el material ${materialGrupoActividadInstance.titulo} en el grupo ${GrupoActividad.get(params.grupoActividadId)}"
			materialGrupoActividadInstance.titulo = params.tituloAnterior
			redirect action: "edit", params: params
			return
		}
		def file = request.getFile('archivoSubido')
		if(!file.empty) {
			if (!materialGrupoActividadInstance?.archivo) {
				materialGrupoActividadInstance.archivo = new ArchivoGrupoActividad()
			}
			materialGrupoActividadInstance.archivo.filename = file.originalFilename
			materialGrupoActividadInstance.archivo.filedata = file.getBytes()
		}
		if (!materialGrupoActividadService.guardar(materialGrupoActividadInstance)) {
			render view:'edit', model: [materialGrupoActividadInstance: materialGrupoActividadInstance], params: ['cursoId': params.cursoId, 
				'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]
			return
		}
		flash.message = "Material ${materialGrupoActividadInstance} actualizado"
		redirect controller: "grupoActividad", action:"grupoAprendiz", params:['id': params.grupoActividadId, 
			'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]	
	}

	@Secured("hasRole('ROL_APRENDIZ')")
	def delete(MaterialGrupoActividad materialGrupoActividadInstance) {
		if (materialGrupoActividadInstance == null) {
			notFound()
			return
		}
		materialGrupoActividadService.eliminar(materialGrupoActividadInstance)
		flash.message = "Material ${materialGrupoActividadInstance} eliminado"
		redirect controller: "grupoActividad", action:"grupoAprendiz", params:['id': params.grupoActividadId, 
			'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId], method: "GET"
	}

	protected void notFound() {
		flash.message = "No se encuentra ese material"
		redirect controller: "grupoActividad", action:"grupoAprendiz", params:['id': params.grupoActividadId, 
			'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId], method: "GET"
	}
}
