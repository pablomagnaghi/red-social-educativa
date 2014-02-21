<%@ page import="com.fiuba.ForoCurso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'foroCurso.label', default: 'ForoCurso')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-foroCurso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
						<g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="general" params="['cursoId': params.cursoId, 'temaId': params.temaId]">
						<g:message code="Volver a foro Tema" /></g:link></li>	
				<li><g:link class="create" controller="publicacionTema" action="nueva" 
						params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]">
						<g:message code="Publicar respuesta" /></g:link></li>
				<g:if test="${mediador}">
					<li><g:link class="create" controller="publicacionTema" action="eliminar" id="${params.pubInicialId}" 
						params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]">
						<g:message code="Eliminar tema" /></g:link></li>
				</g:if>	
			</ul>
		</div>
		<div id="list-foroCurso" class="content scaffold-list" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		</div>	
		<div>
			<h2>Tema: ${publicacion}</h2>
			<br>
			<table>
			<thead>
					<tr>
						<td>Autor</td>
						<td>Pubilicacion</td>
					</tr>
					
			</thead>
		
			<h2>Mediador = ${mediador}</h2>
			<h2>Aprendiz = ${aprendiz}</h2>
			<tbody>	
				<g:each in="${respuestas}">	
					<tr>
						<td>
							<p>${it.responsable}<p>
							<p>Publicado: ${it.fecha} - ${it.hora}<p>
						</td>
						<td>${it.contenido}</td>
							<g:if test="${mediador || (aprendiz?.usuario?.username == it.dni)}">
								<g:if test="${!it.id.equals(params.pubInicialId as long)}">								
									<td>
										<g:link controller="publicacionTema" action="editar" id="${it.id}" 
											params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]">
											<g:message code="Editar" /></g:link>
										-
										<g:link controller="publicacionTema" action="eliminar" 
											id="${it.id}" params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]">
											<g:message code="Borrar" /></g:link>
									</td>	
								</g:if>
								<g:else>
									<td>
										<g:link controller="publicacionTema" action="editar" id="${it.id}" 
											params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]">
											<g:message code="Editar" /></g:link>
										-
										<g:link controller="publicacionTema" action="eliminar" 
											id="${it.id}" params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]">
											<g:message code="Borrar" /></g:link>
											<p>Al borrar la publicacion inicial, se borran todas sus respuestas</p>
											<p>Equivale a eliminar tema</p>
									</td>
								</g:else>	
							</g:if>	
					</tr>
				</g:each>
			</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${respuestasCant ?: 0}" id="${pubInicialId}" params="['cursoId': params.cursoId, 'temaId': params.temaId]"/>
			</div>
		</div>
	</body>
</html>
