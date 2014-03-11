package com.fiuba

import com.foro.*

class Tema {

	String titulo

	static belongsTo = [curso: Curso]

	static hasOne = [foro: ForoTema]

	static hasMany = [contenidos: Contenido, materiales: MaterialTema, actividades: TemaActividad]

	static constraints = {

	}

	String toString() {
		"${titulo}"
	}
}
