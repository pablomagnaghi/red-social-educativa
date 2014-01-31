package com.fiuba

abstract class Grupo {

	Integer numero
	String nombre
	
	String toString() {
		"${numero}"
	}
	
    static constraints = {
    }
	
	static mapping = {
		tablePerHierarchy false
	}
}
