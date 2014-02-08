package com.fiuba

class GrupoCurso extends Grupo {

	static belongsTo = [cuatrimestre: Cuatrimestre]
	
	static hasMany = [aprendices: Aprendiz, materiales: MaterialGrupo]
	
    static constraints = {
		materiales nullable:true
    }

}
