package com.fiuba

class CategoriaActividad {

	String nombre

	static constraints = {
		nombre unique: true
	}

	String toString() {
		"${nombre}"
	}
}
