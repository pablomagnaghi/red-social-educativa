package com.foro

class PublicacionGeneral extends Publicacion {

	static hasMany = [respuestas: PublicacionGeneral]

	static belongsTo = [foro: ForoGeneral, publicacionInicial: PublicacionGeneral]

	static constraints = {
		publicacionInicial nullable:true
		respuestas nullable: true
	}
}
