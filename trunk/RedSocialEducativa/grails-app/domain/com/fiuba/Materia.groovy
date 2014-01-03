package com.fiuba

class Materia {
	
	// todos los atributos del enunciado ya estan puestos
	
	String codigo
	String nombre
	Short creditos
	String contenidosMinimos
	
	static hasMany = [cursos : Curso]
	
	String toString() {
		"${codigo}- ${nombre}"
	}
	
    static constraints = {
    }
}
