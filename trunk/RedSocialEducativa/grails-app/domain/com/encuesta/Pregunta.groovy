package com.encuesta

import com.fiuba.*

class Pregunta {
	
	String pregunta

    static constraints = {
		pregunta maxSize: 256
    }
	
	static mapping = {
		tablePerHierarchy false
		pregunta type: 'text'
	}
	
	String toString() {
		"${pregunta}"
	}
}
