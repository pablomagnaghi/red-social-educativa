package com.fiuba

class Contenido {

	static belongsTo = [tema: Tema]
	
	static hasMany = [materiales: MaterialContenido]
	
    static constraints = {
    }
}
