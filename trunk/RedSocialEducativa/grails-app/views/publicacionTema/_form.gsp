<%@ page import="com.fiuba.PublicacionTema" %>

<div>
	<g:hiddenField name="responsable" value="${usuario}"/>
</div>

<div>
	<g:hiddenField name="dni" value="${usuario.username}"/>
</div>

<div>
	<g:hiddenField name="fecha" value="${(new Date()).format("yyyy-mm-dd")}"/>
</div>

<div>
	<g:hiddenField name="hora" value="${(new Date()).getTimeString()}"/>
</div>

<div>
	<g:hiddenField name="foro.id" value="${com.fiuba.ForoTema.findByTema(com.fiuba.Tema.get(temaId)).id}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: publicacionTemaInstance, field: 'contenido', 'error')} ">
	<label for="contenido">
		<g:message code="publicacionTema.contenido.label" default="Contenido" />
		
	</label>
	<g:textField name="contenido" value="${publicacionTemaInstance?.contenido}"/>
</div>

<g:if test="${pubInicialId}">
	<g:hiddenField name="titulo" value="${com.fiuba.PublicacionTema.get(pubInicialId).titulo}"/>
</g:if>
<g:else>
	<div class="fieldcontain ${hasErrors(bean: publicacionTemaInstance, field: 'titulo', 'error')} ">
		<label for="titulo">
			<g:message code="publicacionTema.titulo.label" default="Titulo" />
			
		</label>
		<g:textField name="titulo" value="${publicacionTemaInstance?.titulo}"/>
	</div>
</g:else>