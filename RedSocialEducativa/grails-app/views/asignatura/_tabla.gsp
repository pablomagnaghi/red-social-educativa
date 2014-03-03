<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Asignaturas</h2>
            <div class="box-icon">
                <g:link action="create"><i class="icon-plus"></i></g:link>
            </div>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Nombre</th>
                        <th>Creditos</th>
                        <th>Contenidos Minimos</th>         
						<!--<th>Curso</th>-->
						<th>Acciones</th>                      
                    </tr>
                </thead>
                <tbody>
                	<g:each in="${asignaturaInstanceList}" var="asignaturaInstance">
	                    <tr>
    	                    <td>${fieldValue(bean: asignaturaInstance, field: "codigo")}</td>
        	                <td class="center">${fieldValue(bean: asignaturaInstance, field: "nombre")}</td>
            	            <td class="center">${fieldValue(bean: asignaturaInstance, field: "creditos")}</td>
            	            <td class="center">${fieldValue(bean: asignaturaInstance, field: "contenidosMinimos")}
            	            </td>
            	            <!-- 
                	        <td class="center">
                    	       	<g:each in="${asignaturaInstance.cursos}" var="c">
									<p>${c}</p>
								</g:each>	
                        	</td>
                        	-->
	                        <td class="center">
	                            <g:link class="btn btn-info" action="edit" resource="${asignaturaInstance}" id="${asignaturaInstance.id}">
	                                <i class="icon-edit "></i> 
	                            </g:link>
	                            <g:link class="btn btn-danger" action="delete" method="DELETE" id="${asignaturaInstance.id}" 
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
