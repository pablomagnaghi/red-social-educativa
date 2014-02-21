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
				<br>	
				<li><g:link class="list" action="index" controller="calendario">
					<g:message code="Calendario" /></g:link></li>	
				<br>
				<li><g:link class="list" action="configuracion" controller="red">
					<g:message code="Configuracion de la red" /></g:link></li>	
				<br>
				<li><g:link class="list" action="index" controller="materia">
					<g:message code="Materias" /></g:link></li>
				<br>
				<li><g:link class="list" action="index" controller="curso">
					<g:message code="Cursos" /></g:link></li>
				<br>	
				<li><g:link class="list" action="index" controller="mediador">
					<g:message code="Mediadores" /></g:link></li>
				<br>	
				<li><g:link class="list" action="index" controller="noticiaRed">
					<g:message code="Cartelera general" /></g:link></li>
				<br>
				<li><g:link class="list" action="general" controller="foroGeneral">
					<g:message code="Foros general" /></g:link></li>	
			</ol>
		</div>
		<div>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		</div>
	</body>
</html>
