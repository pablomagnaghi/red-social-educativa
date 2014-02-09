
<%@ page import="com.fiuba.MaterialGrupo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materialGrupo.label', default: 'MaterialGrupo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-materialGrupo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link controller="grupoCurso" action="general" params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]">
					<g:message code="Lista de grupos del curso ${com.fiuba.Curso.get(cursoId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="grupoCurso" action="mostrar" id="${grupoId}" 
					params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId, 'grupoId': grupoId]">
					<g:message code="Grupo: ${com.fiuba.GrupoCurso.get(grupoId)}" args="[entityName]" /></g:link>
			</ul>
		</div>
		<div id="show-materialGrupo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list materialGrupo">
			
				<g:if test="${materialGrupoInstance?.autor}">
				<li class="fieldcontain">
					<span id="autor-label" class="property-label"><g:message code="materialGrupo.autor.label" default="Autor" /></span>
					
						<span class="property-value" aria-labelledby="autor-label"><g:fieldValue bean="${materialGrupoInstance}" field="autor"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialGrupoInstance?.categoria}">
				<li class="fieldcontain">
					<span id="categoria-label" class="property-label">
						<g:message code="materialGrupo.categoria.label" default="Categoria" /></span>
					
						<span class="property-value" aria-labelledby="categoria-label">
							${materialGrupoInstance?.categoria?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${materialGrupoInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="materialGrupo.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${materialGrupoInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialGrupoInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="materialGrupo.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:fieldValue bean="${materialGrupoInstance}" field="fecha"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialGrupoInstance?.responsable}">
				<li class="fieldcontain">
					<span id="responsable-label" class="property-label"><g:message code="materialGrupo.responsable.label" default="Responsable" /></span>
					
						<span class="property-value" aria-labelledby="responsable-label"><g:fieldValue bean="${materialGrupoInstance}" field="responsable"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialGrupoInstance?.titulo}">
				<li class="fieldcontain">
					<span id="titulo-label" class="property-label"><g:message code="materialGrupo.titulo.label" default="Titulo" /></span>
					
						<span class="property-value" aria-labelledby="titulo-label"><g:fieldValue bean="${materialGrupoInstance}" field="titulo"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form action="delete" method="DELETE" id="${materialGrupoInstance.id}" 
				params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId, 'grupoId': grupoId]">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${materialGrupoInstance}"
						id="${materialGrupoInstance.id}" params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId, 'grupoId': grupoId]">
						<g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>	
		</div>
	</body>
</html>
