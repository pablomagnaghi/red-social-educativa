package com.fiuba

class Red {

	public static final Red instance = new Red();

	private Red() {}

	String titulo = Utilidades.TITULO_RED

	Short cicloConservacion = Utilidades.CICLO_CONSERVACION

	static constraints = {
		cicloConservacion min:(Short)1
	}

	String toString() {
		"${titulo}"
	}
}
