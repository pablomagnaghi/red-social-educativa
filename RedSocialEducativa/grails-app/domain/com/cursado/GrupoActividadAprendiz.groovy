package com.cursado

import com.fiuba.*

class GrupoActividadAprendiz {

	Float nota
	Boolean calificado = false
	boolean cumplio = false
	
	static belongsTo = [aprendiz: Aprendiz, grupo: GrupoActividad]
	
    static constraints = {
		nota nullable: true, min: 0.00F, max: 10.0F, scale: 2
    }
	
	String toString() {
		"${aprendiz}"
	}
}
