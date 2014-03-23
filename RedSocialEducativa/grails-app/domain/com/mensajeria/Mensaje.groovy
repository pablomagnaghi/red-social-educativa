package com.mensajeria

import com.fiuba.Usuario
import java.text.DateFormat
import java.text.SimpleDateFormat

class Mensaje {
	
	Usuario emisor
	Usuario receptor
	String asunto
	String cuerpo
	Date fecha
	Boolean leido = false
	Hilo hilo
	
	static belongsTo = [Hilo, Conversacion]
	
	static hasMany = [conversaciones : Conversacion, para : DestinatariosMensaje]

	static mapping = {
		cuerpo type: "text"
		sort fecha: "desc"
	}
	
    static constraints = {
		conversaciones nullable:true
		receptor nullable: true
		para nullable: true
		asunto nullable: true
		cuerpo nullable: true
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
		if (this.cuerpo == null){
			return "..."
		}
		def length = this.cuerpo.length();
		if (length > 20){
			length = 20
		}
		return this.cuerpo.substring(0, length)
	}
	
	public String getFechaYHora(){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		String fechaFormateada = df.format(this.fecha);
		return fechaFormateada
	}
	
}
