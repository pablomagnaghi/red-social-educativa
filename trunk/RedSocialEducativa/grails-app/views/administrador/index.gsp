<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div>
			<ol>
				<li><g:link class="list" action="index" controller="red">
					<g:message code="Volver"/></g:link></li>
				<li><g:link class="list" action="index" controller="rol">
					<g:message code="Roles" /></g:link></li>
				<li><g:link class="list" action="index" controller="accion">
					<g:message code="Acciones" /></g:link></li>
				<li><g:link class="list" action="index" controller="materia">
					<g:message code="Materias" /></g:link></li>
				<li><g:link class="list" action="index" controller="curso">
					<g:message code="Cursos" /></g:link></li>
				<li><g:link class="list" action="index" controller="cartelera">
					<g:message code="Cartelera General" /></g:link></li>
				<li><g:link class="list" action="configuracion" controller="red">
					<g:message code="Configuracion" /></g:link></li>
				<li><g:link class="list" action="index" controller="foro">
					<g:message code="Foros generales" /></g:link></li>	
				<li><g:link class="list" action="index" controller="mediador">
					<g:message code="Mediadores" /></g:link></li>
			</ol>
		</div>
		<div>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<br>
			<g:if test="${usuarios}">
				<h3>Membresias esperando activacion</h3>
				<ol>
					<g:each in="${usuarios}" var="usuario">
						<li>
							<span>${usuario}</span>
							<span class = "menuButton">
								<g:link action="activarUsuario" id="${usuario.id}">Activar</g:link>
							</span>
						</li>
					</g:each>
				</ol>
			</g:if>
		</div>
	</body>
</html>
