<%@ page import="com.fiuba.Calendario" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'calendario.label', default: 'Calendario')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-calendario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="general" controller="administrador">
						<g:message code="Volver a tareas administrativas" args="[entityName]" /></g:link></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-calendario" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${calendarioInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${calendarioInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="update" method="PUT" >
				<g:hiddenField name="version" value="${calendarioInstance?.version}" />
				<fieldset class="form">
					<div><g:hiddenField name="anio" value="${calendarioInstance.anio}"/></div>
					<div class="fieldcontain ${hasErrors(bean: calendarioInstance, field: 'inicioPrimerCuatrimestre', 'error')} required">
						<label for="inicioPrimerCuatrimestre">
							<g:message code="calendario.inicioPrimerCuatrimestre.label" default="Primer Cuatrimestre" />
							<span class="required-indicator">*</span>
						</label>
						<g:datePicker name="fechaPrimerCuatrimestre" precision="day"  value="${fechaPrimerCuatrimestreDate}" />
					</div>
					
					<div class="fieldcontain ${hasErrors(bean: calendarioInstance, field: 'inicioSegundoCuatrimestre', 'error')} required">
						<label for="inicioSegundoCuatrimestre">
							<g:message code="calendario.inicioSegundoCuatrimestre.label" default="Segundo Cuatrimestre" />
							<span class="required-indicator">*</span>
						</label>
						<g:datePicker name="fechaSegundoCuatrimestre" precision="day"  value="${fechaSegundoCuatrimestreDate}"  />
						
					</div>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
