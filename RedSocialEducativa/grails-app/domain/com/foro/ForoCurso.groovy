package com.foro

import com.cursado.*

class ForoCurso extends Foro {

	static belongsTo = [cuatrimestre: Cuatrimestre]
	
	static hasMany = [publicaciones: PublicacionCurso]
	
    static constraints = {
    }
}
