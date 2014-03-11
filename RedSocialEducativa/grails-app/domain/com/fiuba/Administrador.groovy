package com.fiuba

import com.cartelera.*

class Administrador extends UsuarioRol {

	Boolean activo = true
	
	static hasMany = [noticiasRed: NoticiaRed]

	static constraints = {
	}
}
