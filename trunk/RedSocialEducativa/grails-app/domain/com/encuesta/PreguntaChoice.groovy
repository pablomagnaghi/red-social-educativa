package com.encuesta

import com.fiuba.*

class PreguntaChoice extends Pregunta {

	static belongsTo = [encuesta: Encuesta]
	
	static hasMany = [respuestas: RespuestaChoice, opciones: OpcionChoice]
	
    static constraints = {

    }
}
