<div class="box-header" data-original-title="">
	<h2><i class="icon-table"></i><span class="break"></span>Material ${materialCursoInstance}</h2>
	<div class="box-icon">
		<g:if test="${mediador}"> 
			<g:link action="descargar" id="${materialCursoInstance.idArchivo}" params="['cursoId': params.cursoId]"><i class="icon-download-alt"></i></g:link>
			<g:link action="create" params="['cursoId': params.cursoId]"><i class="icon-plus"></i></g:link>
			<g:link action="edit" id="${materialCursoInstance.id}" params="['cursoId': params.cursoId]"><i class="icon-edit"></i></g:link>
			<g:link action="delete" method="DELETE" id="${materialCursoInstance.id}" params="['cursoId': params.cursoId]"
				onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><i class="icon-trash "></i></g:link>
			</g:if>
		<g:link action="index" params="['cursoId': params.cursoId]"><i class="icon-table"></i></g:link>	
	</div>
</div>
<div class="box-content">
	<dl>
		<dt>Titulo: ${fieldValue(bean: materialCursoInstance, field: "titulo")}</dt>
		<dt>Categoria: ${fieldValue(bean: materialCursoInstance, field: "categoria")}</dt>
		<dt>Responsable: ${fieldValue(bean: materialCursoInstance, field: "responsable")}</dt>
		<dt>Fecha: ${fieldValue(bean: materialCursoInstance, field: "fecha")}</dt>
		<dt>Autor:
			<g:if test="${fieldValue(bean: materialCursoInstance, field: "autor")}">${fieldValue(bean: materialCursoInstance, field: "autor")}</g:if>
			<g:else>${com.fiuba.Utilidades.AUTOR_ANONIMO}</g:else>
		</dt>
		<dt>Descripcion: </dt>
			<dd><div class="message" style="width:100%">${fieldValue(bean: materialCursoInstance, field: "descripcion")}</div></dd> 	
	</dl>
</div>
