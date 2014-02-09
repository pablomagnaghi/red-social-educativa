<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materialGrupo.label', default: 'MaterialGrupo')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-materialGrupo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link controller="grupoCurso" action="general" params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]">
					<g:message code="Lista de grupos del curso ${com.fiuba.Curso.get(cursoId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="grupoCurso" action="mostrar" id="${grupoId}" 
					params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId, 'grupoId': grupoId]">
					<g:message code="Grupo: ${com.fiuba.GrupoCurso.get(grupoId)}" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-materialGrupo" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${materialGrupoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${materialGrupoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'grupoId': params.grupoId]" >
				<fieldset class="form">
					<g:render template="form"/>
						<div> <g:hiddenField name="responsable" value="${aprendiz}-${aprendiz?.usuario?.padron}"/></div>
						<div class="fieldcontain ${hasErrors(bean: materialGrupoInstance, field: 'titulo', 'error')} ">
							<label for="titulo">
								<g:message code="materialGrupo.titulo.label" default="Titulo" />
							</label>
							<g:textField name="titulo" value="${materialGrupoInstance?.titulo}"/>
						</div>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
