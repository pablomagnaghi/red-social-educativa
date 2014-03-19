package com.encuesta

class PreguntaPuntaje extends Pregunta {

	static belongsTo = [encuesta: Encuesta]

	static hasMany = [respuestas: RespuestaPuntaje]
		
    static constraints = {
    }
}
