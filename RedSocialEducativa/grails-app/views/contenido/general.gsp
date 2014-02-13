<%@ page import="com.fiuba.Contenido" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contenido.label', default: 'Contenido')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-foroCurso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="create" controller="tema" action="general" id="${temaId}" params="['cursoId': params.cursoId]">
					<g:message code="Tema ${tema}" /></g:link></li>	
			</ul>
		</div>
		<div>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		</div>	
				<h2>cursoId: ${params.cursoId}</h2>
		<h2>temaId: ${params.temaId}</h2>
		<div>
			<p>Contenidos del ${tema} del Curso: ${com.fiuba.Curso.get(cursoId)}</p>
			<br>
				<ol>
					<g:each in="${contenidos}" >
						<li>${it.titulo}</li>
							<ol>
								<g:if test="${it.materiales}">
									<p>Material del contenido</p>
									<g:each in="${it.materiales}" var="material">
										<div><h1><g:message code="Material: ${material.titulo}" args="[entityName]" /></h1></div>
										<div>
											<p>Autor: ${material.autor}</p>
											<p>Descripcion: ${material.descripcion}</p>
											<p>Categoria": ${material.categoria}</p>	
											<p>Responsable: ${material.responsable}</p>
											<p>Fecha"${material.fecha}</p>
											<p>Poner link al material(URL)</p>
										</div>
									</g:each>
								</g:if>	
							</ol>
					</g:each>	
				</ol>	
			<div class="pagination">
				<g:paginate total="${contenidosCant ?: 0}" params="['cursoId': params.cursoId, 'temaId': params.temaId]"/>
			</div>
		</div>
	</body>
</html>