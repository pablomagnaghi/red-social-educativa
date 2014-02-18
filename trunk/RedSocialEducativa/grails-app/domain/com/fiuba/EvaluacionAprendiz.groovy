package com.fiuba

class EvaluacionAprendiz {

	Integer nota
	
	static belongsTo = [aprendiz: Aprendiz, evaluacion: Evaluacion]
	
    static constraints = {
		nota nullable: true, min:0
    }
}
