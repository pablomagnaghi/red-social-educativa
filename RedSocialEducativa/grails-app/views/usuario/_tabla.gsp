<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i><span class="break"></span>Usuarios</h2>
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
                    	<th>Nombre de usuario</th>
                        <th>DNI</th>
                        <th>Apellido</th>
                        <th>Nombres</th>
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
    	                    <td class="center">${fieldValue(bean: usuarioInstance, field: "dni")}</td>				
							<td class="center">${fieldValue(bean: usuarioInstance, field: "apellido")}</td>				
							<td class="center">${fieldValue(bean: usuarioInstance, field: "nombres")}</td>				
							<td class="center">${fieldValue(bean: usuarioInstance, field: "email")}</td>	
							<td class="center"><g:formatNumber number="${usuarioInstance.fechaSolicitud}"/></td>	
							<td>
								<g:if test="${fieldValue(bean: usuarioInstance, field: "fechaMembresia")}">
									<g:formatNumber number="${usuarioInstance.fechaMembresia}"/>
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
	                        	<g:link class="btn btn-success" action="show" id="${usuarioInstance.id}"><i class="icon-search"></i> </g:link>
	                       		<g:if test="${usuarioInstance.enabled}">
                            		<g:link class="btn btn-danger" action="cambiarEstado" id="${usuarioInstance.id}" 
                            			onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                            			<i class="icon-off"></i></g:link>
                            	</g:if>
                            	<g:else>
	                            	 <g:link class="btn btn-success" action="cambiarEstado" id="${usuarioInstance.id}"><i class="icon-ok"></i></g:link>
                            	</g:else>
	                        </td>
	                    </tr>
					</g:each>   
                </tbody>
            </table>
        </div>
    </div>
    <!--/span-->
</div>
