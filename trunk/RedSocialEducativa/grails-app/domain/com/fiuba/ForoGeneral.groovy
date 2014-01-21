package com.fiuba

// El foro de la red

class ForoGeneral extends Foro {

	static hasMany = [publicaciones: PublicacionGeneral]
	
	static constraints = {
	}

}
