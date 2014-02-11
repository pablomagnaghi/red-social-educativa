package com.fiuba

class Curso {

	Short nroRelativo
	String nombre
	String cuatDict

	static belongsTo = [materia: Materia]

	static hasMany = [cuatrimestres: Cuatrimestre, evaluaciones: Evaluacion, materiales: MaterialCurso,
		mediadores: Mediador, temas: Tema]

	static constraints = {
		nroRelativo min:(Short)1
		cuatDict inList:["1", "2", "1|2"]
	}

	String toString() {
		"${nroRelativo} - ${nombre}"
	}
}
