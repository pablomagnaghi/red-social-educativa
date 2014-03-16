package com.material

import com.cursado.*

class MaterialTema extends Material {
	
	static belongsTo = [tema: Tema]
	
	static hasOne = [archivo: ArchivoTema]
	
    static constraints = {
		archivo nullable: true
    }
}
