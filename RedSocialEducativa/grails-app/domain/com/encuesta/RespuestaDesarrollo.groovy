package com.encuesta

import com.fiuba.*

class RespuestaDesarrollo {
	
	String respuesta
	
	static belongsTo = [pregunta: PreguntaDesarrollo]

	static constraints = {
		respuesta maxSize: Utilidades.MAX_SIZE
	}
	
	static mapping = {
		respuesta type: 'text'
	}
}
