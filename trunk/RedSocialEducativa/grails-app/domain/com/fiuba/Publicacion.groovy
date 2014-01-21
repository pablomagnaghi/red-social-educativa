package com.fiuba

abstract class Publicacion {
	
	String titulo
	String contenido
	
	// responsable, fecha y hora se agregan automaticamente
	String responsable
	String fecha
	String hora

    static constraints = {
    }
	
	static mapping = {
		tablePerHierarchy false
	}
}
