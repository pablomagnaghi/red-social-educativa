package com.fiuba

class MaterialGrupoActividad extends Material {

	static belongsTo = [grupo: GrupoActividad]
	
    static constraints = {
    }
}
