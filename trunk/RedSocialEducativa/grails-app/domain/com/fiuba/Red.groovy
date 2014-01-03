package com.fiuba

class Red {
	
	String titulo = "Red Social Educativa del Departamento de Computacion de la Fiuba"
	
	// Cantidad de fechas de evaluación integradoras a conservar de las calificaciones de aprendices en todo curso
	Short cicloConservacion = 50

	// pueden agregarse mas atributos
	
	static hasMany = [membresias: Membresia, administradores: Administrador, materias: Materia]
	
	
    static constraints = {
    }
	

}
