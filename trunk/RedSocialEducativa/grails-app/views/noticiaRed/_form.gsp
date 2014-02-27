<%@ page import="com.fiuba.NoticiaRed" %>
							
<div class="control-group">
	<label class="control-label" >Texto</label>			
	<div class="controls">
		<g:textArea name="texto" value="${noticiaRedInstance?.texto}" />
	</div>	
</div>			

<div><g:hiddenField name="administrador.id" value="${administrador.id}"/></div>

