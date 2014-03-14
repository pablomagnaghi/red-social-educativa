package com.material

class ArchivoActividad extends Archivo {

	static belongsTo = [material: MaterialActividad]
	
    static constraints = {
    }
}
