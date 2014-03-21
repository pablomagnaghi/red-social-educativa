package com.encuesta

import com.fiuba.*

class EncuestaAprendiz {
	
	String fecha = new Date().format(Utilidades.FORMATO_FECHA)
	
	static belongsTo = [aprendiz: Aprendiz, encuesta: Encuesta]

    static constraints = {
    }
}
