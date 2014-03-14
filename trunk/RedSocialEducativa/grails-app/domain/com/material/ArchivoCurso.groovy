package com.material

class ArchivoCurso extends Archivo {

	static belongsTo = [material: MaterialCurso]

	static constraints = {
	}
}
