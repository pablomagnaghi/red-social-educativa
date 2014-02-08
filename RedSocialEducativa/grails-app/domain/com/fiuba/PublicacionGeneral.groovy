package com.fiuba

class PublicacionGeneral extends Publicacion {

	static hasMany = [respuestas: PublicacionGeneral]
	
	static belongsTo = [foro: ForoGeneral, publicacionInicial: PublicacionGeneral]
	
	// Una publicacion puede no tener respuestas, o no tener padre
	// Es el caso de un foro con un publicacion con un solo mensaje
    static constraints = {
		publicacionInicial nullable:true
		respuestas nullable: true
    }
}
