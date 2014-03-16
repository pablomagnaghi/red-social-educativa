package com.cursado

import com.fiuba.*

class Asignatura {

	String codigo
	String nombre
	Short creditos
	String contenidosMinimos

	static hasMany = [cursos: Curso]

	static constraints = {
		nombre  maxSize: Utilidades.MAX_TITULO
		creditos min:(Short)1
		contenidosMinimos nullable:true, maxSize: Utilidades.MAX_SIZE
	}

	String toString() {
		"${codigo}"
	}
}
