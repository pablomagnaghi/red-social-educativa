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
	
	
	// Categorias: encuesta, TP, ejercicios, cuestionario, brainstorming, otra
	// TODO
	CategoriaActividad categoria

	static belongsTo = [curso: Curso]

	// Puede estar asociada a uno o mas temas de un curso
	static hasMany = [grupos: GrupoActividad, materiales: MaterialActividad, temas: TemaActividad]
	
	String toString() {
		"${titulo}"
	}
	
	static constraints = {
	}
	
	static mapping = {
		tablePerHierarchy false
	}
	
	
	
}

