package com.fiuba

class Aprendiz extends UsuarioRol {

	Boolean participa // Sólo un mediador puede cambiar el estado
	Integer	msjEnviados // Cantidad de mensajes enviados a participantes del mismo curso
	Integer msjLeidos // Cantidad de mensajes leídos de los recibidos en el curso
	Integer pubForos // Cantidad de publicaciones en foros del curso
	Integer descMaterial // Cantidad de materiales descargados del curso
	String ultVisita // AAAAMMDD
	
	// Un aprendiz puede pertecer o no a un grupo en un curso
	// No se usa belongsTo porque si se borra el curso, el aprendiz
	// debe seguir existiendo
	GrupoCurso grupo
	
	
	// Aprendiz tiene como entidad fuerte a Curso.
	// En tabla de aprendiz se pone el id de curso
	// Igual que como esta en la BBDD del enunciado del tp
	
	
	static belongsTo = [curso: Curso]

    static constraints = {
		ultVisita nullable:true
		grupo nullable: true
    }

}
