package com.fiuba

abstract class Noticia {

	String titulo 
	String texto 
	String fecha = new Date().format(Utilidades.FORMATO_FECHA)
	String hora = new Date().getTimeString()
	
	Boolean visibilidad 

    static constraints = {
    }
	
	static mapping = {
		tablePerHierarchy false
	}
}
