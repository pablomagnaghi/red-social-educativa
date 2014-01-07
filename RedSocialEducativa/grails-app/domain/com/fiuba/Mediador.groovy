package com.fiuba

class Mediador extends Usuario {

	// todos los atributos del enunciado ya estan puestos
	
	// Profesor|JTP|AP|AS|Colaborador
	String jerarquia
	
	// Curso tiene la prioridad sobre mediador: Si borro curso, se borra el mediador. Y si guardo curso
	// se guarda el mediador. La fuerte es Curso
	static belongsTo = Curso
	
	// 1 mediador tiene muchos cursos
	static hasMany = [cursos:Curso]
		
    static constraints = {
    }
	
}
