package com.fiuba

import java.util.Date;
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

	String toString() {
		"${nombres} ${apellido}"
	}
	
	static hasMany = [carpetas : Carpeta]
	
	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, matches:"[0-9]{8}", unique: true
		password blank: false
		apellido blank: false
		nombres blank: false
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

}