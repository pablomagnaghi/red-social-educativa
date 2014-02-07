package com.fiuba

class GrupoCurso extends Grupo {

	static belongsTo = [curso: Curso]
	
	static hasMany = [aprendices: Aprendiz, materiales: MaterialGrupo]
	
    static constraints = {
		materiales nullable:true
    }

}
