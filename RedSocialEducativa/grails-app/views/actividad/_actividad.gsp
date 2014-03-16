<div class="box-header" data-original-title="">
	<h2><i class="icon-table"></i><span class="break"></span>Actividad ${actividadInstance} del cuatrimestre ${com.fiuba.Cuatrimestre.get(params.cuatrimestreId)}</h2>
	<div class="box-icon">
		<g:if test="${mediador}">
			<g:link action="create" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
				<i class="icon-plus"></i></g:link>
			<g:link action="edit" id="${actividadInstance.id}" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
				<i class="icon-edit"></i></g:link>
			<g:link action="delete" method="DELETE" id="${actividadInstance.id}" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"
				onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><i class="icon-trash "></i></g:link>
		</g:if>
		<g:link action="index" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"><i class="icon-table"></i></g:link>		
	</div>
</div>
<div class="box-content">
	<dl>
		<dt>Titulo: ${fieldValue(bean: actividadInstance, field: "titulo")}</dt>
		<dt>Categoria: ${fieldValue(bean: actividadInstance, field: "categoria")}</dt>
		<dt>Fecha finalizacion: <g:formatNumber number="${actividadInstance.fechaFinalizacion}" />
		</dt>
		<dt>Grupal: 
			<g:if test="${actividadInstance.grupal}">Si</g:if>
			<g:else>No</g:else>
		</dt>
		<dt>Evaluable: 
			<g:if test="${actividadInstance.evaluable}">Si</g:if>
			<g:else>No</g:else>	
		</dt>
		<dt>Visibilidad: 
			<g:if test="${actividadInstance.visibilidad}">Si</g:if>
			<g:else>No</g:else>
		</dt>
		<dt>Objetivo:</dt>       	
			<dd><div class="message" style="width:100%"> ${fieldValue(bean: actividadInstance, field: "objetivo")}</div></dd>
		<dt>Grupos:</dt>
			<g:each in="${actividadInstance.grupos}" var="grupo">
				<dd>${grupo}
					<ul>
					<g:each in="${grupo.aprendices}">
						<dd>${it.aprendiz}</dd>
					</g:each>
					</ul>
				</dd>
				
			</g:each>
		<dt>Materiales:</dt>
           	<g:each in="${actividadInstance.materiales}"><dd>${it}</dd></g:each>
    	<dt>Temas asociados:</dt>
			<g:each in="${actividadInstance.temas.tema}"><dd>${it}</dd></g:each>	
	</dl>
</div>


