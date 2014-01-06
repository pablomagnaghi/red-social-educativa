package com.fiuba

class Rol {
	
	// todos los atributos del enunciado ya estan puestos
	String nombre

	static hasMany = [acciones : Accion]
	
	String toString() {
		"${nombre}"
	}
	
    static constraints = {
		nombre(blank:false)
    }
}
