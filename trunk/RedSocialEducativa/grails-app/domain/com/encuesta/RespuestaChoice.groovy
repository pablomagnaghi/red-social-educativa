package com.encuesta

import com.fiuba.*

class RespuestaChoice {

	Short respuesta
	
	static belongsTo = [pregunta: PreguntaChoice]
	
    static constraints = {
		respuesta min: (Short)1, max: (Short)10
    }
}
