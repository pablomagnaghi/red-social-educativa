package com.fiuba

class CategoriaMaterial {

	String nombre

	static constraints = {
		nombre unique: true
	}

	String toString() {
		"${nombre}"
	}
}
