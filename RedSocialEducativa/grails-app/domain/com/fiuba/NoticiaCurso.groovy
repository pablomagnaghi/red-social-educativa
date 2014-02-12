package com.fiuba

class NoticiaCurso extends Noticia {

	static belongsTo = [mediador: Mediador, cuatrimestre: Cuatrimestre]
	
    static constraints = {
    }
	
	String toString() {
		"Noticia de la red hecha por ${mediador} del curso ${cuatrimestre}"
	}
}
