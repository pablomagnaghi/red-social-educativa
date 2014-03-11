package com.fiuba

import com.cartelera.*
import com.foro.*

class Cuatrimestre {

	Integer anio
	Short numero

	static belongsTo = [curso: Curso]

	static hasOne = [foro: ForoCurso]

	static hasMany = [actividades: Actividad, aprendices: Aprendiz, noticiasCurso: NoticiaCurso]

	static constraints = {
	}
	
	String toString() {
		"${anio} - ${numero}"
	}
}
