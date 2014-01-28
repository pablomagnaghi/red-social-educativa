<%@ page import="com.fiuba.MaterialTema" %>
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
				<li><g:link class="create" controller="tema" action="general" id="${temaId}" params="['cursoId': cursoId]">
					<g:message code="Tema ${tema}" /></g:link></li>	
			</ul>
		</div>
		<div>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		</div>	
		<div>
			<p>Material del tema ${tema} del Curso: ${com.fiuba.Curso.get(cursoId)}</p>
			<br>
				<ol>
					<g:each in="${materiales}">
						<div><h1><g:message code="Material: ${it.titulo}" args="[entityName]" /></h1></div>
						<div>
							<p>Autor: ${it.autor}</p>
							<p>Descripcion: ${it.descripcion}</p>
							<p>Categoria": ${it.categoria}</p>	
							<p>Responsable: ${it.responsable}</p>
							<p>Fecha"${it.fecha}</p>
							<p>Poner link al material(URL)</p>
						</div>
					</g:each>
				</ol>	
			<div class="pagination">
				<g:paginate total="${contenidosCant ?: 0}" params="['cursoId': cursoId, 'temaId': temaId]"/>
			</div>
		</div>
		<div>
			<p>Materiales de contenidos del ${tema} del Curso: ${com.fiuba.Curso.get(cursoId)}</p>
			<br>
				<ol>
					<g:each in="${contenidos}" >
						<g:if test="${it.materiales}">
							<li>${it.titulo}</li>
							<ol>
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
							</ol>
						</g:if>	
					</g:each>	
				</ol>	
			<div class="pagination">
				<g:paginate total="${contenidosCant ?: 0}" params="['cursoId': cursoId, 'temaId': temaId]"/>
			</div>
		</div>		
	</body>
</html>