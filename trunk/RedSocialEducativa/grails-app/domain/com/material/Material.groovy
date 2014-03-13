package com.material

import com.fiuba.*

class Material {

	String titulo
	String autor
	String descripcion 
	String fecha = new Date().format(Utilidades.FORMATO_FECHA)
	String responsable
	
	CategoriaMaterial categoria
	
	static hasOne = [archivo: Archivo]

    static constraints = {
		titulo maxSize: Utilidades.MAX_TITULO
		autor nullable: true
		descripcion nullable: true, maxSize: Utilidades.MAX_SIZE
		archivo nullable: true
    }
	
	static mapping = {
		tablePerHierarchy false
	}
	
	String toString() {
		"${titulo}"
	}
}
