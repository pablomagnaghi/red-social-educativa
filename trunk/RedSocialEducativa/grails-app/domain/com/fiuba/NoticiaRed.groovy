package com.fiuba

class NoticiaRed extends Noticia {

	static belongsTo = [administrador: Administrador]

	static constraints = {
	}

	String toString() {
		"Noticia de la red hecha por ${administrador}"
	}
}
