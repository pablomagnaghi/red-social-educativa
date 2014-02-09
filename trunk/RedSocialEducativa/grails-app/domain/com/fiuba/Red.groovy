package com.fiuba

class Red {
	
	public static final Red instance = new Red();
	
	private Red() {}
	
	String titulo = Utilidades.TITULO_RED
	
	// Cantidad de fechas de evaluación integradoras a conservar de las calificaciones de aprendices en todo curso
	Short cicloConservacion = Utilidades.CICLO_CONSERVACION

	// pueden agregarse mas atributos
	
	String toString() {
		"${titulo}"
	}
	
    static constraints = {
    }
	
}
