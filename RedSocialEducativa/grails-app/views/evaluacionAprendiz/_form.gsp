<g:if test="${flash.message}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>${flash.message}</strong></div>
	</div>
</g:if>
<g:hasErrors bean="${evaluacionAprendizInstance}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>Revise el formulario</strong></div>										
	</div>
</g:hasErrors>

<g:set var="aprendices" value="${varAprendizService.obtenerTodosAprendicesDeCurso(params.cursoId.toLong())}"/>
 
<div class="control-group">
	<label class="control-label" >Aprendiz</label>			
	<div class="controls">
		<g:select id="aprendiz" name="aprendiz.id" from="${aprendices}" optionKey="id" required="" 
			value="${evaluacionAprendizInstance?.aprendiz?.id}" class="many-to-one"/>
	</div>	
</div>

