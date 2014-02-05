package com.fiuba

class EvaluacionAprendiz {

	static belongsTo = [evaluacion: Evaluacion, aprendiz: Aprendiz]
	
    static constraints = {
    }
}
