package com.cursado

class CategoriaActividad {

	String nombre

	static constraints = {
		nombre unique: true
	}

	String toString() {
		"${nombre}"
	}
}
