package com.fiuba

class Tema {

	String titulo
	
	ForoTema foro
	
	static belongsTo = [curso: Curso]
	
	static hasMany = [contenidos: Contenido, materiales: MaterialTema]
	
    static constraints = {
    }
}
