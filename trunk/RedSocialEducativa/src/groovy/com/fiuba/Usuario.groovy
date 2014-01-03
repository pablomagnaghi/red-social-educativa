package com.fiuba

abstract class Usuario {

	Rol rol
	Membresia membresia

    static constraints = {
    }
	
	//static mapping = [tablePerHierarchy : false ]
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
