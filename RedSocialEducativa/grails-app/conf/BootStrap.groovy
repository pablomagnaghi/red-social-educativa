
import java.util.Date;

import com.fiuba.*
import com.facultad.UsuarioService
import com.mensajeria.Hilo
import com.mensajeria.Mensaje
import org.codehaus.groovy.grails.commons.ApplicationAttributes

class BootStrap {
	def usuarioService
	def mensajeService
	
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
		
		def usuarioPablo = new Usuario(username: "33300432", password: "33300432", apellido: "Magnaghi", nombres: "Pablo", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-mm-dd"), 
			fechaMemb: (new Date()).format("yyyy-mm-dd"))
		usuarioService.guardar(usuarioPablo)
		def usuarioLuis = new Usuario(username: "31861315", password: "31861315", apellido: "Paniagua", nombres: "Luis", 
			legajo: "11", padron: "11", email: "pany100@gmail.com", fechaSolicitud: (new Date()).format("yyyy-mm-dd"), 
			fechaMemb: (new Date()).format("yyyy-mm-dd"))
		usuarioService.guardar(usuarioLuis)
		def usuarioAgus = new Usuario(username: "32725217", password: "32725217", apellido: "Milla", nombres: "Agustina", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-mm-dd"), 
			fechaMemb: (new Date()).format("yyyy-mm-dd"))
		usuarioService.guardar(usuarioAgus)
		def usuarioMessi = new Usuario(username: "10101010", password: "10101010", apellido: "Zarate", nombres: "Facundo",
			legajo: "11", padron: "11", email: "nanozarate@gmail.com", fechaSolicitud: (new Date()).format("yyyy-mm-dd"), 
			fechaMemb: (new Date()).format("yyyy-mm-dd"))
		usuarioService.guardar(usuarioMessi)

		def usuarioUno = new Usuario(username: "00000001", password: "00000001", apellido: "ApeUNO", nombres: "NomUno", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-mm-dd"), 
			fechaMemb: (new Date()).format("yyyy-mm-dd"))
		usuarioService.guardar(usuarioUno)
		def usuarioDos = new Usuario(username: "00000002", password: "00000002", apellido: "ApeDOS", nombres: "NomDOS", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-mm-dd"), 
			fechaMemb: (new Date()).format("yyyy-mm-dd"))
		usuarioService.guardar(usuarioDos)
		def usuarioTres = new Usuario(username: "00000003", password: "00000003", apellido: "ApeTRES", nombres: "NomTRES", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-mm-dd"), 
			fechaMemb: (new Date()).format("yyyy-mm-dd"))
		usuarioService.guardar(usuarioTres)
		def usuarioCuatro = new Usuario(username: "00000004", password: "00000004", apellido: "ApeCUATRO", nombres: "NomCUATRO", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-mm-dd"), 
			fechaMemb: (new Date()).format("yyyy-mm-dd"))
		usuarioService.guardar(usuarioCuatro)
		def usuarioCinco = new Usuario(username: "00000005", password: "00000005", apellido: "ApeCINCO", nombres: "NomCINCO", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-mm-dd"), 
			fechaMemb: (new Date()).format("yyyy-mm-dd"))
		usuarioService.guardar(usuarioCinco)
		def usuarioSeis = new Usuario(username: "00000006", password: "00000006", apellido: "ApeSeis", nombres: "NomSeis",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-mm-dd"), 
			fechaMemb: (new Date()).format("yyyy-mm-dd"))
		usuarioService.guardar(usuarioSeis)
		def usuarioSiete = new Usuario(username: "00000007", password: "00000007", apellido: "ApeSiete", nombres: "NomSiete",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-mm-dd"), 
			fechaMemb: (new Date()).format("yyyy-mm-dd"))
		usuarioService.guardar(usuarioSiete)
		def usuarioOcho = new Usuario(username: "00000008", password: "00000008", apellido: "ApeOcho", nombres: "NomOcho",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-mm-dd"), 
			fechaMemb: (new Date()).format("yyyy-mm-dd"))
		usuarioService.guardar(usuarioOcho)
		def usuarioNueve = new Usuario(username: "00000009", password: "00000009", apellido: "ApeNueve", nombres: "NomNueve",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-mm-dd"), 
			fechaMemb: (new Date()).format("yyyy-mm-dd"))
		usuarioService.guardar(usuarioNueve)
		def usuarioDiez = new Usuario(username: "00000010", password: "00000010", apellido: "ApeDiez", nombres: "NomDiez",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-mm-dd"), 
			fechaMemb: (new Date()).format("yyyy-mm-dd"))
		usuarioService.guardar(usuarioDiez)
		def usuarioOnce = new Usuario(username: "00000011", password: "00000011", apellido: "ApeOnce", nombres: "NomOnce",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-mm-dd"), 
			fechaMemb: (new Date()).format("yyyy-mm-dd"))
		usuarioService.guardar(usuarioOnce)
		def usuarioDoce = new Usuario(username: "00000012", password: "00000012", apellido: "ApeDoce", nombres: "NomDoce",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-mm-dd"), 
			fechaMemb: (new Date()).format("yyyy-mm-dd"))
		usuarioService.guardar(usuarioDoce)
		
