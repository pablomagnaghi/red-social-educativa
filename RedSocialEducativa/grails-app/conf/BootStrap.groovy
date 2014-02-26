
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
			if (!roles.get(i).save()) {
				println roles.get(i).errors
			} else {																										
				println "Roles agregados a la bbdd:"
				println roles.get(i).authority
			}																																																																																																																																																													
		}
		
		def ArrayList<Usuario> usuarios = new ArrayList<Usuario>()
		
		def usuarioPablo = new Usuario(username: "33300432", password: "33300432", apellido: "Magnaghi", nombres: "Pablo", padron: "88126", 
			email: "pablomagnaghi@gmail.com", fechaMemb: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioPablo)
		def usuarioLuis = new Usuario(username: "31861315", password: "31861315", apellido: "Paniagua", nombres: "Luis", padron: "86862", 
			email: "pany100@gmail.com", fechaMemb: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioLuis)
		def usuarioMessi = new Usuario(username: "10101010", password: "10101010", apellido: "Zarate", nombres: "Facundo", padron: "78456",
			email: "nanozarate@gmail.com", fechaMemb: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioMessi)
		def usuarioAgus = new Usuario(username: "32725217", password: "32725217", apellido: "Milla", nombres: "Agustina", padron: "88888", 
			email: "pablomagnaghi@gmail.com", fechaMemb: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioAgus)
		def usuarioUno = new Usuario(username: "00000001", password: "00000001", apellido: "ApeUNO", nombres: "NomUno", padron: "00001", 
			email: "pablomagnaghi@gmail.com", fechaMemb: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioUno)
		def usuarioDos = new Usuario(username: "00000002", password: "00000002", apellido: "ApeDOS", nombres: "NomDOS", padron: "00002", 
			email: "pablomagnaghi@gmail.com", fechaMemb: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioDos)
		def usuarioTres = new Usuario(username: "00000003", password: "00000003", apellido: "ApeTRES", nombres: "NomTRES", padron: "00003", 
			email: "pablomagnaghi@gmail.com", fechaMemb: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioTres)
		def usuarioCuatro = new Usuario(username: "00000004", password: "00000004", apellido: "ApeCUATRO", nombres: "NomCUATRO", padron: "00004", 
			email: "pablomagnaghi@gmail.com", fechaMemb: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioCuatro)
		def usuarioCinco = new Usuario(username: "00000005", password: "00000005", apellido: "ApeCINCO", nombres: "NomCINCO", padron: "00005", 
			email: "pablomagnaghi@gmail.com", fechaMemb: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioCinco)
		def usuarioSeis = new Usuario(username: "00000006", password: "00000006", apellido: "ApeSeis", nombres: "NomSeis", padron: "00006", 
			email: "pablomagnaghi@gmail.com", fechaMemb: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioSeis)
		def usuarioSiete = new Usuario(username: "00000007", password: "00000007", apellido: "ApeSiete", nombres: "NomSiete", padron: "00007", 
			email: "pablomagnaghi@gmail.com", fechaMemb: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioSiete)
		def usuarioOcho = new Usuario(username: "00000008", password: "00000008", apellido: "ApeOcho", nombres: "NomOcho", padron: "00008", 
			email: "pablomagnaghi@gmail.com", fechaMemb: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioOcho)
		def usuarioNueve = new Usuario(username: "00000009", password: "00000009", apellido: "ApeNueve", nombres: "NomNueve", padron: "00009", 
			email: "pablomagnaghi@gmail.com", fechaMemb: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioNueve)
		def usuarioDiez = new Usuario(username: "00000010", password: "00000010", apellido: "ApeDiez", nombres: "NomDiez", padron: "00010", 
			email: "pablomagnaghi@gmail.com", fechaMemb: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioDiez)
		def usuarioOnce = new Usuario(username: "00000011", password: "00000011", apellido: "ApeOnce", nombres: "NomOnce", padron: "00011", 
			email: "pablomagnaghi@gmail.com", fechaMemb: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioOnce)
		def usuarioDoce = new Usuario(username: "00000012", password: "00000012", apellido: "ApeDoce", nombres: "NomDoce", padron: "00012", 
			email: "pablomagnaghi@gmail.com", fechaMemb: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioDoce)
		def usuarioTrece = new Usuario(username: "00000013", password: "00000013", apellido: "ApeTrece", nombres: "NomTrece", padron: "00013", 
			email: "pablomagnaghi@gmail.com", fechaMemb: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioTrece)
		def usuarioCatorce = new Usuario(username: "00000014", password: "00000014", apellido: "ApeCatorce", nombres: "NomCatorce", padron: "00014", 
			email: "pablomagnaghi@gmail.com", fechaMemb: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioCatorce)

		for(int i = 0; i<usuarios.size(); i++){
			if (!usuarioService.guardar(usuarios.get(i))) {
				println usuarios.get(i).errors
			} else {
				println "Usuarios agregados a la bbdd:"
				println usuarios.get(i).username
			}
		}

		// Mensajes
		def mensajePabloToLuis = new Mensaje(emisor: usuarioPablo, receptor: usuarioLuis, asunto: "Mensaje de prueba",
		cuerpo: "Vamos por el campeonato", fecha : new Date())
		mensajeService.nuevoMensaje(mensajePabloToLuis)
		def mensajePabloToLuisDoss = new Mensaje(emisor: usuarioPablo, receptor: usuarioLuis, asunto: "Mensaje de prueba",
		cuerpo: "Vamos por el campeonato", fecha : new Date())
		mensajeService.nuevoMensaje(mensajePabloToLuisDoss)
		def mensajePabloToLuisTres = new Mensaje(emisor: usuarioPablo, receptor: usuarioLuis, asunto: "Mensaje de prueba",
		cuerpo: "Vamos por el campeonato", fecha : new Date())
		mensajeService.nuevoMensaje(mensajePabloToLuisTres)
		def mensajePabloToLuisCuatro = new Mensaje(emisor: usuarioPablo, receptor: usuarioLuis, asunto: "Mensaje de prueba",
		cuerpo: "Vamos por el campeonato", fecha : new Date())
		mensajeService.nuevoMensaje(mensajePabloToLuisCuatro)
		def mensajePabloToLuisCinco = new Mensaje(emisor: usuarioPablo, receptor: usuarioLuis, asunto: "Mensaje de prueba",
		cuerpo: "Vamos por el campeonato", fecha : new Date())
		mensajeService.nuevoMensaje(mensajePabloToLuisCinco)
		def mensajePabloToLuisSeis = new Mensaje(emisor: usuarioPablo, receptor: usuarioLuis, asunto: "Mensaje de prueba",
		cuerpo: "Vamos por el campeonato", fecha : new Date())
		mensajeService.nuevoMensaje(mensajePabloToLuisSeis)
		def mensajePabloToLuisSiete = new Mensaje(emisor: usuarioPablo, receptor: usuarioLuis, asunto: "Mensaje de prueba",
		cuerpo: "Vamos por el campeonato", fecha : new Date())
		mensajeService.nuevoMensaje(mensajePabloToLuisSiete)
		def mensajePabloToLuisOcho = new Mensaje(emisor: usuarioPablo, receptor: usuarioLuis, asunto: "Mensaje de prueba",
		cuerpo: "Vamos por el campeonato", fecha : new Date())
		mensajeService.nuevoMensaje(mensajePabloToLuisOcho)
		def mensajePabloToLuisNueve = new Mensaje(emisor: usuarioPablo, receptor: usuarioLuis, asunto: "Mensaje de prueba",
		cuerpo: "Vamos por el campeonato", fecha : new Date())
		mensajeService.nuevoMensaje(mensajePabloToLuisNueve)
		
		def mensajeAgusToLuisDiez = new Mensaje(emisor: usuarioAgus, receptor: usuarioLuis, asunto: "Mensaje de Agus a Luis 1",
		cuerpo: "Buenas noches!", fecha : new Date())
		mensajeService.nuevoMensaje(mensajeAgusToLuisDiez)
		
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
		def mediadorUnoP = new Mediador(usuario: usuarioUno, rol: rolMediador, jerarquia: "AP");
		def mediadorDos = new Mediador(usuario: usuarioDos, rol: rolMediador, jerarquia: "JTP");
		def mediadorTres = new Mediador(usuario: usuarioTres, rol: rolMediador, jerarquia: "JTP");
		def mediadorCuatro = new Mediador(usuario: usuarioCuatro, rol: rolMediador, jerarquia: "JTP");
		def mediadorCinco = new Mediador(usuario: usuarioCinco, rol: rolMediador, jerarquia: "JTP");
		def mediadorSeis = new Mediador(usuario: usuarioSeis, rol: rolMediador, jerarquia: "JTP");
		
		// Aprendices
		def aprendizAgus = new Aprendiz(usuario: usuarioAgus, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format(Utilidades.FORMATO_FECHA), cursando: true)
		def aprendizUno = new Aprendiz(usuario: usuarioUno, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format(Utilidades.FORMATO_FECHA), cursando: true)
		def aprendizUnoP = new Aprendiz(usuario: usuarioUno, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format(Utilidades.FORMATO_FECHA), cursando: true)
		def aprendizUnoPP = new Aprendiz(usuario: usuarioUno, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format(Utilidades.FORMATO_FECHA), cursando: true)	
		def aprendizDos = new Aprendiz(usuario: usuarioDos, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format(Utilidades.FORMATO_FECHA), cursando: true)
		def aprendizDosP = new Aprendiz(usuario: usuarioDos, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format(Utilidades.FORMATO_FECHA), cursando: false)
		def aprendizDosPP = new Aprendiz(usuario: usuarioDos, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format(Utilidades.FORMATO_FECHA), cursando: false)
		def aprendizDosPPP = new Aprendiz(usuario: usuarioDos, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format(Utilidades.FORMATO_FECHA), cursando: false)
		def aprendizTres = new Aprendiz(usuario: usuarioTres, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format(Utilidades.FORMATO_FECHA), cursando: true)
		def aprendizCuatro = new Aprendiz(usuario: usuarioCuatro, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format(Utilidades.FORMATO_FECHA), cursando: true)		
		def aprendizCinco = new Aprendiz(usuario: usuarioCinco, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format(Utilidades.FORMATO_FECHA), cursando: true)
		def aprendizSeis = new Aprendiz(usuario: usuarioSeis, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format(Utilidades.FORMATO_FECHA), cursando: true)
		def aprendizSiete = new Aprendiz(usuario: usuarioSiete, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format(Utilidades.FORMATO_FECHA), cursando: true)
		def aprendizOcho = new Aprendiz(usuario: usuarioOcho, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format(Utilidades.FORMATO_FECHA), cursando: true)
		def aprendizNueve = new Aprendiz(usuario: usuarioNueve, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format(Utilidades.FORMATO_FECHA), cursando: true)
		def aprendizDiez = new Aprendiz(usuario: usuarioDiez, rol: rolAprendiz, participa: true, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format(Utilidades.FORMATO_FECHA), cursando: true)
		def aprendizOnce = new Aprendiz(usuario: usuarioOnce, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format(Utilidades.FORMATO_FECHA), cursando: false)
		def aprendizDoce = new Aprendiz(usuario: usuarioDoce, rol: rolAprendiz, participa: false, msjEnviados: "0",
			msjLeidos: "0", pubForos: "0", descMaterial: "0", ultVisita: (new Date()).format(Utilidades.FORMATO_FECHA), cursando: false)
		
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
			if (!categoriasMaterial.get(i).save()) {
				println categoriasMaterial.get(i).errors
			} else {
				println "Categorias Material agregadas nombre"
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
			if (!categoriasActividad.get(i).save()) {
				println categoriasActividad.get(i).errors
			} else {
				println "Categorias Actividad agregadas nombre"
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
		def foroCursoUnoP = new ForoCurso(nombre: "ForoGeneralCursoUnoP")
		def foroCursoUnoPP = new ForoCurso(nombre: "ForoGeneralCursoUnoPP")
		def foroCursoUnoPPP = new ForoCurso(nombre: "ForoGeneralCursoUnoPPP")
		def foroCursoUnoPPPP = new ForoCurso(nombre: "ForoGeneralCursoUnoPPPP")
		def foroCursoUnoPPPPP = new ForoCurso(nombre: "ForoGeneralCursoUnoPPPPP")
		def foroCursoUnoPPPPPP = new ForoCurso(nombre: "ForoGeneralCursoUnoPPPPPP")
		def foroCursoDos = new ForoCurso(nombre: "ForoGeneralCursoDos")
		def foroCursoTres = new ForoCurso(nombre: "ForoGeneralCursoTres")
		def foroCursoCuatro = new ForoCurso(nombre: "ForoGeneralCursoCuatro")
		def foroCursoCuatroP = new ForoCurso(nombre: "ForoGeneralCursoCuatroP")
		def foroCursoCuatroPP = new ForoCurso(nombre: "ForoGeneralCursoCuatroPP")
		def foroCursoCuatroPPP = new ForoCurso(nombre: "ForoGeneralCursoCuatroPPP")
		def foroCursoCuatroPPPP = new ForoCurso(nombre: "ForoGeneralCursoCuatroPPPP")

		def foroCursoCinco = new ForoCurso(nombre: "ForoGeneralCursoCinco")
		
		// Publicaciones generales
		def publicacionGeneralUno = new PublicacionGeneral(titulo: "PublicacionGeneralUno", contenido: "Contenido", 
			responsable: "Pablo Magnaghi (Administrador)", dni: "33300432")
		
		def publicacionGeneralDos = new PublicacionGeneral(titulo: "PublicacionGeneralDos", contenido: "Contenido", 
			responsable: "Pablo Magnaghi (Administrador)", dni: "33300432")

		def publicacionGeneralTres = new PublicacionGeneral(titulo: "PublicacionGeneralTres", contenido: "Contenido", 
			responsable: "Pablo Magnaghi (Administrador)", dni: "33300432")
				
		def foroGeneral = new ForoGeneral(nombre: "Foro general")
		foroGeneral.addToPublicaciones(publicacionGeneralUno)
		foroGeneral.addToPublicaciones(publicacionGeneralDos)
		foroGeneral.addToPublicaciones(publicacionGeneralTres)
		
		if (!foroGeneral.save()) {
			println foroGeneral.errors
		} else {
			println "foroGeneral agregada a la bbdd:"
			println foroGeneral
		}
		
		// Materiales, temas, contenidos
		
		// Material de contenido
		def materialContenidoUno = new MaterialContenido(titulo: "material contenido 1", descripcion: "opcional", autor: "anonimo", 
			responsable: "responsable", categoria: CategoriaMaterial.findByNombre("RefBibliografica"))
		def materialContenidoDos = new MaterialContenido(titulo: "material contenido 2", descripcion: "opcional", autor: "anonimo",
			responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Presentacion"))
		def materialContenidoTres = new MaterialContenido(titulo: "material contenido 3", descripcion: "opcional", autor: "anonimo",
			responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Enlace"))
		def materialContenidoCuatro = new MaterialContenido(titulo: "material contenido 4", descripcion: "opcional", autor: "anonimo",
			responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Glosario"))

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
			responsable: "responsable", categoria: CategoriaMaterial.findByNombre("RefBibliografica"))
		def materialTemaDos = new MaterialTema(titulo: "material Tema 2", descripcion: "opcional", autor: "anonimo",
			responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Presentacion"))
		def materialTemaTres = new MaterialTema(titulo: "material Tema 3", descripcion: "opcional", autor: "anonimo",
			responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Enlace"))
		def materialTemaCuatro = new MaterialTema(titulo: "material Tema 4", descripcion: "opcional", autor: "anonimo",
			responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Glosario"))
		
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
			responsable: "responsable", categoria: CategoriaMaterial.findByNombre("RefBibliografica"))
		def materialDos = new MaterialCurso(titulo: "material curso 2", descripcion: "opcional", autor: "anonimo",
			responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Presentacion"))
		def materialTres = new MaterialCurso(titulo: "material curso 3", descripcion: "opcional", autor: "anonimo",
			responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Enlace"))
		def materialCuatro = new MaterialCurso(titulo: "material curso 4", descripcion: "opcional", autor: "anonimo",
			responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Glosario"))
		def materialCinco = new MaterialCurso(titulo: "material curso 5", descripcion: "opcional", autor: "anonimo",
			responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Trabajo"))
		def materialSeis = new MaterialCurso(titulo: "material curso 6", descripcion: "opcional", autor: "anonimo",
			responsable: "responsable", categoria: CategoriaMaterial.findByNombre("Cuestionario"))
		
		// Grupos actividad: agrego grupoActividadAprendiz a los grupo actividad
		def grupoActividadUno = new GrupoActividad(numero: "1", nombre: "grupo 1")
		def grupoActividadDos = new GrupoActividad(numero: "2", nombre: "grupo 2")
		def grupoActividadTres = new GrupoActividad(numero: "1", nombre: "grupo 3")
		
		// Actividades 
		def actividadUno = new Actividad(titulo: "actividad 1", objetivo: "objetivo", evaluable: true,
			grupal: true, visibilidad: true, categoria: CategoriaActividad.findByNombre("Encuesta"), 
			fechaFinalizacion: (new Date()).format(Utilidades.FORMATO_FECHA))
		actividadUno.addToGrupos(grupoActividadUno)
		actividadUno.addToGrupos(grupoActividadDos)
		
		def actividadDos = new Actividad(titulo: "actividad 2", objetivo: "objetivo", evaluable: true,
			grupal: true, visibilidad: true, categoria: CategoriaActividad.findByNombre("TP"), 
			fechaFinalizacion: (new Date()).format(Utilidades.FORMATO_FECHA))	
		actividadDos.addToGrupos(grupoActividadTres)
		
		def actividadTres = new Actividad(titulo: "actividad 3", objetivo: "objetivo", evaluable: true,
			grupal: true, visibilidad: true, categoria: CategoriaActividad.findByNombre("Cuestionario"), 
			fechaFinalizacion: (new Date()).format(Utilidades.FORMATO_FECHA))

		// Evaluaciones	
		def evaluacionUno = new Evaluacion(fecha: (new Date()).format(Utilidades.FORMATO_FECHA), descripcion: "descripcion", 
			horario: (new Date()).getTimeString(), aula: "1", parcial: true, obligatoria: true, habilitada: true)
		def evaluacionDos = new Evaluacion(fecha: (new Date()).format(Utilidades.FORMATO_FECHA), descripcion: "descripcion",
			horario: (new Date()).getTimeString(), aula: "2", parcial: true, obligatoria: true, habilitada: true)
		def evaluacionTres = new Evaluacion(fecha: (new Date()).format(Utilidades.FORMATO_FECHA), descripcion: "descripcion",
			horario: (new Date()).getTimeString(), aula: "3", parcial: true, obligatoria: true, habilitada: true)
		def evaluacionCuatro = new Evaluacion(fecha: (new Date()).format(Utilidades.FORMATO_FECHA), descripcion: "descripcion",
			horario: (new Date()).getTimeString(), aula: "4", parcial: true, obligatoria: true, habilitada: true)
		def evaluacionCinco = new Evaluacion(fecha: (new Date()).format(Utilidades.FORMATO_FECHA), descripcion: "descripcion",
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
		cuatrimestreUno.addToAprendices(aprendizDiez)
		cuatrimestreUno.addToAprendices(aprendizOnce)
		cuatrimestreUno.addToAprendices(aprendizDoce)
		cuatrimestreUno.addToActividades(actividadUno)
		cuatrimestreUno.addToActividades(actividadDos)
		cuatrimestreUno.addToActividades(actividadTres)

		def cuatrimestreUnoP = new Cuatrimestre(anio: 2013, numero: 1, habGrupos: true, nroUltGrupo: 0,
			foro: foroCursoUnoP)
		def cuatrimestreUnoPP = new Cuatrimestre(anio: 2012, numero: 2, habGrupos: true, nroUltGrupo: 0,
			foro: foroCursoUnoPP)
		def cuatrimestreUnoPPP = new Cuatrimestre(anio: 2012, numero: 1, habGrupos: true, nroUltGrupo: 0,
			foro: foroCursoUnoPPP)
		def cuatrimestreUnoPPPP = new Cuatrimestre(anio: 2011, numero: 2, habGrupos: true, nroUltGrupo: 0,
			foro: foroCursoUnoPPPP)
		def cuatrimestreUnoPPPPP = new Cuatrimestre(anio: 2011, numero: 1, habGrupos: true, nroUltGrupo: 0,
			foro: foroCursoUnoPPPPP)
		cuatrimestreUnoPPPPP.addToAprendices(aprendizDosPPP)
		def cuatrimestreUnoPPPPPP = new Cuatrimestre(anio: 2010, numero: 2, habGrupos: true, nroUltGrupo: 0,
			foro: foroCursoUnoPPPPPP)
		
		def cuatrimestreDos = new Cuatrimestre(anio: 2013, numero: 1, habGrupos: true, nroUltGrupo: 0,
			foro: foroCursoDos)
		cuatrimestreDos.addToAprendices(aprendizAgus)

		def cuatrimestreTres = new Cuatrimestre(anio: 2013, numero: 1, habGrupos: true, nroUltGrupo: 0,
			foro: foroCursoTres)
		cuatrimestreTres.addToAprendices(aprendizUno)
		cuatrimestreTres.addToAprendices(aprendizDosP)
		
		def cuatrimestreCuatro = new Cuatrimestre(anio: 2013, numero: 2, habGrupos: true, nroUltGrupo: 0,
			foro: foroCursoCuatro)
		cuatrimestreCuatro.addToAprendices(aprendizUnoP)
		def cuatrimestreCuatroP = new Cuatrimestre(anio: 2012, numero: 2, habGrupos: true, nroUltGrupo: 0,
			foro: foroCursoCuatroP)
		def cuatrimestreCuatroPP = new Cuatrimestre(anio: 2012, numero: 1, habGrupos: true, nroUltGrupo: 0,
			foro: foroCursoCuatroPP)
		def cuatrimestreCuatroPPP = new Cuatrimestre(anio: 2011, numero: 2, habGrupos: true, nroUltGrupo: 0,
			foro: foroCursoCuatroPPP)
		def cuatrimestreCuatroPPPP = new Cuatrimestre(anio: 2011, numero: 1, habGrupos: true, nroUltGrupo: 0,
			foro: foroCursoCuatroPPPP)
		cuatrimestreCuatroPPPP.addToAprendices(aprendizDosPP)

		def cuatrimestreCinco = new Cuatrimestre(anio: 2012, numero: 2, habGrupos: true, nroUltGrupo: 0, 
			foro: foroCursoCinco)
		cuatrimestreCinco.addToAprendices(aprendizUnoPP)
		
		// Cursos		
		def cursoUno = new Curso(nroRelativo: "01", cuatDict: "1|2", nombre: "Curso 1")
		cursoUno.addToMediadores(mediadorAgus)
		cursoUno.addToMediadores(mediadorUno)
		cursoUno.addToCuatrimestres(cuatrimestreUno)
		cursoUno.addToCuatrimestres(cuatrimestreUnoP)
		cursoUno.addToCuatrimestres(cuatrimestreUnoPP)
		cursoUno.addToCuatrimestres(cuatrimestreUnoPPP)
		cursoUno.addToCuatrimestres(cuatrimestreUnoPPPP)
		cursoUno.addToCuatrimestres(cuatrimestreUnoPPPPP)
		cursoUno.addToCuatrimestres(cuatrimestreUnoPPPPPP)
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
		cursoDos.addToCuatrimestres(cuatrimestreDos)
		cursoDos.addToTemas(temaUnoCursoDos)
		
		def cursoTres = new Curso(nroRelativo: "03", cuatDict: "1", nombre: "Curso 3")
		cursoTres.addToMediadores(mediadorTres)
		cursoTres.addToCuatrimestres(cuatrimestreTres)

		def cursoCuatro = new Curso(nroRelativo: "04", cuatDict: "1|2", nombre: "Curso 4")
		cursoCuatro.addToMediadores(mediadorCuatro)
		cursoCuatro.addToCuatrimestres(cuatrimestreCuatro)
		cursoCuatro.addToCuatrimestres(cuatrimestreCuatroP)
		cursoCuatro.addToCuatrimestres(cuatrimestreCuatroPP)
		cursoCuatro.addToCuatrimestres(cuatrimestreCuatroPPP)
		cursoCuatro.addToCuatrimestres(cuatrimestreCuatroPPPP)
		
		def cursoCinco = new Curso(nroRelativo: "05", cuatDict: "2", nombre: "Curso 5")
		cursoCinco.addToMediadores(mediadorCinco)
		cursoCinco.addToMediadores(mediadorSeis)
		cursoCinco.addToCuatrimestres(cuatrimestreCinco)
		
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
			if (!materias.get(i).save()) {
				println materias.get(i).errors
			} else {
				println "Materias agregadas a la bbdd:"
				println materias.get(i).codigo
			}
		}
		
		// Noticias red
		def noticiaRedUno = new NoticiaRed(titulo: "Noticia Uno", texto: "Inauguracion de la Red Social Educativa 2014", 
			fecha: (new Date()).format(Utilidades.FORMATO_FECHA), hora: (new Date()).getTimeString(), visibilidad: true)
		def noticiaRedDos = new NoticiaRed(titulo: "Noticia Dos", texto: "Ajustar panel lateral",
			fecha: (new Date()).format(Utilidades.FORMATO_FECHA), hora: (new Date()).getTimeString(), visibilidad: true)
		def noticiaRedTres = new NoticiaRed(titulo: "Noticia Tres", texto: "Ajustar limites de la cartelera",
			fecha: (new Date()).format(Utilidades.FORMATO_FECHA), hora: (new Date()).getTimeString(), visibilidad: true)
		def noticiaRedCuatro = new NoticiaRed(titulo: "Noticia Cuatro", texto: "Revisar mejor opcion de mensajeria",
			fecha: (new Date()).format(Utilidades.FORMATO_FECHA), hora: (new Date()).getTimeString(), visibilidad: true)

		// Noticias curso
		def ArrayList<NoticiaCurso> noticiasCursoUno = new ArrayList<NoticiaCurso>()
		
		def noticiaCursoUno = new NoticiaCurso(titulo: "Noticia Uno 1", texto: "noticiaCursoUno", visibilidad: true, 
			mediador: mediadorAgus, cuatrimestre: cuatrimestreUno)
		noticiasCursoUno.add(noticiaCursoUno)
		def noticiaCursoUnoP = new NoticiaCurso(titulo: "Noticia Uno 2", texto: "noticiaCursoUnoP ", visibilidad: true,
			mediador: mediadorAgus, cuatrimestre: cuatrimestreUno)
		noticiasCursoUno.add(noticiaCursoUnoP)
		def noticiaCursoUnoPP = new NoticiaCurso(titulo: "Noticia Uno 3", texto: "noticiaCursoUnoPP", visibilidad: true, 
			mediador: mediadorUno, cuatrimestre: cuatrimestreUno)
		noticiasCursoUno.add(noticiaCursoUnoPP)
		def noticiaCursoUnoPPP = new NoticiaCurso(titulo: "Noticia Uno 4", texto: "noticiaCursoUnoPPP", visibilidad: true, 
			mediador: mediadorUno, cuatrimestre: cuatrimestreUno)
		noticiasCursoUno.add(noticiaCursoUnoPPP)
		
		for(int i = 0; i<noticiasCursoUno.size(); i++){
			if (!noticiasCursoUno.get(i).save()) {
				println noticiasCursoUno.get(i).errors
			} else {
				println "Noticias curso uno agregados a la bbdd:"
				println noticiasCursoUno.get(i)
			}
		}
		
		// Administradores
		def ArrayList<Administrador> administradores = new ArrayList<Administrador>()
		
		def admPablo = new Administrador(usuario: usuarioPablo, rol: rolAdmin)
		admPablo.addToNoticiasRed(noticiaRedUno)
		admPablo.addToNoticiasRed(noticiaRedDos)
		administradores.add(admPablo)
		def admLuis = new Administrador(usuario: usuarioLuis, rol: rolAdmin)
		admLuis.addToNoticiasRed(noticiaRedTres)
		admLuis.addToNoticiasRed(noticiaRedCuatro)
		administradores.add(admLuis)
		def admMessi = new Administrador(usuario: usuarioMessi, rol: rolAdmin)
		administradores.add(admMessi)
			
		for(int i = 0; i<administradores.size(); i++){
			if (!administradores.get(i).save()) {
				println administradores.get(i).errors
			} else {
				println "Administradores agregados a la bbdd:"
				println administradores.get(i)
			}
		}
		
		// Miembros
		def ArrayList<Miembro> miembros = new ArrayList<Miembro>()
		
		def miembroPablo = new Miembro(usuario: usuarioPablo, rol: rolMiembro)
		miembros.add(miembroPablo)
		def miembroLuis = new Miembro(usuario: usuarioLuis, rol: rolMiembro)
		miembros.add(miembroLuis)
		def miembroMessi = new Miembro(usuario: usuarioMessi, rol: rolMiembro)
		miembros.add(miembroMessi)
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
		def miembroTrece = new Miembro(usuario: usuarioTrece, rol: rolMiembro)
		miembros.add(miembroTrece)
		def miembroCatorce = new Miembro(usuario: usuarioCatorce, rol: rolMiembro)
		miembros.add(miembroCatorce)
			
		for(int i = 0; i<miembros.size(); i++){
			if (!	miembros.get(i).save()) {
				println miembros.get(i).errors
			} else {
				println "Miembros agregados a la bbdd:"
				println miembros.get(i)
			}
		}	
		
		// TODO
		// Grupos actividad aprendiz (tabla intermedia para la relacion N N entre grupo actividad y aprendiz)	
		def ArrayList<GrupoActividadAprendiz> grupos = new ArrayList<GrupoActividadAprendiz>()
			
		def grupoActividadAprendizUno = new GrupoActividadAprendiz(grupo: grupoActividadUno, aprendiz: aprendizDos)
		grupos.add(grupoActividadAprendizUno)
		def grupoActividadAprendizDos = new GrupoActividadAprendiz(grupo: grupoActividadUno, aprendiz: aprendizTres)
		grupos.add(grupoActividadAprendizDos)
		def grupoActividadAprendizTres = new GrupoActividadAprendiz(grupo: grupoActividadUno, aprendiz: aprendizCuatro)
		grupos.add(grupoActividadAprendizTres)
		def grupoActividadAprendizCuatro = new GrupoActividadAprendiz(grupo: grupoActividadDos, aprendiz: aprendizCinco)
		grupos.add(grupoActividadAprendizCuatro)
		def grupoActividadAprendizCinco = new GrupoActividadAprendiz(grupo: grupoActividadDos, aprendiz: aprendizSeis)
		grupos.add(grupoActividadAprendizCinco)
		def grupoActividadAprendizSeis = new GrupoActividadAprendiz(grupo: grupoActividadDos, aprendiz: aprendizSiete)
		grupos.add(grupoActividadAprendizSeis)
		def grupoActividadAprendizSiete = new GrupoActividadAprendiz(grupo: grupoActividadTres, aprendiz: aprendizDos)
		grupos.add(grupoActividadAprendizSiete)
		def grupoActividadAprendizOcho = new GrupoActividadAprendiz(grupo: grupoActividadTres, aprendiz: aprendizTres)
		grupos.add(grupoActividadAprendizOcho)
		def grupoActividadAprendizNueve = new GrupoActividadAprendiz(grupo: grupoActividadTres, aprendiz: aprendizCuatro)
		grupos.add(grupoActividadAprendizNueve)
			
		for(int i = 0; i<grupos.size(); i++){
			if (!grupos.get(i).save()) {
				println grupos.get(i).errors
			} else {
				println "Grupos agregados a la bbdd:"
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
			if (!evaluacionesAprendiz.get(i).save()) {
				println evaluacionesAprendiz.get(i).errors
			} else {
				println "evaluacionesAprendiz agregados a la bbdd:"
				println "evaluacion: ${evaluacionesAprendiz.get(i).evaluacion} del aprendiz ${evaluacionesAprendiz.get(i).aprendiz}"
			}
		}
		
		// Calendario
		def ArrayList<Calendario> calendarios = new ArrayList<Calendario>()
		def calendarioUno = new Calendario(anio: 2013, inicioPrimerCuatrimestre: 20130220, inicioSegundoCuatrimestre: 20130810)
		calendarios.add(calendarioUno)
		
		def calendarioDos = new Calendario(anio: 2014, inicioPrimerCuatrimestre: 20140228, inicioSegundoCuatrimestre: 20140815)
		calendarios.add(calendarioDos)
		
		for(int i = 0; i<calendarios.size(); i++){
			if (!calendarios.get(i).save()) {
				println calendarios.get(i).errors
			} else {
				println "calendarios agregados a la bbdd:"
				println calendarios.get(i)
			}
		}
		
		// Red
		def red = Red.instance
		
		if (!red.save()) {
			println red.errors
		} else {
			println "Red agregada a la bbdd:"
			println red.titulo
		}
    }
	
    def destroy = {
    }
}
