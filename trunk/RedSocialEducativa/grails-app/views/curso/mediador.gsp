<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="principal" controller="red">
					<g:message code="Pagina principal"/></g:link></li>
				<li><g:link class="list" action="index" controller="aprendiz">
					<g:message code="Administrar aprendices" /></g:link></li>
				<li><g:link class="list" action="index" controller="temas">
					<g:message code="Administrar temas del curso" /></g:link></li>
				<li><g:link class="list" action="index" controller="noticiaCurso">
					<g:message code="Administrar cartelera del curso" /></g:link></li>
				<!--  
				<li><g:link class="list" action="indexMed" controller="curso">
					<g:message code="Administrar Material" /></g:link></li>
				<li><g:link class="list" action="index" controller="mediador">
					<g:message code="Administrar Foros" /></g:link></li>
				-->
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
							<g:link action="activarAprendiz" controller="mediador" id="${aprendiz.id}">Activar</g:link>
						</span>
					</li>
				</g:each>
			</ol>
		</div>
	</body>
</html>
