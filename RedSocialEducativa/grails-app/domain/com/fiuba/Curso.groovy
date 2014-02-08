package com.fiuba

class Curso {
	
	// todos los atributos del enunciado ya estan puestos
	
	// Numero de curso de la materia
	Short nroRelativo 
	// Cuatrimestre en que se da el curso: 1ero, 2do o ambos
	String cuatDict
	
	// Curso tiene como entidad fuerte a Materia. En tabla curso se pone el id de materia
	static belongsTo = [materia: Materia]
	
	static hasMany = [cuatrimestres: Cuatrimestre, evaluaciones: Evaluacion, materiales: MaterialCurso,
		mediadores: Mediador, temas: Tema]
	
	// Atributos extras
	String nombre
	
	String toString() {
		"${nroRelativo} - ${nombre}"
	}
	
    static constraints = {
		//foro nullable: true
    }
	
}
