package com.fiuba

class Categoria {

	String nombre
	
	String toString() {
		"${nombre}"
	}
	
    static constraints = {
		nombre unique: true
    }
}
