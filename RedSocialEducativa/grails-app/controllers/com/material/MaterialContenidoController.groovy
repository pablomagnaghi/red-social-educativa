package com.material

import com.fiuba.*
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

import org.springframework.security.access.annotation.Secured


class MaterialContenidoController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def usuarioService
	def materialContenidoService

	@Secured("hasAnyRole('ROL_MEDIADOR', 'ROL_APRENDIZ')")
	def descargar(Long id) {
		Archivo archivoInstance = Archivo.get(id)
		if (archivoInstance == null) {
			flash.message = "Archivo no encontrado"
			redirect controller:"contenido", action: "index", params:['cursoId': params.cursoId, 'temaId': params.temaId]
		} else {
			response.setContentType("APPLICATION/OCTET-STREAM")
			response.setHeader("Content-Disposition", "Attachment;Filename=\"${archivoInstance.filename}\"")
			def outputStream = response.getOutputStream()
			outputStream << archivoInstance.filedata
			outputStream.flush()
			outputStream.close()
		}
	}
	
	@Secured("hasRole('ROL_MEDIADOR')")
	def show(MaterialContenido materialContenidoInstance) {
		def mediador = Mediador.findByUsuarioAndCurso(usuarioService.usuarioActual(), Curso.get(params.cursoId))
		respond materialContenidoInstance, model: [mediador: mediador], params: ['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def create() {
		respond new MaterialContenido(params), params: ['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def save(MaterialContenido materialContenidoInstance) {
		if (materialContenidoInstance == null) {
			notFound()
			return
		}
		if (materialContenidoService.existe(materialContenidoInstance, params.contenidoId.toLong())) {
			flash.message = "Ya existe el material ${materialContenidoInstance.titulo} en el contenido ${Contenido.get(params.contenidoId)}"
			redirect action: "create", params: ['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]
			return
		}
		if (!materialContenidoInstance.validate()) {
			render view:'create', model: [materialContenidoInstance: materialContenidoInstance], params: ['cursoId': params.cursoId, 
				'temaId': params.temaId, 'contenidoId': params.contenidoId]
			return
		}
		def file = request.getFile('archivoSubido')
		if(file.empty) {
			flash.message = "El archivo esta vacío"
			render view:'create', params:['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]
			return
		}
		def archivoInstance = new ArchivoContenido()
		archivoInstance.filename = file.originalFilename
		archivoInstance.filedata = file.getBytes()
		materialContenidoInstance.archivo = archivoInstance
		if (!materialContenidoService.guardar(materialContenidoInstance)) {
			redirect action: "create", params: ['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]
			flash.message = "Problemas al guardar el archivor ${archivoInstance.filename} en el material ${materialConteidoInstance}"
			return
		}
		flash.message = "Material ${materialContenidoInstance.titulo} del contenido ${materialContenidoInstance.contenido} creado"
		redirect controller:"contenido", action:"index", params:['cursoId': params.cursoId, 'temaId': params.temaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def edit(MaterialContenido materialContenidoInstance) {
		def titulo = materialContenidoInstance.titulo
		respond materialContenidoInstance, model: [titulo: titulo], params: ['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def update(MaterialContenido materialContenidoInstance) {
		if (materialContenidoInstance == null) {
			notFound()
			return
		}
		if (materialContenidoService.existe(materialContenidoInstance, params.contenidoId.toLong())) {
			flash.message = "Ya existe el material ${materialContenidoInstance.titulo} een el contenido ${Contenido.get(params.contenidoId)}"
			materialContenidoInstance.titulo = params.tituloAnterior
			redirect action: "edit", params: params
			return
		}
		if (!materialContenidoService.guardar(materialContenidoInstance)) {
			render view:'edit', model: [materialContenidoInstance: materialContenidoInstance], params: ['cursoId': params.cursoId, 
				'temaId': params.temaId, 'contenidoId': params.contenidoId]
			return
		}
		flash.message = "Material ${materialContenidoInstance.titulo} del contenido ${materialContenidoInstance.contenido} actualizado"
		redirect controller:"contenido", action:"index", params:['cursoId': params.cursoId, 'temaId': params.temaId]
	}

	@Secured("hasRole('ROL_MEDIADOR')")
	def delete(MaterialContenido materialContenidoInstance) {
		if (materialContenidoInstance == null) {
			notFound()
			return
		}
		materialContenidoService.eliminar(materialContenidoInstance)
		flash.message = "Material ${materialContenidoInstance.titulo} del contenido ${materialContenidoInstance.contenido} eliminado"
		redirect controller:"contenido", action:"index", params:['cursoId': params.cursoId, 'temaId': params.temaId], method:"GET"
	}

	protected void notFound() {
		flash.message = "No se encuentra ese material"
		redirect controller:"contenido", action:"index", params:['cursoId': params.cursoId, 'temaId': params.temaId], method: "GET"
	}
}
