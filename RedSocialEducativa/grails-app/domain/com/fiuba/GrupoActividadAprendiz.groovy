package com.fiuba

class GrupoActividadAprendiz {

	Integer nota
	boolean cumplio
	
	static belongsTo = [aprendiz: Aprendiz, grupo: GrupoActividad]
	
    static constraints = {
		nota nullable: true, min: 1
    }
}
