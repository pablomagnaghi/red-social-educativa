<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i><span class="break"></span>Usuarios</h2>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
            	<p>ARREGLAR MENSAJERIA. No se borran porque falta arreglar la cascada en mensajeria</p>
                <thead>
                    <tr>
                        <th>DNI</th>
                        <th>Apellido</th>
                        <th>Nombres</th>
                        <th>Legajo</th>
                        <th>Padron</th>
                        <th>Email</th>
                        <th>Fecha solicitud</th>
                        <th>Fecha membresia</th>
                        <th>Estado</th>
						<th>Acciones</th>                      
                    </tr>
                </thead>
                <tbody>
                	<g:each in="${usuarioInstanceList}" var="usuarioInstance">
	                    <tr>
    	                    <td>${fieldValue(bean: usuarioInstance, field: "username")}</td>				
							<td>${fieldValue(bean: usuarioInstance, field: "apellido")}</td>				
							<td>${fieldValue(bean: usuarioInstance, field: "nombres")}</td>				
							<td>
								<g:if test="${fieldValue(bean: usuarioInstance, field: "legajo")}">
									${fieldValue(bean: usuarioInstance, field: "legajo")}
								</g:if>	
								<g:else>
									No tiene
								</g:else>
							</td>		
							<td>
								<g:if test="${fieldValue(bean: usuarioInstance, field: "padron")}">
									${fieldValue(bean: usuarioInstance, field: "padron")}
								</g:if>		
								<g:else>
									No tiene
								</g:else>	
							</td>
							<td>${fieldValue(bean: usuarioInstance, field: "email")}</td>	
							<td>${fieldValue(bean: usuarioInstance, field: "fechaSolicitud")}</td>	
							<td>
								<g:if test="${fieldValue(bean: usuarioInstance, field: "fechaMembresia")}">
									${fieldValue(bean: usuarioInstance, field: "fechaMembresia")}
								</g:if>	
								<g:else>
									No tiene
								</g:else>
							</td>	
    	                    <td class="center">
                            	<g:if test="${usuarioInstance.enabled}">
                            		<span class="label label-success">Habilitado</span>
                            	</g:if>
                            	<g:else>
	                            	<span class="label label-important">Inhabilitado</span>
                            	</g:else>		
                        	</td>
	                        <td class="center">
	                            <g:link class="btn btn-danger" action="delete" method="DELETE" id="${usuarioInstance.id}" 
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
