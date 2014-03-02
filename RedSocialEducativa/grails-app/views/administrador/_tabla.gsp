<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Administradores</h2>
            <div class="box-icon">
                <g:link controller="usuario" action="create"><i class="icon-plus"></i></g:link>
            </div>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Usuario</th>
						<th>Acciones</th>                      
                    </tr>
                </thead>
                <tbody>
                	<g:each in="${administradorInstanceList}" var="administradorInstance">
	                    <tr>
    	                    <td>${fieldValue(bean: administradorInstance, field: "usuario")}</td>		
	                        <td class="center">
	                            <g:link class="btn btn-danger" action="delete" method="DELETE" id="${administradorInstance.id}" 
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
