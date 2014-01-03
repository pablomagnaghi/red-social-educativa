package com.fiuba

class Mediador extends Usuario {

	// todos los atributos del enunciado ya estan puestos
	
	// Profesor|JTP|AP|AS|Colaborador
	String jerarquia
	
	static hasMany = [cursos: Curso]

	static belongsTo = Curso
		
    static constraints = {
    }
	
}
