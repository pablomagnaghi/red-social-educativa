<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'material.label', default: 'Material')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tema" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
			</ul>
		</div>		
		<h2>Curso id: ${params.cursoId}</h2>
		<div>
			<h1><g:message code="Material del curso: ${com.fiuba.Curso.get(params.cursoId)}" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div>
				<ol>
				<g:each in="${materiales}">
					<g:if test="${it.categoria.nombre == "RefBibliografica" || it.categoria.nombre == "Enlace" ||
							it.categoria.nombre == "Glosario" || mediador || aprendiz}">
						<span>
							<li>${it.titulo}</li> 
							<p>ACA PONER LA TABLA CON LOS DATOS DEL MATERIAL Y EL LINK DE DESCARGAz</p>
							<p> (SALE EL ACCEDER, MENOS CLICKS, se muestra esa info directamente)</p>
						</span>
						<span>
							<g:link controller="materialCurso" action="curso" id="${it.id}" params="['cursoId': params.cursoId]">
								<g:message code="Acceder al material"/></g:link>		
						</span>		
					</g:if>
				</g:each>
				</ol>
			</div>
			<div class="pagination">
				<g:paginate total="${materialesCursoCant ?: 0}" params="['cursoId': params.cursoId]"/>
			</div>
		</div>
	</body>
</html>