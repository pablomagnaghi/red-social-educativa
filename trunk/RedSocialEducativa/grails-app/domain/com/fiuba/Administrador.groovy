package com.fiuba

class Administrador extends UsuarioRol {

	static hasMany = [noticiasRed: NoticiaRed]
	
    static constraints = {
    }
	
}

