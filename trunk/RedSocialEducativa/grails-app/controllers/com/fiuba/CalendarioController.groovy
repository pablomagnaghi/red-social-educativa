package com.fiuba

import static org.springframework.http.HttpStatus.*
import org.springframework.security.access.annotation.Secured

@Secured("hasRole('ROL_ADMIN')")
class CalendarioController {

	//static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def calendarioService

	def index() {
		params.max = Utilidades.MAX_PARAMS
		respond Calendario.list(params), model:[calendarioInstanceCount: Calendario.count()]
	}

	def create() {
		respond new Calendario(anio: params.anio, inicioPrimerCuatrimestre: params.inicioPrimerCuatrimestre, 
			inicioSegundoCuatrimestre: params.inicioSegundoCuatrimestre)
	}

	def save(Calendario calendarioInstance) {

		if (calendarioInstance == null) {
			notFound()
			return
		}

		if (Calendario.findByAnio(params.fechaPrimerCuatrimestre.getYear() + Utilidades.ANIO_INICIAL)) {
			flash.message = "Ya existe el calendario para el año ${params.fechaPrimerCuatrimestre.getYear() + Utilidades.ANIO_INICIAL}"
			redirect action: "create"
			return
		}

		calendarioInstance.anio = params.fechaPrimerCuatrimestre.getYear() + Utilidades.ANIO_INICIAL

		if (!calendarioService.aniosIguales(calendarioInstance, params.fechaPrimerCuatrimestre, params.fechaSegundoCuatrimestre)) {
			flash.message = "Los años deben coincidir"
			redirect action: "create"
			return
		}

		if (!calendarioService.guardar(calendarioInstance)) {
			flash.message = "Problemas en la creacion del calendario"
			redirect action: "create"
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'calendarioInstance.label', default: 'Calendario'), calendarioInstance.id])
		redirect action: "index"
	}

	def edit(Calendario calendarioInstance) {

		if (calendarioInstance == null) {
			notFound()
			return
		}

		Date fechaPCDate = calendarioService.obtenerFecha(calendarioInstance.inicioPrimerCuatrimestre)
		Date fechaSCDate = calendarioService.obtenerFecha(calendarioInstance.inicioSegundoCuatrimestre)

		respond calendarioInstance, model: [fechaPrimerCuatrimestreDate: fechaPCDate, fechaSegundoCuatrimestreDate: fechaSCDate]
	}

	def update(Calendario calendarioInstance) {

		if (calendarioInstance == null) {
			notFound()
			return
		}
		
		if (!calendarioService.aniosIguales(calendarioInstance, params.fechaPrimerCuatrimestre, params.fechaSegundoCuatrimestre)) {
			flash.message = "Los años deben coincidir con ${calendarioInstance.anio}"
			redirect action: "edit", params:['id': calendarioInstance.id]
			return
		}

		if (!calendarioService.guardar(calendarioInstance)) {
			flash.message = "Problemas en la creacion del calendario"
			redirect action: "edit", params:['id': calendarioInstance.id]
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'Calendario.label', default: 'Calendario'), calendarioInstance.id])
		redirect action: "index"
	}

	def delete(Calendario calendarioInstance) {
		
		if (calendarioInstance == null) {
			notFound()
			return
		}

		calendarioService.eliminar(calendarioInstance)

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'Calendario.label', default: 'Calendario'), calendarioInstance.id])
		redirect action:"index", method:"GET"
	}
	
	protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'CalendarioInstance.label', default: 'Calendario'), params.id])
		redirect action: "index", method: "GET"
	}
}
