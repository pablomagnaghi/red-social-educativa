package com.fiuba

class PublicacionTema extends Publicacion {

	static hasMany = [respuestas: PublicacionTema]
	
	static belongsTo = [publicacionInicial: PublicacionTema, foro: ForoTema]
	
	// Una publicacion puede no tener respuestas, o no tener padre
	// Es el caso de un foro con un publicacion con un solo mensaje
	static constraints = {
		publicacionInicial nullable:true
		respuestas nullable: true
	}
	
}
