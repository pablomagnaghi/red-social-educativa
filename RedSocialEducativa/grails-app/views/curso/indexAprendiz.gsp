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
		<div class="nav" role="navigation">
	    	<ul>
		       	<li><g:link class="list" action="solicitarParticionEnCurso">
		       		<g:message code="solicitarParticionEnCurso" args="[entityName]" /></g:link></li>
		       	<li><g:link class="list" action="salir">
		       		<g:message code="Salir" args="[entityName]" /></g:link></li>
	       	</ul>
		</div>
	</body>
</html>