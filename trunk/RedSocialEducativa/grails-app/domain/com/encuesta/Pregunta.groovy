package com.encuesta

import com.fiuba.*

class Pregunta {
	
	String pregunta

    static constraints = {
		pregunta maxSize: Utilidades.MAX_TITULO
    }
	
	String toString() {
		"${pregunta}"
	}
}
