<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materialContenido.label', default: 'MaterialContenido')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-materialContenido" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="mediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Pagina principal de mediador"/></g:link></li>			
				<li><g:link class="create" action="menuMediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Tareas mediador" /></g:link></li>		
				<li><g:link controller="tema" action="index" id="${params.temaId}" params="['cursoId': params.cursoId]">
					<g:message code="Lista de temas del curso ${com.fiuba.Curso.get(cursoId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="tema" action="edit" id="${params.temaId}" params="['cursoId': params.cursoId]">
					<g:message code="Tema: ${com.fiuba.Tema.get(params.temaId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="contenido" action="show" id="${params.contenidoId}" params="['cursoId': params.cursoId, 'temaId': params.temaId]">
					<g:message code="Contenido: ${com.fiuba.Contenido.get(params.contenidoId)}" args="[entityName]" /></g:link></li>		
				<li><g:link class="create" action="create" params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<h2>Curso: ${com.fiuba.Curso.get(params.cursoId)}</h2>
		<h2>Curso Id: ${params.cursoId}</h2>
		<h2>Tema: ${params.temaId}</h2>
		<h2>Contenido: ${params.contenidoId}</h2>
		<div id="create-materialContenido" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${materialContenidoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${materialContenidoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]" >
				<fieldset class="form">
					<div class="fieldcontain ${hasErrors(bean: materialContenidoInstance, field: 'titulo', 'error')} ">
						<label for="titulo">
							<g:message code="materialContenido.titulo.label" default="Titulo" />
						</label>
						<g:textField name="titulo" value="${materialContenidoInstance?.titulo}"/>
					</div>
					<g:render template="form"/>
					<div><g:hiddenField name="responsable" value="${mediador}-${mediador?.jerarquia}"/></div>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>