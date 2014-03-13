<div class="box-header" data-original-title="">
	<h2><i class="icon-table"></i><span class="break"></span>Material ${materialTemaInstance}</h2>
	<div class="box-icon">
		<g:if test="${mediador}"> 
			<g:link action="create" params="['cursoId': params.cursoId, 'temaId': params.temaId]"><i class="icon-plus"></i></g:link>
			<g:link action="edit" id="${materialTemaInstance.id}" params="['cursoId': params.cursoId, 'temaId': params.temaId]"><i class="icon-edit"></i></g:link>
			<g:link action="delete" method="DELETE" id="${materialTemaInstance.id}" params="['cursoId': params.cursoId, 'temaId': params.temaId]"
				onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><i class="icon-trash "></i></g:link>
			</g:if>
		<g:link action="descargar" id="${materialCursoInstance.idArchivo}" params="['cursoId': params.cursoId, 'temaId': params.temaId]">
			<i class="icon-download-alt"></i></g:link>	
		<g:link controller="tema" action="index" params="['cursoId': params.cursoId]"><i class="icon-table"></i></g:link>	
	</div>
</div>
<div class="box-content">
	<dl>
		<dt>Titulo: ${fieldValue(bean: materialTemaInstance, field: "titulo")}</dt>
		<dt>Categoria: ${fieldValue(bean: materialTemaInstance, field: "categoria")}</dt>
		<dt>Responsable: ${fieldValue(bean: materialTemaInstance, field: "responsable")}</dt>
		<dt>Fecha: ${fieldValue(bean: materialTemaInstance, field: "fecha")}</dt>
		<dt>Autor:
			<g:if test="${fieldValue(bean: materialTemaInstance, field: "autor")}">${fieldValue(bean: materialTemaInstance, field: "autor")}</g:if>
			<g:else>${com.fiuba.Utilidades.AUTOR_ANONIMO}</g:else>
		</dt>
		<dt>Descripcion: </dt>
			<dd><div class="message" style="width:100%">${fieldValue(bean: materialTemaInstance, field: "descripcion")}</div></dd> 	
	</dl>
</div>
