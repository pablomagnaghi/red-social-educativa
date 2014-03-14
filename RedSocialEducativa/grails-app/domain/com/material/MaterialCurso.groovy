package com.material

import com.fiuba.*

class MaterialCurso extends Material {

	static belongsTo = [curso: Curso]
	
	static hasOne = [archivo: ArchivoCurso]
	
    static constraints = {
		archivo nullable: true
    }
}
