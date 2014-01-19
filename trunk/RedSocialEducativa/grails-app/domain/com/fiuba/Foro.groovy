package com.fiuba

abstract class Foro {

	String nombre
	
    static constraints = {
    }

	String toString() {
		"${nombre}"
	}
	
	static mapping = {
		tablePerHierarchy false
	}
}
