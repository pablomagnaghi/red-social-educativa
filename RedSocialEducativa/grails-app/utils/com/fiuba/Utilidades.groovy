package com.fiuba

import java.util.Date;

class Utilidades {
	
	final static TITULO_RED = "Red Social Educativa del Departamento de Computacion de la FIUBA"
	final static CICLO_CONSERVACION = 4
	
	final static FORMATO_FECHA_NUMERICO = "yyyyMMdd"
	final static FORMATO_FECHA = "yyyy-MM-dd"
	
	final static ANIO_INICIAL = 1900
	final static ANIO = new Date().getYear() + ANIO_INICIAL
	final static MES =  new Date().getMonth() + 1
	final static DIA = new Date().getAt(Calendar.DAY_OF_MONTH)
	// TODO ir tocando la fecha para pruebas
	final static FECHA = 20140505//10000 * (ANIO) + 100 * MES + DIA
	
	final static ANIO_PROXIMA_SEMANA = new Date(new Date().getTime() + (7 * 24* 60 * 60 * 1000)).getYear() + ANIO_INICIAL
	final static MES_PROXIMA_SEMANA =  new Date(new Date().getTime() + (7 * 24* 60 * 60 * 1000)).getMonth() + 1
	final static DIA_PROXIMA_SEMANA = new Date(new Date().getTime() + (7 * 24* 60 * 60 * 1000)).getAt(Calendar.DAY_OF_MONTH)
	final static FECHA_PROXIMA_SEMANA = 10000 * (ANIO_PROXIMA_SEMANA) + 100 * MES_PROXIMA_SEMANA + DIA_PROXIMA_SEMANA

	final static MAX_PARAMS = 5
	
	final static MENSAJE_BIENVENIDA = "Bienvenido a la Red Social del Departamento de Computacion de la FIUBA"
	
	final static ROL_ADMIN = "ROL_ADMIN"
	final static ROL_MEDIADOR = "ROL_MEDIADOR"
	final static ROL_APRENDIZ = "ROL_APRENDIZ"
	final static ROL_MIEMBRO =	"ROL_MIEMBRO"	
	
	final static VISITANTE = "[VISITANTE]"
	final static MIEMBRO = "[MIEMBRO]"
	final static APRENDIZ = "[APRENDIZ]"
	final static MEDIADOR = "[MEDIADOR]"
	final static ADMINISTRADOR = "[ADMINISTRADOR]"
	
	
	final static CUAT_AMBOS = "1|2"
	final static CUAT_UNO = "1"
	final static CUAT_DOS = "2"
}
