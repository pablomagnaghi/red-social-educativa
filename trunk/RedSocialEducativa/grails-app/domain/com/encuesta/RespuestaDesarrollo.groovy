package com.encuesta

class RespuestaDesarrollo {
	
	String respuesta
	
	static belongsTo = [pregunta: PreguntaDesarrollo]

	static mapping = {
		respuesta type: 'text'
	}
	
    static constraints = {
    }
}
