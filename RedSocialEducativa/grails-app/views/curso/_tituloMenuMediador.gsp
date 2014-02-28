<g:if test="${!dictaCuatrimestre}">
	<h3>Este curso se dicta durante solo durante el cuatrimestre ${com.fiuba.Curso.get(params.cursoId).cuatDict}</h3>
</g:if>	
<g:else>
	<g:if test="${!cuatrimestre?.id}">
		<h3>Todavia no ha sido creado el cuatrimestre actual. Revisar Consolidar cuatrimestre (poner esto con alerta)</h3>
	</g:if>	
	<g:else>
		<h3>Cuatrimestre ${cuatrimestre}</h3>
	</g:else>
</g:else>	