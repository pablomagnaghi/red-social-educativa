package com.fiuba

class GrupoActividadAprendiz {

	static belongsTo = [aprendiz: Aprendiz, grupo: GrupoActividad]
	
	String toString() {
		"${aprendiz}"
	}
	
    static constraints = {
    }
}
