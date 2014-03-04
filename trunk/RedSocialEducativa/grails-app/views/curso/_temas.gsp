<div class="box span6">
	<div class="box-header">
		<h2><i class="icon-font"></i><span class="break">Temas</span></h2>
		<div class="box-icon">
			<a href="#" class="btn-minimize"><i class="icon-chevron-up"></i></a>
		</div>
	</div>
	<div class="box-content">        
		<div>
			<g:each in="${temas}" var="temaInstance">
				<dt>TEMA: ${temaInstance.titulo} ACCESO AL FORO</dt>
				<dd><g:link controller="foroTema" action="general" params="['cursoId': params.cursoId, 'temaId': temaInstance.id]">Foro del tema</g:link></dd>			
				<g:if test="${mediador || aprendiz}">
					<g:if test="${temaInstance.materiales}">
						<dd>Materiales del tema ${com.fiuba.Tema.get(params.temaId)} del Curso: ${com.fiuba.Curso.get(cursoId)}</dd>
							<g:each in="${temaInstance.materiales}" var="materialInstance">
								<dt>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${materialInstance.titulo} Poner link al material(URL)</dt>
									<dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Categoria: ${materialInstance.categoria}</dd>	
									<dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Responsable: ${materialInstance.responsable}</dd>
							</g:each>
					</g:if>
					<g:else>
						<dd>No hay materiales</dd>
					</g:else>
					<g:if test="${temaInstance.contenidos}">
						<dd>Contenidos del tema ${com.fiuba.Tema.get(params.temaId)} del Curso: ${com.fiuba.Curso.get(cursoId)}</dd>
							<g:each in="${temaInstance.contenidos}" var="contenidoInstance">
								<g:each in="${contenidoInstance.materiales}" var="material">
									<dt>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${material.titulo}-Poner link al material(URL)</dt>
										<dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Categoria": ${material.categoria}</dd>	
										<dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Responsable: ${material.responsable}</dd>
								</g:each>
							</g:each>
					</g:if>	
					<g:else>
						<dd>No hay contenidos</dd>
					</g:else>
				</g:if>		
			</g:each>
		</div>
	</div>
</div><!--/span-->
