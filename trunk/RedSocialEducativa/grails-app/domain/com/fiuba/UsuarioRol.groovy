package com.fiuba

abstract class UsuarioRol {

	Usuario usuario
	Rol rol
	
	String toString() {
		"${usuario}"
	}
	
    static constraints = {
    }
	
	static mapping = {
		tablePerHierarchy false
    }
	/*
	def esAdm(String nombreRol) {
		for (rol in this.roles) {
			if (rol.nombre == nombreRol) {
				return true
			}
		}
		return false
	}*/
}
