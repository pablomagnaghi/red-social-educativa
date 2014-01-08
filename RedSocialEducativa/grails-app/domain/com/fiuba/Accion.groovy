package com.fiuba

class Accion {
	
	String nombre
	//String acccion
	//String controlador
	
	String toString() {
		"${nombre}"
	}
	
	static constraints = {
		nombre(blank: false)
	}
	
}
