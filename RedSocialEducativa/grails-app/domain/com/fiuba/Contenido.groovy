package com.fiuba

class Contenido {

	String titulo
	
	static belongsTo = [tema: Tema]
	
	static hasMany = [materiales: MaterialContenido]
	
	String toString() {
		"${titulo}"
	}
	
    static constraints = {
    }
	
}
