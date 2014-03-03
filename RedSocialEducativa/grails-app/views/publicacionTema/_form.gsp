<g:if test="${params.pubInicialId}">
	<g:hiddenField name="titulo" value="${com.fiuba.PublicacionCurso.get(params.pubInicialId).titulo}"/>
</g:if>
<g:else>
	<div class="control-group">
	<label class="control-label" >Titulo</label>			
	<div class="controls">
		<g:textField name="titulo" value="${publicacionTemaInstance?.titulo}"/>
	</div>	
</div>		
</g:else>

<div class="control-group">
	<label class="control-label" >Contenido</label>			
	<div class="controls">
		<g:textField name="contenido" value="${publicacionTemaInstance?.contenido}"/>
	</div>	
</div>		

<div><g:hiddenField name="responsable" value="${usuario}"/></div>
<div><g:hiddenField name="dni" value="${usuario.username}"/></div>
<div><g:hiddenField name="foro.id" value="${com.fiuba.ForoTema.findByTema(com.fiuba.Tema.get(params.temaId)).id}"/></div>

