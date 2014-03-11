package com.foro

class PublicacionCurso extends Publicacion {

	static hasMany = [respuestas: PublicacionCurso]

	static belongsTo = [publicacionInicial: PublicacionCurso, foro: ForoCurso]

	static constraints = {
		publicacionInicial nullable:true
		respuestas nullable: true
	}
}

