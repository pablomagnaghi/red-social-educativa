<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div>
			<ol>
				<li><g:link class="list" action="principal" controller="red">
					<g:message code="Pagina principal"/></g:link></li>
				<li><g:link class="list" action="index" controller="aprendiz" id="${cursoId}">
					<g:message code="Administrar aprendices" /></g:link></li>
				<li><g:link class="list" action="index" controller="temas">
					<g:message code="Administrar temas del curso" /></g:link></li>
				<li><g:link class="list" action="index" controller="noticiaCurso">
					<g:message code="Administrar cartelera del curso" /></g:link></li>
			</ol>
		</div>
		<div>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<br>
			<g:if test="${aprendices}">
				<h3>Aprendices esperando aceptacion</h3>
				<ol>
					<g:each in="${aprendices}" var="aprendiz">
						<li>
							<span>${aprendiz}</span>
							<span class = "menuButton">
								<g:link action="activarAprendiz" controller="mediador" id="${cursoId}" params="['aprendizId': aprendiz.id]">
									Aceptar</g:link>
							</span>
						</li>
					</g:each>
				</ol>
			</g:if>
		</div>
	</body>
</html>
