package com.fiuba

class Accion {
	
	String nombre

    static constraints = {
		nombre(blank: false)
    }
	
	String toString() {
		"${nombre}"
	}
	
}
