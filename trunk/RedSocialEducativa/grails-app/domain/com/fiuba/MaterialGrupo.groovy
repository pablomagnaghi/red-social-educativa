package com.fiuba

class MaterialGrupo extends Material {

	static belongsTo = [grupo: GrupoCurso]
	
    static constraints = {
    }
}
