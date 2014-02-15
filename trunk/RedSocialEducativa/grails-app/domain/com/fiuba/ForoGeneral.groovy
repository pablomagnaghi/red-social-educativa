package com.fiuba

class ForoGeneral extends Foro {

	static hasMany = [publicaciones: PublicacionGeneral]

	static constraints = {
	}
}
