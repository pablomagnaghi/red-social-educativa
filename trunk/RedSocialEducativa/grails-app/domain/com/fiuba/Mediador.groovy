package com.fiuba

class Mediador extends Usuario {

	// todos los atributos del enunciado ya estan puestos
	
	// Profesor|JTP|AP|AS|Colaborador
	String jerarquia
	
	static belongsTo = Curso
	
	static hasMany = [cursos:Curso]
		
    static constraints = {
    }
	
}
