<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Actividad ${actividadInstance}</h2>
            <div class="box-icon">
            	<g:link action="create" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
            		<i class="icon-plus"></i></g:link>
           		<g:link action="edit" id="${actividadInstance.id}" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
           			<i class="icon-edit"></i></g:link>
            	<g:link action="delete" method="DELETE" id="${actividadInstance.id}" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"
	        		onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><i class="icon-trash "></i></g:link>
            </div>
        </div>
        <div class="box-content">
			<dl>
            	<dt>Titulo: ${fieldValue(bean: actividadInstance, field: "titulo")}</dt>
                <dt>Categoria: ${fieldValue(bean: actividadInstance, field: "categoria")}</dt>
                <dt>Fecha finalizacion: ${fieldValue(bean: actividadInstance, field: "fechaFinalizacion")}</dt>
                <dt>Grupal: ${fieldValue(bean: actividadInstance, field: "grupal")}</dt>
                <dt>Evaluable: ${fieldValue(bean: actividadInstance, field: "evaluable")}</dt>
                <dt>Visibilidad: ${fieldValue(bean: actividadInstance, field: "visibilidad")}</dt>
                <dt>Objetivo: ${fieldValue(bean: actividadInstance, field: "objetivo")}</dt>        	
                <dt>Grupos:</dt>
                	<g:each in="${actividadInstance.grupos}">
                		<dd>${it}</dd>
                	</g:each>
				<dt>Materiales:</dt>
                	<g:each in="${actividadInstance.materiales}">
                		<dd>${it}</dd>
                	</g:each>
      			<dt>Temas asociados:</dt>
                	<g:each in="${actividadInstance.temas}">
                		<dd>${it}</dd>
                	</g:each>	
			</dl>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->


