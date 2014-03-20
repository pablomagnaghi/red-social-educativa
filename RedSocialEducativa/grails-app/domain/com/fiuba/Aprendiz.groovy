package com.fiuba

import com.cursado.*
import com.encuesta.*

class Aprendiz extends UsuarioRol {

	Boolean participa 
	Integer	msjEnviados 
	Integer msjLeidos 
	Integer pubForos 
	Integer descMaterial 
	String ultVisita 
	Boolean cursando

	static belongsTo = [cuatrimestre: Cuatrimestre]
	
	static hasMany = [encuestas: EncuestaAprendiz, evaluaciones: EvaluacionAprendiz, gruposActividad: GrupoActividadAprendiz]

    static constraints = {
		ultVisita nullable:true
    }
	
	String toString() {
		"${usuario}"
	}
}

