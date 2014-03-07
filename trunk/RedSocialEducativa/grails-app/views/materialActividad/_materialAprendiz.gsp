<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Material de la actividad</h2>
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
						<th>Acciones</th>            
					</tr>
				</thead>
                <tbody>
                	<g:each in="${materialCursoInstanceList}" var="materialCursoInstance">
	                    <tr>
    	                    <td>${fieldValue(bean: materialCursoInstance, field: "titulo")}</td>
        	                <td class="center">${fieldValue(bean: materialCursoInstance, field: "categoria")}</td>
            	            <td class="center">${fieldValue(bean: materialCursoInstance, field: "responsable")}</td>
            	            <td class="center">${fieldValue(bean: materialCursoInstance, field: "fecha")}</td>
            	            <td class="center">${fieldValue(bean: materialCursoInstance, field: "autor")}</td>
	                        <td class="center">
								PONER LINK DE DESCARGA
	                        </td>
	                    </tr>
					</g:each>   
                </tbody>
            </table>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->
