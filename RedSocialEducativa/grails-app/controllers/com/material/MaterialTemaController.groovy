package com.material

import com.cursado.*
import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

class MaterialTemaController {

	// static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def usuarioService
	def materialTemaService

	@Secured('isFullyAuthenticated()')
	def descargar(Long id) {
		ArchivoTema archivoInstance = ArchivoTema.get(id)
		if (archivoInstance == null) {
			flash.message = "Archivo no encontrado"
			redirect controller:"tema", action: "index", params:['cursoId': params.cursoId]
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
	def show(MaterialTema materialTemaInstance) {
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		respond materialTemaInstance, model: [mediador: mediador], params: ['cursoId': params.cursoId, 'temaId': params.temaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {
		respond new MaterialTema(params), params: ['cursoId': params.cursoId, 'temaId': params.temaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def save(MaterialTema materialTemaInstance) {
		if (materialTemaInstance == null) {
			notFound()
			return
		}	
		if (materialTemaService.existe(materialTemaInstance, params.temaId.toLong())) {
			flash.message = "Ya existe el material ${materialTemaInstance.titulo} en el tema ${Tema.get(params.temaId)}"
			redirect action: "create", params: ['cursoId': params.cursoId, 'temaId': params.temaId]
			return
		}
		def file = request.getFile('archivoSubido')
		if(file.empty) {
			flash.message = "El archivo esta vacío"
			render view:'create', params:['cursoId': params.cursoId, 'temaId': params.temaId]
			return
		}
		def archivoInstance = new ArchivoTema()
		archivoInstance.filename = file.originalFilename
		archivoInstance.filedata = file.getBytes()
		materialTemaInstance.archivo = archivoInstance
		if (!materialTemaService.guardar(materialTemaInstance)) {
			render view:'create', model: [materialTemaInstance: materialTemaInstance], params: ['cursoId': params.cursoId, 'temaId': params.temaId]
			return
		}
		flash.message = "Material ${materialTemaInstance.titulo} del tema ${materialTemaInstance.tema} creado"
		redirect controller:"tema", action:"index", params:['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def edit(MaterialTema materialTemaInstance) {
		def titulo = materialTemaInstance.titulo
		respond materialTemaInstance, model: [titulo: titulo], params: ['cursoId': params.cursoId, 'temaId': params.temaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def update(MaterialTema materialTemaInstance) {
		if (materialTemaInstance == null) {
			notFound()
			return
		}
		if (materialTemaService.existe(materialTemaInstance, params.temaId.toLong())) {
			flash.message = "Ya existe el material ${materialTemaInstance.titulo} en el tema ${Tema.get(params.temaId)}"
			materialTemaInstance.titulo = params.tituloAnterior
			redirect action: "edit", params: params
			return
		}
		def file = request.getFile('archivoSubido')
		if(!file.empty) {
			if (!materialTemaInstance?.archivo) {
				materialTemaInstance.archivo = new ArchivoTema()
			}
			materialTemaInstance.archivo.filename = file.originalFilename
			materialTemaInstance.archivo.filedata = file.getBytes()
		}
		if (!materialTemaService.guardar(materialTemaInstance)) {
			render view:'edit', model: [materialTemaInstance: materialTemaInstance], params: ['cursoId': params.cursoId, 'temaId': params.temaId]
			return
		}
		flash.message = "Material ${materialTemaInstance.titulo} del tema ${materialTemaInstance.tema} actualizado"
		redirect controller:"tema", action:"index", params:['cursoId': params.cursoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(MaterialTema materialTemaInstance) {
		if (materialTemaInstance == null) {
			notFound()
			return
		}
		materialTemaService.eliminar(materialTemaInstance)
		flash.message = "Material ${materialTemaInstance.titulo} del tema ${materialTemaInstance.tema} eliminado"
		redirect controller:"tema", action:"index", params:['cursoId': params.cursoId], method:"GET"
	}

	protected void notFound() {
		flash.message = "No se encuentra ese material"
		redirect controller:"tema", action:"index", params:['cursoId': params.cursoId], method:"GET"
	}
}
