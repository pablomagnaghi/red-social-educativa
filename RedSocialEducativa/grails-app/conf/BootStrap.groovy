
import com.fiuba.*

class BootStrap {

    def init = { servletContext ->
		
		def ArrayList<Rol> roles = new ArrayList<Rol>()
		def rolAdmin = new Rol(authority: 'ROL_ADMIN')
		roles.add(rolAdmin)
		def rolMediador = new Rol(authority: 'ROL_MEDIADOR')
		roles.add(rolMediador)
		def rolAprendiz = new Rol(authority: 'ROL_APRENDIZ')
		roles.add(rolAprendiz)
		def rolMiembro = new Rol(authority: 'ROL_MIEMBRO')
		roles.add(rolMiembro)
		
		for(int i = 0; i<roles.size(); i++){
			if (!roles.get(i).validate()) {
				println roles.get(i).errors
			} else {
				println "Roles agregados a la bbdd:"
				roles.get(i).save()
				println roles.get(i).authority
			}
		}
		
		def ArrayList<Usuario> usuarios = new ArrayList<Usuario>()
		
		def usuarioPablo = new Usuario(username: "33300432", password: "33300432", apellido: "Magnaghi", nombres: "Pablo", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioPablo)
		def usuarioLuis = new Usuario(username: "31861315", password: "31861315", apellido: "Paniagua", nombres: "Luis", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioLuis)
		def usuarioAgus = new Usuario(username: "32725217", password: "32725217", apellido: "Milla", nombres: "Agustina", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioAgus)
		def usuarioUno = new Usuario(username: "11111111", password: "11111111", apellido: "ApUNO", nombres: "NoUno", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioUno)
		def usuarioDos = new Usuario(username: "22222222", password: "22222222", apellido: "ApDOS", nombres: "NoDOS", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioDos)
		def usuarioTres = new Usuario(username: "33333333", password: "33333333", apellido: "ApTRES", nombres: "NoTRES", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioTres)
		def usuarioCuatro = new Usuario(username: "44444444", password: "44444444", apellido: "ApCUATRO", nombres: "NoCUATRO", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioCuatro)
		def usuarioCinco = new Usuario(username: "55555555", password: "55555555", apellido: "ApCINCO", nombres: "NoCINCO", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioCinco)
		
		def usuarioSeis = new Usuario(username: "66666666", password: "66666666", apellido: "ApSeis", nombres: "NoSeis",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), enabled: false)
		usuarios.add(usuarioSeis)
		def usuarioSiete = new Usuario(username: "77777777", password: "77777777", apellido: "ApSiete", nombres: "NoSiete",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), enabled: false)
		usuarios.add(usuarioSiete)
		def usuarioOcho = new Usuario(username: "88888888", password: "88888888", apellido: "ApOcho", nombres: "NoOcho",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), enabled: false)
		usuarios.add(usuarioOcho)
		
		for(int i = 0; i<usuarios.size(); i++){
			if (!usuarios.get(i).validate()) {
				println usuarios.get(i).errors
			} else {
				println "Usuarios agregados a la bbdd:"
				usuarios.get(i).save()
				println usuarios.get(i).username
			}
		}

		// Administradores
		def ArrayList<Administrador> administradores = new ArrayList<Administrador>()
		
		def admPablo = new Administrador(usuario: usuarioPablo, rol: rolAdmin)
		administradores.add(admPablo)
		def admLuis = new Administrador(usuario: usuarioLuis, rol: rolAdmin)
		administradores.add(admLuis)
		
		for(int i = 0; i<administradores.size(); i++){
			if (!administradores.get(i).validate()) {
				println administradores.get(i).errors
			} else {
				println "Administradores agregados a la bbdd:"
				administradores.get(i).save()
				println administradores.get(i)
			}
		}
			
		// Mediadores
		
		def mediadorAgus = new Mediador(usuario: usuarioAgus, rol: rolMediador, jerarquia: "JTP");
		def mediadorUno = new Mediador(usuario: usuarioUno, rol: rolMediador, jerarquia: "JTP");
		def mediadorDos = new Mediador(usuario: usuarioCuatro, rol: rolMediador, jerarquia: "JTP");
		
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
		def aprendizCinco = new Aprendiz(usuario: usuarioCinco, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		
		//--Carga de cursos
		
		def cursoUno = new Curso(nroRelativo: "01", cuatDict: "1|2")
		cursoUno.addToMediadores(mediadorAgus)
		cursoUno.addToMediadores(mediadorUno)
		cursoUno.addToAprendices(aprendizUno)
		cursoUno.addToAprendices(aprendizDos)
		cursoUno.addToAprendices(aprendizTres)

		def cursoDos = new Curso(nroRelativo: "02", cuatDict: "1|2")
		cursoDos.addToMediadores(mediadorDos)
		cursoDos.addToAprendices(aprendizAgus)
		cursoDos.addToAprendices(aprendizUno)
		cursoDos.addToAprendices(aprendizTres)
		
		def cursoTres = new Curso(nroRelativo: "03", cuatDict: "1|2")
		cursoTres.addToMediadores(mediadorAgus)
		cursoTres.addToAprendices(aprendizTres)
		cursoTres.addToAprendices(aprendizCuatro)

		def cursoCuatro = new Curso(nroRelativo: "04", cuatDict: "1|2")
		cursoCuatro.addToMediadores(mediadorAgus)
		cursoCuatro.addToAprendices(aprendizCuatro)
		cursoCuatro.addToAprendices(aprendizCinco)

		def cursoCinco = new Curso(nroRelativo: "05", cuatDict: "1|2")
		cursoCinco.addToMediadores(mediadorAgus)
		cursoCinco.addToAprendices(aprendizCuatro)
		cursoCinco.addToAprendices(aprendizCinco)
		
		
		// Materias - con carga de cursos
		def ArrayList<Materia> materias = new ArrayList<Materia>()
		def materiaUno = new Materia(codigo: "75.01", nombre: "materiaUno", creditos: "6", contenidosMinimos: "contenidos")
		materiaUno.addToCursos(cursoUno)
		materiaUno.addToCursos(cursoDos)
		materiaUno.addToCursos(cursoTres)
		materias.add(materiaUno)
		def materiaDos = new Materia(codigo: "75.02", nombre: "materiaDos", creditos: "6", contenidosMinimos: "contenidos")
		materiaDos.addToCursos(cursoCuatro)
		materias.add(materiaDos)
		def materiaTres = new Materia(codigo: "75.03", nombre: "materiaTres", creditos: "6", contenidosMinimos: "contenidos")
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
