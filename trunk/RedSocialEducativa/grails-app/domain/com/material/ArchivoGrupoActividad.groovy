package com.material

class ArchivoGrupoActividad extends Archivo {

	static belongsTo = [material: MaterialGrupoActividad]
	
    static constraints = {
    }
}
