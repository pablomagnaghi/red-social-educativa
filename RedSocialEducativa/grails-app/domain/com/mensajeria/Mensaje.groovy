package com.mensajeria

import com.fiuba.Usuario
import java.text.DateFormat
import java.text.SimpleDateFormat

class Mensaje {
	
	Usuario emisor
	Usuario receptor
	String asunto
	String cuerpo
	HashMap<String, String> para = new HashMap<String, String>()
	Date fecha
	Boolean leido = false
	Hilo hilo
	
	static belongsTo = [Hilo, Conversacion]
	
	static hasMany = [conversaciones : Conversacion]

	static mapping = {
		cuerpo type: "text"
		sort fecha: "asc"
	}
	
    static constraints = {
		conversaciones nullable:true
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
	
	public String getFechaYHora(){
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy - HH:mm");
		String fechaFormateada = df.format(this.fecha);
		return fechaFormateada
	}
	
}
