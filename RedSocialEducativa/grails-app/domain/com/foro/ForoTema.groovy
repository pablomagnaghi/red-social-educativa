package com.foro

import com.fiuba.*

class ForoTema extends Foro {
	
	static belongsTo = [tema: Tema]

	static hasMany = [publicaciones: PublicacionTema]
	
	static constraints = {
	}
}
