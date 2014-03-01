package com.fiuba

class Asignatura {

	String codigo
	String nombre
	Short creditos
	String contenidosMinimos

	static hasMany = [cursos: Curso]

	static constraints = {
		creditos min:(Short)1
		contenidosMinimos nullable:true
	}

	String toString() {
		"${codigo} - ${nombre}"
	}
}
