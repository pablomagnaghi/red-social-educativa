<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="index" controller="red">
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
			<ol>
				<g:each in="${aprendices}" var="aprendiz">
					<li>
						<span>${aprendiz}</span>
						<span class = "menuButton">
							<g:link action="activarAprendiz" id="${aprendiz.id}">Activar</g:link>
						</span>
					</li>
				</g:each>
			</ol>
		</div>
	</body>
</html>
