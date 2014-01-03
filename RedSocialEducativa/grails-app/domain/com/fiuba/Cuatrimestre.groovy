package com.fiuba

class Cuatrimestre {

	// todos los atributos del enunciado ya estan puestos
	
	// validar que sea mayor a 2000
	Short anio 
	// 1er o 2do cuatrimestre
	Short numero
	// Si el curso esta habilitado para formar grupos
	Boolean habGrupos 
	// numero de grupos actuales, debe cambiarse a cero cuando habGrupos es falso
	Short nroUltGrupo 
	
	static hasMany = [aprendices: Aprendiz]

	static belongsTo = Curso
	
	String toString() {
		"${anio}- ${numero}"
	}
	
    static constraints = {
    }
}
