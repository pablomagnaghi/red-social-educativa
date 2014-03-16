package com.cursado

import com.fiuba.*

class EvaluacionAprendiz {

	Float nota
	Boolean calificado = false
	
	static belongsTo = [aprendiz: Aprendiz, evaluacion: Evaluacion]
	
    static constraints = {
		nota nullable: true, min: 0.00F, max: 10.0F, scale: 2
    }
}
