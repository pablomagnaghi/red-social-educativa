package com.fiuba

abstract class Publicacion {
	
	String titulo
	String contenido
	
	// responsable, fecha y hora se agregan automaticamente
	String responsable
	String dni
	String fecha
	String hora

	String toString() {
		"${titulo}"
	}
	
    static constraints = {
		dni nullable: true
    }
	
	static mapping = {
		tablePerHierarchy false
	}
}
