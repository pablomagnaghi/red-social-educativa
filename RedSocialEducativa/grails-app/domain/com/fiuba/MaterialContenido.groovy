package com.fiuba

class MaterialContenido extends Material {
	
	static belongsTo = [contenido: Contenido]
	
    static constraints = {
    }
}
