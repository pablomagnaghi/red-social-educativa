<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'calendario.label', default: 'Calendario')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-calendario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="general" controller="administrador">
					<g:message code="Volver a tareas administrativas" args="[entityName]" /></g:link></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-calendario" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ul class="errors" role="alert">
				<g:eachError bean="${calendarioInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			<g:form url="[resource:calendarioInstance, action:'save']" >
				<fieldset class="form">
					<div class="fieldcontain">
						<label for="inicioPrimerCuatrimestre">
							<g:message code="calendario.inicioPrimerCuatrimestre.label" default="Primer Cuatrimestre" />
							<span class="required-indicator">*</span>
						</label>
						<g:datePicker name="fechaPrimerCuatrimestre" precision="day"  value="${new Date()}" />
					</div>
					
					<div class="fieldcontain">
						<label for="inicioSegundoCuatrimestre">
							<g:message code="calendario.inicioSegundoCuatrimestre.label" default="Segundo Cuatrimestre" />
							<span class="required-indicator">*</span>
						</label>
						<g:datePicker name="fechaSegundoCuatrimestre" precision="day"  value="${new Date()}" />
						
					</div>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
