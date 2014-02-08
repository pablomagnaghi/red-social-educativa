package com.fiuba

class TemaActividad {

	static belongsTo = [actividad: Actividad, tema: Tema]
	
	String toString() {
		"${tema}"
	}
	
    static constraints = {
    }
}
