package com.cursado

import com.fiuba.*
import com.material.*

class Actividad {

	String titulo
	String objetivo
	boolean evaluable
	boolean grupal
	boolean visibilidad
	Integer fechaFinalizacion
	
	CategoriaActividad categoria

	static belongsTo = [cuatrimestre: Cuatrimestre]

	static hasMany = [grupos: GrupoActividad, materiales: MaterialActividad, temas: TemaActividad]
	
	static constraints = {
		titulo maxSize: Utilidades.MAX_TITULO
		objetivo maxSize: Utilidades.MAX_SIZE
	}
	
	static mapping = {
		tablePerHierarchy false
		objetivo type: 'text'
	}
	
	String toString() {
		"${titulo}"
	}
}

