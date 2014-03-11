package com.fiuba

class Mediador extends UsuarioRol {

	Boolean activo = true
	String jerarquia

	static belongsTo = [curso: Curso]

	static hasMany = [noticiasCurso: NoticiaCurso]

	static constraints = {
		jerarquia inList:["1-Profesor", "2-JTP", "3-AP", "4-AS", "5-Colaborador"]
	}
}
