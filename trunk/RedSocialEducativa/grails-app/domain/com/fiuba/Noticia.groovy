package com.fiuba

abstract class Noticia {

	String titulo 
	String texto 
	String fecha 
	String hora
	
	// Indica si se muestra en la cartelera o no para los visitantes o aprendices
	// Para administradores/mediadores se muestran todas las noticias
	Boolean visibilidad 

    static constraints = {
    }
	
	static mapping = {
		tablePerHierarchy false
	}
}
