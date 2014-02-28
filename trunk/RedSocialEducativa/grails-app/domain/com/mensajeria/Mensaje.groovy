package com.mensajeria

import com.fiuba.Usuario

class Mensaje {
	
	Usuario emisor
	Usuario receptor
	String asunto
	String cuerpo
	String para
	Date fecha
	Boolean leido = false
	
	static belongsTo = [hilo : Hilo, conversacion : Conversacion]

    static constraints = {
		conversacion nullable:true
		receptor nullable: true 
    }
	static findMessagesByCarpeta(Usuario usuario, String nombreCarpeta){
		def msg = Mensaje.findAll("from Mensaje as m, Conversacion as conv, Carpeta as carp \
			where m.receptor = :usuario and m.hilo = conv.hilo \
			and carp.nombre = :nombre and conv.padre = carp group by conv order by m.leido desc, m.fecha", [usuario: usuario, nombre: nombreCarpeta])
		return msg
	}
	
	
	static getNewMessages(Usuario usuario, String nombreCarpeta){
		def msg = Mensaje.findAll("from Mensaje as m, Conversacion as conv, Carpeta as carp \
			where m.receptor = :usuario and m.leido = false and m.hilo = conv.hilo \
			and carp.nombre = :nombre and conv.padre = carp", [usuario: usuario, nombre: nombreCarpeta])
		return msg.size()
	}
	
	public String getCuerpoResumido(){
		def length = this.cuerpo.length();
		if (length > 20){
			length = 20
		}
		return this.cuerpo.substring(0, length)
	}
	
}
