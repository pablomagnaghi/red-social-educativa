package com.material

import com.cursado.*

class MaterialGrupoActividad extends Material {

	static belongsTo = [grupo: GrupoActividad]
	
	static hasOne = [archivo: ArchivoGrupoActividad]
	
    static constraints = {
		archivo nullable: true
    }
}
