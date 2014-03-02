<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Calendarios</h2>
            <div class="box-icon">
                <g:link action="create"><i class="icon-plus"></i></g:link>
            </div>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Año</th>
                        <th>Inicio primer cuatrimestre</th>
                        <th>Inicio segundo cuatrimestre</th>
						<th>Acciones</th>      
                    </tr>
                </thead>                       
                <tbody>
                	<g:each in="${calendarioInstanceList}" status="i" var="calendarioInstance">
	                    <tr>
    	                    <td>${fieldValue(bean: calendarioInstance, field: "anio")}</td>
            	            <td class="center">${fieldValue(bean: calendarioInstance, field: "inicioPrimerCuatrimestre")}</td>
            	            <td class="center">${fieldValue(bean: calendarioInstance, field: "inicioSegundoCuatrimestre")}</td>
	                         <td class="center">
	                            <g:link class="btn btn-info" action="edit" resource="${calendarioInstance}" id="${calendarioInstance.id}">
	                                <i class="icon-edit "></i> 
	                            </g:link>
	                            <g:link class="btn btn-danger" action="delete" method="DELETE" id="${calendarioInstance.id}" 
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
