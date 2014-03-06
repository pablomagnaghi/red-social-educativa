<div class="control-group">
	<label class="control-label" >Categoria</label>			
	<div class="controls">
		<g:select id="categoria" name="categoria.id" from="${com.fiuba.CategoriaActividad.list()}" optionKey="id" required="" 
			value="${actividadInstance?.categoria?.id}" class="many-to-one"/>
	</div>	
</div>
<div class="control-group">
	<label class="control-label" >Objetivo</label>			
	<div class="controls">
		<g:textField name="objetivo" value="${actividadInstance?.objetivo}"/>
	</div>	
</div>
<div class="control-group">
	<label class="control-label" >Fecha finalizacion</label>			
	<div class="controls">
		<g:datePicker name="fechaFinalizacionDate" precision="day"  value="${new Date()}"  />
	</div>	
</div>
<g:if test="${actividadInstance?.visibilidad}">
	<g:hiddenField name="visibilidad" value="${actividadInstance.visibilidad}"/>
</g:if>
<g:else>
	<div class="control-group">
		<label class="control-label" >Visibilidad</label>			
		<div class="controls">
			<g:checkBox name="visibilidad" value="${actividadInstance?.visibilidad}" />
		</div>	
	</div>
</g:else>



