package com.fiuba

class Aprendiz extends UsuarioRol {

	Boolean participa 
	Integer	msjEnviados 
	Integer msjLeidos 
	Integer pubForos 
	Integer descMaterial 
	String ultVisita 
	
	Boolean cursando
	
	// No se usa belongsTo porque si se borra el curso, el aprendiz debe seguir existiendo
	GrupoCurso grupo
	
	static belongsTo = [cuatrimestre: Cuatrimestre]
	
	// Un aprendiz puede pertenecer a un grupo por cada actividad por lo 
	// que puede estar en N grupos de actividad por curso
	static hasMany = [evaluaciones: EvaluacionAprendiz, gruposActividad: GrupoActividadAprendiz]

    static constraints = {
		ultVisita nullable:true
		grupo nullable: true
    }
}
