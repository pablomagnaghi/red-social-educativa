<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Cuatrimestres del curso</h2>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Anio</th>
                        <th>Numero</th>
						<th>Acciones</th>  
					</tr>
				</thead>
                <tbody>
                	<g:each in="${cuatrimestreInstanceList}" var="cuatrimestreInstance">
	                    <tr>
    	                    <td>"${fieldValue(bean: cuatrimestreInstance, field: "anio")}"</td>
        	                <td class="center">${fieldValue(bean: cuatrimestreInstance, field: "numero")}</td>
	                        <td class="center">
	                        	<g:link class="btn btn-success" controller="foroCurso" action="general" 
	                        		params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestreInstance.id]">Foro</g:link>
	                            <g:link class="btn btn-success" action="historial" resource="${cuatrimestreInstance}" id="${cuatrimestreInstance.id}"
	                            	params="['cursoId': params.cursoId]"><i class="icon-search "></i> 
	                            </g:link>
	                            <g:link class="btn btn-danger" action="delete" method="DELETE" id="${cuatrimestreInstance.id}" params="['cursoId': params.cursoId]"
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

