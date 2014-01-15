package com.mensajeria

import com.fiuba.Usuario

class Mensaje {
	
	Usuario emisor
	Usuario receptor
	String asunto
	String cuerpo
	Date fecha
	Boolean leido = false
	
	static belongsTo = [hilo : Hilo]

    static constraints = {
		
    }
}
