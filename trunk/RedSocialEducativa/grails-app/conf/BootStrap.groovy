import java.util.Date;

import com.fiuba.*

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
		
		//Roles
		def ArrayList<Rol> roles = new ArrayList<Rol>()
		
		def rolAdministrador = new Rol(nombre: "Administrador", acciones: [accionMaterias, accionAnunciosGenerales, accionConfiguracion, accionMiembros, accionMediadores, accionRoles])
		roles.add(rolAdministrador)
		def rolMediador = new Rol(nombre: "Mediador", acciones: [accionAprendices, accionTemas, accionAnunciosCurso])
		roles.add(rolMediador)
		def rolAprendiz = new Rol (nombre: "Aprendiz")
		roles.add(rolAprendiz)
		// Visitante que solicito usuario, ya cargo sus datos y esta esperando confirmacion
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
		
		// Usuarios
		def ArrayList<Usuario> usuarios = new ArrayList<Usuario>()
		
		def usuarioPablo = new Usuario(dni: "33300432", password: "33300432", apellido: "Magnaghi", nombres: "Pablo", 
			legajo: "11", padron: "11", email: "pablo@gmail.com", membresia: true, fechaSolicitud: new Date(),
			fechaMemb: new Date())
		usuarios.add(usuarioPablo)
		def usuarioLuis = new Usuario(dni: "31861315", password: "31861315", apellido: "Paniagua", nombres: "Luis", 
			legajo: "11", padron: "11", email: "luis@gmail.com", membresia: true, fechaSolicitud: new Date(),
			fechaMemb: new Date())
		usuarios.add(usuarioLuis)
		def usuarioAgus = new Usuario(dni: "32725217", password: "32725217", apellido: "Milia", nombres: "Agustina", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", membresia: true, fechaSolicitud: new Date());
		usuarios.add(usuarioAgus)	
		
		def usuarioUno = new Usuario(dni: "11111111", password: "33300432", apellido: "Magnaghi", nombres: "Pablo UNO",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", membresia: true, fechaSolicitud: new Date(),
			fechaMemb: new Date())
		usuarios.add(usuarioUno)
		def usuarioDos = new Usuario(dni: "22222222", password: "33300432", apellido: "Magnaghi", nombres: "Pablo DOS",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", membresia: true, fechaSolicitud: new Date(),
			fechaMemb: new Date())
		usuarios.add(usuarioDos)
		def usuarioTres = new Usuario(dni: "33333333", password: "33300432", apellido: "Magnaghi", nombres: "Pablo TRES",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", membresia: true, fechaSolicitud: new Date(),
			fechaMemb: new Date())
		usuarios.add(usuarioTres)
		def usuarioCuatro = new Usuario(dni: "44444444", password: "33300432", apellido: "Magnaghi", nombres: "Pablo CUATRO",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", membresia: true, fechaSolicitud: new Date(),
			fechaMemb: new Date())
		usuarios.add(usuarioCuatro)
		
		def usuarioCinco = new Usuario(dni: "55555555", password: "33300432", apellido: "Magnaghi", nombres: "Pablo CINCO",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", membresia: false, fechaSolicitud: new Date(),
			fechaMemb: new Date())
		usuarios.add(usuarioCinco)		
		def usuarioSeis = new Usuario(dni: "66666666", password: "33300432", apellido: "Magnaghi", nombres: "Pablo SEIS",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", membresia: false, fechaSolicitud: new Date(),
			fechaMemb: new Date())
		usuarios.add(usuarioSeis)
		def usuarioSiete = new Usuario(dni: "77777777", password: "33300432", apellido: "Magnaghi", nombres: "Pablo SIETE",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", membresia: false, fechaSolicitud: new Date(),
			fechaMemb: new Date())
		usuarios.add(usuarioSiete)
		
		for(int i = 0; i<usuarios.size(); i++){
			if (!usuarios.get(i).validate()) {
				println usuarios.get(i).errors
			} else {
				println "Miembros agregados a la bbdd:"
				usuarios.get(i).save()
				println usuarios.get(i).dni
			}
		}
		
		// Administradores
		def ArrayList<Administrador> administradores = new ArrayList<Administrador>()
		
		def admPablo = new Administrador(usuario: usuarioPablo, rol: rolAdministrador)
		administradores.add(admPablo)
		def admLuis = new Administrador(usuario: usuarioLuis, rol: rolAdministrador)
		administradores.add(admLuis)
		
		for(int i = 0; i<administradores.size(); i++){
			if (!administradores.get(i).validate()) {
				println administradores.get(i).errors
			} else {
				println "Administradores agregados a la bbdd:"
				administradores.get(i).save()
				println administradores.get(i).usuario.dni
			}
		}
		
		// Mediadores
		def mediadorAgus = new Mediador(usuario: usuarioAgus, rol: rolMediador, jerarquia: "JTP")
		def mediadorUno = new Mediador(usuario: usuarioUno, rol: rolMediador, jerarquia: "JTP")
		def mediadorCuatro = new Mediador(usuario: usuarioCuatro, rol: rolMediador, jerarquia: "JTP")
	
		// Aprendices
		def aprendizAgus = new Aprendiz(usuario: usuarioAgus, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		def aprendizUno = new Aprendiz(usuario: usuarioUno, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())		
		def aprendizDos = new Aprendiz(usuario: usuarioDos, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		def aprendizTres = new Aprendiz(usuario: usuarioTres, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		def aprendizCuatro = new Aprendiz(usuario: usuarioCuatro, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		
		// Cursos
		
		def cursoUno = new Curso(nroRelativo: "01", cuatDict: "1|2")
		cursoUno.addToMediadores(mediadorCuatro)
		cursoUno.addToMediadores(mediadorUno)
		cursoUno.addToAprendices(aprendizAgus)
		cursoUno.addToAprendices(aprendizDos)
		cursoUno.addToAprendices(aprendizTres)
		
		def cursoDos = new Curso(nroRelativo: "02", cuatDict: "1|2")
		cursoDos.addToMediadores(mediadorCuatro)
		cursoDos.addToAprendices(aprendizAgus)
		cursoDos.addToAprendices(aprendizDos)
		cursoDos.addToAprendices(aprendizTres)
		
		def cursoTres = new Curso(nroRelativo: "03", cuatDict: "1|2")
		cursoTres.addToMediadores(mediadorAgus)
		cursoTres.addToAprendices(aprendizTres)
		cursoTres.addToAprendices(aprendizCuatro)
		
		def cursoCuatro = new Curso(nroRelativo: "04", cuatDict: "1|2")
		cursoCuatro.addToMediadores(mediadorAgus)
		cursoCuatro.addToAprendices(aprendizCuatro)
		
		def cursoCinco = new Curso(nroRelativo: "05", cuatDict: "1|2")
		cursoCinco.addToMediadores(mediadorAgus)
		cursoCinco.addToAprendices(aprendizCuatro)
		
		// Materias - con carga de cursos
		def ArrayList<Materia> materias = new ArrayList<Materia>()
		def materiaUno = new Materia(codigo: "75.01", nombre: "materiaUno", creditos: "6",
			contenidosMinimos: "contenidos")
		materiaUno.addToCursos(cursoUno)
		materiaUno.addToCursos(cursoDos)
		materiaUno.addToCursos(cursoTres)
		materias.add(materiaUno)
		def materiaDos = new Materia(codigo: "75.02", nombre: "materiaDos", creditos: "6",
			contenidosMinimos: "contenidos")
		materiaDos.addToCursos(cursoCuatro)
		materias.add(materiaDos)
		def materiaTres = new Materia(codigo: "75.03", nombre: "materiaTres", creditos: "6",
			contenidosMinimos: "contenidos")
		materiaTres.addToCursos(cursoCinco)
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
		
		// Red

		def red = new Red() 
		
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
