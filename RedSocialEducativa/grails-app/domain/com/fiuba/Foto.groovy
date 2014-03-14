package com.fiuba

import java.util.Date;

class Foto {
	String filename
	byte[] filedata // long blob
	Date uploadDate = new Date()
	
	static belongsTo = [usuario: Usuario]
	
	static constraints = {
		filename blank:false
		filedata blank: false, maxSize:33554432 //32MB de tamaño
	}
}
