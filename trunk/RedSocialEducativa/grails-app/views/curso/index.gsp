
<%@ page import="com.fiuba.Curso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'curso.label', default: 'Curso')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-curso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-curso" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="asignatura" title="${message(code: 'curso.nroRelativo.label', default: 'Asignatura')}" />
						<g:sortableColumn property="nroRelativo" title="${message(code: 'curso.nroRelativo.label', default: 'Nro Relativo')}" />
						<g:sortableColumn property="nombre" title="${message(code: 'curso.nombre.label', default: 'Nombre')}" />
						<g:sortableColumn property="cuatDict" title="${message(code: 'curso.nombre.label', default: 'Cuat Dict')}" />
						<td> Mediadores </td>
						<td> Cuatrimestres </td>
						<td> Opciones </td>	
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${cursoInstanceList}" status="i" var="cursoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: cursoInstance, field: "asignatura")}</td>					
						<td>${fieldValue(bean: cursoInstance, field: "nroRelativo")}</td>						
						<td>${fieldValue(bean: cursoInstance, field: "nombre")}</td>
						<td>${fieldValue(bean: cursoInstance, field: "cuatDict")}</td>
						<td>
							<g:each in="${cursoInstance.mediadores}" var="m">
								<p>${m.usuario}</p>
							</g:each>
						</td>
						<td>
							<g:each in="${cursoInstance.cuatrimestres}" var="c">
								<p>${c}</p>
							</g:each>
						</td>
						<td>
							<g:form action="delete" method="DELETE" id="${cursoInstance.id}" >
							<fieldset class="buttons">
								<g:link class="edit" action="edit" resource="${cursoInstance}" id="${cursoInstance.id}">
									<g:message code="default.button.edit.label" default="Edit" /></g:link>
								<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
								onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
							</fieldset>
							</g:form>					
						</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${cursoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
