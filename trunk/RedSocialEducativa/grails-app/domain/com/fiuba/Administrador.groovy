package com.fiuba

class Administrador extends UsuarioRol {

	Boolean activo = true
	
	static hasMany = [noticiasRed: NoticiaRed]

	static constraints = {
	}
}
