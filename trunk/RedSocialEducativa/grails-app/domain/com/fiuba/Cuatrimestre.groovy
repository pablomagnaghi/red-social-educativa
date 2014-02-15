package com.fiuba

class Cuatrimestre {

	//	// validar que sea mayor a 2000
	Integer anio
	//	// 1er o 2do cuatrimestre
	Short numero
	//	// Si el curso esta habilitado para formar grupos
	boolean habGrupos
	//	// numero de grupos actuales, debe cambiarse a cero cuando habGrupos es falso
	Short nroUltGrupo

	static belongsTo = [curso: Curso]

	static hasOne = [foro: ForoCurso]

	static hasMany = [actividades: Actividad, aprendices: Aprendiz, grupos: GrupoCurso, noticiasCurso: NoticiaCurso]

	static constraints = {
	}
	
	String toString() {
		"${anio} - ${numero}"
	}
}
