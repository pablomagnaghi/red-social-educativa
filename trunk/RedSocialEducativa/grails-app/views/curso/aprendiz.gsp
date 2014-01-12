<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="salir">
					<g:message code="Salir"/></g:link></li>
				<li><g:link class="list" action="index" controller="aprendiz">
					<g:message code="AdministrarAprendices" /></g:link></li>
			</ul>
		</div>
		<div>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<br>
		</div>
	</body>
</html>
