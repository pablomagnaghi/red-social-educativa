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
			<div>
		    "Bienvenido al curso aprendiz: ${aprendiz}"
		    	<div>
	       	<br><h2>Cartelera general</h2>
			<g:each in="${noticiasCurso}">
				<g:if test="${it.visibilidad}">
					<p>Noticia: ${it.titulo} - Fecha: ${it.fecha} - Mediador: ${it.mediador}</p>
					<p>[${it.texto}]</p>
					<br>
				</g:if>
    		</g:each>
    		</div>
    	</g:if>
    	<g:else>
    		<div>
		    	<p>Su solicitud de particion en el curso ya ha sido recibida.</p>
			</div>
		</g:else>       
	</body>
</html>