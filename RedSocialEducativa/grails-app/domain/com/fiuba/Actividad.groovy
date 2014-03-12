package com.fiuba

import com.material.*

class Actividad {

	String titulo
	String objetivo
	boolean evaluable
	boolean grupal
	boolean visibilidad
	String fechaFinalizacion
	
	CategoriaActividad categoria

	static belongsTo = [cuatrimestre: Cuatrimestre]

	static hasMany = [grupos: GrupoActividad, materiales: MaterialActividad, temas: TemaActividad]
	
	static constraints = {
	}
	
	static mapping = {
		tablePerHierarchy false
	}
	
	String toString() {
		"${titulo}"
	}
}

