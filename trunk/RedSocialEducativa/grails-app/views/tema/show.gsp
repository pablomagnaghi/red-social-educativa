
<%@ page import="com.fiuba.Tema" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tema.label', default: 'Tema')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tema" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="mediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Pagina principal de mediador"/></g:link></li>		
				<li><g:link class="create" action="menuMediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Tareas mediador" /></g:link></li>
				<li><g:link class="list" action="index" params="['cursoId': params.cursoId]">
					<g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create" params="['cursoId': params.cursoId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
			<h2>Params: ${params}</h2>
		<h2>Curso: ${com.fiuba.Curso.get(params.cursoId)}</h2>
		<h2>Curso Id: ${params.cursoId}</h2>
		<div id="show-tema" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tema">
			
				<g:if test="${temaInstance?.titulo}">
				<li class="fieldcontain">
					<span id="titulo-label" class="property-label"><g:message code="tema.titulo.label" default="Titulo" /></span>
						<span class="property-value" aria-labelledby="titulo-label"><g:fieldValue bean="${temaInstance}" field="titulo"/></span>
				</li>
				</g:if>
				<g:if test="${temaInstance?.foro}">
				<li class="fieldcontain">
					<span id="foro-label" class="property-label"><g:message code="tema.foro.label" default="Foro" /></span>
						<span class="property-value" aria-labelledby="foro-label">${temaInstance?.foro?.encodeAsHTML()}</span>
				</li>
				</g:if>
				<g:if test="${temaInstance?.contenidos}">
				<li class="fieldcontain">
					<span id="contenidos-label" class="property-label"><g:message code="tema.contenidos.label" default="Contenidos" /></span>
						<g:each in="${temaInstance.contenidos}" var="c">
						<span class="property-value" aria-labelledby="contenidos-label">
							<g:link controller="contenido" action="show" id="${c.id}" 
								params="['cursoId': params.cursoId, 'temaId': temaInstance.id]">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
				</li>
				</g:if>
				<g:if test="${temaInstance?.materiales}">
				<li class="fieldcontain">
					<span id="materiales-label" class="property-label"><g:message code="tema.materiales.label" default="Materiales" /></span>
						<g:each in="${temaInstance.materiales}" var="m">
						<span class="property-value" aria-labelledby="materiales-label">
							<g:link controller="materialTema" action="show" id="${m.id}" 
								params="['cursoId': params.cursoId, 'temaId': temaInstance.id]">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
				</li>
				</g:if>
			</ol>
			<g:form action="delete" method="DELETE" id="${temaInstance.id}" params="['cursoId': params.cursoId]">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${temaInstance}" 
						id="${temaInstance.id}" params="['cursoId': params.cursoId]">
						<g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
						onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
