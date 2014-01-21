package com.fiuba

class PublicacionGeneral extends Publicacion {

	static hasOne = [publicacion: PublicacionGeneral]
	
	static belongsTo = [publicacionPadre: PublicacionGeneral, foro: ForoGeneral]
	
	// Una publicacion puede no tener respueste, o no tener padre
	// Es el caso de un foro con un publicacion con un solo mensaje
    static constraints = {
		publicacionPadre nullable:true
		//publicacion nullable: true
    }
}
