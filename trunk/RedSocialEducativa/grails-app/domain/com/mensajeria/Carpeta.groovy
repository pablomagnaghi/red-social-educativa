package com.mensajeria

import com.fiuba.Usuario

class Carpeta {
	
	String nombre
	Carpeta padre = null
	
	static belongsTo = [usuario : Usuario] 

    static constraints = {
		nombre(blank: false)
    }
}
