package com.fiuba

import com.mensajeria.Carpeta

class Usuario {
	transient springSecurityService

	String username
	String password
	String dni
	String apellido
	String nombres
	Integer legajo
	Integer padron
	String email
	Integer fechaSolicitud = Utilidades.FECHA
	Integer fechaMembresia
	
	String codigoConfirmacion = UUID.randomUUID().toString()

	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static hasMany = [carpetas: Carpeta]

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true, maxSize: 16
		password blank: false
		dni blank: false, unique: true, matches:"[0-9]{8}"
		apellido blank: false, maxSize: 24
		nombres blank: false, maxSize: 48
		legajo nullable: true, unique: true, matches:"[0-9]+"
		padron nullable: true, unique: true, matches:"[0-9]+"
		email email: true, maxSize: 64
		fechaMembresia nullable: true 
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Rol> getAuthorities() {
		UsuarioRol.findAllByUsuario(this).collect { it.rol } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}

	String toString() {
		"${dni} - ${nombres} ${apellido}"
	}
}