<%@ page import="com.fiuba.Actividad" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'actividad.label', default: 'Actividad')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-actividad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index" params="['cursoId': params.cursoId]">
					<g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create" params="['cursoId': params.cursoId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-actividad" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${actividadInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${actividadInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="update" method="PUT" id="${actividadInstance.id}" params="['cursoId': params.cursoId]"  >
				<g:hiddenField name="version" value="${actividadInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
						<div>
							<g:hiddenField name="titulo" value="${actividadInstance.titulo}" />
						</div>
						<div
							class="fieldcontain ${hasErrors(bean: actividadInstance, field: 'materiales', 'error')} ">
							<label for="materiales"> <g:message
									code="actividad.materiales.label" default="Materiales" />
							</label>
							<ul class="one-to-many">
								<g:each in="${materialInstance?.materiales?}" var="m">
									<li><g:link controller="materialActividad" action="show" id="${m.id}" 
										params="['cursoId': cursoId, 'activdadId': actividadInstance.id]">${m?.encodeAsHTML()}</g:link></li>
								</g:each>
								<li class="add"><g:link controller="materialActividad" action="create"  
									params="['cursoId': cursoId, 'actividadId': actividadInstance?.id]">
									${message(code: 'default.add.label', args: [message(code: 'materialActividad.label', default: 'MaterialActividad')])}
									</g:link></li>
							</ul>
						</div>
			</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
