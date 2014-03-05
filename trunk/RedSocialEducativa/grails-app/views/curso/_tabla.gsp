<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Cursos</h2>
            <div class="box-icon">
                <g:link action="create"><i class="icon-plus"></i></g:link>
            </div>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Asignatura</th>
                        <th>Nro relativo</th>
                        <th>Nombre</th>
                        <th>Cuat dict</th>         
						<th>Acciones</th>                      
                    </tr>
                </thead>
                <tbody>
                	<g:each in="${cursoInstanceList}" var="cursoInstance">
	                    <tr>
    	                    <td>${fieldValue(bean: cursoInstance, field: "asignatura")}</td>					
							<td>${fieldValue(bean: cursoInstance, field: "nroRelativo")}</td>						
							<td>${fieldValue(bean: cursoInstance, field: "nombre")}</td>
							<td>${fieldValue(bean: cursoInstance, field: "cuatDict")}</td>
	                        <td class="center">
	                        	<g:link class="btn btn-success" action="show" id="${cursoInstance.id}"><i class="icon-search"></i></g:link>
	                            <g:link class="btn btn-info" action="edit" id="${cursoInstance.id}"><i class="icon-edit"></i></g:link>
	                            <g:link class="btn btn-danger" action="delete" method="DELETE" id="${cursoInstance.id}" 
	                            	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >	                          
	                                <i class="icon-trash"></i></g:link>
	                        </td>
	                    </tr>
					</g:each>   
                </tbody>
            </table>
        </div>
    </div>
    <!--/span-->
</div>
