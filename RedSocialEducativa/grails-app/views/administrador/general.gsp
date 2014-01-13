<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div>
			<ol>
				<li><g:link class="list" action="principal" controller="red">
					<g:message code="Volver"/></g:link></li>
				<li><g:link class="list" action="index" controller="materia">
					<g:message code="Registrar y eliminar materias" /></g:link></li>
				<li><g:link class="list" action="index" controller="curso">
					<g:message code="Registrar y eliminar cursos de materias" /></g:link></li>
				<li><g:link class="list" action="index" controller="cartelera">
					<g:message code="Administrar cartelera general" /></g:link></li>
				<li><g:link class="list" action="configuracion" controller="red">
					<g:message code="Administrar configuracion de la red" /></g:link></li>
				<li><g:link class="list" action="index" controller="foro">
					<g:message code="Administrar foros generales" /></g:link></li>	
				<li><g:link class="list" action="index" controller="mediador">
					<g:message code="Administrar mediadores" /></g:link></li>
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
