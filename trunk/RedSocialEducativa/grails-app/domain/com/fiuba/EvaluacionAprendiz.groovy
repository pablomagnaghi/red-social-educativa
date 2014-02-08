package com.fiuba

class EvaluacionAprendiz {

	static belongsTo = [aprendiz: Aprendiz, evaluacion: Evaluacion]
	
    static constraints = {
    }
}
