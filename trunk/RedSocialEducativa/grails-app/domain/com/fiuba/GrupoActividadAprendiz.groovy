package com.fiuba

class GrupoActividadAprendiz {

	static belongsTo = [aprendiz: Aprendiz, grupo: GrupoActividad]
	
    static constraints = {
    }
}
