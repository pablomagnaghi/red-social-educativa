package com.encuesta

import com.fiuba.*

class EncuestaAprendiz {
	
	static belongsTo = [aprendiz: Aprendiz, encuesta: Encuesta]

    static constraints = {
    }
}
