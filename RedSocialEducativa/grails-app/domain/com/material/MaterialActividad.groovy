package com.material

import com.fiuba.*

class MaterialActividad extends Material {

	static belongsTo = [actividad: Actividad]
	
	static hasOne = [archivo: ArchivoActividad]
	
    static constraints = {
		archivo nullable: true
    }
}
