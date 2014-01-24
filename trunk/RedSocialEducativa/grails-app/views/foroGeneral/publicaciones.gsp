<%@ page import="com.fiuba.ForoGeneral" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'foroGeneral.label', default: 'ForoGeneral')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-foroGeneral" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="general">
					<g:message code="Volver a foro general" /></g:link></li>	
				<li><g:link class="create" controller="publicacionGeneral" action="nueva" params="['pubInicialId': pubInicialId]">
					<g:message code="Publicar respuesta" /></g:link></li>
				<g:if test="${administrador}">
					<li><g:link class="create" controller="publicacionGeneral" action="eliminar" id="${pubInicialId}" 
						params="['pubInicialId': pubInicialId]">
						<g:message code="Eliminar tema" /></g:link></li>
				</g:if>	
			</ul>
		</div>
		<div id="list-foroGeneral" class="content scaffold-list" role="main">
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
						
						<g:if test="${administrador}">
							<td></td>
						</g:if>	
						
					</tr>
					
			</thead>
			<tbody>	
				<g:each in="${respuestas}">	
					<tr>
						<td>
							<p>${it.responsable}<p>
							<p>Publicado: ${it.fecha} - ${it.hora}<p>
						</td>
						<td>${it.contenido}</td>
						
						<g:if test="${administrador}">
							<g:if test="${!it.id.equals(pubInicialId as long)}">								
								<td>
									<g:link controller="publicacionGeneral" action="editar" id="${it.id}" params="['pubInicialId': pubInicialId]">
										<g:message code="Editar" /></g:link>
									-
									<g:link controller="publicacionGeneral" action="eliminar" 
										id="${it.id}" params="['pubInicialId': pubInicialId]">
										<g:message code="Borrar" /></g:link>
								</td>	
							</g:if>
							<g:else>
								<td>
									<g:link controller="publicacionGeneral" action="editar" id="${it.id}" params="['pubInicialId': pubInicialId]">
										<g:message code="Editar" /></g:link>
									-
									<g:link controller="publicacionGeneral" action="eliminar" 
										id="${it.id}" params="['pubInicialId': pubInicialId]">
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
				<g:paginate total="${respuestasCant ?: 0}" id="${pubInicialId}"/>
			</div>
		</div>
	</body>
</html>
