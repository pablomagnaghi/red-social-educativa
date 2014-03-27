<g:if test="${!dictaCuatrimestre}">
	<h3>Este curso se dicta durante solo durante el cuatrimestre ${com.cursado.Curso.get(params.cursoId).cuatDict}</h3>
</g:if>	
<g:else>
	<g:if test="${!cuatrimestre?.id}">
	
		<div class="box-content alerts">
			<div class="alert alert-error">
				<button class="close" data-dismiss="alert" type="button"></button>
					Todavia no ha sido creado el cuatrimestre actual. Revisar <strong>consolidar cuatrimestre</strong> 								
			</div>
		</div>    
	</g:if>	
	<g:else>
		<h3>Cuatrimestre ${cuatrimestre}</h3>
	</g:else>
</g:else>	