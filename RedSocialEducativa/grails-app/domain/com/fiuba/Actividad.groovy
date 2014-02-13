package com.fiuba

class Actividad {

	String titulo
	String objetivo
	// Indicador de si es evaluable o no
	boolean evaluable
	// Indicador si es para todo el curso o grupal
	boolean grupal
	// Indicador de visibilidad para los aprendices
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

