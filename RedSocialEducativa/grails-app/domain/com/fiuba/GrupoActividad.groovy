package com.fiuba

class GrupoActividad {

	Integer numero
	String nombre
	
	static belongsTo = [actividad: Actividad]
	
	static hasMany = [aprendices: GrupoActividadAprendiz, materiales: MaterialGrupoActividad]
	
    static constraints = {
		numero min: 1
    }
	
	String toString() {
		"${numero}"
	}
}
