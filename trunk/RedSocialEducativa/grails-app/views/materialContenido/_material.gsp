<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Material ${materialContenidoInstance}</h2>
            <div class="box-icon">
            	<g:link action="create" params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]"><i class="icon-plus"></i></g:link>
           		<g:link action="edit" id="${materialContenidoInstance.id}" params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]">
           			<i class="icon-edit"></i></g:link>
            	<g:link action="delete" method="DELETE" id="${materialContenidoInstance.id}" 
            		params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]"
	        		onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><i class="icon-trash "></i></g:link>
            </div>
        </div>
        <div class="box-content">
			<dl>
            	<dt>Titulo: ${fieldValue(bean: materialContenidoInstance, field: "titulo")}</dt>
                <dt>Categoria: ${fieldValue(bean: materialContenidoInstance, field: "categoria")}</dt>
                <dt>Responsable: ${fieldValue(bean: materialContenidoInstance, field: "responsable")}</dt>
                <dt>Fecha: ${fieldValue(bean: materialContenidoInstance, field: "fecha")}</dt>
                <dt>Autor: ${fieldValue(bean: materialContenidoInstance, field: "autor")}</dt>
                <dt>Descripcion: </dt>
                	<dd>${fieldValue(bean: materialContenidoInstance, field: "descripcion")}</dd>        	
			</dl>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->
