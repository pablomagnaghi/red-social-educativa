<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Material del tema</h2>
            <div class="box-icon">
                <g:link action="create" params="['cursoId': params.cursoId]"><i class="icon-plus"></i></g:link>
            </div>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Titulo</th>
                        <th>Categoria</th>
                        <th>Responsable</th>
                        <th>Fecha</th> 
                        <th>Autor</th>
   						<th>Descripcion</th>
						<th>Acciones</th>      	      
					</tr>
				</thead>
                <tbody>
					<tr>
						<td>${fieldValue(bean: materialTemaInstance, field: "titulo")}</td>
						<td class="center">${fieldValue(bean: materialTemaInstance, field: "categoria")}</td>
						<td class="center">${fieldValue(bean: materialTemaInstance, field: "responsable")}</td>
						<td class="center">${fieldValue(bean: materialTemaInstance, field: "fecha")}</td>
 						<td class="center">${fieldValue(bean: materialTemaInstance, field: "autor")}</td>
						<td class="center">${fieldValue(bean: materialTemaInstance, field: "descripcion")}</td>
						<td class="center">
							<g:link class="btn btn-info" action="edit" resource="${materialTemaInstance}" id="${materialTemaInstance.id}"
								params="['cursoId': params.cursoId, 'temaId': params.temaId]">
	                       		<i class="icon-edit "></i> 
	                    	</g:link>
	                    	<g:link class="btn btn-danger" action="delete" method="DELETE" id="${materialTemaInstance.id}" params="['cursoId': params.cursoId, 'temaId': params.temaId]"
	                     		onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >	                          
	                     		<i class="icon-trash "></i>                        
							</g:link>
	                	</td>
	               	</tr>
                </tbody>
            </table>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->
	