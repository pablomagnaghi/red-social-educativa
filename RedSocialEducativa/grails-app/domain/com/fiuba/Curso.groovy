package com.fiuba

class Curso {
	
	// todos los atributos del enunciado ya estan puestos
	
	// Numero de curso de la materia
	Short nroRelativo 
	// Cuatrimestre en que se da el curso: 1ero, 2do o ambos
	String cuatDict
	
	// Dividir los atributos entre aprendiz y curso
//	// validar que sea mayor a 2000
//	Short anio
//	// 1er o 2do cuatrimestre
//	Short numero
//	// Si el curso esta habilitado para formar grupos
//	Boolean habGrupos
//	// numero de grupos actuales, debe cambiarse a cero cuando habGrupos es falso
//	Short nroUltGrupo
	
	// Curso tiene como entidad fuerte a Materia. En tabla curso se pone el id de materia
	static belongsTo = [materia: Materia]
	
	static hasMany = [mediadores: Mediador, aprendices: Aprendiz, noticiasCurso: NoticiaCurso]

	String toString() {
		"${materia} - ${nroRelativo}"
	}
	
    static constraints = {
		
    }
	
}
