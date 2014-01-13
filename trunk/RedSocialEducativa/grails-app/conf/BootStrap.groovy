
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
			legajo: "11", padron: "11", email: "pany100@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioLuis)
		def usuarioAgus = new Usuario(username: "32725217", password: "32725217", apellido: "Milla", nombres: "Agustina", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioAgus)
		def usuarioMessi = new Usuario(username: "10101010", password: "10101010", apellido: "Zarate", nombres: "Facundo",
			legajo: "11", padron: "11", email: "nanozarate@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioMessi)

		def usuarioUno = new Usuario(username: "00000001", password: "00000001", apellido: "ApeUNO", nombres: "NomUno", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioUno)
		def usuarioDos = new Usuario(username: "00000002", password: "00000002", apellido: "ApeDOS", nombres: "NomDOS", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioDos)
		def usuarioTres = new Usuario(username: "00000003", password: "00000003", apellido: "ApeTRES", nombres: "NomTRES", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioTres)
		def usuarioCuatro = new Usuario(username: "00000004", password: "00000004", apellido: "ApeCUATRO", nombres: "NomCUATRO", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioCuatro)
		def usuarioCinco = new Usuario(username: "00000005", password: "00000005", apellido: "ApeCINCO", nombres: "NomCINCO", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioCinco)
		def usuarioSeis = new Usuario(username: "00000006", password: "00000006", apellido: "ApeSeis", nombres: "NomSeis",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioSeis)
		def usuarioSiete = new Usuario(username: "00000007", password: "00000007", apellido: "ApeSiete", nombres: "NomSiete",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioSiete)
		def usuarioOcho = new Usuario(username: "00000008", password: "00000008", apellido: "ApeOcho", nombres: "NomOcho",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioOcho)
		def usuarioNueve = new Usuario(username: "00000009", password: "00000009", apellido: "ApeNueve", nombres: "NomNueve",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioNueve)
		def usuarioDiez = new Usuario(username: "00000010", password: "00000010", apellido: "ApeDiez", nombres: "NomDiez",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioDiez)
		def usuarioOnce = new Usuario(username: "00000011", password: "00000011", apellido: "ApeOnce", nombres: "NomOnce",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioOnce)
		def usuarioDoce = new Usuario(username: "00000012", password: "00000012", apellido: "ApeDoce", nombres: "NomDoce",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), fechaMemb: new Date())
		usuarios.add(usuarioDoce)
		def usuarioTrece = new Usuario(username: "00000013", password: "00000013", apellido: "ApeTrece", nombres: "NomTrece",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), enabled: false)
		usuarios.add(usuarioTrece)
		def usuarioCatorce = new Usuario(username: "00000014", password: "00000014", apellido: "ApeCatorce", nombres: "NomCatorce",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: new Date(), enabled: false)
		usuarios.add(usuarioCatorce)
		
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
		def admMessi = new Administrador(usuario: usuarioMessi, rol: rolAdmin)
		administradores.add(admMessi)
			
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
		def mediadorUnoP = new Mediador(usuario: usuarioUno, rol: rolMediador, jerarquia: "AYU1");
		def mediadorDos = new Mediador(usuario: usuarioDos, rol: rolMediador, jerarquia: "JTP");
		def mediadorTres = new Mediador(usuario: usuarioTres, rol: rolMediador, jerarquia: "JTP");
		def mediadorCuatro = new Mediador(usuario: usuarioCuatro, rol: rolMediador, jerarquia: "JTP");
		def mediadorCinco = new Mediador(usuario: usuarioCinco, rol: rolMediador, jerarquia: "JTP");
		def mediadorSeis = new Mediador(usuario: usuarioSeis, rol: rolMediador, jerarquia: "JTP");
		
		// Aprendices
		def aprendizAgus = new Aprendiz(usuario: usuarioAgus, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		def aprendizUno = new Aprendiz(usuario: usuarioUno, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		def aprendizUnoP = new Aprendiz(usuario: usuarioUno, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		def aprendizUnoPP = new Aprendiz(usuario: usuarioUno, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		def aprendizDos = new Aprendiz(usuario: usuarioDos, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		def aprendizTres = new Aprendiz(usuario: usuarioTres, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		def aprendizCuatro = new Aprendiz(usuario: usuarioCuatro, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())		
		def aprendizCinco = new Aprendiz(usuario: usuarioCinco, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		def aprendizSeis = new Aprendiz(usuario: usuarioSeis, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		def aprendizSiete = new Aprendiz(usuario: usuarioSiete, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		def aprendizOcho = new Aprendiz(usuario: usuarioOcho, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		def aprendizNueve = new Aprendiz(usuario: usuarioNueve, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		def aprendizDiez = new Aprendiz(usuario: usuarioDiez, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		def aprendizOnce = new Aprendiz(usuario: usuarioOnce, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		def aprendizDoce = new Aprendiz(usuario: usuarioDoce, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: new Date())
		
		//--Carga de cursos
		
		def cursoUno = new Curso(nroRelativo: "01", cuatDict: "1|2")
		cursoUno.addToMediadores(mediadorAgus)
		cursoUno.addToMediadores(mediadorUno)
		cursoUno.addToAprendices(aprendizDos)
		cursoUno.addToAprendices(aprendizTres)
		cursoUno.addToAprendices(aprendizOcho)

		def cursoDos = new Curso(nroRelativo: "02", cuatDict: "1|2")
		cursoDos.addToMediadores(mediadorUnoP)
		cursoDos.addToMediadores(mediadorDos)
		cursoDos.addToAprendices(aprendizAgus)
		cursoDos.addToAprendices(aprendizCuatro)
		cursoDos.addToAprendices(aprendizNueve)
		
		def cursoTres = new Curso(nroRelativo: "03", cuatDict: "1|2")
		cursoTres.addToMediadores(mediadorTres)
		cursoTres.addToAprendices(aprendizUno)
		cursoTres.addToAprendices(aprendizCinco)
		cursoTres.addToAprendices(aprendizDiez)

		def cursoCuatro = new Curso(nroRelativo: "04", cuatDict: "1|2")
		cursoCuatro.addToMediadores(mediadorCuatro)
		cursoCuatro.addToAprendices(aprendizUnoP)
		cursoCuatro.addToAprendices(aprendizSeis)
		cursoCuatro.addToAprendices(aprendizOnce)

		def cursoCinco = new Curso(nroRelativo: "05", cuatDict: "1|2")
		cursoCinco.addToMediadores(mediadorCinco)
		cursoCinco.addToMediadores(mediadorSeis)
		cursoCinco.addToAprendices(aprendizUnoPP)
		cursoCinco.addToAprendices(aprendizSiete)
		cursoCinco.addToAprendices(aprendizDoce)
		
		
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
