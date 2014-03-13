package com.fiuba

class GrupoActividadAprendiz {

	Integer nota
	boolean cumplio = false
	
	static belongsTo = [aprendiz: Aprendiz, grupo: GrupoActividad]
	
    static constraints = {
		nota nullable: true, min: 1
    }
}
