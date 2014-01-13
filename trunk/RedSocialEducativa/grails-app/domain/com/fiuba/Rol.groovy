package com.fiuba

class Rol {

	String authority

	String toString() {
		"${authority}"
	}
	
	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
