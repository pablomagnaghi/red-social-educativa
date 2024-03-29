<div class="box-header" data-original-title="">
	<h2><i class="icon-table"></i><span class="break"></span>Material ${materialGrupoActividadInstance}</h2>
	<div class="box-icon">
		<g:if test="${aprendiz}">
			<g:link action="create" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, 
	            'grupoActividadId': params.grupoActividadId]"><i class="icon-plus"></i></g:link>
	        <g:link action="edit" id="${materialGrupoActividadInstance.id}" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
	           	'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]"><i class="icon-edit "></i></g:link>
	        <g:link action="delete" method="DELETE" id="${materialGrupoActividadInstance.id}" 
				params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, 
				'grupoActividadId': params.grupoActividadId]"
			    onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><i class="icon-trash "></i></g:link>
		</g:if>
		<g:link action="descargar" id="${materialGrupoActividadInstance?.archivo?.id}" 
			params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, 
			'grupoActividadId': params.grupoActividadId]"><i class="icon-download-alt"></i></g:link>		
		<g:if test="${aprendiz}">
			<g:link controller="grupoActividad" action="grupoAprendiz" id="${params.grupoActividadId}"
				params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
				<i class="icon-group"></i></g:link>	   
		</g:if>
		<g:else>
			<g:link controller="grupoActividad" action="gruposMediador" 
				params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
				<i class="icon-group"></i></g:link>	   
		</g:else>	
	</div>
</div>
<div class="box-content">
	<dl>
		<dt>Titulo: ${fieldValue(bean: materialGrupoActividadInstance, field: "titulo")}</dt>
		<dt>Categoria: ${fieldValue(bean: materialGrupoActividadInstance, field: "categoria")}</dt>
		<dt>Responsable: ${fieldValue(bean: materialGrupoActividadInstance, field: "responsable")}</dt>
		<dt>Fecha: ${fieldValue(bean: materialGrupoActividadInstance, field: "fecha")}</dt>
		<dt>Autor:
			<g:if test="${fieldValue(bean: materialGrupoActividadInstance, field: "autor")}">${fieldValue(bean: materialGrupoActividadInstance, field: "autor")}</g:if>
			<g:else>${com.fiuba.Utilidades.AUTOR_ANONIMO}</g:else>
		</dt>
		<dt>Descripcion: </dt>
			<dd><div class="message" style="width:100%">${fieldValue(bean: materialGrupoActividadInstance, field: "descripcion")}</div></dd> 	
	</dl>
</div>

