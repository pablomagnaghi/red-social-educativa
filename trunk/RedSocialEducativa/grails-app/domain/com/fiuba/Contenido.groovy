package com.fiuba

class Contenido {

	String titulo
	
	static belongsTo = [tema: Tema]
	
	static hasMany = [materiales: MaterialContenido]
	
    static constraints = {
    }
	
	String toString() {
		"${titulo}"
	}
}
