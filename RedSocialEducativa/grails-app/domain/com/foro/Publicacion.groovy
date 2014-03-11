package com.foro

import com.fiuba.*
abstract class Publicacion {

	String titulo
	String contenido
	String responsable
	String dni
	String fecha = new Date().format(Utilidades.FORMATO_FECHA)
	String hora = new Date().getTimeString()

	static constraints = {
		titulo maxSize: Utilidades.MAX_TITULO
		contenido maxSize: Utilidades.MAX_SIZE
	}

	static mapping = {
		tablePerHierarchy false
		contenido type: 'text'
	}

	String toString() {
		"${titulo}"
	}
}
