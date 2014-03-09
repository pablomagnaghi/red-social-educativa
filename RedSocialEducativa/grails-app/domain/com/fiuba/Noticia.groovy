package com.fiuba

abstract class Noticia {

	String titulo 
	String texto 
	String fecha = new Date().format(Utilidades.FORMATO_FECHA)
	String hora = new Date().getTimeString()
	
	Boolean visibilidad 

    static constraints = {
		titulo maxSize: 48
		texto maxSize: 512
    }
	
	static mapping = {
		tablePerHierarchy false
		texto type: 'text'
	}
}
