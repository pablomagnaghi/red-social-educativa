<div class="box-header" data-original-title="">
	<h2><i class="icon-table"></i><span class="break"></span>Curso ${cursoInstance}</h2>
    <div class="box-icon">
		<g:link action="create"><i class="icon-plus"></i></g:link>
		<g:link action="edit" id="${cursoInstance.id}"><i class="icon-edit"></i></g:link>
 		<g:link action="delete" method="DELETE" id="${cursoInstance.id}" 
			onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><i class="icon-trash "></i></g:link>  
		<g:link action="index"><i class="icon-table"></i></g:link>	  	
	</div>
</div>
<div class="box-content">
	<dl>
		<dt>Asignatura: ${fieldValue(bean: cursoInstance, field: "asignatura")}</dt>
     	<dt>Numero relativo: ${fieldValue(bean: cursoInstance, field: "nroRelativo")}</dt>
	 	<dt>Nombre: ${fieldValue(bean: cursoInstance, field: "nombre")}</dt>
		<dt>Cuatrimestres en los que se dicta: ${fieldValue(bean: cursoInstance, field: "cuatDict")}</dt>
		<dt>Mediadores:</dt>
			<g:each in="${cursoInstance.mediadores}" var="m">
				<dd>${m}</dd>
			</g:each>	
		<dt>Cuatrimestres dictados:</dt>
			<g:each in="${cursoInstance.cuatrimestres}" var="c">
				<dd>${c}</dd>
			</g:each>		
	</dl>
</div>
