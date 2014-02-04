package com.fiuba

abstract class Grupo {

	Integer numero
	String nombre
	
	String toString() {
		"${numero}"
	}
	
    static constraints = {
		//nombre nullable: true
    }
	
	static mapping = {
		tablePerHierarchy false
	}
}
