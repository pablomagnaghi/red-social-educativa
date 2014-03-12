package com.material

class CategoriaMaterial {

	String nombre

	static constraints = {
		nombre unique: true
	}

	String toString() {
		"${nombre}"
	}
}
