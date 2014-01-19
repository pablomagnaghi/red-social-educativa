<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="menuMediador" controller="curso">
					<g:message code="Pagina principal"/></g:link></li>
			</ul>
		</div>
		<div>
			<h2>Administracion de cartelera de mensajes</h2>
		</div>
		<div>
			<g:each in="${noticiasCurso}">
				<p>Noticia: ${it.titulo} - Fecha: ${it.fecha} - Mediador: ${it.mediador}</p>
				<p>[${it.texto}]</p>
				<br>
    		</g:each>
		</div>
	</body>
</html>