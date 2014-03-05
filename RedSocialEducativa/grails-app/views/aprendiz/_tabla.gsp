<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-user"></i>
                <span class="break"></span>Aprendices</h2>
            <div class="box-icon">
            	<g:link action="create" params="['cursoId': params.cursoId, 
            		'cuatrimestreId': params.cuatrimestreId]"><i class="icon-plus"></i></g:link></a>
            </div>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Apellido</th>
                        <th>Nombres</th>
                        <th>Padron</th>     
                        <th>Email</th>               
                        <th>Estado</th>    
                        <th>Acciones</th>            
                    </tr>
                </thead>
                <tbody>
                	<g:each in="${aprendizInstanceList}" var="aprendizInstance">
	                    <tr>
    	                    <td>${aprendizInstance.usuario.apellido}</td>
    	                    <td>${aprendizInstance.usuario.nombres}</td>
    	            		<td>${aprendizInstance.usuario.padron}</td>
    	                    <td>${aprendizInstance.usuario.email}</td>
							<td class="center">
								 <g:if test="${!aprendizInstance.participa}">
									<button class="btn btn-primary btn-danger">Esperando aceptacion</button>
								</g:if>		
              					<g:else>
									<button class="btn btn-primary btn-success">Activo</button>
								</g:else>
							</td>						
							<td class="center">
								<g:if test="${!aprendizInstance.participa}">
									<g:link class="btn btn-success" controller="mediador" action="activarAprendiz" id="${aprendizInstance.id}" 
										params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]" value="${message(code: 'Activar')}" 
										onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
										<i class="icon-ok"></i></g:link>
								</g:if>
								<g:else>		
									<g:link class="btn btn-info" action="" id="${aprendizInstance.id}" 
										params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"
										value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
										onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
										<i class="icon-trash"></i></g:link>
									<g:link class="btn btn-danger" action="delete" id="${aprendizInstance.id}" 
										params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"
										value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
										onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
										<i class="icon-trash"></i></g:link>
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
<!--/row-->

