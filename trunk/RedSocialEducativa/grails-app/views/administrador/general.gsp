<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div>
			<h3>Menu principal de administrador</h3>
			<br>
			<ol>
				<li><g:link class="list" action="principal" controller="red">
					<g:message code="Pagina principal"/></g:link></li>
				<li><g:link class="list" action="index" controller="materia">
					<g:message code="Administrar materias" /></g:link></li>
				<li><g:link class="list" action="index" controller="curso">
					<g:message code="Administrar cursos" /></g:link></li>
				<li><g:link class="list" action="index" controller="noticiaRed">
					<g:message code="Administrar cartelera general" /></g:link></li>
				<li><g:link class="list" action="configuracion" controller="red">
					<g:message code="Administrar configuracion de la red" /></g:link></li>
				<li><g:link class="list" action="index" controller="foro">
					<g:message code="Administrar foros generales" /></g:link></li>	
				<li><g:link class="list" action="index" controller="mediador">
					<g:message code="Administrar mediadores" /></g:link></li>
				<li><g:link class="list" action="index" controller="usuario">
					<g:message code="Aceptar miembros" /></g:link></li>
			</ol>
		</div>
		<div>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		</div>
	</body>
</html>
