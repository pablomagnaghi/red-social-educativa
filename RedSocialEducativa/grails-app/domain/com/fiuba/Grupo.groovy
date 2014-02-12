package com.fiuba

abstract class Grupo {

	Integer numero
	String nombre
	
    static constraints = {
		numero min:1
    }
	
	static mapping = {
		tablePerHierarchy false
	}
	
	String toString() {
		"${numero}"
	}
}
