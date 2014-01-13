package com.fiuba

class NoticiaRed extends Noticia {
	
	static belongsTo = [administrador: Administrador]

	String toString() {
		"Noticia de la red hecha por ${administrador}"
	}
	
    static constraints = {
    }
}
