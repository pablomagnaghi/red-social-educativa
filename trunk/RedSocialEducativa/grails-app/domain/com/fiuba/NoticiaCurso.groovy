package com.fiuba

class NoticiaCurso extends Noticia {

	static belongsTo = [mediador: Mediador, curso: Curso]

	String toString() {
		"Noticia de la red hecha por ${mediador} del curso ${curso}"
	}
	
    static constraints = {
    }
}