		def usuarioTrece = new Usuario(username: "00000013", password: "00000013", apellido: "ApeTrece", nombres: "NomTrece",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-mm-dd"), enabled: false)
		usuarioService.guardar(usuarioTrece)
		def usuarioCatorce = new Usuario(username: "00000014", password: "00000014", apellido: "ApeCatorce", nombres: "NomCatorce",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-mm-dd"), enabled: false)
		usuarioService.guardar(usuarioCatorce)
		
		// Mensajes
		
		def mensajePabloToLuis = new Mensaje(emisor: usuarioPablo, receptor: usuarioLuis, asunto: "Mensaje de prueba",
		cuerpo: "Vamos por el campeonato", fecha : new Date())
		mensajeService.nuevoMensaje(mensajePabloToLuis)
		def mensajeAgusToLuis = new Mensaje(emisor: usuarioAgus, receptor: usuarioLuis, asunto: "Mensaje de Agus a Luis 1",
		cuerpo: "Buenas noches!", fecha : new Date())
		mensajeService.nuevoMensaje(mensajeAgusToLuis)
		def mensajePabloToLuisDos = new Mensaje(emisor: usuarioPablo, receptor: usuarioLuis, asunto: "Mensaje de Pablo a Luis 2",
		cuerpo: "Vamos por el campeonato mundial", fecha : new Date())
		mensajeService.nuevoMensaje(mensajePabloToLuisDos)
		def mensajeLuisToPablo = new Mensaje(emisor: usuarioLuis, receptor: usuarioPablo, asunto: "Contestación de Luis a Pablo",
		cuerpo: "Sin dudas vamos por eso", fecha : new Date())
		mensajeService.nuevoMensaje(mensajeLuisToPablo)
		def mensajeLuisToAgus = new Mensaje(emisor: usuarioLuis, receptor: usuarioAgus, asunto: "Respuesta a Agus",
		cuerpo: "Buen dia!!", fecha : new Date())
		mensajeService.nuevoMensaje(mensajeLuisToAgus)
		def mensajePabloToAgus = new Mensaje(emisor: usuarioPablo, receptor: usuarioAgus, asunto: "Mensaje de Pablo a Agus ",
		cuerpo: "Volver al futuro 2", fecha : new Date())
		mensajeService.nuevoMensaje(mensajePabloToAgus)
		def mensajeAgusToLuisDos = new Mensaje(emisor: usuarioAgus, receptor: usuarioLuis, asunto: "Mensaje de Agus a Luis 2",
		cuerpo: "Hola como estas?", fecha : new Date())
		mensajeService.nuevoMensaje(mensajeAgusToLuisDos)
	
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
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-mm-dd"))
		def aprendizUno = new Aprendiz(usuario: usuarioUno, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-mm-dd"))
		def aprendizUnoP = new Aprendiz(usuario: usuarioUno, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-mm-dd"))
		def aprendizUnoPP = new Aprendiz(usuario: usuarioUno, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-mm-dd"))
		def aprendizDos = new Aprendiz(usuario: usuarioDos, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-mm-dd"))
		def aprendizTres = new Aprendiz(usuario: usuarioTres, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-mm-dd"))
		def aprendizCuatro = new Aprendiz(usuario: usuarioCuatro, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-mm-dd"))		
		def aprendizCinco = new Aprendiz(usuario: usuarioCinco, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-mm-dd"))
		def aprendizSeis = new Aprendiz(usuario: usuarioSeis, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-mm-dd"))
		def aprendizSiete = new Aprendiz(usuario: usuarioSiete, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-mm-dd"))
		def aprendizOcho = new Aprendiz(usuario: usuarioOcho, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-mm-dd"))
		def aprendizNueve = new Aprendiz(usuario: usuarioNueve, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-mm-dd"))
		def aprendizDiez = new Aprendiz(usuario: usuarioDiez, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-mm-dd"))
		def aprendizOnce = new Aprendiz(usuario: usuarioOnce, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-mm-dd"))
		def aprendizDoce = new Aprendiz(usuario: usuarioDoce, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-mm-dd"))
		
		// Categorias
		def ArrayList<Categoria> categorias = new ArrayList<Categoria>()
		
		def categoriaArticulo = new Categoria(nombre: "Articulo")
		categorias.add(categoriaArticulo)
		def categoriaPresentacion = new Categoria(nombre: "Presentacion")
		categorias.add(categoriaPresentacion)
		def categoriaCuestionario = new Categoria(nombre: "Cuestionario")
		categorias.add(categoriaCuestionario)
		def categoriaEjerciciosPlanteados = new Categoria(nombre: "EjerciciosPlanteados")
		categorias.add(categoriaEjerciciosPlanteados)
		def categoriaEjerciciosResueltos = new Categoria(nombre: "EjerciciosResueltos")
		categorias.add(categoriaEjerciciosResueltos)
		def categoriaTrabajo = new Categoria(nombre: "Trabajo")
		categorias.add(categoriaTrabajo)
		def categoriaRefBibliografica = new Categoria(nombre: "RefBibliografica")
		categorias.add(categoriaRefBibliografica )
		def categoriaEnlace = new Categoria(nombre: "Enlace")
		categorias.add(categoriaEnlace)
		def categoriaGlosario = new Categoria(nombre: "Glosario")
		categorias.add(categoriaGlosario)
		def categoriaEnunciadoDeEvaluacion = new Categoria(nombre: "EnunciadoDeEvaluacion")
		categorias.add(categoriaEnunciadoDeEvaluacion)
		def categoriaOtros = new Categoria(nombre: "Otros")
		categorias.add(categoriaOtros)
		
		for(int i = 0; i<categorias.size(); i++){
			if (!categorias.get(i).validate()) {
				println categorias.get(i).errors
			} else {
				println "Categorias agregadas nombre"
				categorias.get(i).save()
				println categorias.get(i).nombre
			}
		}
	
		// TODO Foros
		
		// Foros de un tema
		
		def foroTemaUnoCursoUno = new ForoTema(nombre: "ForoGeneralTemaUnoCursoUno")
		def foroTemaDosCursoUno = new ForoTema(nombre: "ForoGeneralTemaDosCursoUno")
		def foroTemaUnoCursoDos = new ForoTema(nombre: "ForoGeneralTemaUnoCursoDos")
		
		// Foros generales de curso
		
		def foroCursoUno = new ForoCurso(nombre: "ForoGeneralCursoUno")
		def foroCursoDos = new ForoCurso(nombre: "ForoGeneralCursoDos")
		def foroCursoTres = new ForoCurso(nombre: "ForoGeneralCursoTres")
		def foroCursoCuatro = new ForoCurso(nombre: "ForoGeneralCursoCuatro")
		def foroCursoCinco = new ForoCurso(nombre: "ForoGeneralCursoCinco")
		
		// Publicaciones generales
		
		def publicacionGeneralUno = new PublicacionGeneral(titulo: "PublicacionGeneralUno", contenido: "Contenido", 
			responsable: "Pablo Magnaghi (Administrador)", dni: "33300432", fecha: (new Date()).format("yyyy-mm-dd"), 
			hora: (new Date()).getTimeString())
		
		def publicacionGeneralDos = new PublicacionGeneral(titulo: "PublicacionGeneralDos", contenido: "Contenido", 
			responsable: "Pablo Magnaghi (Administrador)", dni: "33300432", fecha: (new Date()).format("yyyy-mm-dd"), 
			hora: (new Date()).getTimeString())

		def publicacionGeneralTres = new PublicacionGeneral(titulo: "PublicacionGeneralTres", contenido: "Contenido", 
			responsable: "Pablo Magnaghi (Administrador)", dni: "33300432", fecha: (new Date()).format("yyyy-mm-dd"), 
			hora: (new Date()).getTimeString())
				
		def foroGeneral = new ForoGeneral(nombre: "Foro general")
		foroGeneral.addToPublicaciones(publicacionGeneralUno)
		foroGeneral.addToPublicaciones(publicacionGeneralDos)
		foroGeneral.addToPublicaciones(publicacionGeneralTres)
		
		if (!foroGeneral.validate()) {
			println foroGeneral.errors
		} else {
			println "foroGeneral agregada a la bbdd:"
			foroGeneral.save()
			println foroGeneral
		}
			
		// TODO aca termina la zona de pruebas
		
		// Materiales, temas, contenidos
		
		// Material de contenido
		def materialContenidoUno = new MaterialContenido(titulo: "material contenido 1", descripcion: "opcional", autor: "anonimo", 
			fecha: (new Date()).format("yyyy-mm-dd"), responsable: "responsable", categoria: Categoria.findByNombre("RefBibliografica"))
		def materialContenidoDos = new MaterialContenido(titulo: "material contenido 2", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-mm-dd"), responsable: "responsable", categoria: Categoria.findByNombre("Presentacion"))
		def materialContenidoTres = new MaterialContenido(titulo: "material contenido 3", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-mm-dd"), responsable: "responsable", categoria: Categoria.findByNombre("Enlace"))
		def materialContenidoCuatro = new MaterialContenido(titulo: "material contenido 4", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-mm-dd"), responsable: "responsable", categoria: Categoria.findByNombre("Glosario"))

		// Contenidos
		def contenidoUnoTemaUnoCursoUno = new Contenido(titulo: "ContenidoUnoTemaUnoCursoUno")
		contenidoUnoTemaUnoCursoUno.addToMateriales(materialContenidoUno)
		contenidoUnoTemaUnoCursoUno.addToMateriales(materialContenidoDos)
	
		def contenidoDosTemaUnoCursoUno = new Contenido(titulo: "ContenidoDosTemaUnoCursoUno")
		contenidoDosTemaUnoCursoUno.addToMateriales(materialContenidoTres)
		contenidoDosTemaUnoCursoUno.addToMateriales(materialContenidoCuatro)
		
		def contenidoTresTemaUnoCursoUno = new Contenido(titulo: "ContenidoTresTemaUnoCursoUno")
		
		def contenidoUnoTemaDosCursoUno = new Contenido(titulo: "ContenidoUnoTemaDosCursoUno")
		def contenidoDosTemaDosCursoUno = new Contenido(titulo: "ContenidoDosTemaDosCursoUno")
		
		def contenidoUnoTemaUnoCursoDos = new Contenido(titulo: "ContenidoUnoTemaUnoCursoDos")
		def contenidoDosTemaUnoCursoDos = new Contenido(titulo: "ContenidoDosTemaUnoCursoDos")
		
		// Material de temas
		def materialTemaUno = new MaterialTema(titulo: "material Tema 1", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-mm-dd"), responsable: "responsable", categoria: Categoria.findByNombre("RefBibliografica"))
		def materialTemaDos = new MaterialTema(titulo: "material Tema 2", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-mm-dd"), responsable: "responsable", categoria: Categoria.findByNombre("Presentacion"))
		def materialTemaTres = new MaterialTema(titulo: "material Tema 3", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-mm-dd"), responsable: "responsable", categoria: Categoria.findByNombre("Enlace"))
		def materialTemaCuatro = new MaterialTema(titulo: "material Tema 4", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-mm-dd"), responsable: "responsable", categoria: Categoria.findByNombre("Glosario"))
		
		// Temas
		
		def temaUnoCursoUno = new Tema(titulo: "TemaUnoCursoUno", foro: foroTemaUnoCursoUno)
		temaUnoCursoUno.addToContenidos(contenidoUnoTemaUnoCursoUno)
		temaUnoCursoUno.addToContenidos(contenidoDosTemaUnoCursoUno)
		temaUnoCursoUno.addToContenidos(contenidoTresTemaUnoCursoUno)
		temaUnoCursoUno.addToMateriales(materialTemaUno)
		temaUnoCursoUno.addToMateriales(materialTemaDos)
		
		def temaDosCursoUno = new Tema(titulo: "TemaDosCursoUno", foro: foroTemaDosCursoUno)
		temaDosCursoUno.addToContenidos(contenidoUnoTemaDosCursoUno)
		temaDosCursoUno.addToContenidos(contenidoDosTemaDosCursoUno)
		
		def temaUnoCursoDos = new Tema(titulo: "TemaUnoCursoDos", foro: foroTemaUnoCursoDos)
		temaUnoCursoDos.addToContenidos(contenidoUnoTemaUnoCursoDos)
		temaUnoCursoDos.addToContenidos(contenidoDosTemaUnoCursoDos)
		temaUnoCursoDos.addToMateriales(materialTemaTres)
		temaUnoCursoDos.addToMateriales(materialTemaCuatro)
		
		// Material curso
		def materialUno = new MaterialCurso(titulo: "material curso 1", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-mm-dd"), responsable: "responsable", categoria: Categoria.findByNombre("RefBibliografica"))
		def materialDos = new MaterialCurso(titulo: "material curso 2", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-mm-dd"), responsable: "responsable", categoria: Categoria.findByNombre("Presentacion"))
		def materialTres = new MaterialCurso(titulo: "material curso 3", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-mm-dd"), responsable: "responsable", categoria: Categoria.findByNombre("Enlace"))
		def materialCuatro = new MaterialCurso(titulo: "material curso 4", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-mm-dd"), responsable: "responsable", categoria: Categoria.findByNombre("Glosario"))
		def materialCinco = new MaterialCurso(titulo: "material curso 5", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-mm-dd"), responsable: "responsable", categoria: Categoria.findByNombre("Trabajo"))
		def materialSeis = new MaterialCurso(titulo: "material curso 6", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-mm-dd"), responsable: "responsable", categoria: Categoria.findByNombre("Cuestionario"))
		
		// Cursos
		
		def cursoUno = new Curso(nroRelativo: "01", cuatDict: "1|2", foro: foroCursoUno, nombre: "Curso 1")
		cursoUno.addToMediadores(mediadorAgus)
		cursoUno.addToMediadores(mediadorUno)
		cursoUno.addToAprendices(aprendizDos)
		cursoUno.addToAprendices(aprendizTres)
		cursoUno.addToAprendices(aprendizOcho)
		cursoUno.addToTemas(temaUnoCursoUno)
		cursoUno.addToTemas(temaDosCursoUno)
		cursoUno.addToMateriales(materialUno)
		cursoUno.addToMateriales(materialDos)
		cursoUno.addToMateriales(materialTres)
		cursoUno.addToMateriales(materialCuatro)
		cursoUno.addToMateriales(materialCinco)
		cursoUno.addToMateriales(materialSeis)

		def cursoDos = new Curso(nroRelativo: "02", cuatDict: "1|2", foro: foroCursoDos, nombre: "Curso 2")
		cursoDos.addToMediadores(mediadorUnoP)
		cursoDos.addToMediadores(mediadorDos)
		cursoDos.addToAprendices(aprendizAgus)
		cursoDos.addToAprendices(aprendizCuatro)
		cursoDos.addToAprendices(aprendizNueve)
		cursoDos.addToTemas(temaUnoCursoDos)
		
		def cursoTres = new Curso(nroRelativo: "03", cuatDict: "1|2", foro: foroCursoTres, nombre: "Curso 3")
		cursoTres.addToMediadores(mediadorTres)
		cursoTres.addToAprendices(aprendizUno)
		cursoTres.addToAprendices(aprendizCinco)
		cursoTres.addToAprendices(aprendizDiez)

		def cursoCuatro = new Curso(nroRelativo: "04", cuatDict: "1|2", foro: foroCursoCuatro, nombre: "Curso 4")
		cursoCuatro.addToMediadores(mediadorCuatro)
		cursoCuatro.addToAprendices(aprendizUnoP)
		cursoCuatro.addToAprendices(aprendizSeis)
		cursoCuatro.addToAprendices(aprendizOnce)

		def cursoCinco = new Curso(nroRelativo: "05", cuatDict: "1|2", foro: foroCursoCinco, nombre: "Curso 5")
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
		
		// Noticias red
		
		def noticiaRedUno = new NoticiaRed(titulo: "Noticia Uno", texto: "Inauguracion de la Red Social Educativa 2014", 
			fecha: (new Date()).format("yyyy-mm-dd"), hora: (new Date()).getTimeString(), visibilidad: true)

		// Noticias curso
		
		def ArrayList<NoticiaCurso> noticiasCursoUno = new ArrayList<NoticiaCurso>()
		
		def noticiaCursoUno = new NoticiaCurso(titulo: "Noticia Uno 1", texto: "noticiaCursoUno",
			fecha: (new Date()).format("yyyy-mm-dd"), hora: (new Date()).getTimeString(), visibilidad: true, 
			mediador: mediadorAgus, curso: cursoUno)
		noticiasCursoUno.add(noticiaCursoUno)
		def noticiaCursoUnoP = new NoticiaCurso(titulo: "Noticia Uno 2", texto: "noticiaCursoUnoP ",
			fecha: (new Date()).format("yyyy-mm-dd"), hora: (new Date()).getTimeString(), visibilidad: true,
			mediador: mediadorAgus, curso: cursoUno)
		noticiasCursoUno.add(noticiaCursoUnoP)
		def noticiaCursoUnoPP = new NoticiaCurso(titulo: "Noticia Uno 3", texto: "noticiaCursoUnoPP",
			fecha: (new Date()).format("yyyy-mm-dd"), hora: (new Date()).getTimeString(), visibilidad: true, 
			mediador: mediadorUno, curso: cursoUno)
		noticiasCursoUno.add(noticiaCursoUnoPP)
		def noticiaCursoUnoPPP = new NoticiaCurso(titulo: "Noticia Uno 4", texto: "noticiaCursoUnoPPP",
			fecha: (new Date()).format("yyyy-mm-dd"), hora: (new Date()).getTimeString(), visibilidad: true, 
			mediador: mediadorUno, curso: cursoUno)
		noticiasCursoUno.add(noticiaCursoUnoPPP)
		
		for(int i = 0; i<noticiasCursoUno.size(); i++){
			if (!noticiasCursoUno.get(i).validate()) {
				println noticiasCursoUno.get(i).errors
			} else {
				println "Noticias curso uno agregados a la bbdd:"
				noticiasCursoUno.get(i).save()
				println noticiasCursoUno.get(i)
			}
		}
		
		// Administradores
		def ArrayList<Administrador> administradores = new ArrayList<Administrador>()
		
		def admPablo = new Administrador(usuario: usuarioPablo, rol: rolAdmin)
		admPablo.addToNoticiasRed(noticiaRedUno)
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
		
		// Miembros
		def ArrayList<Miembro> miembros = new ArrayList<Miembro>()
		
		def miembroAgus = new Miembro(usuario: usuarioAgus, rol: rolMiembro)
		miembros.add(miembroAgus)
		def miembroUno = new Miembro(usuario: usuarioUno, rol: rolMiembro)
		miembros.add(miembroUno)
		def miembroDos = new Miembro(usuario: usuarioDos, rol: rolMiembro)
		miembros.add(miembroDos)
		def miembroTres = new Miembro(usuario: usuarioTres, rol: rolMiembro)
		miembros.add(miembroTres)
		def miembroCuatro = new Miembro(usuario: usuarioCuatro, rol: rolMiembro)
		miembros.add(miembroCuatro)
		def miembroCinco = new Miembro(usuario: usuarioCinco, rol: rolMiembro)
		miembros.add(miembroCinco)
		def miembroSeis = new Miembro(usuario: usuarioSeis, rol: rolMiembro)
		miembros.add(miembroSeis)
		def miembroSiete = new Miembro(usuario: usuarioSiete, rol: rolMiembro)
		miembros.add(miembroSiete)
		def miembroOcho = new Miembro(usuario: usuarioOcho, rol: rolMiembro)
		miembros.add(miembroOcho)
		def miembroNueve = new Miembro(usuario: usuarioNueve, rol: rolMiembro)
		miembros.add(miembroNueve)
		def miembroDiez = new Miembro(usuario: usuarioDiez, rol: rolMiembro)
		miembros.add(miembroDiez)
		def miembroOnce = new Miembro(usuario: usuarioOnce, rol: rolMiembro)
		miembros.add(miembroOnce)
		def miembroDoce = new Miembro(usuario: usuarioDoce, rol: rolMiembro)
		miembros.add(miembroDoce)
			
		for(int i = 0; i<miembros.size(); i++){
			if (!miembros.get(i).validate()) {
				println miembros.get(i).errors
			} else {
				println "Miembros agregados a la bbdd:"
				miembros.get(i).save()
				println miembros.get(i)
			}
		}	
		
		// Red
		def red = new Red()
		
		if (!red.validate()) {
			println red.errors
		} else {
			println "Red agregada a la bbdd:"
			red.save()
			println red.titulo
		}
    }
	
    def destroy = {
    }
}
