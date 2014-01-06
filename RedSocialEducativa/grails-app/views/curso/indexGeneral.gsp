<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div class="nav" role="navigation">
		</div>
		<div>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<br>
		</div>
		<div>
			<br>
			<h4>"${materia}"</h4>>
			<g:link action="indexGeneral" controller="materia" id="${materia.id}">
			<g:message code="Volver" /></g:link>
			<br>
		</div>
		<p>Para tener mayores accesos por favor, inicie sesion</p>
		<div class="nav" role="navigation">
	    	<ul>
		       	<li><g:link class="list" action="index" controller="red">
		       		<g:message code="Pagina Inicial" args="[entityName]" /></g:link></li>
	       	</ul>
		</div>
		<div>
			<br>
			<h2>Proximamente</h2>
			<br>
			<p>1 - Visualizar información y material de los cursos (foros, temas y material general)</p>
			<p>2 - Dejar comentario o mensaje para un curso (foro de curso)</p>
		</div>
	</body>
</html>

