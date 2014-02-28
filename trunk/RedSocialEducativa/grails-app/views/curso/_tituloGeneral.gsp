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