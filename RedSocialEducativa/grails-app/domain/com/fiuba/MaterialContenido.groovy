package com.fiuba

class MaterialContenido extends Materia {
	
	static belongsTo = [contenido: Contenido]
	
    static constraints = {
    }
}
