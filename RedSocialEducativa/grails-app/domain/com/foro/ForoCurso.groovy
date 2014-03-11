package com.foro

import com.fiuba.*

class ForoCurso extends Foro {

	static belongsTo = [cuatrimestre: Cuatrimestre]
	
	static hasMany = [publicaciones: PublicacionCurso]
	
    static constraints = {
    }
}
