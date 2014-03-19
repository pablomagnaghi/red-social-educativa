<div class="box-header" data-original-title="">
	<h2><i class="icon-table"></i><span class="break"></span>Encuesta ${encuestaInstance}</h2>
	<div class="box-icon">
		<g:if test="${mediador}"> 
			<g:link action="create" params="['cursoId': params.cursoId]"><i class="icon-plus"></i></g:link>
			<g:link action="edit" id="${encuestaInstance.id}" params="['cursoId': params.cursoId]"><i class="icon-edit"></i></g:link>
			<g:link action="delete" method="DELETE" id="${encuestaInstance.id}" params="['cursoId': params.cursoId]"
				onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><i class="icon-trash "></i></g:link>
			</g:if>
		<g:link action="index" params="['cursoId': params.cursoId]"><i class="icon-table"></i></g:link>	
	</div>
</div>
<div class="box-content">
	<dl>
		<dt>Titulo: ${fieldValue(bean: encuestaInstance, field: "nombre")}</dt>
		<dt>Preguntas: </dt>
	</dl>
</div>
