package com.material

import com.cursado.*

class MaterialContenido extends Material {
	
	static belongsTo = [contenido: Contenido]
	
	static hasOne = [archivo: ArchivoContenido]
	
    static constraints = {
		archivo nullable: true
    }
}
