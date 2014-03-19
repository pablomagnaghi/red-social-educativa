package com.encuesta

class PreguntaDesarrollo extends Pregunta {

	static belongsTo = [encuesta: Encuesta]
	
	static hasMany = [respuestas: RespuestaDesarrollo]
	
    static constraints = {
    }
}
