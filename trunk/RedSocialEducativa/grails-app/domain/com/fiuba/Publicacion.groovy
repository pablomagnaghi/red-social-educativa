package com.fiuba

abstract class Publicacion {

	String titulo
	String contenido
	String responsable
	String dni
	String fecha = new Date().format(Utilidades.FORMATO_FECHA)
	String hora = new Date().getTimeString()

	static constraints = {
		dni nullable: true
	}

	static mapping = {
		tablePerHierarchy false
	}

	String toString() {
		"${titulo}"
	}
}
