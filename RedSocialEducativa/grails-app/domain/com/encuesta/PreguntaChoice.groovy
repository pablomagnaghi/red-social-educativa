package com.encuesta

import com.fiuba.*

class PreguntaChoice extends Pregunta {

	static belongsTo = [encuesta: Encuesta]
	
	static hasMany = [opciones: OpcionChoice]
	
    static constraints = {

    }
}
