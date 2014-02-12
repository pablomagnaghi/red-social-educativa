package com.fiuba

abstract class Noticia {

	String titulo 
	String texto 
	String fecha 
	String hora
	
	Boolean visibilidad 

    static constraints = {
    }
	
	static mapping = {
		tablePerHierarchy false
	}
}
