package com.fiuba

import java.util.Date;

class Utilidades {
	
	final static TITULO_RED = "Red Social Educativa del Departamento de Computacion de la FIUBA"
	final static TITULO_CONFIRMACION = TITULO_RED + " - Confirmacion de cuenta"
	final static TITULO_BIENVENIDA = TITULO_RED + " - Cuenta activada"
	final static TITULO_CAMBIO_CLAVE = TITULO_RED + " - Cambio de clave"
	final static MSJ_MAIL_BIENVENIDA = "Bienvenido a la red social" 

	final static FORMATO_FECHA = "yyyyMMdd"
	
	final static ANIO_INICIAL = 1900
	final static ANIO = new Date().getYear() + ANIO_INICIAL
	final static MES =  new Date().getMonth() + 1
	final static DIA = new Date().getAt(Calendar.DAY_OF_MONTH)
	// TODO ir tocando la fecha para pruebas
	final static FECHA = 10000 * (ANIO) + 100 * MES + DIA
	
	final static ANIO_PROXIMA_SEMANA = new Date(new Date().getTime() + (7 * 24* 60 * 60 * 1000)).getYear() + ANIO_INICIAL
	final static MES_PROXIMA_SEMANA =  new Date(new Date().getTime() + (7 * 24* 60 * 60 * 1000)).getMonth() + 1
	final static DIA_PROXIMA_SEMANA = new Date(new Date().getTime() + (7 * 24* 60 * 60 * 1000)).getAt(Calendar.DAY_OF_MONTH)
	final static FECHA_PROXIMA_SEMANA = 10000 * (ANIO_PROXIMA_SEMANA) + 100 * MES_PROXIMA_SEMANA + DIA_PROXIMA_SEMANA

	// TODO despues poner fechas coherentes
	final static FECHA_PRIMER_CUATRIMESTRE = 10000 * (ANIO) + 100 * 3 + 30
	final static FECHA_SEGUNDO_CUATRIMESTRE = 10000 * (ANIO) + 100 * 8 + 15
	
	final static MAX_PARAMS = 200
	final static MAX_PAGE_MAIL = 8 
	final static MAX_TITULO = 64
	final static MAX_SIZE = 1024
	
	final static RESPUESTA = "Respuesta a: "
	
	final static ROL_ADMIN = "ROL_ADMIN"
	final static ROL_MEDIADOR = "ROL_MEDIADOR"
	final static ROL_APRENDIZ = "ROL_APRENDIZ"
	final static ROL_MIEMBRO =	"ROL_MIEMBRO"	
	
	final static MIEMBRO = "[Miembro]"
	final static APRENDIZ = "[Aprendiz]"
	final static MEDIADOR = "[Mediador]"
	final static ADMINISTRADOR = "[Administrador]"
	
	final static CUAT_AMBOS = "1|2"
	final static CUAT_UNO = "1"
	final static CUAT_DOS = "2"
	
	final static AUTOR_ANONIMO = "Anónimo"
}
