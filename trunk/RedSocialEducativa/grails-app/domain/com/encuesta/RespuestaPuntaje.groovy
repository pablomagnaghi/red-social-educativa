package com.encuesta

class RespuestaPuntaje {
	
	Short puntaje
	
	static belongsTo = [pregunta: PreguntaPuntaje]

    static constraints = {
		puntaje min: (Short)1, max: (Short)10 
    }
}
