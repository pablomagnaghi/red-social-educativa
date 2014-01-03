package com.fiuba

class Curso {
	
	// todos los atributos del enunciado ya estan puestos
	
	// Numero de curso de la materia
	Short nroRelativo 
	// Cuatrimestre en que se da el curso: 1ero, 2do o ambos
	String cuatDict
	
	static belongsTo = Materia
	
	static hasMany = [mediadores: Mediador, cuatrimestres: Cuatrimestre]
	
	String toString() {
		"${nroRelativo}- ${cuatDict}"
	}
	
    static constraints = {
    }
}
