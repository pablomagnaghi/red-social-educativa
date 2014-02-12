package com.fiuba

class Material {

	String titulo
	String autor 
	String descripcion 
	String fecha
	String responsable
	
	CategoriaMaterial categoria

    static constraints = {
		autor nullable: true
		descripcion nullable: true
    }
	
	static mapping = {
		tablePerHierarchy false
	}
	
	String toString() {
		"${titulo}"
	}
}
