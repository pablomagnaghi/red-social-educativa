package com.material

import com.cursado.*

class MaterialActividad extends Material {

	static belongsTo = [actividad: Actividad]
	
	static hasOne = [archivo: ArchivoActividad]
	
    static constraints = {
		archivo nullable: true
    }
}
