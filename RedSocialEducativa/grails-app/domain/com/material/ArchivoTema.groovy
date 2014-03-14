package com.material

class ArchivoTema extends Archivo {

	static belongsTo = [material: MaterialTema]
	
    static constraints = {
    }
}
