<div class="box span6">
	<div class="box-header" data-original-title="">
		<h2><i class="icon-list"></i><span class="break"></span>Material del curso</h2>
	</div>
	<div class="box-content">
		<ol>
			<g:each in="${materiales}">
				<g:if test="${it.categoria.nombre == "RefBibliografica" || it.categoria.nombre == "Enlace" || it.categoria.nombre == "Glosario" || mediador || aprendiz}">
					<li><h4>${it.titulo} poner aca al lado link de descarga</h4></dt>
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
	</div>
	<div class="box-content">
		<dl>
			<dt>Description lists</dt>
			<dd>A description list is perfect for defining terms.</dd>
			<dt>Euismod</dt>
			<dd>Vestibulum id ligula porta felis euismod semper eget lacinia odio sem nec elit.</dd>
			<dd>Donec id elit non mi porta gravida at eget metus.</dd>
			<dt>Malesuada porta</dt>
			<dd>Etiam porta sem malesuada magna mollis euismod.</dd>
		</dl>            
	</div>
</div><!--/span-->	
