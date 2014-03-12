<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Material del curso</h2>
            <g:if test="${mediador}">
	            <div class="box-icon">
	                <g:link action="create" params="['cursoId': params.cursoId]"><i class="icon-plus"></i></g:link>
	            </div>
            </g:if>
        </div>
        <div class="box-content">
        	<g:if test="${flash.message}">
				<div class="box-content alerts">
		    		<div class="alert alert-info">
						<button class="close" data-dismiss="alert" type="button"></button>
						<strong></strong> 
						${flash.message}
				    </div>
				</div>    
			</g:if>
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Titulo</th>
                        <th>Categoria</th>
                        <th>Responsable</th>
                        <th>Fecha</th>  
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
	                        <td class="center">
	                        	<g:link class="btn btn-success" action="show" resource="${materialCursoInstance}" id="${materialCursoInstance.id}"
	                            	params="['cursoId': params.cursoId]"><i class="icon-search "></i></g:link>
	                            <g:if test="${mediador}">
		                            <g:link class="btn btn-info" action="edit" resource="${materialCursoInstance}" id="${materialCursoInstance.id}"
		                            	params="['cursoId': params.cursoId]"><i class="icon-edit "></i></g:link>
		                            <g:link class="btn btn-danger" action="delete" method="DELETE" id="${materialCursoInstance.id}" params="['cursoId': params.cursoId]"
		                            	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >	                          
		                                <i class="icon-trash "></i></g:link>
	                            </g:if>    
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
