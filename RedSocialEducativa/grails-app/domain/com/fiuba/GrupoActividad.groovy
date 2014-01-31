package com.fiuba

class GrupoActividad extends Grupo {

	static belongsTo = [actividad: Actividad]
	
	//static hasMany = [aprendices: AprendizGrupoActividad]
	
    static constraints = {
    }
}
