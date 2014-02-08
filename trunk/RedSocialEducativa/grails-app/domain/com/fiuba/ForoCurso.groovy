package com.fiuba

// El foro general del curso

class ForoCurso extends Foro {

	static belongsTo = [cuatrimestre: Cuatrimestre]
	
	static hasMany = [publicaciones: PublicacionCurso]
	
    static constraints = {
    }
}
