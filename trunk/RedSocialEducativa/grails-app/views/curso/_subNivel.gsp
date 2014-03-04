<div class="box span6">
	<div class="box-header" data-original-title="">
		<h2><i class="icon-list"></i><span class="break"></span>Material del curso</h2>
		<div class="box-icon">
			<a href="#" class="btn-minimize"><i class="icon-chevron-up"></i></a>
		</div>
	</div>
	<div class="box-content">
		<ol>
			<g:each in="${materiales}">
				<g:if test="${it.categoria.nombre == "RefBibliografica" || it.categoria.nombre == "Enlace" || it.categoria.nombre == "Glosario" || mediador || aprendiz}">
					<li>${it.titulo} poner aca al lado link de descarga</li>
						<ul>
							<li>Categoria: ${it.categoria}</li>
							<li>${it.responsable} - ${it.fecha}</li> 
						</ul>
				</g:if>
			</g:each>
		</ol>        
	</div>
</div><!--/span-->
						
<div class="box span6">
	<div class="box-header">
		<h2><i class="icon-list"></i>Temas del curso</h2>
		<div class="box-icon">
			<a href="#" class="btn-minimize"><i class="icon-chevron-up"></i></a>
		</div>
	</div>
	<div class="box-content">
		<ol>
			<g:each in="${temas}" var="temaInstance">
				<li>TEMA: ${temaInstance.titulo} ACCESO AL FORO</li>
				<ul>
					<li><g:link controller="foroTema" action="general" params="['cursoId': params.cursoId, 'temaId': temaInstance.id]">Foro del tema</g:link></li>
								
					<g:if test="${mediador || aprendiz}">
						<g:if test="${temaInstance.materiales}">
							<li>Materiales del tema ${com.fiuba.Tema.get(params.temaId)} del Curso: ${com.fiuba.Curso.get(cursoId)}</li>
								<ol>
									<g:each in="${temaInstance.materiales}" var="materialInstance">
										<li>${materialInstance.titulo} Poner link al material(URL)</li>	
										<ul>
											<li>Categoria: ${materialInstance.categoria}</li>	
											<li>Responsable: ${materialInstance.responsable}</li>
										</ul>	
									</g:each>
								</ol>
						</g:if>
						<g:else>
							<li>No hay materiales</li>
						</g:else>		
						<g:if test="${temaInstance.contenidos}">
							<li>Contenidos del tema ${com.fiuba.Tema.get(params.temaId)} del Curso: ${com.fiuba.Curso.get(cursoId)}</li>
								<ol>	
									<g:each in="${temaInstance.contenidos}" var="contenidoInstance">
										<g:each in="${contenidoInstance.materiales}" var="material">
											<li>${material.titulo}-Poner link al material(URL)</li>
											<ul>
												<li>Categoria": ${material.categoria}</li>	
												<li>Responsable: ${material.responsable}</li>
											</ul>	
										</g:each>
									</g:each>
								</ol>	
						</g:if>	
						<g:else>
							<li>No hay contenidos</li>
						</g:else>
					</g:if>		
				</ul>
			</g:each>
		</ol>         
	</div>
</div><!--/span-->	
