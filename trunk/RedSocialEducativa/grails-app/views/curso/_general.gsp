<div>
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>
</div>
<div>
	<g:if test="${!dictaCuatrimestre}">
		<p>Este curso se dicta durante solo durante el cuatrimestre ${com.fiuba.Curso.get(params.cursoId).cuatDict}</p>
	</g:if>	
	<g:else>
		<g:if test="${!cuatrimestre?.id}">
			<p>Las opciones del cuatrimestre de cursada se visualizara en breve</p>
		</g:if>	
	</g:else>
</div>
<div>
	<g:link action="materiales" params="['cursoId': params.cursoId]">
		<g:message code="Material del curso"/></g:link>
</div>
<div>
	<g:link action="temas" params="['cursoId': params.cursoId]">
		<g:message code="Temas del curso"/></g:link>
</div>


