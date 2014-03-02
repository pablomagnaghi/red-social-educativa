<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Mediadores</h2>
            <div class="box-icon">
                <g:link action="create"><i class="icon-plus"></i></g:link>
            </div>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Usuario</th>
                        <th>Curso</th>
                        <th>Jerarquia</th>
						<th>Acciones</th>      
                    </tr>
                </thead>                       
                <tbody>
                	<g:each in="${mediadorInstanceList}" var="mediadorInstance">
	                    <tr>
    	                    <td>${fieldValue(bean: mediadorInstance, field: "usuario")}</td>
            	            <td class="center">${fieldValue(bean: mediadorInstance, field: "curso")}</td>
            	            <td class="center">${fieldValue(bean: mediadorInstance, field: "jerarquia")}</td>
	                        <td class="center">
	                            <g:link class="btn btn-danger" action="delete" method="DELETE" id="${mediadorInstance.id}" 
	                            	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >	                          
	                                <i class="icon-trash "></i>                        
								</g:link>
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