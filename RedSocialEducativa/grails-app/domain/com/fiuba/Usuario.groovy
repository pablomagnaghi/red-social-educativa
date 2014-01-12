package com.fiuba

class Usuario {
	
	String dni
	String password
	String apellido
	String nombres
	Integer legajo
	Integer padron
	String email
	Boolean membresia
	Date fechaSolicitud 
	Date fechaMemb

	String toString() {
		"${nombres} ${apellido}"
	}

	static constraints = {
		dni(unique:true,  matches:"[0-9]{8}")
		password(password: true, minSize: 6)
		apellido(blank:false, matches:"[a-zA-Z]+")
		nombres(blank:false)
		email(email:true)
		fechaMemb(nullable: true)
	}
	
	def beforeInsert(){
		password = password.encodeAsMD5();
	}
}
