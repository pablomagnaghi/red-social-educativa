package com.material

import com.fiuba.*

class MaterialCurso extends Material {

	static belongsTo = [curso: Curso]
	
    static constraints = {
    }
}
