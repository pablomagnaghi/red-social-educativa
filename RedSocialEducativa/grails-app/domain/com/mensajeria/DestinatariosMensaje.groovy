package com.mensajeria

class DestinatariosMensaje {
	
	String key	
	String value

	static belongsTo = [mensaje: Mensaje]
	
	static constraints = {
		key unique: 'mensaje'
	}
	
	static mapping = {
		key column: 'clave'
		value column: 'valor'
	}

}
