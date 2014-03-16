package com.material

import com.cursado.*

class MaterialCurso extends Material {

	static belongsTo = [curso: Curso]
	
	static hasOne = [archivo: ArchivoCurso]
	
    static constraints = {
		archivo nullable: true
    }
}
