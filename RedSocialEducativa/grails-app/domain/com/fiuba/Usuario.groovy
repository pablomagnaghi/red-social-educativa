package com.fiuba

import com.mensajeria.Carpeta

class Usuario {
	transient springSecurityService

	String username
	String password

	String apellido
	String nombres
	Integer legajo
	Integer padron
	String email
	String fechaSolicitud
	String fechaMemb

	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static hasMany = [carpetas: Carpeta]

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false//, matches:"[0-9]{8}", unique: true
		password blank: false//, minSize: 6
		apellido blank: false
		nombres blank: false
		legajo nullable: true, unique: true
		padron nullable: true//, matches:"[0-9]{5}", unique: true
		email email: true
		fechaMemb nullable: true
	}

	static mapping = {
		username column: 'dni'
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
		"${nombres} ${apellido}"
	}
}