package com.cursado

import com.fiuba.*
import com.material.*

class Curso {

	Short nroRelativo
	String nombre
	String cuatDict

	static belongsTo = [asignatura: Asignatura]

	static hasMany = [cuatrimestres: Cuatrimestre, evaluaciones: Evaluacion, materiales: MaterialCurso,
		mediadores: Mediador, temas: Tema]

	static constraints = {
		nroRelativo min: (Short)1
		nombre maxSize: Utilidades.MAX_TITULO
		cuatDict inList:[Utilidades.CUAT_UNO, Utilidades.CUAT_DOS, Utilidades.CUAT_AMBOS]
	}

	String toString() {
		"${nroRelativo} - ${nombre}"
	}
}
