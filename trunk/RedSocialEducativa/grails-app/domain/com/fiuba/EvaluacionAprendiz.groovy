package com.fiuba

class EvaluacionAprendiz {

	Integer nota
	
	static belongsTo = [aprendiz: Aprendiz, evaluacion: Evaluacion]
	
    static constraints = {
		nota nulable: true, min:0
    }
}
