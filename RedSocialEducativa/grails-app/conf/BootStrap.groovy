
import java.util.Date;

import com.fiuba.*
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
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-MM-dd"), 
			fechaMemb: (new Date()).format("yyyy-MM-dd"))
		usuarioService.guardar(usuarioPablo)
		def usuarioLuis = new Usuario(username: "31861315", password: "31861315", apellido: "Paniagua", nombres: "Luis", 
			legajo: "11", padron: "11", email: "pany100@gmail.com", fechaSolicitud: (new Date()).format("yyyy-MM-dd"), 
			fechaMemb: (new Date()).format("yyyy-MM-dd"))
		usuarioService.guardar(usuarioLuis)
		def usuarioAgus = new Usuario(username: "32725217", password: "32725217", apellido: "Milla", nombres: "Agustina", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-MM-dd"), 
			fechaMemb: (new Date()).format("yyyy-MM-dd"))
		usuarioService.guardar(usuarioAgus)
		def usuarioMessi = new Usuario(username: "10101010", password: "10101010", apellido: "Zarate", nombres: "Facundo",
			legajo: "11", padron: "11", email: "nanozarate@gmail.com", fechaSolicitud: (new Date()).format("yyyy-MM-dd"), 
			fechaMemb: (new Date()).format("yyyy-MM-dd"))
		usuarioService.guardar(usuarioMessi)

		def usuarioUno = new Usuario(username: "00000001", password: "00000001", apellido: "ApeUNO", nombres: "NomUno", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-MM-dd"), 
			fechaMemb: (new Date()).format("yyyy-MM-dd"))
		usuarioService.guardar(usuarioUno)
		def usuarioDos = new Usuario(username: "00000002", password: "00000002", apellido: "ApeDOS", nombres: "NomDOS", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-MM-dd"), 
			fechaMemb: (new Date()).format("yyyy-MM-dd"))
		usuarioService.guardar(usuarioDos)
		def usuarioTres = new Usuario(username: "00000003", password: "00000003", apellido: "ApeTRES", nombres: "NomTRES", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-MM-dd"), 
			fechaMemb: (new Date()).format("yyyy-MM-dd"))
		usuarioService.guardar(usuarioTres)
		def usuarioCuatro = new Usuario(username: "00000004", password: "00000004", apellido: "ApeCUATRO", nombres: "NomCUATRO", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-MM-dd"), 
			fechaMemb: (new Date()).format("yyyy-MM-dd"))
		usuarioService.guardar(usuarioCuatro)
		def usuarioCinco = new Usuario(username: "00000005", password: "00000005", apellido: "ApeCINCO", nombres: "NomCINCO", 
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-MM-dd"), 
			fechaMemb: (new Date()).format("yyyy-MM-dd"))
		usuarioService.guardar(usuarioCinco)
		def usuarioSeis = new Usuario(username: "00000006", password: "00000006", apellido: "ApeSeis", nombres: "NomSeis",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-MM-dd"), 
			fechaMemb: (new Date()).format("yyyy-MM-dd"))
		usuarioService.guardar(usuarioSeis)
		def usuarioSiete = new Usuario(username: "00000007", password: "00000007", apellido: "ApeSiete", nombres: "NomSiete",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-MM-dd"), 
			fechaMemb: (new Date()).format("yyyy-MM-dd"))
		usuarioService.guardar(usuarioSiete)
		def usuarioOcho = new Usuario(username: "00000008", password: "00000008", apellido: "ApeOcho", nombres: "NomOcho",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-MM-dd"), 
			fechaMemb: (new Date()).format("yyyy-MM-dd"))
		usuarioService.guardar(usuarioOcho)
		def usuarioNueve = new Usuario(username: "00000009", password: "00000009", apellido: "ApeNueve", nombres: "NomNueve",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-MM-dd"), 
			fechaMemb: (new Date()).format("yyyy-MM-dd"))
		usuarioService.guardar(usuarioNueve)
		def usuarioDiez = new Usuario(username: "00000010", password: "00000010", apellido: "ApeDiez", nombres: "NomDiez",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-MM-dd"), 
			fechaMemb: (new Date()).format("yyyy-MM-dd"))
		usuarioService.guardar(usuarioDiez)
		def usuarioOnce = new Usuario(username: "00000011", password: "00000011", apellido: "ApeOnce", nombres: "NomOnce",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-MM-dd"), 
			fechaMemb: (new Date()).format("yyyy-MM-dd"))
		usuarioService.guardar(usuarioOnce)
		def usuarioDoce = new Usuario(username: "00000012", password: "00000012", apellido: "ApeDoce", nombres: "NomDoce",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-MM-dd"), 
			fechaMemb: (new Date()).format("yyyy-MM-dd"))
		usuarioService.guardar(usuarioDoce)
		
		def usuarioTrece = new Usuario(username: "00000013", password: "00000013", apellido: "ApeTrece", nombres: "NomTrece",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-MM-dd"), enabled: false)
		usuarioService.guardar(usuarioTrece)
		def usuarioCatorce = new Usuario(username: "00000014", password: "00000014", apellido: "ApeCatorce", nombres: "NomCatorce",
			legajo: "11", padron: "11", email: "pablomagnaghi@gmail.com", fechaSolicitud: (new Date()).format("yyyy-MM-dd"), enabled: false)
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
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-MM-dd"))
		def aprendizUno = new Aprendiz(usuario: usuarioUno, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-MM-dd"))
		def aprendizUnoP = new Aprendiz(usuario: usuarioUno, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-MM-dd"))
		def aprendizUnoPP = new Aprendiz(usuario: usuarioUno, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-MM-dd"))
		def aprendizDos = new Aprendiz(usuario: usuarioDos, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-MM-dd"))
		def aprendizTres = new Aprendiz(usuario: usuarioTres, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-MM-dd"))
		def aprendizCuatro = new Aprendiz(usuario: usuarioCuatro, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-MM-dd"))		
		def aprendizCinco = new Aprendiz(usuario: usuarioCinco, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-MM-dd"))
		def aprendizSeis = new Aprendiz(usuario: usuarioSeis, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-MM-dd"))
		def aprendizSiete = new Aprendiz(usuario: usuarioSiete, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-MM-dd"))
		def aprendizOcho = new Aprendiz(usuario: usuarioOcho, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-MM-dd"))
		def aprendizNueve = new Aprendiz(usuario: usuarioNueve, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-MM-dd"))
		def aprendizDiez = new Aprendiz(usuario: usuarioDiez, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-MM-dd"))
		def aprendizOnce = new Aprendiz(usuario: usuarioOnce, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-MM-dd"))
		def aprendizDoce = new Aprendiz(usuario: usuarioDoce, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format("yyyy-MM-dd"))
		
		// Categorias
		
		// Categoria material
		def ArrayList<CategoriaMaterial> categoriasMaterial = new ArrayList<CategoriaMaterial>()
		
		def categoriaArticulo = new CategoriaMaterial(nombre: "Articulo")
		categoriasMaterial.add(categoriaArticulo)
		def categoriaPresentacion = new CategoriaMaterial(nombre: "Presentacion")
		categoriasMaterial.add(categoriaPresentacion)
		def categoriaCuestionario = new CategoriaMaterial(nombre: "Cuestionario")
		categoriasMaterial.add(categoriaCuestionario)
		def categoriaEjerciciosPlanteados = new CategoriaMaterial(nombre: "EjerciciosPlanteados")
		categoriasMaterial.add(categoriaEjerciciosPlanteados)
		def categoriaEjerciciosResueltos = new CategoriaMaterial(nombre: "EjerciciosResueltos")
		categoriasMaterial.add(categoriaEjerciciosResueltos)
		def categoriaTrabajo = new CategoriaMaterial(nombre: "Trabajo")
		categoriasMaterial.add(categoriaTrabajo)
		def categoriaRefBibliografica = new CategoriaMaterial(nombre: "RefBibliografica")
		categoriasMaterial.add(categoriaRefBibliografica )
		def categoriaEnlace = new CategoriaMaterial(nombre: "Enlace")
		categoriasMaterial.add(categoriaEnlace)
		def categoriaGlosario = new CategoriaMaterial(nombre: "Glosario")
		categoriasMaterial.add(categoriaGlosario)
		def categoriaEnunciadoDeEvaluacion = new CategoriaMaterial(nombre: "EnunciadoDeEvaluacion")
		categoriasMaterial.add(categoriaEnunciadoDeEvaluacion)
		def categoriaOtros = new CategoriaMaterial(nombre: "Otros")
		categoriasMaterial.add(categoriaOtros)
		
		for(int i = 0; i<categoriasMaterial.size(); i++){
			if (!categoriasMaterial.get(i).validate()) {
				println categoriasMaterial.get(i).errors
			} else {
				println "Categorias Material agregadas nombre"
				categoriasMaterial.get(i).save()
				println categoriasMaterial.get(i).nombre
			}
		}
		
		// Categoria actividad
		def ArrayList<CategoriaActividad> categoriasActividad = new ArrayList<CategoriaActividad>()

		def categoriaEncuesta = new CategoriaActividad(nombre: "Encuesta")
		categoriasActividad.add(categoriaEncuesta)
		def categoriaTP = new CategoriaActividad(nombre: "TP")
		categoriasActividad.add(categoriaTP)
		def categoriaCuestionarioAct = new CategoriaActividad(nombre: "Cuestionario")
		categoriasActividad.add(categoriaCuestionarioAct)
		def categoriaBrainstorming = new CategoriaActividad(nombre: "Brainstorming")
		categoriasActividad.add(categoriaBrainstorming)
		def categoriaOtra = new CategoriaActividad(nombre: "Otra")
		categoriasActividad.add(categoriaOtra)
		
		for(int i = 0; i<categoriasActividad.size(); i++){
			if (!categoriasActividad.get(i).validate()) {
				println categoriasActividad.get(i).errors
			} else {
				println "Categorias Actividad agregadas nombre"
				categoriasActividad.get(i).save()
				println categoriasActividad.get(i).nombre
			}
		}
	
		// Foros
		
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
		def foroCursoSeis = new ForoCurso(nombre: "ForoGeneralCursoSeis")
		
		// Publicaciones generales
		
		def publicacionGeneralUno = new PublicacionGeneral(titulo: "PublicacionGeneralUno", contenido: "Contenido", 
			responsable: "Pablo Magnaghi (Administrador)", dni: "33300432", fecha: (new Date()).format("yyyy-MM-dd"), 
			hora: (new Date()).getTimeString())
		
		def publicacionGeneralDos = new PublicacionGeneral(titulo: "PublicacionGeneralDos", contenido: "Contenido", 
			responsable: "Pablo Magnaghi (Administrador)", dni: "33300432", fecha: (new Date()).format("yyyy-MM-dd"), 
			hora: (new Date()).getTimeString())

		def publicacionGeneralTres = new PublicacionGeneral(titulo: "PublicacionGeneralTres", contenido: "Contenido", 
			responsable: "Pablo Magnaghi (Administrador)", dni: "33300432", fecha: (new Date()).format("yyyy-MM-dd"), 
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
		
		// Materiales, temas, contenidos
		
		// Material de contenido
		def materialContenidoUno = new MaterialContenido(titulo: "material contenido 1", descripcion: "opcional", autor: "anonimo", 
			fecha: (new Date()).format("yyyy-MM-dd"), responsable: "responsable", categoria: CategoriaMaterial.findByNombre("RefBibliografica"))
		def materialContenidoDos = new MaterialContenido(titulo: "material contenido 2", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-MM-dd"), responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Presentacion"))
		def materialContenidoTres = new MaterialContenido(titulo: "material contenido 3", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-MM-dd"), responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Enlace"))
		def materialContenidoCuatro = new MaterialContenido(titulo: "material contenido 4", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-MM-dd"), responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Glosario"))

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
			fecha: (new Date()).format("yyyy-MM-dd"), responsable: "responsable", categoria: CategoriaMaterial.findByNombre("RefBibliografica"))
		def materialTemaDos = new MaterialTema(titulo: "material Tema 2", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-MM-dd"), responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Presentacion"))
		def materialTemaTres = new MaterialTema(titulo: "material Tema 3", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-MM-dd"), responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Enlace"))
		def materialTemaCuatro = new MaterialTema(titulo: "material Tema 4", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-MM-dd"), responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Glosario"))
		
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
			fecha: (new Date()).format("yyyy-MM-dd"), responsable: "responsable", categoria: CategoriaMaterial.findByNombre("RefBibliografica"))
		def materialDos = new MaterialCurso(titulo: "material curso 2", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-MM-dd"), responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Presentacion"))
		def materialTres = new MaterialCurso(titulo: "material curso 3", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-MM-dd"), responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Enlace"))
		def materialCuatro = new MaterialCurso(titulo: "material curso 4", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-MM-dd"), responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Glosario"))
		def materialCinco = new MaterialCurso(titulo: "material curso 5", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-MM-dd"), responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Trabajo"))
		def materialSeis = new MaterialCurso(titulo: "material curso 6", descripcion: "opcional", autor: "anonimo",
			fecha: (new Date()).format("yyyy-MM-dd"), responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Cuestionario"))
		
		// Grupos curso
		
		def grupoUno = new GrupoCurso(numero: "1", nombre: "grupo 1")
		grupoUno.addToAprendices(aprendizDos)
		grupoUno.addToAprendices(aprendizTres)
		grupoUno.addToAprendices(aprendizCuatro)
		def grupoDos = new GrupoCurso(numero: "2", nombre: "grupo 2")
		grupoDos.addToAprendices(aprendizCinco)
		grupoDos.addToAprendices(aprendizSeis)
		grupoDos.addToAprendices(aprendizSiete)
		
		// Grupos actividad: agrego grupoActividadAprendiz a los grupo actividad
		
		def grupoActividadUno = new GrupoActividad(numero: "1", nombre: "grupo 1")
		def grupoActividadDos = new GrupoActividad(numero: "2", nombre: "grupo 2")
		def grupoActividadTres = new GrupoActividad(numero: "3", nombre: "grupo 3")
		
		// Actividades 
		
		def actividadUno = new Actividad(titulo: "actividad 1", objetivo: "objetivo", evaluable: true,
			grupal: true, visibilidad: true, categoria: CategoriaActividad.findByNombre("Encuesta"), fechaFinalizacion: "fechafinalizacion")
		actividadUno.addToGrupos(grupoActividadUno)
		actividadUno.addToGrupos(grupoActividadDos)
		
		def actividadDos = new Actividad(titulo: "actividad 2", objetivo: "objetivo", evaluable: true,
			grupal: true, visibilidad: true, categoria: CategoriaActividad.findByNombre("TP"), fechaFinalizacion: "fechafinalizacion")	
		actividadDos.addToGrupos(grupoActividadTres)
		
		def actividadTres = new Actividad(titulo: "actividad 3", objetivo: "objetivo", evaluable: true,
			grupal: true, visibilidad: true, categoria: CategoriaActividad.findByNombre("Cuestionario"), fechaFinalizacion: "fechafinalizacion")

		// Evaluaciones
		
		def evaluacionUno = new Evaluacion(fecha: (new Date()).format("yyyy-MM-dd"), descripcion: "descripcion", 
			horario: (new Date()).getTimeString(), aula: "1", parcial: true, obligatoria: true, habilitada: true)
		def evaluacionDos = new Evaluacion(fecha: (new Date()).format("yyyy-MM-dd"), descripcion: "descripcion",
			horario: (new Date()).getTimeString(), aula: "2", parcial: true, obligatoria: true, habilitada: true)
		def evaluacionTres = new Evaluacion(fecha: (new Date()).format("yyyy-MM-dd"), descripcion: "descripcion",
			horario: (new Date()).getTimeString(), aula: "3", parcial: true, obligatoria: true, habilitada: true)
		def evaluacionCuatro = new Evaluacion(fecha: (new Date()).format("yyyy-MM-dd"), descripcion: "descripcion",
			horario: (new Date()).getTimeString(), aula: "4", parcial: true, obligatoria: true, habilitada: true)
		def evaluacionCinco = new Evaluacion(fecha: (new Date()).format("yyyy-MM-dd"), descripcion: "descripcion",
			horario: (new Date()).getTimeString(), aula: "5", parcial: true, obligatoria: true, habilitada: true)
		
		// Cuatrimestres
		def cuatrimestreUno = new Cuatrimestre(anio: 2013, numero: 2, habGrupos: true, nroUltGrupo: 0, 
			foro: foroCursoUno)
		cuatrimestreUno.addToAprendices(aprendizDos)
		cuatrimestreUno.addToAprendices(aprendizTres)
		cuatrimestreUno.addToAprendices(aprendizCuatro)
		cuatrimestreUno.addToAprendices(aprendizCinco)
		cuatrimestreUno.addToAprendices(aprendizSeis)
		cuatrimestreUno.addToAprendices(aprendizSiete)
		cuatrimestreUno.addToAprendices(aprendizOcho)
		cuatrimestreUno.addToAprendices(aprendizNueve)
		cuatrimestreUno.addToAprendices(aprendizOnce)
		cuatrimestreUno.addToAprendices(aprendizDoce)
		cuatrimestreUno.addToGrupos(grupoUno)
		cuatrimestreUno.addToGrupos(grupoDos)
		cuatrimestreUno.addToActividades(actividadUno)
		cuatrimestreUno.addToActividades(actividadDos)
		cuatrimestreUno.addToActividades(actividadTres)

		def cuatrimestreDos = new Cuatrimestre(anio: 2013, numero: 1, habGrupos: true, nroUltGrupo: 0,
			foro: foroCursoDos)
		cuatrimestreDos.addToAprendices(aprendizDiez)
		
		def cuatrimestreTres = new Cuatrimestre(anio: 2013, numero: 2, habGrupos: true, nroUltGrupo: 0,
			foro: foroCursoTres)
		cuatrimestreTres.addToAprendices(aprendizAgus)

		def cuatrimestreCuatro = new Cuatrimestre(anio: 2013, numero: 1, habGrupos: true, nroUltGrupo: 0,
			foro: foroCursoCuatro)
		cuatrimestreCuatro.addToAprendices(aprendizUno)
		
		def cuatrimestreCinco = new Cuatrimestre(anio: 2013, numero: 2, habGrupos: true, nroUltGrupo: 0,
			foro: foroCursoCinco)
		cuatrimestreCinco.addToAprendices(aprendizUnoP)

		def cuatrimestreSeis = new Cuatrimestre(anio: 2013, numero: 1, habGrupos: true, nroUltGrupo: 0, 
			foro: foroCursoSeis)
		cuatrimestreSeis.addToAprendices(aprendizUnoPP)
		
		// Cursos
		
		def cursoUno = new Curso(nroRelativo: "01", cuatDict: "1|2", nombre: "Curso 1")
		cursoUno.addToMediadores(mediadorAgus)
		cursoUno.addToMediadores(mediadorUno)
		cursoUno.addToCuatrimestres(cuatrimestreUno)
		cursoUno.addToCuatrimestres(cuatrimestreDos)
		cursoUno.addToTemas(temaUnoCursoUno)
		cursoUno.addToTemas(temaDosCursoUno)
		cursoUno.addToMateriales(materialUno)
		cursoUno.addToMateriales(materialDos)
		cursoUno.addToMateriales(materialTres)
		cursoUno.addToMateriales(materialCuatro)
		cursoUno.addToMateriales(materialCinco)
		cursoUno.addToMateriales(materialSeis)

		cursoUno.addToEvaluaciones(evaluacionUno)
		cursoUno.addToEvaluaciones(evaluacionDos)
		cursoUno.addToEvaluaciones(evaluacionTres)
		cursoUno.addToEvaluaciones(evaluacionCuatro)
		cursoUno.addToEvaluaciones(evaluacionCinco)

		def cursoDos = new Curso(nroRelativo: "02", cuatDict: "1|2", nombre: "Curso 2")
		cursoDos.addToMediadores(mediadorUnoP)
		cursoDos.addToMediadores(mediadorDos)
		cursoDos.addToCuatrimestres(cuatrimestreTres)
		cursoDos.addToTemas(temaUnoCursoDos)
		
		def cursoTres = new Curso(nroRelativo: "03", cuatDict: "1|2", nombre: "Curso 3")
		cursoTres.addToMediadores(mediadorTres)
		cursoTres.addToCuatrimestres(cuatrimestreCuatro)

		def cursoCuatro = new Curso(nroRelativo: "04", cuatDict: "1|2", nombre: "Curso 4")
		cursoCuatro.addToMediadores(mediadorCuatro)
		cursoCuatro.addToCuatrimestres(cuatrimestreCinco)
		
		def cursoCinco = new Curso(nroRelativo: "05", cuatDict: "1|2", nombre: "Curso 5")
		cursoCinco.addToMediadores(mediadorCinco)
		cursoCinco.addToMediadores(mediadorSeis)
		cursoCinco.addToCuatrimestres(cuatrimestreSeis)
		
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
			fecha: (new Date()).format("yyyy-MM-dd"), hora: (new Date()).getTimeString(), visibilidad: true)

		// Noticias curso
		
		def ArrayList<NoticiaCurso> noticiasCursoUno = new ArrayList<NoticiaCurso>()
		
		def noticiaCursoUno = new NoticiaCurso(titulo: "Noticia Uno 1", texto: "noticiaCursoUno",
			fecha: (new Date()).format("yyyy-MM-dd"), hora: (new Date()).getTimeString(), visibilidad: true, 
			mediador: mediadorAgus, cuatrimestre: cuatrimestreUno)
		noticiasCursoUno.add(noticiaCursoUno)
		def noticiaCursoUnoP = new NoticiaCurso(titulo: "Noticia Uno 2", texto: "noticiaCursoUnoP ",
			fecha: (new Date()).format("yyyy-MM-dd"), hora: (new Date()).getTimeString(), visibilidad: true,
			mediador: mediadorAgus, cuatrimestre: cuatrimestreUno)
		noticiasCursoUno.add(noticiaCursoUnoP)
		def noticiaCursoUnoPP = new NoticiaCurso(titulo: "Noticia Uno 3", texto: "noticiaCursoUnoPP",
			fecha: (new Date()).format("yyyy-MM-dd"), hora: (new Date()).getTimeString(), visibilidad: true, 
			mediador: mediadorUno, cuatrimestre: cuatrimestreUno)
		noticiasCursoUno.add(noticiaCursoUnoPP)
		def noticiaCursoUnoPPP = new NoticiaCurso(titulo: "Noticia Uno 4", texto: "noticiaCursoUnoPPP",
			fecha: (new Date()).format("yyyy-MM-dd"), hora: (new Date()).getTimeString(), visibilidad: true, 
			mediador: mediadorUno, cuatrimestre: cuatrimestreUno)
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
		
		// Grupos actividad aprendiz (tabla intermedia para la relacion N N entre grupo actividad y aprendiz)
		
		def ArrayList<GrupoActividadAprendiz> grupos = new ArrayList<GrupoActividadAprendiz>()
		
		def grupoActividadAprendizUno = new GrupoActividadAprendiz(numero: "1", nombre: "grupo 1 aprendiz 2",
			grupo: grupoActividadUno, aprendiz: aprendizDos)
		grupos.add(grupoActividadAprendizUno)
		def grupoActividadAprendizDos = new GrupoActividadAprendiz(numero: "1", nombre: "grupo 1 aprendiz 3",
			grupo: grupoActividadUno, aprendiz: aprendizTres)
		grupos.add(grupoActividadAprendizDos)
		def grupoActividadAprendizTres = new GrupoActividadAprendiz(numero: "1", nombre: "grupo 1 aprendiz 4",
			grupo: grupoActividadUno, aprendiz: aprendizCuatro)
		grupos.add(grupoActividadAprendizTres)
		def grupoActividadAprendizCuatro = new GrupoActividadAprendiz(numero: "2", nombre: "grupo 2 aprendiz 2",
			grupo: grupoActividadDos, aprendiz: aprendizCinco)
		grupos.add(grupoActividadAprendizCuatro)
		def grupoActividadAprendizCinco = new GrupoActividadAprendiz(numero: "2", nombre: "grupo 2 aprendiz 3",
			grupo: grupoActividadDos, aprendiz: aprendizSeis)
		grupos.add(grupoActividadAprendizCinco)
		def grupoActividadAprendizSeis = new GrupoActividadAprendiz(numero: "2", nombre: "grupo 2 aprendiz 4",
			grupo: grupoActividadDos, aprendiz: aprendizSiete)
		grupos.add(grupoActividadAprendizSeis)
		def grupoActividadAprendizSiete = new GrupoActividadAprendiz(numero: "3", nombre: "grupo 3 aprendiz 2",
			grupo: grupoActividadTres, aprendiz: aprendizDos)
		grupos.add(grupoActividadAprendizSiete)
		def grupoActividadAprendizOcho = new GrupoActividadAprendiz(numero: "3", nombre: "grupo 3 aprendiz 3",
			grupo: grupoActividadTres, aprendiz: aprendizTres)
		grupos.add(grupoActividadAprendizOcho)
		def grupoActividadAprendizNueve = new GrupoActividadAprendiz(numero: "3", nombre: "grupo 3 aprendiz 4",
			grupo: grupoActividadTres, aprendiz: aprendizCuatro)
		grupos.add(grupoActividadAprendizNueve)
			
		for(int i = 0; i<grupos.size(); i++){
			if (!grupos.get(i).validate()) {
				println grupos.get(i).errors
			} else {
				println "Grupos agregados a la bbdd:"
				grupos.get(i).save()
				println grupos.get(i)
			}
		}
		
		// Evaluacion aprendiz (tabla intermedia para la relacion N N entre evaluacion y aprendiz)
		// TODO
		def ArrayList<EvaluacionAprendiz> evaluacionesAprendiz = new ArrayList<EvaluacionAprendiz>()
		
		def evaluacionAprendizUno = new EvaluacionAprendiz(evaluacion: evaluacionUno, aprendiz: aprendizDos)
		evaluacionesAprendiz.add(evaluacionAprendizUno)
		def evaluacionAprendizDos = new EvaluacionAprendiz(evaluacion: evaluacionUno, aprendiz: aprendizTres)
		evaluacionesAprendiz.add(evaluacionAprendizDos)
		def evaluacionAprendizTres = new EvaluacionAprendiz(evaluacion: evaluacionDos, aprendiz: aprendizDos)
		evaluacionesAprendiz.add(evaluacionAprendizTres)
		def evaluacionAprendizCuatro = new EvaluacionAprendiz(evaluacion: evaluacionDos, aprendiz: aprendizTres)
		evaluacionesAprendiz.add(evaluacionAprendizCuatro)
		def evaluacionAprendizCinco = new EvaluacionAprendiz(evaluacion: evaluacionTres, aprendiz: aprendizDos)
		evaluacionesAprendiz.add(evaluacionAprendizCinco)
		def evaluacionAprendizSeis = new EvaluacionAprendiz(evaluacion: evaluacionTres, aprendiz: aprendizCuatro)
		evaluacionesAprendiz.add(evaluacionAprendizSeis)
		def evaluacionAprendizSiete = new EvaluacionAprendiz(evaluacion: evaluacionCuatro, aprendiz: aprendizDos)
		evaluacionesAprendiz.add(evaluacionAprendizSiete)
		def evaluacionAprendizOcho = new EvaluacionAprendiz(evaluacion: evaluacionCuatro, aprendiz: aprendizCinco)
		evaluacionesAprendiz.add(evaluacionAprendizOcho)
		def evaluacionAprendizNueve = new EvaluacionAprendiz(evaluacion: evaluacionCinco, aprendiz: aprendizDos)
		evaluacionesAprendiz.add(evaluacionAprendizNueve)
			
		for(int i = 0; i<evaluacionesAprendiz.size(); i++){
			if (!evaluacionesAprendiz.get(i).validate()) {
				println evaluacionesAprendiz.get(i).errors
			} else {
				println "evaluacionesAprendiz agregados a la bbdd:"
				evaluacionesAprendiz.get(i).save()
				println evaluacionesAprendiz.get(i)
			}
		}
		
		// Red
		def red = Red.instance
		
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
