<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Material ${materialTemaInstance}</h2>
            <div class="box-icon">
            	<g:link action="create" params="['cursoId': params.cursoId, 'temaId': params.temaId]"><i class="icon-plus"></i></g:link>
           		<g:link action="edit" id="${materialTemaInstance.id}" params="['cursoId': params.cursoId, 'temaId': params.temaId]"><i class="icon-edit"></i></g:link>
            	<g:link action="delete" method="DELETE" id="${materialTemaInstance.id}" params="['cursoId': params.cursoId, 'temaId': params.temaId]"
	        	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><i class="icon-trash "></i></g:link>
            </div>
        </div>
        <div class="box-content">
			<dl>
            	<dt>Titulo: ${fieldValue(bean: materialTemaInstance, field: "titulo")}</dt>
                <dt>Categoria: ${fieldValue(bean: materialTemaInstance, field: "categoria")}</dt>
                <dt>Responsable: ${fieldValue(bean: materialTemaInstance, field: "responsable")}</dt>
                <dt>Fecha: ${fieldValue(bean: materialTemaInstance, field: "fecha")}</dt>
                <dt>Autor: ${fieldValue(bean: materialTemaInstance, field: "autor")}</dt>
                <dt>Descripcion: </dt>
                	<dd>${fieldValue(bean: materialTemaInstance, field: "descripcion")}</dd>        	
			</dl>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->
