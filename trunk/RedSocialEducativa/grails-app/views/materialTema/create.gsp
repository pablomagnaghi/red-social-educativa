<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materialTema.label', default: 'MaterialTema')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-materialTema" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link controller="tema" action="index" id="${temaId}" params="['cursoId': cursoId]">
					<g:message code="Lista de temas del curso ${com.fiuba.Curso.get(cursoId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="tema" action="show" id="${temaId}" params="['cursoId': cursoId, 'temaId': temaId]">
					<g:message code="Tema: ${com.fiuba.Tema.get(temaId)}" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-materialTema" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${materialTemaInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${materialTemaInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" params="['cursoId': params.cursoId, 'temaId': params.temaId]" >
				<fieldset class="form">
					<g:render template="form"/>
						<div class="fieldcontain ${hasErrors(bean: materialTemaInstance, field: 'titulo', 'error')} ">
							<label for="titulo">
								<g:message code="materialTema.titulo.label" default="Titulo" />
							</label>
							<g:textField name="titulo" value="${materialTemaInstance?.titulo}"/>
						</div>
						<div> <g:hiddenField name="responsable" value="${mediador}-${mediador?.jerarquia}"/></div>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
