<div>
	<g:if test="${!dictaCuatrimestre}">
		<h3>Este curso se dicta durante solo durante el cuatrimestre ${com.fiuba.Curso.get(params.cursoId).cuatDict}</h3>
	</g:if>	
	<g:else>
		<g:if test="${!cuatrimestre?.id}">
			<h3>Las opciones del cuatrimestre de cursada se visualizaran en breve</h3>
		</g:if>	
		<g:else>
			<h3>Cuatrimestre ${cuatrimestre}</h3>
	</g:else>
	</g:else>
</div>