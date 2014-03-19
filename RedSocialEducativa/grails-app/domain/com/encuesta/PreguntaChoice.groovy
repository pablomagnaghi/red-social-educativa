package com.encuesta

import com.fiuba.*

class PreguntaChoice extends Pregunta {

	Short opciones
	ArrayList<String> preguntas = new ArrayList<String>()
	
    static constraints = {
		opciones min: (Short)2, max: (Short)10
    }
}
