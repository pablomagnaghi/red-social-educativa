package com.fiuba

abstract class Foro {

	String nombre

	static constraints = {
	}

	static mapping = {
		tablePerHierarchy false
	}

	String toString() {
		"${nombre}"
	}
}
