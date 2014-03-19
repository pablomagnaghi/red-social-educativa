<div class="box-header" data-original-title="">
	<h2><i class="icon-table"></i><span class="break"></span>Pregunta ${preguntaChoiceInstance}</h2>
	<div class="box-icon">
		<g:if test="${mediador}"> 
			<g:link action="create" params="['cursoId': params.cursoId, 'encuestaId': params.encuestaId]"><i class="icon-plus"></i></g:link>
			<g:link action="edit" id="${preguntaChoiceInstance.id}" params="['cursoId': params.cursoId, 'encuestaId': params.encuestaId]"><i class="icon-edit"></i></g:link>
			<g:link action="delete" method="DELETE" id="${preguntaChoiceInstance.id}" params="['cursoId': params.cursoId, 'encuestaId': params.encuestaId]"
				onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><i class="icon-trash "></i></g:link>
			</g:if>
		<g:link controller="tema" action="index" params="['cursoId': params.cursoId]"><i class="icon-table"></i></g:link>	
	</div>
</div>
<div class="box-content">
	<dl>
		<dt>Pregunta: ${fieldValue(bean: preguntaChoiceInstance, field: "pregunta")}</dt>
	</dl>
</div>
