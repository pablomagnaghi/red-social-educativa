package com.foro

class PublicacionTema extends Publicacion {

	static hasMany = [respuestas: PublicacionTema]
	
	static belongsTo = [foro: ForoTema, publicacionInicial: PublicacionTema]
	
	static constraints = {
		publicacionInicial nullable:true
		respuestas nullable: true
	}
}
