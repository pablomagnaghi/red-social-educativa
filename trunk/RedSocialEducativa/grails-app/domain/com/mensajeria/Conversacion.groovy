package com.mensajeria

class Conversacion {
	
	Carpeta padre
	
	static belongsTo = [hilo : Hilo]
	static hasMany = [mensajes : Mensaje]

    static constraints = {
		mensajes nullable:true
    }
	
	public Date lastMessageDate(){
		Mensaje m = this.mensajes.last()
		return m.fecha
	}
}
