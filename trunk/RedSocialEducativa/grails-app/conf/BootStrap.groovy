import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date;
import com.cartelera.*
import com.cursado.*
import com.encuesta.*
import com.fiuba.*
import com.foro.*
import com.material.*
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
		
		def usuarioPablo = new Usuario(username: "33300432", password: "33300432", dni: "33300432", apellido: "Magnaghi", nombres: "Pablo", padron: "88126", 
			email: "pablomagnaghi@gmail.com", fechaMembresia: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioPablo)
		def usuarioLuis = new Usuario(username: "31861315", password: "31861315", dni: "31861315", apellido: "Paniagua", nombres: "Luis", padron: "86862", 
			email: "pany100@gmail.com", fechaMembresia: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioLuis)
		def usuarioMessi = new Usuario(username: "10101010", password: "10101010", dni: "10101010", apellido: "Zarate", nombres: "Facundo", padron: "78456",
			email: "nanozarate@gmail.com", fechaMembresia: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioMessi)
		def usuarioAgus = new Usuario(username: "32725217", password: "32725217", dni: "32725217", apellido: "Milla", nombres: "Agustina", padron: "88888", 
			email: "pablomagnaghi@gmail.com", fechaMembresia: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioAgus)
		def usuarioUno = new Usuario(username: "00000001", password: "00000001", dni: "00000001", apellido: "ApeUNO", nombres: "NomUno", padron: "00001", 
			email: "pablomagnaghi@gmail.com", fechaMembresia: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioUno)
		def usuarioDos = new Usuario(username: "00000002", password: "00000002", dni: "00000002", apellido: "ApeDOS", nombres: "NomDOS", padron: "00002", 
			email: "pablomagnaghi@gmail.com", fechaMembresia: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioDos)
		def usuarioTres = new Usuario(username: "00000003", password: "00000003", dni: "00000003", apellido: "ApeTRES", nombres: "NomTRES", padron: "00003", 
			email: "pablomagnaghi@gmail.com", fechaMembresia: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioTres)
		def usuarioCuatro = new Usuario(username: "00000004", password: "00000004", dni: "00000004", apellido: "ApeCUATRO", nombres: "NomCUATRO", padron: "00004", 
			email: "pablomagnaghi@gmail.com", fechaMembresia: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioCuatro)
		def usuarioCinco = new Usuario(username: "00000005", password: "00000005", dni: "00000005", apellido: "ApeCINCO", nombres: "NomCINCO", padron: "00005", 
			email: "pablomagnaghi@gmail.com", fechaMembresia: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioCinco)
		def usuarioSeis = new Usuario(username: "00000006", password: "00000006", dni: "00000006", apellido: "ApeSeis", nombres: "NomSeis", padron: "00006", 
			email: "pablomagnaghi@gmail.com", fechaMembresia: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioSeis)
		def usuarioSiete = new Usuario(username: "00000007", password: "00000007", dni: "00000007", apellido: "ApeSiete", nombres: "NomSiete", padron: "00007", 
			email: "pablomagnaghi@gmail.com", fechaMembresia: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioSiete)
		def usuarioOcho = new Usuario(username: "00000008", password: "00000008", dni: "00000008", apellido: "ApeOcho", nombres: "NomOcho", padron: "00008", 
			email: "pablomagnaghi@gmail.com", fechaMembresia: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioOcho)
		def usuarioNueve = new Usuario(username: "00000009", password: "00000009", dni: "00000009", apellido: "ApeNueve", nombres: "NomNueve", padron: "00009", 
			email: "pablomagnaghi@gmail.com", fechaMembresia: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioNueve)
		def usuarioDiez = new Usuario(username: "00000010", password: "00000010", dni: "00000010", apellido: "ApeDiez", nombres: "NomDiez", padron: "00010", 
			email: "pablomagnaghi@gmail.com", fechaMembresia: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioDiez)
		def usuarioOnce = new Usuario(username: "00000011", password: "00000011", dni: "00000011", apellido: "ApeOnce", nombres: "NomOnce", padron: "00011", 
			email: "pablomagnaghi@gmail.com", fechaMembresia: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioOnce)
		def usuarioDoce = new Usuario(username: "00000012", password: "00000012", dni: "00000012", apellido: "ApeDoce", nombres: "NomDoce", padron: "00012", 
			email: "pablomagnaghi@gmail.com", fechaMembresia: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioDoce)
		def usuarioTrece = new Usuario(username: "00000013", password: "00000013", dni: "00000013", apellido: "ApeTrece", nombres: "NomTrece", padron: "00013", 
			email: "pablomagnaghi@gmail.com", fechaMembresia: (new Date()).format(Utilidades.FORMATO_FECHA))
		usuarios.add(usuarioTrece)
		def usuarioCatorce = new Usuario(username: "00000014", password: "00000014", dni: "00000014", apellido: "ApeCatorce", nombres: "NomCatorce", padron: "00014", 
			email: "pablomagnaghi@gmail.com", fechaMembresia: (new Date()).format(Utilidades.FORMATO_FECHA))
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
		cuerpo: "Vamos por el campeonato", fecha : deStringToDate("01-03-2014"))
		mensajeService.nuevoMensaje(mensajePabloToLuis)
		
		def mensajeAgusToLuisDiez = new Mensaje(emisor: usuarioAgus, receptor: usuarioLuis, asunto: "Mensaje de Agus a Luis 1",
		cuerpo: "Buenas noches!", fecha : deStringToDate("10-03-2014"))
		mensajeService.nuevoMensaje(mensajeAgusToLuisDiez)
		
		def mensajePabloToLuisDos = new Mensaje(emisor: usuarioPablo, receptor: usuarioLuis, asunto: "Mensaje de Pablo a Luis 2",
		cuerpo: "Vamos por el campeonato mundial", fecha : deStringToDate("11-03-2014"))
		mensajeService.nuevoMensaje(mensajePabloToLuisDos)
		def mensajeLuisToPablo = new Mensaje(emisor: usuarioLuis, receptor: usuarioPablo, asunto: "Contestación de Luis a Pablo",
		cuerpo: "Sin dudas vamos por eso", fecha : deStringToDate("12-03-2014"))
		mensajeService.nuevoMensaje(mensajeLuisToPablo)
		def mensajeLuisToAgus = new Mensaje(emisor: usuarioLuis, receptor: usuarioAgus, asunto: "Respuesta a Agus",
		cuerpo: "Buen dia!!", fecha : deStringToDate("13-03-2014"))
		mensajeService.nuevoMensaje(mensajeLuisToAgus)
		def mensajePabloToAgus = new Mensaje(emisor: usuarioPablo, receptor: usuarioAgus, asunto: "Mensaje de Pablo a Agus ",
		cuerpo: "Volver al futuro 2", fecha : deStringToDate("14-03-2014"))
		mensajeService.nuevoMensaje(mensajePabloToAgus)
		def mensajeAgusToLuisDos = new Mensaje(emisor: usuarioAgus, receptor: usuarioLuis, asunto: "Mensaje de Agus a Luis 2",
		cuerpo: "Hola como estas?", fecha : deStringToDate("15-03-2014"))
		mensajeService.nuevoMensaje(mensajeAgusToLuisDos)
	
		// Mediadores	
		def mediadorAgus = new Mediador(usuario: usuarioAgus, rol: rolMediador, jerarquia: "2-JTP");
		def mediadorUno = new Mediador(usuario: usuarioUno, rol: rolMediador, jerarquia: "1-Profesor");
		def mediadorUnoP = new Mediador(usuario: usuarioUno, rol: rolMediador, jerarquia: "3-AP");
		def mediadorDos = new Mediador(usuario: usuarioDos, rol: rolMediador, jerarquia: "1-Profesor");
		def mediadorTres = new Mediador(usuario: usuarioTres, rol: rolMediador, jerarquia: "2-JTP");
		def mediadorCuatro = new Mediador(usuario: usuarioCuatro, rol: rolMediador, jerarquia: "2-JTP");
		def mediadorCinco = new Mediador(usuario: usuarioCinco, rol: rolMediador, jerarquia: "2-JTP");
		def mediadorSeis = new Mediador(usuario: usuarioSeis, rol: rolMediador, jerarquia: "2-JTP");
		
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
		def foroTemaUnoCursoUno = new ForoTema(nombre: "Foro del tema TemaUnoCursoUno del curso ")
		def foroTemaDosCursoUno = new ForoTema(nombre: "Foro del tema TemaDosCursoUno del curso")
		def foroTemaUnoCursoDos = new ForoTema(nombre: "Foro del tema TemaUnoCursoDos del curso")

		// Foros generales de curso		
		def foroCursoUno = new ForoCurso(nombre: "Foro general del curso cursoUno durante el cuatrimestre anio - numero")
		def foroCursoUnoP = new ForoCurso(nombre: "Foro general del curso cursoUno durante el cuatrimestre anio - numero")
		def foroCursoUnoPP = new ForoCurso(nombre: "Foro general del curso cursoUnoP durante el cuatrimestre anio - numero")
		def foroCursoUnoPPP = new ForoCurso(nombre: "Foro general del curso cursoUnoPP durante el cuatrimestre anio - numero")
		def foroCursoUnoPPPP = new ForoCurso(nombre: "Foro general del curso cursoUnoPPP durante el cuatrimestre anio - numero")
		def foroCursoUnoPPPPP = new ForoCurso(nombre: "Foro general del curso cursoUnoPPPP durante el cuatrimestre anio - numero")
		def foroCursoUnoPPPPPP = new ForoCurso(nombre: "Foro general del curso cursoUnoPPPPP durante el cuatrimestre anio - numero")
		def foroCursoDos = new ForoCurso(nombre: "Foro general del curso cursoDos durante el cuatrimestre anio - numero")
		def foroCursoTres = new ForoCurso(nombre: "Foro general del curso cursoTres durante el cuatrimestre anio - numero")
		def foroCursoCuatro = new ForoCurso(nombre: "Foro general del curso cursoCuatro durante el cuatrimestre anio - numero")
		def foroCursoCuatroP = new ForoCurso(nombre: "Foro general del curso cursoCuatroP durante el cuatrimestre anio - numero")
		def foroCursoCuatroPP = new ForoCurso(nombre: "Foro general del curso cursoCuatroPP durante el cuatrimestre anio - numero")
		def foroCursoCuatroPPP = new ForoCurso(nombre: "Foro general del curso cursoCuatroPPP durante el cuatrimestre anio - numero")
		def foroCursoCuatroPPPP = new ForoCurso(nombre: "Foro general del curso cursoCuatroPPPP durante el cuatrimestre anio - numero")
		def foroCursoCinco = new ForoCurso(nombre: "Foro general del curso cursoCinco durante el cuatrimestre anio - numero")
		
		// Publicaciones generales
		def publicacionGeneralUno = new PublicacionGeneral(titulo: "PublicacionGeneralUno", contenido: "Contenido", 
			responsable: "Pablo Magnaghi ${Utilidades.ADMINISTRADOR}", dni: "33300432")
		
		def respuestaUnoPublicacionGeneralUno = new PublicacionGeneral(titulo: "PublicacionGeneralUno", 
			contenido: "Respuesta 1 para saber si funciona el foro principal de la red",
			responsable: "Pablo Magnaghi ${Utilidades.ADMINISTRADOR}", dni: "33300432")
		def respuestaDosPublicacionGeneralUno = new PublicacionGeneral(titulo: "PublicacionGeneralUno",
			contenido: "Respuesta 2 para saber si funciona el foro principal de la red, agregamos mas palabras",
			responsable: "Nom 1 ape 1 ${Utilidades.MIEMBRO}", dni: "00000001")
		def respuestaTresPublicacionGeneralUno = new PublicacionGeneral(titulo: "PublicacionGeneralUno",
			contenido: "Respuesta 3 para saber si funciona el foro principal de la red",
			responsable: "Nom 2 Ape 2 ${Utilidades.MIEMBRO}", dni: "00000002")

		def publicacionGeneralDos = new PublicacionGeneral(titulo: "PublicacionGeneralDos", contenido: "Contenido", 
			responsable: "Pablo Magnaghi ${Utilidades.ADMINISTRADOR}", dni: "33300432")

		def publicacionGeneralTres = new PublicacionGeneral(titulo: "PublicacionGeneralTres", contenido: "Contenido", 
			responsable: "Pablo Magnaghi ${Utilidades.ADMINISTRADOR}", dni: "33300432")
				
		def foroGeneral = new ForoGeneral(nombre: "Foro general")
		foroGeneral.addToPublicaciones(publicacionGeneralUno)
		// Agrego respuestas al foro
		foroGeneral.addToPublicaciones(respuestaUnoPublicacionGeneralUno)
		foroGeneral.addToPublicaciones(respuestaDosPublicacionGeneralUno)
		foroGeneral.addToPublicaciones(respuestaTresPublicacionGeneralUno)
		// Agrego respuestas al tema
		publicacionGeneralUno.addToRespuestas(respuestaUnoPublicacionGeneralUno)
		publicacionGeneralUno.addToRespuestas(respuestaDosPublicacionGeneralUno)
		publicacionGeneralUno.addToRespuestas(respuestaTresPublicacionGeneralUno)
		
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
			responsable: "${mediadorUno.usuario}-${mediadorUno.jerarquia}", categoria: CategoriaMaterial.findByNombre("RefBibliografica"))
		def materialContenidoDos = new MaterialContenido(titulo: "material contenido 2", descripcion: "opcional", autor: "anonimo",
			responsable: "${mediadorUno.usuario}-${mediadorUno.jerarquia}", categoria: CategoriaMaterial.findByNombre("Presentacion"))
		def materialContenidoTres = new MaterialContenido(titulo: "material contenido 3", descripcion: "opcional", autor: "anonimo",
			responsable: "${mediadorUno.usuario}-${mediadorUno.jerarquia}", categoria: CategoriaMaterial.findByNombre("Enlace"))
		def materialContenidoCuatro = new MaterialContenido(titulo: "material contenido 4", descripcion: "opcional", autor: "anonimo",
			responsable: "${mediadorUno.usuario}-${mediadorUno.jerarquia}", categoria: CategoriaMaterial.findByNombre("Glosario"))

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
			responsable: "${mediadorUno}", categoria: CategoriaMaterial.findByNombre("RefBibliografica"))
		def materialTemaDos = new MaterialTema(titulo: "material Tema 2", descripcion: "opcional", autor: "anonimo",
			responsable: "${mediadorUno}", categoria: CategoriaMaterial.findByNombre("Presentacion"))
		def materialTemaTres = new MaterialTema(titulo: "material Tema 3", descripcion: "opcional", autor: "anonimo",
			responsable: "${mediadorDos}", categoria: CategoriaMaterial.findByNombre("Enlace"))
		def materialTemaCuatro = new MaterialTema(titulo: "material Tema 4", descripcion: "opcional", autor: "anonimo",
			responsable: "${mediadorDos}", categoria: CategoriaMaterial.findByNombre("Glosario"))
		
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
		def materialUno = new MaterialCurso(titulo: "material 1 curso 1", descripcion: "opcional", autor: "autor 1",
			responsable: "${mediadorUno}", categoria: CategoriaMaterial.findByNombre("RefBibliografica"))
		def materialDos = new MaterialCurso(titulo: "material 2 curso 1", descripcion: "opcional", autor: "autor 2",
			responsable: "${mediadorUno}", categoria: CategoriaMaterial.findByNombre("Presentacion"))
		def materialTres = new MaterialCurso(titulo: "material 3 curso 1", descripcion: "opcional", autor: "autor 3",
			responsable: "${mediadorUno}", categoria: CategoriaMaterial.findByNombre("Enlace"))
		def materialCuatro = new MaterialCurso(titulo: "material 4 curso 1", descripcion: "opcional", autor: "autor 4",
			responsable: "${mediadorUno}", categoria: CategoriaMaterial.findByNombre("Glosario"))
		def materialCinco = new MaterialCurso(titulo: "material 5 curso 1", descripcion: "opcional", autor: "autor 5",
			responsable: "${mediadorUno}", categoria: CategoriaMaterial.findByNombre("Trabajo"))
		def materialSeis = new MaterialCurso(titulo: "material 6 curso 1", descripcion: "opcional", autor: "autor 6",
			responsable: "${mediadorUno}", categoria: CategoriaMaterial.findByNombre("Cuestionario"))
		
		// Grupos actividad: agrego grupoActividadAprendiz a los grupo actividad
		def grupoActividadUno = new GrupoActividad(numero: "1", nombre: "grupo 1")
		def grupoActividadUnoP = new GrupoActividad(numero: "2", nombre: "grupo 2")
		def grupoActividadDos = new GrupoActividad(numero: "1", nombre: "grupo 1")
		def grupoActividadTres = new GrupoActividad(numero: "1", nombre: "grupo 1")
		def grupoActividadTresP = new GrupoActividad(numero: "2", nombre: "grupo 2")
		def grupoActividadCuatro = new GrupoActividad(numero: "1", nombre: "grupo 1")
		
		// Actividades 
		def actividadUno = new Actividad(titulo: "actividad 1", objetivo: "objetivo", evaluable: true,
			grupal: true, visibilidad: true, categoria: CategoriaActividad.findByNombre("Brainstorming"), 
			fechaFinalizacion: (new Date()).format(Utilidades.FORMATO_FECHA))
		actividadUno.addToGrupos(grupoActividadUno)
		actividadUno.addToGrupos(grupoActividadUnoP)
		
		def actividadDos = new Actividad(titulo: "actividad 2", objetivo: "objetivo", evaluable: true,
			grupal: true, visibilidad: true, categoria: CategoriaActividad.findByNombre("TP"), 
			fechaFinalizacion: 20140315)	
		actividadDos.addToGrupos(grupoActividadDos)
		
		def actividadTres = new Actividad(titulo: "actividad 3", objetivo: "objetivo", evaluable: false,
			grupal: true, visibilidad: true, categoria: CategoriaActividad.findByNombre("Cuestionario"), 
			fechaFinalizacion: (new Date()).format(Utilidades.FORMATO_FECHA))
		actividadTres.addToGrupos(grupoActividadTres)
		actividadTres.addToGrupos(grupoActividadTresP)
		
		def actividadCuatro = new Actividad(titulo: "actividad 4", objetivo: "objetivo", evaluable: false,
			grupal: true, visibilidad: true, categoria: CategoriaActividad.findByNombre("Cuestionario"),
			fechaFinalizacion: (new Date()).format(Utilidades.FORMATO_FECHA))
		actividadCuatro.addToGrupos(grupoActividadCuatro)
		
		def actividadCinco = new Actividad(titulo: "actividad 5", objetivo: "objetivo", evaluable: false,
			grupal: true, visibilidad: true, categoria: CategoriaActividad.findByNombre("Cuestionario"),
			fechaFinalizacion: (new Date()).format(Utilidades.FORMATO_FECHA))

		// Evaluaciones	
		def evaluacionUno = new Evaluacion(nombre: "evaluacion 1", fecha: "20140225", horario: "1900", 
			aula: "1", parcial: true, obligatoria: false, habilitada: true)
		def evaluacionDos = new Evaluacion(nombre: "evaluacion 2", fecha: "20140315", horario: "1930", 
			aula: "2", parcial: true, obligatoria: false, habilitada: true)
		def evaluacionTres = new Evaluacion(nombre: "evaluacion 3", fecha: "20140327", horario: "2000", 
			aula: "3", parcial: true, obligatoria: false, habilitada: false)
		def evaluacionCuatro = new Evaluacion(nombre: "evaluacion 4", fecha: "20140328", horario: "2030", 
			aula: "4", parcial: true, obligatoria: false, habilitada: true)
		def evaluacionCinco = new Evaluacion(nombre: "evaluacion 5", fecha: "20140329", horario: "1900", 
			aula: "5", parcial: true, obligatoria: false, habilitada: true)
		def evaluacionSeis = new Evaluacion(nombre: "evaluacion 6", fecha: "20140330", horario: "1800",
			aula: "6", parcial: true, obligatoria: false, habilitada: true)
		def evaluacionSiete = new Evaluacion(nombre: "evaluacion 7", fecha: "20140325", horario: "1700",
			aula: "7", parcial: true, obligatoria: false, habilitada: true)
		def evaluacionOcho = new Evaluacion(nombre: "evaluacion 8", fecha: "20140326", horario: "1630",
			aula: "8", parcial: true, obligatoria: false, habilitada: true)
		def evaluacionNueve = new Evaluacion(nombre: "evaluacion 9", fecha: (new Date()).format(Utilidades.FORMATO_FECHA), horario: "1930",
			aula: "9", parcial: true, obligatoria: false, habilitada: true)
		
		// Cuatrimestres
		def cuatrimestreUno = new Cuatrimestre(anio: 2013, numero: 2, foro: foroCursoUno)
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
		cuatrimestreUno.addToActividades(actividadCuatro)
		cuatrimestreUno.addToActividades(actividadCinco)

		def cuatrimestreUnoP = new Cuatrimestre(anio: 2013, numero: 1, foro: foroCursoUnoP)
		def cuatrimestreUnoPP = new Cuatrimestre(anio: 2012, numero: 2, foro: foroCursoUnoPP)
		def cuatrimestreUnoPPP = new Cuatrimestre(anio: 2012, numero: 1, foro: foroCursoUnoPPP)
		def cuatrimestreUnoPPPP = new Cuatrimestre(anio: 2011, numero: 2, foro: foroCursoUnoPPPP)
		def cuatrimestreUnoPPPPP = new Cuatrimestre(anio: 2011, numero: 1, foro: foroCursoUnoPPPPP)
		cuatrimestreUnoPPPPP.addToAprendices(aprendizDosPPP)
		
		def cuatrimestreUnoPPPPPP = new Cuatrimestre(anio: 2010, numero: 2, foro: foroCursoUnoPPPPPP)
		
		def cuatrimestreDos = new Cuatrimestre(anio: 2013, numero: 1, foro: foroCursoDos)
		cuatrimestreDos.addToAprendices(aprendizAgus)

		def cuatrimestreTres = new Cuatrimestre(anio: 2013, numero: 1, foro: foroCursoTres)
		cuatrimestreTres.addToAprendices(aprendizUno)
		cuatrimestreTres.addToAprendices(aprendizDosP)
		
		def cuatrimestreCuatro = new Cuatrimestre(anio: 2013, numero: 2, foro: foroCursoCuatro)
		cuatrimestreCuatro.addToAprendices(aprendizUnoP)
		
		def cuatrimestreCuatroP = new Cuatrimestre(anio: 2012, numero: 2, foro: foroCursoCuatroP)
		def cuatrimestreCuatroPP = new Cuatrimestre(anio: 2012, numero: 1, foro: foroCursoCuatroPP)
		def cuatrimestreCuatroPPP = new Cuatrimestre(anio: 2011, numero: 2, foro: foroCursoCuatroPPP)
		def cuatrimestreCuatroPPPP = new Cuatrimestre(anio: 2011, numero: 1, foro: foroCursoCuatroPPPP)
		cuatrimestreCuatroPPPP.addToAprendices(aprendizDosPP)

		def cuatrimestreCinco = new Cuatrimestre(anio: 2012, numero: 2, foro: foroCursoCinco)
		cuatrimestreCinco.addToAprendices(aprendizUnoPP)
		
		// TODO
		// Encuestas
		def opcionChoiceUno = new OpcionChoice(opcion: "nunca")
		def opcionChoiceUnoP = new OpcionChoice(opcion: "a veces")
		def opcionChoiceUnoPP = new OpcionChoice(opcion: "siempre")
		def opcionChoiceDos = new OpcionChoice(opcion: "malo")
		def opcionChoiceDosP = new OpcionChoice(opcion: "regular")
		def opcionChoiceDosPP = new OpcionChoice(opcion: "bueno")
		def opcionChoiceDosPPP = new OpcionChoice(opcion: "muy bueno")
		def opcionChoiceTres = new OpcionChoice(opcion: "si")
		def opcionChoiceTresP = new OpcionChoice(opcion: "no")
		
		def preguntaChoiceUno = new PreguntaChoice(pregunta: "¿Usaste el material del curso?")
		preguntaChoiceUno.addToOpciones(opcionChoiceUno)
		preguntaChoiceUno.addToOpciones(opcionChoiceUnoP)
		preguntaChoiceUno.addToOpciones(opcionChoiceUnoPP)
		
		def preguntaChoiceDos = new PreguntaChoice(pregunta: "¿Como calificas el curso?")
		preguntaChoiceDos.addToOpciones(opcionChoiceDos)
		preguntaChoiceDos.addToOpciones(opcionChoiceDosP)
		preguntaChoiceDos.addToOpciones(opcionChoiceDosPP)
		preguntaChoiceDos.addToOpciones(opcionChoiceDosPPP)
		
		def preguntaChoiceTres = new PreguntaChoice(pregunta: "¿Volverias a elegir este curso?")
		preguntaChoiceTres.addToOpciones(opcionChoiceTres)
		preguntaChoiceTres.addToOpciones(opcionChoiceTresP)
		
		def preguntaDesarrolloUno = new PreguntaDesarrollo(pregunta: "¿Cual es tu opinión sobre el curso?")
		def preguntaDesarrolloDos = new PreguntaDesarrollo(pregunta: "¿Que aspectos mejorarias?")
		
		def preguntaPuntajeUno = new PreguntaPuntaje(pregunta: "Nivel de dificultad de las evaluaciones")
		def preguntaPuntajeDos = new PreguntaPuntaje(pregunta: "Nivel de dificultad de las actividades")
		
		def encuestaUno = new Encuesta(nombre: "Opinion sobre el curso en general")
		encuestaUno.addToPreguntasChoice(preguntaChoiceUno)
		encuestaUno.addToPreguntasChoice(preguntaChoiceDos)
		encuestaUno.addToPreguntasChoice(preguntaChoiceTres)
		encuestaUno.addToPreguntasDesarrollo(preguntaDesarrolloUno)
		encuestaUno.addToPreguntasDesarrollo(preguntaDesarrolloDos)
		encuestaUno.addToPreguntasPuntaje(preguntaPuntajeUno)
		encuestaUno.addToPreguntasPuntaje(preguntaPuntajeDos)
		
		
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
		cursoUno.addToEvaluaciones(evaluacionSeis)
		cursoUno.addToEvaluaciones(evaluacionSiete)
		cursoUno.addToEvaluaciones(evaluacionOcho)
		cursoUno.addToEvaluaciones(evaluacionNueve)
		
		cursoUno.addToEncuestas(encuestaUno)

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
		
		// Asignaturas - con carga de cursos
		def ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>()
		def asignaturaUno = new Asignatura(codigo: "75.01", nombre: "asignaturaUno", creditos: "6", contenidosMinimos: "contenidos")
		asignaturaUno.addToCursos(cursoUno)
		asignaturaUno.addToCursos(cursoDos)
		asignaturaUno.addToCursos(cursoTres)
		asignaturas.add(asignaturaUno)
		def asignaturaDos = new Asignatura(codigo: "75.02", nombre: "asignaturaDos", creditos: "6", contenidosMinimos: "contenidos")
		asignaturaDos.addToCursos(cursoCuatro)
		asignaturas.add(asignaturaDos)
		def asignaturaTres = new Asignatura(codigo: "75.03", nombre: "asignaturaTres", creditos: "6", contenidosMinimos: "contenidos")
		asignaturaTres.addToCursos(cursoCinco)
		asignaturas.add(asignaturaTres)
		
		println "Asignaturas agregadas a la bbdd"
		for(int i = 0; i<asignaturas.size(); i++){
			if (!asignaturas.get(i).save()) {
				println asignaturas.get(i).errors
			} else {
				//println "Asignaturas agregadas a la bbdd:"
				//println asignaturas.get(i).codigo
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
		
		println "Noticias curso uno agregados a la bbdd"
		for(int i = 0; i<noticiasCursoUno.size(); i++){
			if (!noticiasCursoUno.get(i).save()) {
				println noticiasCursoUno.get(i).errors
			} else {
				//println "Noticias curso uno agregados a la bbdd:"
				//println noticiasCursoUno.get(i)
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
			
		println "Administradores agregados a la bbdd"
		for(int i = 0; i<administradores.size(); i++){
			if (!administradores.get(i).save()) {
				println administradores.get(i).errors
			} else {
				//println "Administradores agregados a la bbdd:"
				//println administradores.get(i).usuario
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
		
		println "Miembros agregados a la bbdd"
		for(int i = 0; i<miembros.size(); i++){
			if (!	miembros.get(i).save()) {
				println miembros.get(i).errors
			} else {
				//println "Miembros agregados a la bbdd:"
				//println miembros.get(i).usuario
			}
		}	
		
		// Grupos actividad aprendiz (tabla intermedia para la relacion N N entre grupo actividad y aprendiz)	
		def ArrayList<GrupoActividadAprendiz> grupos = new ArrayList<GrupoActividadAprendiz>()
			
		def grupoActividadAprendizUno = new GrupoActividadAprendiz(grupo: grupoActividadUno, aprendiz: aprendizDos)
		grupos.add(grupoActividadAprendizUno)
		def grupoActividadAprendizDos = new GrupoActividadAprendiz(grupo: grupoActividadUno, aprendiz: aprendizTres)
		grupos.add(grupoActividadAprendizDos)
		def grupoActividadAprendizTres = new GrupoActividadAprendiz(grupo: grupoActividadUno, aprendiz: aprendizCuatro)
		grupos.add(grupoActividadAprendizTres)
		def grupoActividadAprendizCuatro = new GrupoActividadAprendiz(grupo: grupoActividadUnoP, aprendiz: aprendizCinco)
		grupos.add(grupoActividadAprendizCuatro)
		def grupoActividadAprendizCinco = new GrupoActividadAprendiz(grupo: grupoActividadUnoP, aprendiz: aprendizSeis)
		grupos.add(grupoActividadAprendizCinco)
		def grupoActividadAprendizSeis = new GrupoActividadAprendiz(grupo: grupoActividadUnoP, aprendiz: aprendizSiete)
		grupos.add(grupoActividadAprendizSeis)
		def grupoActividadAprendizSiete = new GrupoActividadAprendiz(grupo: grupoActividadDos, aprendiz: aprendizDos)
		grupos.add(grupoActividadAprendizSiete)
		def grupoActividadAprendizOcho = new GrupoActividadAprendiz(grupo: grupoActividadDos, aprendiz: aprendizTres)
		grupos.add(grupoActividadAprendizOcho)
		def grupoActividadAprendizNueve = new GrupoActividadAprendiz(grupo: grupoActividadDos, aprendiz: aprendizCuatro)
		grupos.add(grupoActividadAprendizNueve)
		def grupoActividadAprendizDiez = new GrupoActividadAprendiz(grupo: grupoActividadTres, aprendiz: aprendizDos)
		grupos.add(grupoActividadAprendizDiez)
		def grupoActividadAprendizOnce = new GrupoActividadAprendiz(grupo: grupoActividadTres, aprendiz: aprendizTres)
		grupos.add(grupoActividadAprendizOnce)
		def grupoActividadAprendizDoce = new GrupoActividadAprendiz(grupo: grupoActividadTres, aprendiz: aprendizCuatro)
		grupos.add(grupoActividadAprendizDoce)
		def grupoActividadAprendizTrece = new GrupoActividadAprendiz(grupo: grupoActividadTres, aprendiz: aprendizCinco)
		grupos.add(grupoActividadAprendizTrece)
		def grupoActividadAprendizCatorce = new GrupoActividadAprendiz(grupo: grupoActividadTresP, aprendiz: aprendizSeis)
		grupos.add(grupoActividadAprendizCatorce)
		def grupoActividadAprendizQuince = new GrupoActividadAprendiz(grupo: grupoActividadTresP, aprendiz: aprendizSiete)
		grupos.add(grupoActividadAprendizQuince)
		def grupoActividadAprendizDieciseis = new GrupoActividadAprendiz(grupo: grupoActividadCuatro, aprendiz: aprendizDos)
		grupos.add(grupoActividadAprendizDieciseis)
		def grupoActividadAprendizDiecisiete = new GrupoActividadAprendiz(grupo: grupoActividadCuatro, aprendiz: aprendizTres)
		grupos.add(grupoActividadAprendizDiecisiete)
		def grupoActividadAprendizDieciocho = new GrupoActividadAprendiz(grupo: grupoActividadCuatro, aprendiz: aprendizCuatro)
		grupos.add(grupoActividadAprendizDieciocho)
		def grupoActividadAprendizDiecinueve = new GrupoActividadAprendiz(grupo: grupoActividadCuatro, aprendiz: aprendizCinco)
		grupos.add(grupoActividadAprendizDiecinueve)
		def grupoActividadAprendizVeinte = new GrupoActividadAprendiz(grupo: grupoActividadCuatro, aprendiz: aprendizSeis)
		grupos.add(grupoActividadAprendizVeinte)
		def grupoActividadAprendizVeintiUno = new GrupoActividadAprendiz(grupo: grupoActividadCuatro, aprendiz: aprendizSiete)
		grupos.add(grupoActividadAprendizVeintiUno)
			
		for(int i = 0; i<grupos.size(); i++){
			if (!grupos.get(i).save()) {
				println grupos.get(i).errors
			} else {
				println "Grupos agregados a la bbdd:"
				println grupos.get(i)
			}
		}
		
		// Evaluacion aprendiz (tabla intermedia para la relacion N N entre evaluacion y aprendiz)
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
		// Para probar notas por recursantes
		def evaluacionAprendizOnce = new EvaluacionAprendiz(evaluacion: evaluacionSiete, aprendiz: aprendizDosPPP)
		evaluacionesAprendiz.add(evaluacionAprendizOnce)
		def evaluacionAprendizDoce = new EvaluacionAprendiz(evaluacion: evaluacionOcho, aprendiz: aprendizDosPPP)
		evaluacionesAprendiz.add(evaluacionAprendizDoce)
		def evaluacionAprendizTrece = new EvaluacionAprendiz(evaluacion: evaluacionNueve, aprendiz: aprendizDosPPP)
		evaluacionesAprendiz.add(evaluacionAprendizTrece)
		
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
		
		def calendarioDos = new Calendario(anio: 2014, inicioPrimerCuatrimestre: 20140328, inicioSegundoCuatrimestre: 20140815)
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
	
	public static synchronized Date deStringToDate(String fecha) {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaEnviar = null;
		try {
			fechaEnviar = formatoDelTexto.parse(fecha);
			return fechaEnviar;
		} catch (ParseException ex) {
			ex.printStackTrace();
			return null;
		}
	}

    def destroy = {
    }
}
