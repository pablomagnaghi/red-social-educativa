package com.encuesta

import com.cursado.*
import com.fiuba.*

class Encuesta {
	
	String nombre
	
	static belongsTo = [curso: Curso]
	
	static hasMany = [preguntas: Pregunta, preguntasPuntaje: PreguntaPuntaje, preguntasChoice: PreguntaChoice]

    static constraints = {
		nombre maxSize: Utilidades.MAX_TITULO
    }
}
