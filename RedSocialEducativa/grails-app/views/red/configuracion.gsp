<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'red.label', default: 'Red')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-red" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="principal">
					<g:message code="Pagina inicial" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="general" controller="administrador">
					<g:message code="Volver" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-red" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${redInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${redInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form controller="red" action="actualizarConfiguracion" >
				<g:hiddenField name="version" value="${redInstance?.version}" />
				<div class="fieldcontain ${hasErrors(bean: redInstance, field: 'cicloConservacion', 'error')} required">
					<label for="cicloConservacion">
					<g:message code="red.cicloConservacion.label" default="Ciclo Conservacion" />
						<span class="required-indicator">*</span>
					</label>
					<g:field name="cicloConservacion" type="number" value="${redInstance.cicloConservacion}" required=""/>
				</div>
				<fieldset class="buttons">
					<g:submitButton name="actualizar" class ="save" value="Actualizar"/>	
				</fieldset>
				
			</g:form>
		</div>
	</body>
</html>




















