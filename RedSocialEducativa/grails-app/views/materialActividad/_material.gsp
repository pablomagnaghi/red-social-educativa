<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Material ${materialActividadInstance}</h2>
            <div class="box-icon">
            	<g:link action="create" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
            		<i class="icon-plus"></i></g:link>
           		<g:link action="edit" id="${materialActividadInstance.id}" 
           			params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]"><i class="icon-edit"></i></g:link>
            	<g:link action="delete" method="DELETE" id="${materialActividadInstance.id}" 
	            	params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]"
		        	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><i class="icon-trash "></i></g:link>
            </div>
        </div>
        <div class="box-content">
			<dl>
            	<dt>Titulo: ${fieldValue(bean: materialActividadInstance, field: "titulo")}</dt>
                <dt>Categoria: ${fieldValue(bean: materialActividadInstance, field: "categoria")}</dt>
                <dt>Responsable: ${fieldValue(bean: materialActividadInstance, field: "responsable")}</dt>
                <dt>Fecha: ${fieldValue(bean: materialActividadInstance, field: "fecha")}</dt>
                <dt>Autor: ${fieldValue(bean: materialActividadInstance, field: "autor")}</dt>
                <dt>Descripcion: </dt>
                	<dd>${fieldValue(bean: materialActividadInstance, field: "descripcion")}</dd>        	
			</dl>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->
