<div class="box-content buttons">
	<p class="btn-group">
		<g:link action="actualizarMembresias"><button class="btn btn-large btn-primary">Actualizar membresías</button></g:link>	  	
	</p>
</div>

<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Administradores</h2>
            <div class="box-icon">
                <g:link action="create"><i class="icon-plus"></i></g:link>
            </div>
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
                        <th>DNI</th>
                        <th>Apellido</th>
                        <th>Nombres</th>
                        <th>Email</th>
                        <th>Estado</th>
						<th>Acciones</th>                      
                    </tr>
                </thead>
                <tbody>
                	<g:each in="${administradorInstanceList}" var="administradorInstance">
	                    <tr>
    	                    <td>${administradorInstance.usuario.dni}</td>		
    	                    <td class="center">${administradorInstance.usuario.apellido}</td>	
    	                    <td class="center">${administradorInstance.usuario.nombres}</td>	
    	                    <td class="center">${administradorInstance.usuario.email}</td>	
    	                    <td class="center">
                            	<g:if test="${administradorInstance.activo}">
                            		<span class="label label-success">Activo</span>
                            	</g:if>
                            	<g:else>
	                            	<span class="label label-important">Inactivo</span>
                            	</g:else>		
                        	</td>
	                        <td class="center">
	                       		<g:if test="${administradorInstance.activo}">
                            		<g:link class="btn btn-danger" action="cambiarEstado" id="${administradorInstance.id}"
                            			onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                            			<i class="icon-off"></i></g:link>
                            	</g:if>
                            	<g:else>
	                            	 <g:link class="btn btn-success" action="cambiarEstado" id="${administradorInstance.id}"> 
	                            	 	<i class="icon-ok"></i></g:link>
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
