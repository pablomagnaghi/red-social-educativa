package com.fiuba

class TemaActividad {

	static belongsTo = [tema: Tema, actividad: Actividad]
	
	String toString() {
		"${tema}"
	}
	
    static constraints = {
    }
}
