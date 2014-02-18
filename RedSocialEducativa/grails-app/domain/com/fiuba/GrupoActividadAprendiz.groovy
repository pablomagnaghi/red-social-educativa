package com.fiuba

class GrupoActividadAprendiz {

	Integer nota
	
	static belongsTo = [aprendiz: Aprendiz, grupo: GrupoActividad]
	
    static constraints = {
		nota nullable: true, min: 1
    }
}
