package com.encuesta

import com.fiuba.*

class OpcionChoice {

	String opcion
	
	static belongsTo = [pregunta: PreguntaChoice]
	
    static constraints = {
		opcion maxSize: Utilidades.MAX_TITULO
    }
	
	String toString() {
		"${opcion}"
	}
}
