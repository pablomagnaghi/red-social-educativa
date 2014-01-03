package com.fiuba

class Aprendiz extends Usuario{

	Boolean participa // Sólo un mediador puede cambiar el estado
	Integer	msjEnviados // Cantidad de mensajes enviados a participantes del mismo curso
	Integer msjLeidos // Cantidad de mensajes leídos de los recibidos en el curso
	Integer pubForos // Cantidad de publicaciones en foros del curso
	Integer descMaterial // Cantidad de materiales descargados del curso
	Date ultVisita // AAAAMMDD
	
	static hasMany = [cuatrismestres: Cuatrimestre]
	
	static belongsTo = Cuatrimestre
	
    static constraints = {
    }

}
