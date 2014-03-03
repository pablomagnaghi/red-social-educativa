<div class="box span6">
	<div class="box-header">
		<h2><i class="icon-font"></i><span class="break"></span>Materiales</h2>
		<div class="box-icon">
			<a href="#" class="btn-minimize"><i class="icon-chevron-up"></i></a>
		</div>
	</div>
	<div class="box-content">        
		<dl>
			<g:each in="${materiales}">
				<g:if test="${it.categoria.nombre == "RefBibliografica" || it.categoria.nombre == "Enlace" || it.categoria.nombre == "Glosario" || mediador || aprendiz}">
					<dt>${it.titulo} poner aca al lado link de descarga</dt>
						<dd>Categoria: ${it.categoria}</dd>
						<dd>${it.responsable} - ${it.fecha}</dd> 
				</g:if>
			</g:each>
		</dl>
	</div>
</div><!--/span-->
