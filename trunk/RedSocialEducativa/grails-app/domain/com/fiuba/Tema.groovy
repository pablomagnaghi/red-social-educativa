package com.fiuba

class Tema {

	String titulo
	
	// 
	//Actividad actividad
	
	static belongsTo = [curso: Curso]
	
	static hasOne = [foro: ForoTema]
	
	static hasMany = [contenidos: Contenido, materiales: MaterialTema]
	
	String toString() {
		"${titulo}"
	}
	
    static constraints = {
		//foro nullable: true
    }
	
}
