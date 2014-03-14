package com.material

import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

import org.springframework.web.multipart.commons.CommonsMultipartFile

class MaterialCursoController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def usuarioService
	def materialCursoService

	@Secured('isFullyAuthenticated()')
	def descargar(Long id) {
		Archivo archivoInstance = Archivo.get(id)
		if (archivoInstance == null) {
			flash.message = "Archivo no encontrado"
			redirect action: "index", params:['cursoId': params.cursoId]
		} else {
			response.setContentType("APPLICATION/OCTET-STREAM")
			response.setHeader("Content-Disposition", "Attachment;Filename=\"${archivoInstance.filename}\"")
			def outputStream = response.getOutputStream()
			outputStream << archivoInstance.filedata
			outputStream.flush()
			outputStream.close()
		}
	}
	
	@Secured('isFullyAuthenticated()')
	def index() {
		params.max = Utilidades.MAX_PARAMS
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		[materialCursoInstanceList: MaterialCurso.findAllByCurso(Curso.get(params.cursoId)), mediador: mediador, params: ['cursoId': params.cursoId]]
	}

	@Secured('isFullyAuthenticated()')
	def show(MaterialCurso materialCursoInstance) {
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		[materialCursoInstance: materialCursoInstance, mediador: mediador, params: ['cursoId': params.cursoId]]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {
		respond new MaterialCurso(params), params: ['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def save(MaterialCurso materialCursoInstance) {
		if (materialCursoInstance == null) {
			notFound()
			return
		}
		if (materialCursoService.existe(materialCursoInstance, params.cursoId.toLong())) {
			flash.message = "Ya existe el material ${materialCursoInstance.titulo} en el curso ${Curso.get(params.cursoId)}"
			redirect action: "create", params:['cursoId': params.cursoId]
			return
		}
		def file = request.getFile('archivoSubido')
		if(file.empty) {
			flash.message = "El archivo esta vacío"
			render view:'create', params:['cursoId': params.cursoId]
			return
		}
		def archivoInstance = new ArchivoCurso()
		archivoInstance.filename = file.originalFilename
		archivoInstance.filedata = file.getBytes()
		materialCursoInstance.archivo = archivoInstance
		if (!materialCursoService.guardar(materialCursoInstance)) {
			render view:'create', model: [materialCursoInstance: materialCursoInstance], params:['cursoId': params.cursoId]
			return
		}
		flash.message = "Material ${materialCursoInstance} creado"
		redirect action: "index", params:['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def edit(MaterialCurso materialCursoInstance) {
		def titulo = materialCursoInstance.titulo
		respond materialCursoInstance, model: [titulo: titulo],  params:['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def update(MaterialCurso materialCursoInstance) {
		if (materialCursoInstance == null) {
			notFound()
			return
		}
		if (materialCursoService.existe(materialCursoInstance, params.cursoId.toLong())) {
			flash.message = "Ya existe el material ${materialCursoInstance.titulo} en el curso ${Curso.get(params.cursoId)}"
			materialCursoInstance.titulo = params.tituloAnterior
			redirect action: "edit", params: params
			return
		}
		def file = request.getFile('archivoSubido')
		if(!file.empty) {
			materialCursoInstance.archivo.filename = file.originalFilename
			materialCursoInstance.archivo.filedata = file.getBytes()
		}
		if (!materialCursoService.guardar(materialCursoInstance)) {
			render view:'edit', model: [materialCursoInstance: materialCursoInstance], params: params
			return
		}
		flash.message = "Material ${materialCursoInstance} actualizado"
		redirect action: "index", params:['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(MaterialCurso materialCursoInstance) {
		if (materialCursoInstance == null) {
			notFound()
			return
		}
		materialCursoService.eliminar(materialCursoInstance)
		flash.message = "Material ${materialCursoInstance} eliminado"
		redirect action:"index", params:['cursoId': params.cursoId], method:"GET"
	}

	protected void notFound() {
		flash.message = "No se encuentra ese material"
		redirect action: "index", params:['cursoId': params.cursoId], method: "GET"
	}
}
