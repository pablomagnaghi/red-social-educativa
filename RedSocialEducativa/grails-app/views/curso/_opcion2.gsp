<div class="box span6">
	<div class="box-header" data-original-title="">
		<h2><i class="icon-list"></i><span class="break"></span>Material del curso</h2>
		<div class="box-icon">
			<a href="#" class="btn-minimize"><i class="icon-chevron-up"></i></a>
		</div>
	</div>
	<div class="box-content">
		<!--<table class="table table-striped table-bordered bootstrap-datatable datatable">-->
		<!--<table class="table table-bordered table-striped">-->
		<table class="table table-condensed">
			<thead>
 				<tr>
					<th>Titulo</th>
					<th>Categoria</th>
					<th>Responsable</th>
					<th>Acciones</th>            
				</tr>
			</thead>
			<tbody>
				<g:each in="${materiales}">
					<tr>
						<td>${it.titulo}</td>
						<td class="center">${it.categoria}</td>
						<td class="center">${it.responsable}</td>
						<td class="center">
							<g:if test="${it.categoria.nombre == "RefBibliografica" || it.categoria.nombre == "Enlace" || it.categoria.nombre == "Glosario" || mediador || aprendiz}">
								<p>Poner link de descarga</p>
							</g:if>	
						</td>
					</tr>
				</g:each>   
            </tbody>
		</table>
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
		<table class="table table-condensed">
			<g:each in="${temas}" var="temaInstance">
			
				<thead><tr>
						<th colspan="4"><h3>${temaInstance.titulo}  
							<g:link style="float: right;" controller="foroTema" action="general" 
							params="['cursoId': params.cursoId, 'temaId': temaInstance.id]">Foro del tema</g:link></h3></th>    
						</tr>
				</thead>  
				<g:if test="${mediador || aprendiz}">
					<g:if test="${temaInstance.materiales}">
						<thead><tr><th colspan="4"><dd>Materiales del tema ${com.fiuba.Tema.get(temaInstance.id)}</dd></th></tr></thead>
						<tbody>
							<g:each in="${temaInstance.materiales}" var="materialInstance">
								<tr>
									<td><dd>&nbsp;&nbsp;&nbsp;&nbsp;${materialInstance.titulo}</dd></td>
									<td class="center">${materialInstance.categoria}</td>
									<td class="center">${materialInstance.responsable}</td>
									<td class="center">Poner link de descarga</td>
								</tr>
							</g:each>   
						</tbody>  
					</g:if>
					<g:else>
						<thead><tr><th colspan="4"><dd>No posee materiales de temas</dd></th></tr></thead>
					</g:else>
					<g:if test="${temaInstance.contenidos}">	    
					 	<g:each in="${temaInstance.contenidos}" var="contenidoInstance">
					 		<thead><tr><th colspan="4"><dd>Contenido ${com.fiuba.Contenido.get(contenidoInstance.id)}</dd></th></tr></thead>
								<g:if test="${contenidoInstance.materiales}">						
									<tbody>
										<g:each in="${contenidoInstance.materiales}" var="materialInstance">
											<tr>
												<td><dd>&nbsp;&nbsp;&nbsp;&nbsp;${materialInstance.titulo}</dd></td>
												<td class="center">${materialInstance.categoria}</td>
												<td class="center">${materialInstance.responsable}</td>
												<td class="center">Poner link de descarga</td>
											</tr>  
										</g:each>
									</tbody> 	
								</g:if> 
						</g:each>      		
					</g:if>	
					<g:else>
						<thead><tr><th colspan="4"><dd>No posee contenidos</dd></th></tr></thead>
					</g:else>
				</g:if>	
			</g:each>	
		</table>		 
	</div>
</div><!--/span-->	
