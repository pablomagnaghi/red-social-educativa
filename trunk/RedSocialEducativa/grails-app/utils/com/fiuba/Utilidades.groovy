package com.fiuba

class Utilidades {
	
	final static TITULO_RED = "Red Social Educativa del Departamento de Computacion de la Fiuba"
		
	final static CICLO_CONSERVACION = 4
	
	final static INICIO_PRIMER_CUATRIMESTRE = 20140228
	
	final static INICIO_SEGUNDO_CUATRIMESTRE = 20140815
	
	final static ANIO = new Date().getYear() + 1900
	
	final static MES =  new Date().getMonth() + 1
	
	final static DIA = new Date().getAt(Calendar.DAY_OF_MONTH)
	
	final static FECHA = 10000 * (ANIO) + 100 * MES + DIA

}
