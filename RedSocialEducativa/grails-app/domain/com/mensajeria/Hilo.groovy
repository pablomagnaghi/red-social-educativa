package com.mensajeria

class Hilo {
	
	static hasMany = [mensajes : Mensaje, conversaciones : Conversacion]
	
    static constraints = {
    }
}
