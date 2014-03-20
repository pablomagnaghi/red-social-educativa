package com.mensajeria

import com.fiuba.Usuario
import com.mensajeria.MensajeriaController.MensajeComparator
import java.util.Comparator;

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
	
	public Mensaje getLastMessage(Usuario receptor){
		ArrayList<Mensaje> lista = mensajes.findAll{
			it.receptor == receptor
		}
		Collections.sort(lista, new MensajeComparator())
		Mensaje last = lista.get(0)
		return last
	}
}
