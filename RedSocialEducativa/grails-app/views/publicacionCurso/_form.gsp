<g:if test="${params.pubInicialId}">
	<g:hiddenField name="titulo" value="${com.fiuba.PublicacionCurso.get(params.pubInicialId).titulo}"/>
</g:if>
<g:else>
	<div class="control-group">
	<label class="control-label" >Titulo</label>			
	<div class="controls">
		<g:textField name="titulo" value="${publicacionCursoInstance?.titulo}"/>
	</div>	
</div>		
</g:else>

<div class="control-group">
	<label class="control-label" >Contenido</label>			
	<div class="controls">
		<g:textField name="contenido" value="${publicacionCursoInstance?.contenido}"/>
	</div>	
</div>		

<div><g:hiddenField name="responsable" value="${usuario}"/></div>
<div><g:hiddenField name="dni" value="${usuario.username}"/></div>
<div><g:hiddenField name="foro.id" value="${com.fiuba.ForoCurso.findByCuatrimestre(com.fiuba.Cuatrimestre.get(params.cuatrimestreId)).id}"/></div>
