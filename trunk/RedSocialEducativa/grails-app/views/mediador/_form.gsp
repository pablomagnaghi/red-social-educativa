<g:if test="${flash.message}">
	<div class="box-content alerts">
		<div class="alert alert-error">
			<button class="close" data-dismiss="alert" type="button"></button>
			<strong></strong> 
			${flash.message}
		</div>
	</div>    
</g:if>

<g:hasErrors bean="${mediadorInstance}">
	<div class="box-content alerts">
		<div class="alert alert-error">
			<button class="close" data-dismiss="alert" type="button"></button>
			<strong>Revise el formulario</strong> 
		</div>
	</div>   
</g:hasErrors>

<!-- CURSO -->
<g:if test="${!params.cursoId}">
	<div class="control-group">
		<label class="control-label" >Curso</label>			
		<div class="controls">
			<g:select id="curso" name="curso.id" from="${varCursoService.obtenerCursosOrdenados()}" optionKey="id" class="many-to-one"/>
		</div>	
	</div>		
</g:if>
<g:else>
	<g:hiddenField name="curso.id" value="${params.cursoId}"/>
</g:else>
<!-- JERARQUIA -->
<div class="control-group">
	<label class="control-label" >Jerarquia</label>			
	<div class="controls">
		<g:select name="jerarquia" from="${['1-Profesor', '2-JTP', '3-AP', '4-AS', '5-Colaborador']}" />
	</div>	
</div>	
<!-- USUARIO -->
<div class="control-group">
	<label class="control-label" >Usuario</label>			
	<div class="controls">
		<g:select id="usuario" name="usuario.id" from="${varUsuarioService.obtenerCandidatos()}" optionKey="id" class="many-to-one"/>
	</div>	
</div>		
