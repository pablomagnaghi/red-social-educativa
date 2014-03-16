package com.cursado

import com.material.*

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
