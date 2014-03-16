<div class="box-header" data-original-title="">
	<h2><i class="icon-table"></i><span class="break"></span>Material ${materialActividadInstance}</h2>
	<div class="box-icon">
		<g:if test="${mediador}"> 
			<g:link action="create" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
					<i class="icon-plus"></i></g:link>
			<g:link action="edit" id="${materialActividadInstance.id}" 
				params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]"><i class="icon-edit"></i></g:link>
			<g:link action="delete" method="DELETE" id="${materialActividadInstance.id}" 
				params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]"
				onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><i class="icon-trash "></i></g:link>
			</g:if>
		<g:link action="descargar" id="${materialActividadInstance?.archivo?.id}" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
			<i class="icon-download-alt"></i></g:link>		
		<g:link controller="actividad" action="index" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"><i class="icon-table"></i></g:link>	
	</div>
</div>
<div class="box-content">
	<dl>
		<dt>Titulo: ${fieldValue(bean: materialActividadInstance, field: "titulo")}</dt>
		<dt>Categoria: ${fieldValue(bean: materialActividadInstance, field: "categoria")}</dt>
		<dt>Responsable: ${fieldValue(bean: materialActividadInstance, field: "responsable")}</dt>
		<dt>Fecha: ${fieldValue(bean: materialActividadInstance, field: "fecha")}</dt>
		<dt>Autor:
			<g:if test="${fieldValue(bean: materialActividadInstance, field: "autor")}">${fieldValue(bean: materialActividadInstance, field: "autor")}</g:if>
			<g:else>${com.fiuba.Utilidades.AUTOR_ANONIMO}</g:else>
		</dt>
		<dt>Descripcion: </dt>
			<dd><div class="message" style="width:100%">${fieldValue(bean: materialActividadInstance, field: "descripcion")}</div></dd> 	
	</dl>
</div>
