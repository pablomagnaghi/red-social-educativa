package com.fiuba

abstract class Publicacion {

	String titulo
	String contenido
	String responsable
	String dni
	String fecha
	String hora

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
