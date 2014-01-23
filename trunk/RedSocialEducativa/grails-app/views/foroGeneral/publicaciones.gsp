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
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div>
			<h2></h2>
			<br>
			<h2>Tema: ${publicacion}</h2>
			<br>
			<hr>
			<p>Publicacion: ${publicacion.titulo} - Responsable: ${publicacion.responsable} - Fecha: ${publicacion.fecha} - Hora: ${publicacion.hora} </p>
			<p>[[[${publicacion.contenido}]]]</p>
			<br>
			<hr>
			<g:if test="${!respuestas}">
				<g:link controller="publicacionGeneral" action="respuesta" params="['padreId': padreId]">Responder</g:link>	
		    </g:if>
		    <g:else>			
		 		<g:each in="${respuestas}">
		 			<p>Publicacion: ${it.titulo} - Responsable: ${it.responsable} - Fecha: ${it.fecha} - Hora: ${it.hora} </p>
					<p>[[[${it.contenido}]]]</p>
						<g:set var="padreId" value="${it.id}" />
						padreId = ${padreId}
			    		<br>
			    		<br>
			    		<hr>
				</g:each>
				<g:link controller="publicacionGeneral" action="respuesta" 
			    	params="['padreId': padreId]">Responder</g:link>
			</g:else>
			<!-- 
			<div class="pagination">
				<g:paginate total="${respuestasCant ?: 0}"/>
			</div>-->
		</div>
	</body>
</html>



