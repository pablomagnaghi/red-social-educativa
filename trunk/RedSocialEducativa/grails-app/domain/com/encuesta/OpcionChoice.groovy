package com.encuesta

import com.fiuba.*

class OpcionChoice {

	String opcion
	Integer cantRespuestas = 0
	
	static belongsTo = [pregunta: PreguntaChoice]
	
    static constraints = {
		opcion maxSize: Utilidades.MAX_TITULO
    }
	
	String toString() {
		"${opcion}"
	}
}
