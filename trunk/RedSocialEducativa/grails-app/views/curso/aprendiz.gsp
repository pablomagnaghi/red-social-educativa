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
			<g:link class="list" action="principal" controller="red">
			<g:message code="Pagina Inicial" args="[entityName]" /></g:link>
		</div>
		<g:if test="${aprendiz?.participa}">
			<fieldset class="form">
			<g:render template="noticiasCurso"/>
			</fieldset>
    	</g:if>
    	<g:else>
    		<div>
		    	<p>Su solicitud de particion en el curso ya ha sido recibida.</p>
			</div>
		</g:else>       
	</body>
</html>