package com.fiuba

import java.text.SimpleDateFormat;
import grails.transaction.Transactional

@Transactional
class CalendarioService {

	def aniosIguales(Calendario calendario,  Date fechaPrimerCuatrimestre, Date fechaSegundoCuatrimestre) {

		def anioFPC = fechaPrimerCuatrimestre.getYear() + Utilidades.ANIO_INICIAL
		def anioFSC = fechaSegundoCuatrimestre.getYear() + Utilidades.ANIO_INICIAL

		if ((anioFPC == anioFSC) && (anioFPC == calendario.anio)) {
			calendario.inicioPrimerCuatrimestre = fechaPrimerCuatrimestre.format(Utilidades.FORMATO_FECHA).toInteger()
			calendario.inicioSegundoCuatrimestre = fechaSegundoCuatrimestre.format(Utilidades.FORMATO_FECHA).toInteger()
			return true
		}

		return false
	}

	def obtenerFecha(Integer fechaCalendario) {

		String fecha = fechaCalendario.toString()
		def formato = new SimpleDateFormat(Utilidades.FORMATO_FECHA);
		Date fechaDate = (Date) formato.parse(fecha);

		return fechaDate
	}

	def guardar(Calendario calendario) {

		if (calendario.save(flush: true)) {
			return calendario
		}

		return null
	}

	def eliminar(Calendario calendario) {
		calendario.delete flush:true
	}
}
