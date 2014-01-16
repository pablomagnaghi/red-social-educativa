package com.fiuba

// El foro general del curso

class ForoCurso extends Foro {

	static belongsTo = [curso: Curso]
	
    static constraints = {
    }
}
