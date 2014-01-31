package com.fiuba

class GrupoCurso extends Grupo {

	//ArrayList<Aprendiz> aprendices
	
	static belongsTo = [curso: Curso]
	
	static hasMany = [aprendices: Aprendiz, materiales: MaterialGrupo]
	
    static constraints = {
		materiales nullable:true
    }

}
