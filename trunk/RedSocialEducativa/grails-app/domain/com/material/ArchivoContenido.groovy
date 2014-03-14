package com.material

class ArchivoContenido extends Archivo {

	static belongsTo = [material: MaterialContenido]
	
    static constraints = {
    }
}
