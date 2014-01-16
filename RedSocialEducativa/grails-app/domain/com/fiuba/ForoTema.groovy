package com.fiuba

// El foro de cada tema de  un curso

class ForoTema {
	static belongsTo = [tema: Tema]

	static constraints = {
	}
}
