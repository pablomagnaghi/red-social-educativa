package com.fiuba

class Accion {
	
	String nombre
	
	String toString() {
		"${nombre}"
	}
	
	static constraints = {
		nombre(blank: false)
	}
	
}
