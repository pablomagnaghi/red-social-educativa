package com.fiuba

import com.cartelera.*
import com.cursado.*

class Mediador extends UsuarioRol {

	Boolean activo = true
	String jerarquia

	static belongsTo = [curso: Curso]

	static hasMany = [noticiasCurso: NoticiaCurso]

	static constraints = {
		jerarquia inList:["1-Profesor", "2-JTP", "3-AP", "4-AS", "5-Colaborador"]
	}
	
	String toString() {
		"${usuario.nombres} ${usuario.apellido} - ${jerarquia}"
	}
}
