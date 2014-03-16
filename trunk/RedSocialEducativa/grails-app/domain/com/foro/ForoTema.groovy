package com.foro

import com.cursado.*

class ForoTema extends Foro {
	
	static belongsTo = [tema: Tema]

	static hasMany = [publicaciones: PublicacionTema]
	
	static constraints = {
	}
}
