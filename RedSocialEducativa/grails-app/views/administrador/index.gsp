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
				<li><g:link class="list" action="index" controller="rol">
					<g:message code="AdministrarRoles" /></g:link></li>
				<li><g:link class="list" action="index" controller="accion">
					<g:message code="AdministrarAcciones" /></g:link></li>
				<li><g:link class="list" action="indexAdm" controller="materia">
					<g:message code="AdministrarMaterias" /></g:link></li>
				<li><g:link class="list" action="indexMed" controller="curso">
					<g:message code="AdministrarCursos" /></g:link></li>
				<li><g:link class="list" action="index" controller="mediador">
					<g:message code="AdministrarMediadores" /></g:link></li>
			</ul>
		</div>
		<div>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<br>
			<ol>
				<g:each in="${membresias}" var="membresia">
					<li>
						<span>${membresia}</span>
						<span class = "menuButton">
							<g:link action="activarMiembro" id="${membresia.id}">Activar</g:link>
						</span>
					</li>
				</g:each>
			</ol>
		</div>
	</body>
</html>
