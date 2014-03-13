<div class="box-header" data-original-title="">
	<h2><i class="icon-table"></i><span class="break"></span>Material ${materialContenidoInstance}</h2>
	<div class="box-icon">
		<g:if test="${mediador}"> 
			<g:link action="create" params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]"><i class="icon-plus"></i></g:link>
			<g:link action="edit" id="${materialContenidoInstance.id}" params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]">
				<i class="icon-edit"></i></g:link>
			<g:link action="delete" method="DELETE" id="${materialContenidoInstance.id}" params="['cursoId': params.cursoId, 'temaId': params.temaId, 
				'contenidoId': params.contenidoId]"
				onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><i class="icon-trash "></i></g:link>
			</g:if>
		<g:link action="descargar" id="${materialContenidoInstance.idArchivo}" params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]">
			<i class="icon-download-alt"></i></g:link>		
		<g:link controller="contenido" action="index" params="['cursoId': params.cursoId, 'temaId': params.temaId]"><i class="icon-table"></i></g:link>	
	</div>
</div>
<div class="box-content">
	<dl>
		<dt>Titulo: ${fieldValue(bean: materialContenidoInstance, field: "titulo")}</dt>
		<dt>Categoria: ${fieldValue(bean: materialContenidoInstance, field: "categoria")}</dt>
		<dt>Responsable: ${fieldValue(bean: materialContenidoInstance, field: "responsable")}</dt>
		<dt>Fecha: ${fieldValue(bean: materialContenidoInstance, field: "fecha")}</dt>
		<dt>Autor:
			<g:if test="${fieldValue(bean: materialContenidoInstance, field: "autor")}">${fieldValue(bean: materialContenidoInstance, field: "autor")}</g:if>
			<g:else>${com.fiuba.Utilidades.AUTOR_ANONIMO}</g:else>
		</dt>
		<dt>Descripcion: </dt>
			<dd><div class="message" style="width:100%">${fieldValue(bean: materialContenidoInstance, field: "descripcion")}</div></dd> 	
	</dl>
</div>
