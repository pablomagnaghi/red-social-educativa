package com.mensajeria

import com.fiuba.Usuario

class Carpeta {
	
	String nombre
	
	static belongsTo = [usuario : Usuario] 

    static constraints = {
		nombre(blank: false)
    }
}
