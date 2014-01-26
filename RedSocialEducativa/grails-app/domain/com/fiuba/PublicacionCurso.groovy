package com.fiuba

class PublicacionCurso extends Publicacion {

	static hasMany = [respuestas: PublicacionCurso]
	
	static belongsTo = [publicacionInicial: PublicacionCurso, foro: ForoCurso]
	
	// Una publicacion puede no tener respuestas, o no tener padre
	// Es el caso de un foro con un publicacion con un solo mensaje
	static constraints = {
		publicacionInicial nullable:true
		respuestas nullable: true
	}

}

