<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div>
			<h2>
				<p>"${com.fiuba.Curso.get(cursoId)}"</p>
				<br>
				<p>Bienvenido aprendiz: "${aprendiz}"</p>
			</h2> 
		</div>
		<div>
			<g:link class="list" action="principal" controller="red">
			<g:message code="Pagina Inicial" args="[entityName]" /></g:link>
		</div>
		<div>
			<fieldset class="form">
				<g:render template="esquema"/>
			</fieldset>   
		</div>
		<div>
			<g:if test="${aprendiz?.participa}">
				<p>PARTICIPA</p>
	    	</g:if>
	    	<g:else>
			    <p>Su solicitud de particion en el curso ya ha sido recibida.</p>
			</g:else>    
		</div>
	</body>
</html>