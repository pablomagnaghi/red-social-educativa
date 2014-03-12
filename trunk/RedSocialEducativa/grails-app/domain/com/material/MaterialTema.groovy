package com.material

import com.fiuba.*

class MaterialTema extends Material {
	
	static belongsTo = [tema: Tema]
	
    static constraints = {
    }
}
