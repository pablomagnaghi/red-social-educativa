package com.fiuba

// El foro de cada tema de  un curso

class ForoTema extends Foro {
	
	static belongsTo = [tema: Tema]

	static hasMany = [publicaciones: PublicacionTema]
	
	static constraints = {
	}
}
