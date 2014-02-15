package com.fiuba

class TemaActividad {

	static belongsTo = [actividad: Actividad, tema: Tema]
	
    static constraints = {
    }
}
