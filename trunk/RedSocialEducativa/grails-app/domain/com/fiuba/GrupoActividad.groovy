package com.fiuba

class GrupoActividad extends Grupo {

	static belongsTo = [actividad: Actividad]
	
	static hasMany = [aprendices: GrupoActividadAprendiz, materiales: MaterialGrupoActividad]
	
    static constraints = {
    }
}
