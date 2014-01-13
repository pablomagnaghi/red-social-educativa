<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div>
		    <g:link class="list" action="principal" controller="red">
		       	<g:message code="Volver" args="[entityName]" /></g:link>
		</div>
		<div>
		    <g:link class="list" action="solicitarParticionEnElCurso">
				<g:message code="Solicitar particion en el curso" args="[entityName]" /></g:link>    
		</div>
	</body>
</html>