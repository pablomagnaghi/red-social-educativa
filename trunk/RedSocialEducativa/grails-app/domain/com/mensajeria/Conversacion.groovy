package com.mensajeria

class Conversacion {
	
	Carpeta padre
	
	static belongsTo = [hilo : Hilo]

    static constraints = {
    }
}
