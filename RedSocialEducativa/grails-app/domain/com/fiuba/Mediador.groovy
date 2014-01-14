package com.fiuba

class Mediador extends UsuarioRol {

	// todos los atributos del enunciado ya estan puestos
	
	// Profesor|JTP|AP|AS|Colaborador
	String jerarquia
	
	// Mediador tiene como entidad fuerte a Curso. 
	// En tabla de mediador se pone el id de curso
	// Igual que como esta en la BBDD del enunciado del tp 
	
	static belongsTo = [curso: Curso]
	
	static hasMany = [noticiasCurso: NoticiaCurso]
	
    static constraints = {
    }
	
}
