import java.util.Date;

import com.fiuba.Accion
import com.fiuba.Rol

import com.fiuba.Membresia
import com.fiuba.Administrador
import com.fiuba.Mediador
import com.fiuba.Aprendiz

import com.fiuba.Cuatrimestre
import com.fiuba.Curso
import com.fiuba.Materia
import com.fiuba.Mediador
import com.fiuba.Aprendiz

import com.fiuba.Red

class BootStrap {

    def init = { servletContext ->
		
		//Acciones
		def ArrayList<Accion> acciones = new ArrayList<Accion>()
		
		def accionMaterias = new Accion(nombre:"ABM Materias")
		acciones.add(accionMaterias)
		def accionAnunciosGenerales = new Accion(nombre:"ABM Anuncios Generales")
		acciones.add(accionAnunciosGenerales)
		def accionConfiguracion = new Accion(nombre:"ABM Configuración")
		acciones.add(accionConfiguracion)
		def accionMiembros = new Accion(nombre:"ABM Miembros")
		acciones.add(accionMiembros)
		def accionRoles = new Accion(nombre:"ABM Roles")
		acciones.add(accionRoles)
		def accionMediadores = new Accion(nombre:"ABM Mediadores")
		acciones.add(accionMediadores)
		def accionAprendices = new Accion(nombre:"ABM Aprendices")
		acciones.add(accionAprendices)
		def accionTemas = new Accion(nombre:"ABM Temas")
		acciones.add(accionTemas)
		def accionAnunciosCurso = new Accion(nombre:"ABM Anuncios Cursos")
		acciones.add(accionAnunciosCurso)
		
		for(int i = 0; i<acciones.size(); i++){
			if (!acciones.get(i).validate()) {
				println acciones.get(i).errors
			} else {
				println "Acciones agregadas a la bbdd:"
				acciones.get(i).save()
				println acciones.get(i).nombre
			}
		}
		
		//Roles - con carga de acciones
		def ArrayList<Rol> roles = new ArrayList<Rol>()
		
		def rolAdministrador = new Rol(nombre: "Administrador", acciones : [accionMaterias, accionAnunciosGenerales, accionConfiguracion, accionMiembros, accionMediadores, accionRoles])
		roles.add(rolAdministrador)
		def rolMediador = new Rol(nombre: "Mediador", acciones : [accionAprendices, accionTemas, accionAnunciosCurso])
		roles.add(rolMediador)
		def rolAprendiz = new Rol (nombre: "Aprendiz")
		roles.add(rolAprendiz)
		// Visitante que solicito membresia, ya cargo sus datos y esta esperando confirmacion
		def rolMiembro = new Rol (nombre: "Miembro")
		roles.add(rolMiembro)

		for(int i = 0; i<roles.size(); i++){
			if (!roles.get(i).validate()) {
				println roles.get(i).errors
			} else {
				println "Roles agregados a la bbdd:"
				roles.get(i).save()
				println roles.get(i).nombre
			}
		}
		
		// Membresias
		def ArrayList<Membresia> membresias = new ArrayList<Membresia>()
		
		// Membresias
		def membresiaPablo = new Membresia(dni: "33300432", password: "33300432", apellido: "Magnaghi", nombres: "Pablo", 
			legajo: "11", padron: "11", email: "pablo@gmail.com", membresia: true, fechaSolicitud: new Date(),
			fechaMemb: new Date())
		membresias.add(membresiaPablo)
		def membresiaLuis = new Membresia(dni: "31861315", password: "31861315", apellido: "Paniagua", nombres: "Luis", 
			legajo: "11", padron: "11", email: "luis@gmail.com", membresia: true, fechaSolicitud: new Date(),
			fechaMemb: new Date())
		membresias.add(membresiaLuis)
		def membresiaAgus = new Membresia(dni: "32725217", password: "32725217", apellido: "Milia", nombres: "Agustina", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", membresia: true, fechaSolicitud: new Date());
		membresias.add(membresiaAgus)	
		
		def membresiaUno = new Membresia(dni: "11111111", password: "33300432", apellido: "Magnaghi", nombres: "Pablo UNO",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", membresia: true, fechaSolicitud: new Date(),
			fechaMemb: new Date())
		membresias.add(membresiaUno)
		def membresiaDos = new Membresia(dni: "22222222", password: "33300432", apellido: "Magnaghi", nombres: "Pablo DOS",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", membresia: false, fechaSolicitud: new Date(),
			fechaMemb: new Date())
		membresias.add(membresiaDos)
		def membresiaTres = new Membresia(dni: "33333333", password: "33300432", apellido: "Magnaghi", nombres: "Pablo TRES",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", membresia: false, fechaSolicitud: new Date(),
			fechaMemb: new Date())
		membresias.add(membresiaTres)

		def membresiaCuatro = new Membresia(dni: "44444444", password: "33300432", apellido: "Magnaghi", nombres: "Pablo CUATRO",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", membresia: true, fechaSolicitud: new Date(),
			fechaMemb: new Date())
		membresias.add(membresiaCuatro)
		
		for(int i = 0; i<membresias.size(); i++){
			if (!membresias.get(i).validate()) {
				println membresias.get(i).errors
			} else {
				println "Miembros agregados a la bbdd:"
				membresias.get(i).save()
				println membresias.get(i).dni
			}
		}
		
		// Carga de usuarios
		
		// Administradores - con carga de rol
		def ArrayList<Administrador> administradores = new ArrayList<Administrador>()
		
		def admPablo = new Administrador (membresia: membresiaPablo, rol: rolAdministrador);
		administradores.add(admPablo)
		def admLuis = new Administrador (membresia: membresiaLuis, rol: rolAdministrador);
		administradores.add(admLuis)
		
		for(int i = 0; i<administradores.size(); i++){
			if (!administradores.get(i).validate()) {
				println administradores.get(i).errors
			} else {
				println "Administradores agregados a la bbdd:"
				administradores.get(i).save()
				println administradores.get(i).membresia.dni
			}
		}
		
		// Mediador
		def ArrayList<Mediador> mediadores = new ArrayList<Mediador>()
		
		def mediadorAgus = new Mediador(membresia: membresiaAgus, rol: rolMediador, jerarquia: "JTP");
		mediadores.add(mediadorAgus)
		
		def mediadorCuatro = new Mediador(membresia: membresiaCuatro, rol: rolMediador, jerarquia: "JTP");
		mediadores.add(mediadorCuatro)
		
		for(int i = 0; i<mediadores.size(); i++){
			if (!mediadores.get(i).validate()) {
				println mediadores.get(i).errors
			} else {
				println "Mediadores agregados a la bbdd:"
				mediadores.get(i).save()
				println mediadores.get(i).membresia.dni
			}
		}
		
		// Aprendices
		def ArrayList<Aprendiz> aprendices = new ArrayList<Aprendiz>()
		
		def aprendizAgus = new Aprendiz(membresia: membresiaAgus, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		aprendices.add(aprendizAgus)
		
		def aprendizCuatro = new Aprendiz(membresia: membresiaCuatro, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		aprendices.add(aprendizAgus)
		
		for(int i = 0; i<aprendices.size(); i++){
			if (!aprendices.get(i).validate()) {
				println aprendices.get(i).errors
			} else {
				println "Aprendices agregadas a la bbdd:"
				aprendices.get(i).save()
				println aprendices.get(i).membresia.dni
			}
		}
		
		
		//--Carga cuatrimestre, curso, materia
			
		// Cuatrimestre - con carga de aprendices
		
		def ArrayList<Cuatrimestre> cuatrimestres = new ArrayList<Cuatrimestre>()
	
		def cuatrimestreUno = new Cuatrimestre(anio: "2014", numero: 1, habGrupos: false, nroUltGrupo: "0", 
			aprendices: [aprendizAgus])
		cuatrimestres.add(cuatrimestreUno)
		def cuatrimestreDos = new Cuatrimestre(anio: "2014", numero: 1, habGrupos: false, nroUltGrupo: "0", 
			aprendices: [aprendizAgus])
		cuatrimestres.add(cuatrimestreDos)
		def cuatrimestreTres = new Cuatrimestre(anio: "2014", numero: 1, habGrupos: false, nroUltGrupo: "0", 
			aprendices: [aprendizCuatro])
		cuatrimestres.add(cuatrimestreTres)

		for(int i = 0; i<cuatrimestres.size(); i++){
			if (!cuatrimestres.get(i).validate()) {
				println cuatrimestres.get(i).errors
			} else {
				println "Cuatrimestres agregadas a la bbdd:"
				cuatrimestres.get(i).save()
				println cuatrimestres.get(i).anio
			}
		}
	
		// Cursos - con carga de cuatrimestres y mediadores
		def ArrayList<Curso> cursos = new ArrayList<Curso>()
		
		def cursoUno = new Curso(nroRelativo: "01", cuatDict: "1|2", mediadores: [mediadorCuatro], 
			cuatrimestres: [cuatrimestreUno, cuatrimestreDos])
		cursos.add(cursoUno)
		def cursoDos = new Curso(nroRelativo: "02", cuatDict: "1|2", mediadores: [mediadorCuatro], 
			cuatrimestres: [cuatrimestreDos])
		cursos.add(cursoDos)
		def cursoTres = new Curso(nroRelativo: "03", cuatDict: "1|2", mediadores: [mediadorAgus], 
			cuatrimestres: [cuatrimestreTres])
		cursos.add(cursoTres)
		
		for(int i = 0; i<cursos.size(); i++){
			if (!cursos.get(i).validate()) {
				println cursos.get(i).errors
			} else {
				println "Cursos agregadas a la bbdd:"
				cursos.get(i).save()
				println cursos.get(i).nroRelativo
			}
		}

		// Materias - con carga de cursos
		def ArrayList<Materia> materias = new ArrayList<Materia>()		
		def materiaUno = new Materia(codigo: "75.01", nombre: "materiaUno", creditos: "6", 
			contenidosMinimos: "contenidos", cursos: [cursoUno, cursoDos, cursoTres])
		materias.add(materiaUno)
		def materiaDos = new Materia(codigo: "75.02", nombre: "materiaDos", creditos: "6", 
			contenidosMinimos: "contenidos", cursos: [cursoDos])
		materias.add(materiaDos)
		def materiaTres = new Materia(codigo: "75.03", nombre: "materiaTres", creditos: "6", 
			contenidosMinimos: "contenidos", cursos: [cursoTres])
		materias.add(materiaTres)
		
		for(int i = 0; i<materias.size(); i++){
			if (!materias.get(i).validate()) {
				println materias.get(i).errors
			} else {
				println "Materias agregadas a la bbdd:"
				materias.get(i).save()
				println materias.get(i).codigo
			}
		}
		
		// --Agrego todo a la red-- //

		def red = new Red (membresias: [membresiaPablo, membresiaLuis, membresiaAgus, membresiaUno, membresiaDos, membresiaTres],
			administradores: [admPablo, admLuis], materias: [materiaUno, materiaDos, materiaTres])
		
		if (!red.validate()) {
			println red.get.errors
		} else {
			println "Red agregada a la bbdd:"
			red.save()
			println red.titulo
		}
		
    }
    def destroy = {
    }
}
