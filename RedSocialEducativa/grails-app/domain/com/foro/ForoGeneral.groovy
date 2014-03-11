package com.foro

class ForoGeneral extends Foro {

	static hasMany = [publicaciones: PublicacionGeneral]

	static constraints = {
	}
}
