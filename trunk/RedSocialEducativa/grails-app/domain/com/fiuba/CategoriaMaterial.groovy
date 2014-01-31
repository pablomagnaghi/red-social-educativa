package com.fiuba

class CategoriaMaterial {

	String nombre
	
	String toString() {
		"${nombre}"
	}
	
    static constraints = {
		nombre unique: true
    }
}
