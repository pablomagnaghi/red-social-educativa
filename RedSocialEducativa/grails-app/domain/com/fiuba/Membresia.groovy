package com.fiuba

import java.util.Date

class Membresia {
	
	Integer dni
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
		"${apellido}, ${nombres}"
	}

	static constraints = {
		dni(unique:true,  matches:"[1-9]{8}")
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
