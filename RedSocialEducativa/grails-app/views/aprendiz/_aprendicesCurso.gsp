<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-user"></i><span class="break"></span>Aprendices</h2>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Apellido</th>
                        <th>Nombres</th>
                        <th>Padron</th>     
                        <th>Cuatrimestre de cursada</th>
                        <th>Email</th>       
                        <th>Estado</th>    
                        <th>Acciones</th>            
                    </tr>
                </thead>
                <tbody>
                	<g:each in="${aprendices}" var="aprendizInstance">
	                    <tr>
    	                    <td>${aprendizInstance.usuario.apellido}</td>
    	                    <td>${aprendizInstance.usuario.nombres}</td>
    	            		<td>${aprendizInstance.usuario.padron}</td>
    	            		<td>${aprendizInstance.cuatrimestre}</td>
    	                    <td>${aprendizInstance.usuario.email}</td>
							<td class="center">
								<g:if test="${aprendizInstance.participa}">
								 	<span class="label label-success">Activo</span>									
								</g:if>		
								<g:else>	
              						<g:if test="${aprendizInstance.cuatrimestre == cuatrimestre}">
										<span class="label label-warning">Esperando aceptacion</span>
									</g:if>
									<g:else>
										<span class="label label-important">Inactivo</span>
									</g:else>
								</g:else>	
							</td>						
							<td class="center">
								<g:if test="${!aprendizInstance.participa && aprendizInstance.cuatrimestre == cuatrimestre}">
									<g:link class="btn btn-success" controller="mediador" action="activarAprendiz" id="${aprendizInstance.id}" 
										params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]" value="${message(code: 'Activar')}" 
										onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
										<i class="icon-ok"></i></g:link>
								</g:if>
								<g:else>		
									<g:link class="btn btn-success" controller="evaluacionAprendiz" action="mostrarAprendiz"
	                            		params="['cursoId': params.cursoId, 'aprendizId': aprendizInstance.id]"><i class="icon-search"></i> 
	                          		</g:link>		
									<g:link class="btn btn-danger" action="delete" id="${aprendizInstance.id}" 
										params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]"
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