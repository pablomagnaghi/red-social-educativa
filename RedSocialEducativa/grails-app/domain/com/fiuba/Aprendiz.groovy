package com.fiuba

class Aprendiz extends UsuarioRol {

	Boolean participa // Solo un mediador puede cambiar el estado
	Integer	msjEnviados // Cantidad de mensajes enviados a participantes del mismo curso
	Integer msjLeidos // Cantidad de mensajes leídos de los recibidos en el curso
	Integer pubForos // Cantidad de publicaciones en foros del curso
	Integer descMaterial // Cantidad de materiales descargados del curso
	String ultVisita // AAAAMMDD
	
	// Un aprendiz puede pertecer o no a un grupo en un curso
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
