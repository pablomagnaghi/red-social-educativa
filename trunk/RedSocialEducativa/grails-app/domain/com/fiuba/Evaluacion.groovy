package com.fiuba

class Evaluacion {

	String nombre
	Integer fecha
	Integer horario
	String aula 
	boolean parcial //el tipo (parcial o integradora)
	boolean obligatoria //si es obligatoria para todo el curso o por inscripcion 
	boolean habilitada // si es por inscripcion, si esta habilitada o no la inscripcion de aprendices
	
	static belongsTo = [curso: Curso]
	
	static hasMany = [aprendices: EvaluacionAprendiz]
	
    static constraints = {
		aula nullable: true 
    }
	
	String toString() {
		"${nombre} - ${fecha}"
	}
}
