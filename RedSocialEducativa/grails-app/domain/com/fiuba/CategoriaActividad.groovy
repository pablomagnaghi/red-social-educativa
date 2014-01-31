package com.fiuba

class CategoriaActividad {

	String nombre
	
	String toString() {
		"${nombre}"
	}
	
	static constraints = {
		nombre unique: true
	}

}
