package com.material

import com.fiuba.*

class MaterialGrupoActividad extends Material {

	static belongsTo = [grupo: GrupoActividad]
	
	static hasOne = [archivo: ArchivoGrupoActividad]
	
    static constraints = {
		archivo nullable: true
    }
}
