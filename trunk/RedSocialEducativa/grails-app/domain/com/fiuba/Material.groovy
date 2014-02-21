package com.fiuba

class Material {

	String titulo
	String autor 
	String descripcion 
	String fecha = new Date().format(Utilidades.FORMATO_FECHA)
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
