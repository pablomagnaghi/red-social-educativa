package com.fiuba

class MaterialTema extends Materia {
	
	static belongsTo = [tema: Tema]
	
    static constraints = {
    }
}
